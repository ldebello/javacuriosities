package ar.com.javacuriosities.facade.api.clients.third_party;

import ar.com.javacuriosities.facade.api.clients.third_party.model.Product;
import ar.com.javacuriosities.facade.api.model.ProductDTO;
import net.jodah.failsafe.CircuitBreaker;
import net.jodah.failsafe.Failsafe;
import net.jodah.failsafe.FailsafeExecutor;
import net.jodah.failsafe.Fallback;
import net.jodah.failsafe.RetryPolicy;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.time.Duration;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;

@Component
public class ThirdPartyClient {

    private static final Logger LOGGER = LoggerFactory.getLogger(ThirdPartyClient.class);

    private RestTemplate restTemplate;

    private Fallback<Product[]> fallback;

    private CircuitBreaker<Product[]> circuitBreaker;

    private RetryPolicy retryPolicy;

    @Autowired
    public ThirdPartyClient(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;

        this.fallback = Fallback.of(new Product[0]);

        this.retryPolicy = new RetryPolicy<ResponseEntity>()
                .handleResultIf(response -> response.getStatusCode().is5xxServerError() || HttpStatus.TOO_MANY_REQUESTS.value() == response.getStatusCode().value())
                .withBackoff(100, 1000, ChronoUnit.MILLIS)
                .withMaxRetries(3)
                .withJitter(0.1);

        this.circuitBreaker = new CircuitBreaker<Product[]>()
                .handle(Exception.class)
                .onOpen(() -> LOGGER.info("ThirdPartyClient - On Open"))
                .onClose(() -> LOGGER.info("ThirdPartyClient - On Close"))
                .onHalfOpen(() -> LOGGER.info("ThirdPartyClient - On Half Open"))
                .onFailure(executionResult -> LOGGER.error("Failure in ThirdPartyClient", executionResult.getFailure()))
                .withFailureThreshold(5, 5 * 4)
                .withSuccessThreshold(3)
                .withDelay(Duration.ofMinutes(1));
    }

    public List<ProductDTO> getProducts() {
        /*
         * Fault tolerance (Mejorado)
         */
        FailsafeExecutor<Product[]> executor = Failsafe.with(fallback, retryPolicy, circuitBreaker);

        Product[] thirdPartyProducts = executor
                .onFailure((event) -> LOGGER.info("Failure"))
                .onSuccess((event) -> LOGGER.info("Success"))
                .get(() -> {
                    ResponseEntity<Product[]> response = restTemplate.getForEntity("http://" + System.getenv("THIRD_PARTY_HOST") + "/third-party/api/products", Product[].class);
                    return response.getBody();
                });

        List<ProductDTO> productDTOS = new ArrayList<>(thirdPartyProducts.length);

        for (Product thirdPartyProduct : thirdPartyProducts) {
            ProductDTO productDTO = new ProductDTO();

            productDTO.setName(thirdPartyProduct.getName());
            productDTO.setBrand(thirdPartyProduct.getBrand());
            productDTO.setPrice(thirdPartyProduct.getPrice());

            productDTO.setCategory("ThirdParty");

            productDTOS.add(productDTO);
        }

        return productDTOS;
    }
}

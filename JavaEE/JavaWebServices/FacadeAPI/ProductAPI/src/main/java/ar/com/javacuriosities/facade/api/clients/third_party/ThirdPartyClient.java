package ar.com.javacuriosities.facade.api.clients.third_party;

import ar.com.javacuriosities.facade.api.clients.third_party.model.Product;
import ar.com.javacuriosities.facade.api.model.ProductDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@Component
public class ThirdPartyClient {

    private RestTemplate restTemplate;

    @Autowired
    public ThirdPartyClient(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public List<ProductDTO> getProducts() {

        ResponseEntity<Product[]> response = restTemplate.getForEntity("http://" + System.getenv("THIRD_PARTY_HOST") + "/third-party/api/products", Product[].class);

        Product[] thirdPartyProducts = response.getBody();
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

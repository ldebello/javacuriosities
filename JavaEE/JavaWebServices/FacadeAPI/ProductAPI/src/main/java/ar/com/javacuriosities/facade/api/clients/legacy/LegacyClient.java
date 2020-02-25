package ar.com.javacuriosities.facade.api.clients.legacy;

import ar.com.javacuriosities.facade.api.clients.legacy.utils.Product;
import ar.com.javacuriosities.facade.api.clients.legacy.utils.ProductsWS;
import ar.com.javacuriosities.facade.api.clients.legacy.utils.ProductsWS_Service;
import ar.com.javacuriosities.facade.api.model.ProductDTO;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class LegacyClient {

    public List<ProductDTO> getProducts() {
        ProductsWS_Service productsService = new ProductsWS_Service();
        ProductsWS productsWSPort = productsService.getProductsWSPort();
        List<Product> legacyProducts = productsWSPort.products();
        List<ProductDTO> productDTOS = new ArrayList<>(legacyProducts.size());

        for (Product legacyProduct : legacyProducts) {
            ProductDTO productDTO = new ProductDTO();

            productDTO.setName(legacyProduct.getName());
            productDTO.setCategory(legacyProduct.getCategory());
            productDTO.setPrice(legacyProduct.getPrice());

            productDTO.setBrand("Legacy");

            productDTOS.add(productDTO);
        }
        return productDTOS;
    }
}

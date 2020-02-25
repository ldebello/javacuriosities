package ar.com.javacuriosities.facade.api.services;

import ar.com.javacuriosities.facade.api.clients.legacy.LegacyClient;
import ar.com.javacuriosities.facade.api.clients.third_party.ThirdPartyClient;
import ar.com.javacuriosities.facade.api.model.ProductDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProductService {

    private LegacyClient legacyClient;

    private ThirdPartyClient thirdPartyClient;

    @Autowired
    public ProductService(LegacyClient legacyClient, ThirdPartyClient thirdPartyClient) {
        this.legacyClient = legacyClient;
        this.thirdPartyClient = thirdPartyClient;
    }

    public ProductService() {
    }

    public List<ProductDTO> getProducts() {
        List<ProductDTO> legacyClientProducts = legacyClient.getProducts();
        List<ProductDTO> thirdPartyProducts = thirdPartyClient.getProducts();
        List<ProductDTO> productDTOs = new ArrayList<>(legacyClientProducts.size() + thirdPartyProducts.size());
        productDTOs.addAll(legacyClientProducts);
        productDTOs.addAll(thirdPartyProducts);
        return productDTOs;
    }
}

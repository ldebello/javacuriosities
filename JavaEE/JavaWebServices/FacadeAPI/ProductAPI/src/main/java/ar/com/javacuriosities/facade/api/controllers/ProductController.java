package ar.com.javacuriosities.facade.api.controllers;

import ar.com.javacuriosities.facade.api.model.ProductDTO;
import ar.com.javacuriosities.facade.api.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("products")
public class ProductController {

    @Autowired
    private ProductService productService;

    @GetMapping(produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE, "application/x-yaml"})
    public List<ProductDTO> getProducts() {
        return productService.getProducts();
    }
}

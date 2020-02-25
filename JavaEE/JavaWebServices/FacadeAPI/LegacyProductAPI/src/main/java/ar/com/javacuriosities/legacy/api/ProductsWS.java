package ar.com.javacuriosities.legacy.api;

import ar.com.javacuriosities.legacy.api.dao.ProductDAO;
import ar.com.javacuriosities.legacy.api.model.Product;

import javax.jws.WebMethod;
import javax.jws.WebService;
import java.util.List;

/*
 * http://localhost:8081/legacy/ProductsWS?wsdl
 */
@WebService(serviceName = "ProductsWS")
public class ProductsWS {

    private ProductDAO dao = new ProductDAO();

    @WebMethod(operationName = "products")
    public List<Product> getProducts() {
        return dao.getProducts();
    }
}

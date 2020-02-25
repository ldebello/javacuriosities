
package ar.com.javacuriosities.facade.api.clients.legacy.utils;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the ar.com.javacuriosities.facade.api.clients.legacy.utils package. 
 * <p>An ObjectFactory allows you to programatically 
 * construct new instances of the Java representation 
 * for XML content. The Java representation of XML 
 * content can consist of schema derived interfaces 
 * and classes representing the binding of schema 
 * type definitions, element declarations and model 
 * groups.  Factory methods for each of these are 
 * provided in this class.
 * 
 */
@XmlRegistry
public class ObjectFactory {

    private final static QName _Products_QNAME = new QName("http://api.legacy.javacuriosities.com.ar/", "products");
    private final static QName _ProductsResponse_QNAME = new QName("http://api.legacy.javacuriosities.com.ar/", "productsResponse");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: ar.com.javacuriosities.facade.api.clients.legacy.utils
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link ProductsResponse }
     * 
     */
    public ProductsResponse createProductsResponse() {
        return new ProductsResponse();
    }

    /**
     * Create an instance of {@link Products }
     * 
     */
    public Products createProducts() {
        return new Products();
    }

    /**
     * Create an instance of {@link Product }
     * 
     */
    public Product createProduct() {
        return new Product();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Products }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://api.legacy.javacuriosities.com.ar/", name = "products")
    public JAXBElement<Products> createProducts(Products value) {
        return new JAXBElement<Products>(_Products_QNAME, Products.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ProductsResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://api.legacy.javacuriosities.com.ar/", name = "productsResponse")
    public JAXBElement<ProductsResponse> createProductsResponse(ProductsResponse value) {
        return new JAXBElement<ProductsResponse>(_ProductsResponse_QNAME, ProductsResponse.class, null, value);
    }

}

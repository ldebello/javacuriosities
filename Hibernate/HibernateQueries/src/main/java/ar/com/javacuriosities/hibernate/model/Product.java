package ar.com.javacuriosities.hibernate.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityResult;
import javax.persistence.FieldResult;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedNativeQuery;
import javax.persistence.SqlResultSetMapping;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import java.util.Date;

@Entity
@Table(name = "products")
@SqlResultSetMapping(name = Product.ROW_MAPPER_ALL_COLUMNS,
        entities = @EntityResult(entityClass = Product.class,
                fields = {
                        @FieldResult(name = "id", column = "my_id"),
                        @FieldResult(name = "description", column = "my_description"),
                        @FieldResult(name = "price", column = "my_price"),
                        @FieldResult(name = "creationDate", column = ",my_creation_date")})
)
@NamedNativeQuery(name = Product.NATIVE_QUERY_SELECT_ALL_ROWS, query = "SELECT id_product AS my_id, description AS my_description, price AS my_price, creation_date AS my_creation_date FROM products", resultSetMapping = Product.ROW_MAPPER_ALL_COLUMNS)
public class Product {

    public static final String ROW_MAPPER_ALL_COLUMNS = "PRODUCT_ROW_MAPPER_ALL_COLUMNS";
    public static final String NATIVE_QUERY_SELECT_ALL_ROWS = "PRODUCT_NATIVE_QUERY_SELECT_ALL_ROWS";

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_product")
    private Long id;

    @Column(name = "description")
    private String description;

    private Double price;

    @Temporal(TemporalType.DATE)
    @Column(name = "creation_date")
    private Date creationDate;

    public Product() {
    }

    public Product(String description, Double price, Date creationDate) {
        this.description = description;
        this.price = price;
        this.creationDate = creationDate;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Date getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }

    @Override
    public String toString() {
        return "Product{" + "id=" + id + ", description=" + description + ", price=" + price + ", creationDate=" + creationDate + '}';
    }
}
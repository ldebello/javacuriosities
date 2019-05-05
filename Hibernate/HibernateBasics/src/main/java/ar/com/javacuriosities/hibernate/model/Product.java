package ar.com.javacuriosities.hibernate.model;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import java.util.Date;

@Entity
@Table(name = "products")
public class Product {

    /*
     * Las Annotations pueden ir sobre los atributos o sobre los getters
     * dependiendo que estrategia usemos el valor va a ser obtenido por medio de
     * los getters o directamente del atributo
     */


    /*
     * La estrategia puede tomar distintos valores
     *
     * GenerationType.AUTO: Se selecciona automáticamente la forma de generar
     * las claves primarias en función de la base de datos que estemos usando.
     * GenerationType.IDENTITY: Clave generada de forma auto-incremental siempre
     * y cuando el motor lo soporte.
     * GenerationType.SEQUENCE: Claves auto-generadas por una secuencia,
     * usando @SequenceGenerator podemos especificar los datos de la secuencia.
     * GenerationType.TABLE: Claves auto-generadas por una tabla,
     * usando @TableGenerator podemos especificarlos datos de la tabla.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_product")
    private Long id;

    @Column(name = "description")
    private String description;

    private Double price;

    @Temporal(TemporalType.DATE)
    private Date date;

    public Product() {
    }

    public Product(String description, Double price) {
        this.description = description;
        this.price = price;
    }

    @PrePersist
    public void executePrePersist() {
        System.out.println("Pre Persist");
    }

    @PreUpdate
    public void executePreUpdate() {
        System.out.println("Pre Update");
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

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", description='" + description + '\'' +
                ", price=" + price +
                ", date=" + date +
                '}';
    }
}

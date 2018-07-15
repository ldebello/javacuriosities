package ar.com.javacuriosities.hibernate.model;


import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import java.util.List;

@Entity
@Table(name = "people")
public class Person {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String name;

    /**
     * CascadeType.DETACH - Cuando desenganchamos la entidad todas las entidades que contenga esta variable serán persistidas también.
     * CascadeType.PERSIST - Cuando persistamos la entidad todas las entidades que contenga esta variable serán persistidas también.
     * CascadeType.REFRESH - Cuando actualicemos la entidad todas las entidades que contenga esta variable se actualizarán.
     * CascadeType.REMOVE - Cuando borremos la entidad todas las entidades que contenga esta variable se borrarán del mismo modo.
     * CascadeType.MERGE - Cuando hagamos un "merge" de la entidad todas las entidades que contenga esta variable realizarán la misma operación.
     * CascadeType.ALL - Todas las operaciones citadas anteriormente.
     */
    /**
     * Las annotation @OneToOne tienen como valor por defecto Eager para el fetch
     */
    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Address primaryAddress;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Address secondaryAddress;

    /**
     * Las annotation @OneToMany tienen como valor por defecto Lazy para el fetch
     */
    @OneToMany(fetch = FetchType.LAZY)
    private List<MobilePhone> mobilePhones;

    public Person() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Address getPrimaryAddress() {
        return primaryAddress;
    }

    public void setPrimaryAddress(Address primaryAddress) {
        this.primaryAddress = primaryAddress;
    }

    public Address getSecondaryAddress() {
        return secondaryAddress;
    }

    public void setSecondaryAddress(Address secondaryAddress) {
        this.secondaryAddress = secondaryAddress;
    }

    public List<MobilePhone> getMobilePhones() {
        return mobilePhones;
    }

    public void setMobilePhones(List<MobilePhone> mobilePhones) {
        this.mobilePhones = mobilePhones;
    }

    @Override
    public String toString() {
        return "Person{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", primaryAddress=" + primaryAddress +
                ", secondaryAddress=" + secondaryAddress +
                ", mobilePhones=" + mobilePhones +
                '}';
    }
}

package ar.com.javacuriosities.hibernate.utils;

public class PartialProduct {

    private Long idProduct;
    private String description;

    public PartialProduct(Long idProduct, String description) {
        this.idProduct = idProduct;
        this.description = description;
    }

    public Long getIdProduct() {
        return idProduct;
    }

    public void setIdProduct(Long idProduct) {
        this.idProduct = idProduct;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "PartialProduct{" +
                "idProduct=" + idProduct +
                ", description='" + description + '\'' +
                '}';
    }
}

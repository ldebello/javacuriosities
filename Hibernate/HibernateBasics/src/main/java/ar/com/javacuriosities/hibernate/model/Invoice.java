package ar.com.javacuriosities.hibernate.model;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "invoices")
public class Invoice {

    @EmbeddedId
    private InvoicePK id;
    private String client;
    private double price;

    public Invoice() {
    }

    public InvoicePK getId() {
        return id;
    }

    public void setId(InvoicePK id) {
        this.id = id;
    }

    public String getClient() {
        return client;
    }

    public void setClient(String client) {
        this.client = client;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
}

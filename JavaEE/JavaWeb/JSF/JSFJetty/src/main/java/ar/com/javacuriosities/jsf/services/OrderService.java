package ar.com.javacuriosities.jsf.services;

import ar.com.javacuriosities.jsf.model.Order;

import javax.annotation.PostConstruct;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Named;
import java.util.ArrayList;
import java.util.List;

@Named
@ApplicationScoped
public class OrderService {

    private List<Order> orders = new ArrayList<>();

    @PostConstruct
    public void initialize() {
        orders.add(new Order(1, "Order 1"));
        orders.add(new Order(2, "Order 2"));
    }

    public List<Order> getOrders() {
        return orders;
    }

    public void addOrder(Order order) {
        orders.add(order);
    }
}

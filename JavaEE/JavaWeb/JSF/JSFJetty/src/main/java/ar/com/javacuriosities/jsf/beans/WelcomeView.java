package ar.com.javacuriosities.jsf.beans;

import ar.com.javacuriosities.jsf.model.Order;
import ar.com.javacuriosities.jsf.services.OrderService;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.util.List;

@Named
@ViewScoped
public class WelcomeView implements Serializable {

    private Order currentOrder = new Order();

    private List<Order> orders;

    @Inject
    private OrderService orderService;

    @PostConstruct
    public void initialize() {
        orders = orderService.getOrders();
    }

    public Order getCurrentOrder() {
        return currentOrder;
    }

    public void setCurrentOrder(Order currentOrder) {
        this.currentOrder = currentOrder;
    }

    public List<Order> getOrders() {
        return orders;
    }

    public void setOrders(List<Order> orders) {
        this.orders = orders;
    }

    public OrderService getOrderService() {
        return orderService;
    }

    public void setOrderService(OrderService orderService) {
        this.orderService = orderService;
    }

    public void addOrder() {
        orderService.addOrder(currentOrder);
        currentOrder = new Order();
        orders = orderService.getOrders();
    }
}

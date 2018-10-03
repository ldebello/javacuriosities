package ar.com.javacuriosities.hibernate.model;

public class EventSubscriber {

    private String subscriber;

    public EventSubscriber() {
    }

    public EventSubscriber(String subscriber) {
        this.subscriber = subscriber;
    }

    public String getSubscriber() {
        return subscriber;
    }

    public void setSubscriber(String subscriber) {
        this.subscriber = subscriber;
    }
}

package ar.com.javacuriosities.hibernate.model;

public class EventProperty {

    private String propertyValue;

    public EventProperty() {
    }

    public EventProperty(String propertyValue) {
        this.propertyValue = propertyValue;
    }

    public String getPropertyValue() {
        return propertyValue;
    }

    public void setPropertyValue(String propertyValue) {
        this.propertyValue = propertyValue;
    }
}

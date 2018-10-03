package ar.com.javacuriosities.hibernate.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class Event {
    private Long id;

    private String title;

    private Date date;

    private EventData eventData = new EventData();

    private Set<EventHistory> eventHistory = new HashSet<>();

    private List<EventSubscriber> eventSubscribers = new ArrayList<>();

    private Map<String, EventProperty> eventProperties = new HashMap<>();

    public Event() {
    }

    public Event(String title, Date date) {
        this.title = title;
        this.date = date;
    }

    public Long getId() {
        return id;
    }

    private void setId(Long id) {
        this.id = id;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public EventData getEventData() {
        return eventData;
    }

    public void setEventData(EventData eventData) {
        this.eventData = eventData;
    }

    public Set<EventHistory> getEventHistory() {
        return eventHistory;
    }

    public void setEventHistory(Set<EventHistory> eventHistory) {
        this.eventHistory = eventHistory;
    }

    public List<EventSubscriber> getEventSubscribers() {
        return eventSubscribers;
    }

    public void setEventSubscribers(List<EventSubscriber> eventSubscribers) {
        this.eventSubscribers = eventSubscribers;
    }

    public Map<String, EventProperty> getEventProperties() {
        return eventProperties;
    }

    public void setEventProperties(Map<String, EventProperty> eventProperties) {
        this.eventProperties = eventProperties;
    }
}
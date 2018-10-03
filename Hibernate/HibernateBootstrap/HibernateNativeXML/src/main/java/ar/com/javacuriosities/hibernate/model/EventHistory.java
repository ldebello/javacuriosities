package ar.com.javacuriosities.hibernate.model;

import java.util.Date;
import java.util.Objects;

public class EventHistory {

    private Date entryTime;

    private String entry;

    public EventHistory() {
    }

    public EventHistory(Date entryTime, String entry) {
        this.entryTime = entryTime;
        this.entry = entry;
    }

    public Date getEntryTime() {
        return entryTime;
    }

    public void setEntryTime(Date entryTime) {
        this.entryTime = entryTime;
    }

    public String getEntry() {
        return entry;
    }

    public void setEntry(String entry) {
        this.entry = entry;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        EventHistory that = (EventHistory) o;
        return Objects.equals(entryTime, that.entryTime) &&
                Objects.equals(entry, that.entry);
    }

    @Override
    public int hashCode() {
        return Objects.hash(entryTime, entry);
    }
}

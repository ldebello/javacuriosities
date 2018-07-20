package ar.com.javacuriosities.springboot.model.request;

import java.time.LocalDate;

public class TaskRequest {

    private String description;

    private LocalDate effectiveDate;

    public TaskRequest() {
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDate getEffectiveDate() {
        return effectiveDate;
    }

    public void setEffectiveDate(LocalDate effectiveDate) {
        this.effectiveDate = effectiveDate;
    }
}

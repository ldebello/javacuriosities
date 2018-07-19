package ar.com.javacuriosities.springboot.model.request;

import java.util.Date;

public class TaskRequest {

    private String description;

    private Date effectiveDate;

    public TaskRequest() {
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getEffectiveDate() {
        return effectiveDate;
    }

    public void setEffectiveDate(Date effectiveDate) {
        this.effectiveDate = effectiveDate;
    }
}

package ar.com.javacuriosities.spring.model.xml;

import java.util.Map;

public class SimpleBeanWithParameters {

    private final Map<String, String> employees;
    private final double multiplier;

    private SimpleBeanWithParameters(Map<String, String> employees, double multiplier) {
        this.employees = employees;
        this.multiplier = multiplier;
    }

    public Map<String, String> getEmployees() {
        return employees;
    }

    public double getMultiplier() {
        return multiplier;
    }
}

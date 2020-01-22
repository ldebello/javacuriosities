package ar.com.javacurisioties.jaxb.lesson2;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import java.util.List;

// Definimos esta clase como root del XML
@XmlRootElement
// Definimos el orden del XML
@XmlType(propOrder = {"countryName", "countryPopulation", "listOfStates"})
public class Country {

    private String countryName;
    private double countryPopulation;
    private List<State> listOfStates;

    public Country() {
    }

    public String getCountryName() {
        return countryName;
    }

    @XmlElement
    public void setCountryName(String countryName) {
        this.countryName = countryName;
    }

    public double getCountryPopulation() {
        return countryPopulation;
    }

    @XmlElement
    public void setCountryPopulation(double countryPopulation) {
        this.countryPopulation = countryPopulation;
    }

    public List<State> getListOfStates() {
        return listOfStates;
    }

    // Indica un elemento Wrapper para el XML
    @XmlElementWrapper(name = "stateList")
    // Indica el nombre de cada elemento
    @XmlElement(name = "state")
    public void setListOfStates(List<State> listOfStates) {
        this.listOfStates = listOfStates;
    }
}
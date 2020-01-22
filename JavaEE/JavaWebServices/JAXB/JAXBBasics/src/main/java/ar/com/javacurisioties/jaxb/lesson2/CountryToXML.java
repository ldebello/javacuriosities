package ar.com.javacurisioties.jaxb.lesson2;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import java.util.ArrayList;
import java.util.List;

public class CountryToXML {

    public static void main(String[] args) {

        // Country
        Country country = new Country();
        country.setCountryName("Small Country");
        country.setCountryPopulation(50000);

        // Lista de States
        List<State> stateList = new ArrayList<>();
        stateList.add(new State("Small State", 10000));
        stateList.add(new State("Big State", 40000));

        country.setListOfStates(stateList);

        try {
            // Creando contexto JAXB e inicializando Marshaller
            JAXBContext jaxbContext = JAXBContext.newInstance(Country.class);
            Marshaller jaxbMarshaller = jaxbContext.createMarshaller();

            // Pretty print
            jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);

            // Escribimos por consola
            jaxbMarshaller.marshal(country, System.out);

        } catch (JAXBException e) {
            // Log and Handle exception
            e.printStackTrace();
        }
    }
}

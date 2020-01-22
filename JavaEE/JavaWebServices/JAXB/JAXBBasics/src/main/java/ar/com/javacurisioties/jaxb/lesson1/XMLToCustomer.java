package ar.com.javacurisioties.jaxb.lesson1;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import java.io.File;

public class XMLToCustomer {

    public static void main(String[] args) {
        try {
            File file = new File(CustomerToXML.FILE_NAME);
            JAXBContext jaxbContext = JAXBContext.newInstance(Customer.class);

            Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
            Customer customer = (Customer) jaxbUnmarshaller.unmarshal(file);

            System.out.println(customer);
        } catch (JAXBException e) {
            // Log and Handle exception
            e.printStackTrace();
        }
    }
}

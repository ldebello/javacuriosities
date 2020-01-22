package ar.com.javacurisioties.jaxb.lesson1;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import java.io.File;

public class CustomerToXML {

    public static final String FILE_NAME = "JAXBObject.xml";

    public static void main(String[] args) {
        Customer customer = new Customer();
        customer.setId(100);
        customer.setName("Cosme Fulanito");
        customer.setAge(99);

        try {
            File file = new File(FILE_NAME);
            JAXBContext jaxbContext = JAXBContext.newInstance(Customer.class);
            Marshaller jaxbMarshaller = jaxbContext.createMarshaller();

            // Pretty print
            jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);

            jaxbMarshaller.marshal(customer, file);
            jaxbMarshaller.marshal(customer, System.out);
        } catch (JAXBException e) {
            // Log and Handle exception
            e.printStackTrace();
        }
    }
}
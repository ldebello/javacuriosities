package ar.com.javacurisioties.jaxb.lesson5;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Date;

public class Main {

    public static void main(String[] args) {
        Parent parent = new Parent();
        parent.setName("Parent Name");
        parent.setLastName("Parent Last Name");
        parent.setBirthdate(new Date());

        Child child = new Child();
        child.setName("Child Name");
        child.setLastName("Child Last Name");
        child.setBirthdate(new Date());
        child.setDni("11111111");
        child.setSchool("Child School");

        SubChild subChild = new SubChild();
        subChild.setName("SubChild Name");
        subChild.setLastName("SubChild Last Name");
        subChild.setBirthdate(new Date());
        subChild.setDni("22222222");
        subChild.setSchool("SubChild School");
        subChild.setAge(28);

        try {
            System.out.println("Serializing");

            Path parentPath = Files.createTempFile("Parent-", ".xml");
            Path childPath = Files.createTempFile("Child-", ".xml");
            Path subChildPath = Files.createTempFile("SubChild-", ".xml");

            JAXBContext jaxbContext = JAXBContext.newInstance(Parent.class);
            Marshaller jaxbMarshaller = jaxbContext.createMarshaller();

            // Asignar una propiedad para que le de formato al XML
            jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);

            System.out.println("XML Parent");
            jaxbMarshaller.marshal(parent, parentPath.toFile());
            jaxbMarshaller.marshal(parent, System.out);

            System.out.println("XML Child");
            jaxbMarshaller.marshal(child, childPath.toFile());
            jaxbMarshaller.marshal(child, System.out);

            System.out.println("XML SubChild");
            jaxbMarshaller.marshal(subChild, subChildPath.toFile());
            jaxbMarshaller.marshal(subChild, System.out);

            System.out.println("Deserializing");
            Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();

            File subChildFile = subChildPath.toFile();
            File childFile = childPath.toFile();
            File parentFile = parentPath.toFile();

            Parent subChildInstance = (Parent) jaxbUnmarshaller.unmarshal(subChildFile);
            System.out.println(subChildInstance);

            Parent childInstance = (Parent) jaxbUnmarshaller.unmarshal(childFile);
            System.out.println(childInstance);

            Parent parentInstance = (Parent) jaxbUnmarshaller.unmarshal(parentFile);
            System.out.println(parentInstance);
        } catch (IOException | JAXBException e) {
            // Log and Handle exception
            e.printStackTrace();
        }
    }
}
package ar.com.javacurisioties.jaxb.lesson4;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.SchemaOutputResolver;
import javax.xml.transform.Result;
import javax.xml.transform.stream.StreamResult;
import java.io.File;
import java.io.IOException;

public class Main {

    public static void main(String[] args) {
        try {
            final File schema = new File("JAXBObject.xsd");
            JAXBContext jc = JAXBContext.newInstance(School.class);
            jc.generateSchema(new SchemaOutputResolver() {
                @Override
                public Result createOutput(String namespaceURI, String suggestedFileName) throws IOException {
                    return new StreamResult(schema);
                }
            });
        } catch (JAXBException | IOException e) {
            // Log and Handle exception
            e.printStackTrace();
        }
    }
}

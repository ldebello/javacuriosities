package ar.com.javacurisioties.jaxws.mtom.client;

import ar.com.javacurisioties.jaxws.mtom.service.HelloWorldMTOM;

import javax.imageio.ImageIO;
import javax.xml.namespace.QName;
import javax.xml.ws.BindingProvider;
import javax.xml.ws.Service;
import javax.xml.ws.soap.SOAPBinding;
import java.net.URL;
import java.util.Objects;

public class MTOMClientUpload {

    private static final String WS_URL = "http://127.0.0.1:9988/HelloWorldMTOM?wsdl";

    private static final String SERVICE_NAME = "HelloWorldMTOMImplService";
    private static final String TARGET_NAMESPACE = "http://service.mtom.jaxws.javacurisioties.com.ar/";

    public static void main(String[] args) throws Exception {

        URL url = new URL(WS_URL);

        QName qname = new QName(TARGET_NAMESPACE, SERVICE_NAME);

        Service service = Service.create(url, qname);

        HelloWorldMTOM implementation = service.getPort(HelloWorldMTOM.class);

        // Activando MTOM en el cliente
        BindingProvider bp = (BindingProvider) implementation;
        SOAPBinding binding = (SOAPBinding) bp.getBinding();
        binding.setMTOMEnabled(true);


        String result = implementation.uploadImage(ImageIO.read(Objects.requireNonNull(ClassLoader.getSystemClassLoader().getResourceAsStream("java.jpg"))));

        System.out.println(result);
    }
}

package ar.com.javacurisioties.jaxws.mtom.client;

import ar.com.javacurisioties.jaxws.mtom.service.HelloWorldMTOM;

import javax.swing.*;
import javax.xml.namespace.QName;
import javax.xml.ws.Service;
import java.awt.*;
import java.net.URL;

public class MTOMClientDownload {

    private static final String WS_URL = "http://127.0.0.1:9988/HelloWorldMTOM?wsdl";

    private static final String SERVICE_NAME = "HelloWorldMTOMImplService";
    private static final String TARGET_NAMESPACE = "http://service.mtom.jaxws.javacurisioties.com.ar/";

    public static void main(String[] args) throws Exception {

        URL url = new URL(WS_URL);

        QName qname = new QName(TARGET_NAMESPACE, SERVICE_NAME);

        Service service = Service.create(url, qname);

        HelloWorldMTOM implementation = service.getPort(HelloWorldMTOM.class);

        Image imageFromServer = implementation.downloadImage("java.jpg");

        // Mostrar imagen
        JFrame frame = new JFrame();
        frame.setSize(300, 300);
        JLabel label = new JLabel(new ImageIcon(imageFromServer));
        frame.add(label);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);

        System.out.println("Imagen descargada");
    }
}

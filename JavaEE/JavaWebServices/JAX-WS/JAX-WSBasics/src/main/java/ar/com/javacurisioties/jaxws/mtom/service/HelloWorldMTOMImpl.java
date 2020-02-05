package ar.com.javacurisioties.jaxws.mtom.service;

import javax.imageio.ImageIO;
import javax.jws.WebService;
import javax.xml.ws.WebServiceException;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Objects;

/**
 * MTOM (Message Transmission Optimization Mechanism) es un mecanismo propuesto por la W3C para transmitir
 * contenido binario. También existe SWA (SOAP Messages with Attachments).
 */
@WebService(endpointInterface = "ar.com.javacurisioties.jaxws.mtom.service.HelloWorldMTOM")
public class HelloWorldMTOMImpl implements HelloWorldMTOM {

    @Override
    public Image downloadImage(String name) {
        try {
            return ImageIO.read(Objects.requireNonNull(getClass().getClassLoader().getResourceAsStream(name)));
        } catch (IOException e) {
            // Log and Handle exception
            e.printStackTrace();
            return null;
        }
    }


    @Override
    public String uploadImage(Image data) {
        if (data != null) {
            try {
                ImageIO.write((BufferedImage) data, "jpg", new File("mtom.jpg"));
                return "Upload Complete";
            } catch (IOException e) {
                // Log and Handle exception
                e.printStackTrace();
            }
        }
        throw new WebServiceException("Upload Error!!!");
    }
}
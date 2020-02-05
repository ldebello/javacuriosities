package ar.com.javacurisioties.jaxws.mtom.service;

import javax.jws.WebService;
import javax.xml.ws.soap.MTOM;
import java.awt.*;

/**
 * MTOM (Message Transmission Optimization Mechanism) es un mecanismo propuesto por la W3C para transmitir
 * contenido binario. También existe SWA (SOAP Messages with Attachments).
 */
@MTOM
@WebService
public interface HelloWorldMTOM {
    Image downloadImage(String name);

    String uploadImage(Image data);
}
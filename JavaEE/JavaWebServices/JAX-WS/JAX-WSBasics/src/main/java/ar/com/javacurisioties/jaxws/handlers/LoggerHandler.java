package ar.com.javacurisioties.jaxws.handlers;

import javax.xml.namespace.QName;
import javax.xml.soap.SOAPMessage;
import javax.xml.ws.handler.MessageContext;
import javax.xml.ws.handler.soap.SOAPHandler;
import javax.xml.ws.handler.soap.SOAPMessageContext;
import java.io.ByteArrayOutputStream;
import java.util.Collections;
import java.util.Set;
import java.util.logging.Logger;

public class LoggerHandler implements SOAPHandler<SOAPMessageContext> {

    private static final Logger LOGGER = Logger.getLogger(LoggerHandler.class.getName());

    @Override
    public boolean handleMessage(SOAPMessageContext context) {
        return logMessage(context);
    }

    @Override
    public boolean handleFault(SOAPMessageContext context) {
        return logMessage(context);
    }

    @Override
    public void close(MessageContext context) {
    }

    private boolean logMessage(SOAPMessageContext smc) {
        Boolean outboundProperty = (Boolean) smc.get(MessageContext.MESSAGE_OUTBOUND_PROPERTY);

        if (outboundProperty) {
            LOGGER.info("\nOutgoing message:");
        } else {
            LOGGER.info("\nIncoming message:");
        }

        SOAPMessage message = smc.getMessage();
        try {
            ByteArrayOutputStream out = new ByteArrayOutputStream();
            message.writeTo(out);
            String strMsg = new String(out.toByteArray());
            LOGGER.info("XML content:");
            LOGGER.info(strMsg);
        } catch (Exception e) {
            e.printStackTrace();
            LOGGER.severe("Exception while writing message: " + e.getMessage());
        }

        return true;
    }

    @Override
    public Set<QName> getHeaders() {
        return Collections.emptySet();
    }
}
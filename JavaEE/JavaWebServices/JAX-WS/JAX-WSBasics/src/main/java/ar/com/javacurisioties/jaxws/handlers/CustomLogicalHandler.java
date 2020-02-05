package ar.com.javacurisioties.jaxws.handlers;

import javax.xml.ws.handler.LogicalHandler;
import javax.xml.ws.handler.LogicalMessageContext;
import javax.xml.ws.handler.MessageContext;
import java.util.logging.Logger;

public class CustomLogicalHandler implements LogicalHandler<LogicalMessageContext> {

    private static final Logger LOGGER = Logger.getLogger(CustomLogicalHandler.class.getName());

    @Override
    public boolean handleMessage(LogicalMessageContext lmc) {
        Boolean outboundProperty = (Boolean) lmc.get(MessageContext.MESSAGE_OUTBOUND_PROPERTY);

        if (outboundProperty) {
            LOGGER.info("\nLogical Handler - Outgoing message:");
        } else {
            LOGGER.info("\nLogical Handler - Incoming message:");
        }

        Object payload = lmc.getMessage().getPayload();
        LOGGER.info("Payload: " + payload.getClass());

        return true;
    }

    @Override
    public boolean handleFault(LogicalMessageContext context) {
        return true;
    }

    @Override
    public void close(MessageContext context) {
    }
}

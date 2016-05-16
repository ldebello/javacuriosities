package ar.com.javacuriosities.nio.server.message.processor;

import ar.com.javacuriosities.nio.server.message.Message;
import ar.com.javacuriosities.nio.server.message.writer.WriteProxy;

/*
 * La implementación esta encargada de leer el mensaje y responder en consecuencia
 */
public interface IMessageProcessor {

    public void process(Message message, WriteProxy writeProxy);
}
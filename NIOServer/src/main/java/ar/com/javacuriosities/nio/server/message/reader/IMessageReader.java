package ar.com.javacuriosities.nio.server.message.reader;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.util.List;

import ar.com.javacuriosities.nio.server.clients.ClientSocket;
import ar.com.javacuriosities.nio.server.message.Message;
import ar.com.javacuriosities.nio.server.message.MessageBuffer;

/*
 * El reader del mensaje proveniente del cliente
 */
public interface IMessageReader {

    void init(MessageBuffer readMessageBuffer);

    void read(ClientSocket socket, ByteBuffer byteBuffer) throws IOException;

    List<Message> getMessages();
}
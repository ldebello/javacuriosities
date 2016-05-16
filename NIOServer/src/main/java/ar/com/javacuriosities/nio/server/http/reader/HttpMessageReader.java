package ar.com.javacuriosities.nio.server.http.reader;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.List;

import ar.com.javacuriosities.nio.server.clients.ClientSocket;
import ar.com.javacuriosities.nio.server.http.util.HttpHeaders;
import ar.com.javacuriosities.nio.server.http.util.HttpUtil;
import ar.com.javacuriosities.nio.server.message.Message;
import ar.com.javacuriosities.nio.server.message.MessageBuffer;
import ar.com.javacuriosities.nio.server.message.reader.IMessageReader;

/*
 * Implementación del Reader para un mensaje que utiliza el protocolo HTTP
 */
public class HttpMessageReader implements IMessageReader {

	private Message nextMessage = null;
	private MessageBuffer messageBuffer = null;
	private List<Message> completeMessages = new ArrayList<Message>();

    public HttpMessageReader() {
    }

    @Override
    public void init(MessageBuffer readMessageBuffer) {
		this.messageBuffer = readMessageBuffer;
		this.nextMessage = messageBuffer.getMessage();
		this.nextMessage.metaData = new HttpHeaders();
    }

    @Override
    public void read(ClientSocket socket, ByteBuffer byteBuffer) throws IOException {
        socket.read(byteBuffer);
        byteBuffer.flip();

        if(byteBuffer.remaining() == 0){
            byteBuffer.clear();
            return;
        }

        this.nextMessage.writeToMessage(byteBuffer);

        int endIndex = HttpUtil.parseHttpRequest(this.nextMessage.sharedBuffer, this.nextMessage.offset, this.nextMessage.offset + this.nextMessage.length, (HttpHeaders) this.nextMessage.metaData);
        if(endIndex != -1){
            Message message = this.messageBuffer.getMessage();
            message.metaData = new HttpHeaders();

            message.writePartialMessageToMessage(nextMessage, endIndex);

            completeMessages.add(nextMessage);
            nextMessage = message;
        }
        byteBuffer.clear();
    }


    @Override
    public List<Message> getMessages() {
        return this.completeMessages;
    }
}
package ar.com.javacuriosities.nio.server.message.writer;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.List;

import ar.com.javacuriosities.nio.server.clients.ClientSocket;
import ar.com.javacuriosities.nio.server.message.Message;

/*
 * Se encarga de mantener el mensaje en progreso y además tener mensajes encolados
 */
public class MessageWriter {

	private int bytesWritten = 0;
	private Message messageInProgress = null;
	private List<Message> writeQueue = new ArrayList<>();

    public MessageWriter() {
    }

    public void enqueue(Message message) {
        if(this.messageInProgress == null){
            this.messageInProgress = message;
        } else {
            this.writeQueue.add(message);
        }
    }

    public void write(ClientSocket socket, ByteBuffer byteBuffer) throws IOException {
        byteBuffer.put(this.messageInProgress.sharedBuffer, this.messageInProgress.offset + this.bytesWritten, this.messageInProgress.length - this.bytesWritten);
        byteBuffer.flip();

        this.bytesWritten += socket.write(byteBuffer);
        byteBuffer.clear();

        if(bytesWritten >= this.messageInProgress.length){
            if(this.writeQueue.size() > 0){
                this.messageInProgress = this.writeQueue.remove(0);
            } else {
                this.messageInProgress = null;
                // TODO Unregister from selector
            }
        }
    }

    public boolean isEmpty() {
        return this.writeQueue.isEmpty() && this.messageInProgress == null;
    }
}
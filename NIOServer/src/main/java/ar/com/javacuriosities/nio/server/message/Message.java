package ar.com.javacuriosities.nio.server.message;

import java.nio.ByteBuffer;

/*
 * El Mensaje representa la información recibida desde el cliente y este puede estar completo o de forma parcial,
 * además conoce directamente al buffer que lo contiene
 */
public class Message {

	public long socketId = 0; // Id que identifica el source/destination socket

	public byte[] sharedBuffer = null; // Shared buffer
	
	public int offset = 0; // Offset dentro del shared buffer
	public int capacity = 0; // Tamaño de la sección dentro del shared buffer
	public int length = 0; // Números de bytes alocados en la sección

    public Object metaData    = null;

    private MessageBuffer messageBuffer = null;
    
    public Message(MessageBuffer messageBuffer) {
        this.messageBuffer = messageBuffer;
    }

    // Escribe los datos desde el ByteBuffer en el mensaje, o sea en el buffer backing que lo contiene
    public int writeToMessage(byte[] byteArray){
        return writeToMessage(byteArray, 0, byteArray.length);
    }
    
    public int writeToMessage(ByteBuffer byteBuffer){
        int remaining = byteBuffer.remaining();

        while(this.length + remaining > capacity){
            if(!this.messageBuffer.expandMessage(this)) {
                return -1;
            }
        }

        int bytesToCopy = Math.min(remaining, this.capacity - this.length);
        byteBuffer.get(this.sharedBuffer, this.offset + this.length, bytesToCopy);
        this.length += bytesToCopy;

        return bytesToCopy;
    }
    
    public int writeToMessage(byte[] byteArray, int offset, int length){
        int remaining = length;

        while(this.length + remaining > capacity){
            if(!this.messageBuffer.expandMessage(this)) {
                return -1;
            }
        }

        int bytesToCopy = Math.min(remaining, this.capacity - this.length);
        System.arraycopy(byteArray, offset, this.sharedBuffer, this.offset + this.length, bytesToCopy);
        this.length += bytesToCopy;
        
        return bytesToCopy;
    }

    // Escribimos los datos parciales de un mensaje a otro mensaje
    public void writePartialMessageToMessage(Message message, int endIndex){
        int startIndexOfPartialMessage = message.offset + endIndex;
        int lengthOfPartialMessage     = (message.offset + message.length) - endIndex;

        System.arraycopy(message.sharedBuffer, startIndexOfPartialMessage, this.sharedBuffer, this.offset, lengthOfPartialMessage);
    }
}
package ar.com.javacuriosities.nio.server.message.reader;

/*
 * Factory pattern para los Readers
 */
public interface IMessageReaderFactory {

    IMessageReader createMessageReader();
}
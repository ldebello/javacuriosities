package ar.com.javacuriosities.nio.server.http.reader;

import ar.com.javacuriosities.nio.server.message.reader.IMessageReader;
import ar.com.javacuriosities.nio.server.message.reader.IMessageReaderFactory;

public class HttpMessageReaderFactory implements IMessageReaderFactory {

    public HttpMessageReaderFactory() {
    }

    @Override
    public IMessageReader createMessageReader() {
        return new HttpMessageReader();
    }
}
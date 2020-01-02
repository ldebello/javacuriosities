package ar.com.javacuriosities.websockets.coders;

import ar.com.javacuriosities.websockets.model.User;

import javax.websocket.EncodeException;
import javax.websocket.Encoder;
import javax.websocket.EndpointConfig;

/*
 * WebSockets provee soporte para convertir nuestros objetos
 * a texto o binario usando encoders y decores
 * Encoder.Text<T>: Para texto
 * Encoder.Binary<T>: Para binario
 *
 * Luego esos encoders son registrados en el Endpoint y usamos el metodo
 * sendObject por medio de la session
 */
public class CustomMessageEncoder implements Encoder.Text<User> {

    @Override
    public String encode(User user) throws EncodeException {
        return user.toString();
    }

    @Override
    public void init(EndpointConfig config) {
    }

    @Override
    public void destroy() {
    }

}

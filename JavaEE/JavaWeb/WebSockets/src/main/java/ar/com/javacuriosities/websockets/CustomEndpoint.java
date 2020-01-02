package ar.com.javacuriosities.websockets;


import ar.com.javacuriosities.websockets.coders.CustomMessageDecoder;
import ar.com.javacuriosities.websockets.coders.CustomMessageEncoder;
import ar.com.javacuriosities.websockets.model.User;

import javax.websocket.EndpointConfig;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;

@ServerEndpoint(
        value = "/custom/{user-name}",
        encoders = {CustomMessageEncoder.class},
        decoders = {CustomMessageDecoder.class})
public class CustomEndpoint {

    /*
     * Podemos usar partes del path como parametros
     */
    @OnOpen
    public void open(Session session, EndpointConfig c, @PathParam("user-name") String userName) {
    }

    @OnMessage
    public void message(Session session, User msg) {
        // Al usar el metodo sendObject el container buscara el Encoder apropiado
        // session.getBasicRemote().sendObject();
    }
}

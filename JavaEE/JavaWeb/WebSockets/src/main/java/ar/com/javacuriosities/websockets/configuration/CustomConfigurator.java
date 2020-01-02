package ar.com.javacuriosities.websockets.configuration;

import javax.websocket.HandshakeResponse;
import javax.websocket.server.HandshakeRequest;
import javax.websocket.server.ServerEndpointConfig;
import javax.websocket.server.ServerEndpointConfig.Configurator;

/*
 * WebSockets nos permite configurar como el container va
 * a crear nuestros Endpoints
 */
public class CustomConfigurator extends Configurator {

    @Override
    public void modifyHandshake(ServerEndpointConfig conf, HandshakeRequest req, HandshakeResponse resp) {
        conf.getUserProperties().put("handshakereq", req);
    }

}
package ar.com.javacuriosities.websockets;

import ar.com.javacuriosities.websockets.configuration.CustomConfigurator;

import javax.websocket.CloseReason;
import javax.websocket.EndpointConfig;
import javax.websocket.OnClose;
import javax.websocket.OnError;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.PongMessage;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;
import java.nio.ByteBuffer;

/*
 * Los WebSocket(JSR 356) permiten una comunicacion bidericcional entre
 * el cliente y el servidor, pero ademas provee la ventaja de que el
 * servidor puede inicar el envio de datos.
 *
 * Proceso:
 * - Se establece una conexion (Esto se conoce como handshake)
 * Este handshake se genera por medio de un request el cual envia un header
 * "Sec-WebSocket-key" y el Server responde un header "Sec-WebSocket-Accept",
 * estas dos partes se generan usando una operacion conocida entre el cliente y el server.
 * Si los dos obtiene el mismo resultado se establece la conexion
 * - Luego de la conexion podemos empezar el momento de intercambio (Se lo conoce como data transfer)
 *
 * Para acceder a un WebSocket lo hacemos por medio de un endpoint (Combinacion de host + port)
 *
 * Las URIs se representan como
 *
 * ws://host:port/path?query
 * wss://host:port/path?query
 *
 * ws Es el schema sin encriptacion generalmente por el puerto 80 y wss es el schema encriptado
 * generalmente en el puerto 443
 *
 * Los Browsers modernos ofrecen APIs JavaScript para la conexion,
 * envio de mensajes y asignacion de metodo callback
 */

/*
 * Podemos utilizar la Annotation y el Endpoint es deployado directamente
 * o heredar de la clase Endpoint.
 * Al contrario de los Servlets los Endpoints son instanciados multiples veces
 * y se crea uno por conexion para poder mantener el estado.
 * Los metodos del ciclo de vida son 3 y se pueden indicar por medio de las Annotations
 * @OnOpen: Se establece la conexion
 * @OnError: Error en la conexion
 * @Onclose: Se cierra la conexion
 *
 * El parametro del tipo "javax.websocket.Session" representa la conversacion entre el cliente y el server
 */
@ServerEndpoint(
        value = "/echo",
        configurator = CustomConfigurator.class)
public class EchoEndpoint {

    @OnOpen
    public void open(Session session, EndpointConfig conf) {
    }

    /*
     * Podemos tener 3 metodos con la Annotation @OnMessage
     */
    @OnMessage
    public void textMessage(Session session, String msg) {
        // Permite enviar mensajes bloqueantes
        // session.getBasicRemote();

        // Permite enviar mensaje asincronicos
        // session.getAsyncRemote();

        // Permite obtener todas las session y hacer envio de mensajes masivos
        // session.getOpenSessions();

        // Podemos asignar propiedades por cliente ya que cada session es separada
        // session.getUserProperties();
        System.out.println("Mensaje de texto: " + msg);
    }

    @OnMessage
    public void binaryMessage(Session session, ByteBuffer msg) {
        System.out.println("Mensaje binario: " + msg.toString());
    }

    @OnMessage
    public void pongMessage(Session session, PongMessage msg) {
        System.out.println("Mensaje Pong: " + msg.getApplicationData().toString());
    }

    @OnError
    public void error(Session session, Throwable error) {
    }

    @OnClose
    public void close(Session session, CloseReason reason) {
    }
}

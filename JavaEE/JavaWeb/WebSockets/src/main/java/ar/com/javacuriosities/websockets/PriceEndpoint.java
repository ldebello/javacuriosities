package ar.com.javacuriosities.websockets;

import javax.websocket.OnClose;
import javax.websocket.OnError;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.logging.Level;
import java.util.logging.Logger;

@ServerEndpoint("/price")
public class PriceEndpoint {

    // Cola para todas las sessions iniciadas
    private static Queue<Session> queue = new ConcurrentLinkedQueue<>();

    public static void send(double price, int volume) {
        try {
            String msg = String.format("%.2f, %d", price, volume);

            for (Session session : queue) {
                session.getBasicRemote().sendText(msg);
            }
        } catch (IOException e) {
            // Log and Handle exception
            e.printStackTrace();
        }
    }

    @OnOpen
    public void openConnection(Session session) {
        queue.add(session);
    }

    @OnClose
    public void closedConnection(Session session) {
        queue.remove(session);
    }

    @OnError
    public void error(Session session, Throwable t) {
        queue.remove(session);
    }
}

package ar.com.javacuriosities.websockets.coders;

import ar.com.javacuriosities.websockets.model.User;

import javax.json.Json;
import javax.json.JsonObject;
import javax.json.JsonReader;
import javax.websocket.Decoder;
import javax.websocket.EndpointConfig;
import java.io.StringReader;

public class CustomMessageDecoder implements Decoder.Text<User> {

    @Override
    public User decode(String data) {
        JsonReader reader = Json.createReader(new StringReader(data));
        JsonObject object = reader.readObject();

        User user = new User();
        user.setName(object.getString("name"));
        user.setPass(object.getString("pass"));

        return user;
    }

    /*
     * Se usa para ver si el mensaje puede ser convertido
     */
    @Override
    public boolean willDecode(String arg0) {
        return true;
    }

    @Override
    public void init(EndpointConfig config) {
    }

    @Override
    public void destroy() {
    }


}

package ar.com.javacuriosities.xml.rpc.client;

import org.apache.xmlrpc.XmlRpcException;
import org.apache.xmlrpc.client.XmlRpcClient;
import org.apache.xmlrpc.client.XmlRpcClientConfigImpl;
import org.apache.xmlrpc.client.XmlRpcSunHttpTransportFactory;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/*
 * Ejemplo Cliente XML-RPC
 */
public class Main {

    public static void main(String[] args) {
        try {
            // Creamos la config del cliente
            XmlRpcClientConfigImpl config = new XmlRpcClientConfigImpl();

            // Dato importante la URL del web serve
            config.setServerURL(new URL("http://localhost:8580/XmlRpcServer"));

            // Se crea el cliente y asignamos unas properties
            XmlRpcClient client = new XmlRpcClient();

            /*
             * XmlRpcSunHttpTransportFactory: Es el Transport Factory por default. Se conecta mediante HTTP usando la clase java.net.HttpURLConnection
             * XmlRpcCommonsTransportFactory: Otra Transport Factory que usa HTTP, esta implementación usa las librerías Jakarta Commons HttpClient.
             * XmlRpcLiteHttpTransportFactory: Otra Transport Factory HTTP, utiliza un cliente HTTP muy liviano, la desventaja es que no puede dejar conexiones abiertas
             * XmlRpcLocalTransportFactory: Esta implementación es parte de XML-RPC Server y es invocada mediante java directamente, es muy útil para debug y desarrollo
             */
            client.setTransportFactory(new XmlRpcSunHttpTransportFactory(client));
            client.setConfig(config);

            // Parámetros para el primer RPC
            List<Object> params = new ArrayList<>();
            params.add("Argentina");

            // Ejecuto y espero resultado
            String result = (String) client.execute("Weather.check", params);
            System.out.println(result);

            // Parámetros para el segundo RPC
            params = new ArrayList<>();
            Map<String, Object> alumno = new HashMap<>();
            alumno.put("name", "Cosme");
            alumno.put("lastName", "Fulanito");
            alumno.put("age", 99);
            alumno.put("documentType", "DNI");
            alumno.put("documentNumber", 11_111_111);
            alumno.put("project", "JavaCuriosities");
            params.add(alumno);

            // Ejecuto y espero resultado
            Map<String, Object> response = (Map<String, Object>) client.execute("Students.save", params);

            // Recibo la respuesta
            String name = (String) response.get("name");
            String code = (String) response.get("code");
            String description = (String) response.get("description");

            System.out.println("Name: " + name);
            System.out.println("Code: " + code);
            System.out.println("Description: " + description);
        } catch (MalformedURLException | XmlRpcException e) {
            // Log and Handle exception
            e.printStackTrace();
        }
    }
}
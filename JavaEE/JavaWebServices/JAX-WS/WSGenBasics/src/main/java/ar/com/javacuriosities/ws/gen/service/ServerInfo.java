package ar.com.javacuriosities.ws.gen.service;

import javax.jws.WebMethod;
import javax.jws.WebService;

@WebService
public class ServerInfo {

    @WebMethod
    public String getIpAddress() {
        return "201.235.131.228";
    }
}

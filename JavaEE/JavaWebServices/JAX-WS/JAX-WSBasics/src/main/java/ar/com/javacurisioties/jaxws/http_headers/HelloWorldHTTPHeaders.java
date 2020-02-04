package ar.com.javacurisioties.jaxws.http_headers;

import javax.annotation.Resource;
import javax.jws.WebService;
import javax.xml.ws.WebServiceContext;
import javax.xml.ws.handler.MessageContext;
import java.util.List;
import java.util.Map;

@WebService
public class HelloWorldHTTPHeaders {

    @Resource
    private WebServiceContext wsctx;

    public String sayHello() {
        MessageContext ctx = wsctx.getMessageContext();

        Map httpHeaders = (Map) ctx.get(MessageContext.HTTP_REQUEST_HEADERS);
        List<String> nameHeaders = (List<String>) httpHeaders.get("Name");
        String name = nameHeaders != null ? nameHeaders.get(0) : null;

        return "Hello " + name;
    }
}

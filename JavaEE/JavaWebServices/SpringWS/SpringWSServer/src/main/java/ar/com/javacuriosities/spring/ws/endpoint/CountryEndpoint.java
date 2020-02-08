package ar.com.javacuriosities.spring.ws.endpoint;

import ar.com.javacuriosities.spring.ws.GetCountryRequest;
import ar.com.javacuriosities.spring.ws.GetCountryResponse;
import ar.com.javacuriosities.spring.ws.repository.CountryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;


/**
 * La annotation @Endpoint sirve para registrar la clase con Spring WS como candidato para procesar SOAP Messages.
 */
@Endpoint
public class CountryEndpoint {

    public static final String NAMESPACE_URI = "http://javacuriosities.com.ar/spring/ws";

    private CountryRepository countryRepository;

    @Autowired
    public CountryEndpoint(CountryRepository countryRepository) {
        this.countryRepository = countryRepository;
    }

    /*
     * La annotation @PayloadRoot es utilizada por Spring WS para seleccionar el handler method, en base al namespace y local part del mensaje.
     * La annotation @RequestPayload y @ResponsePayload indican como mapear el SOAP Message.
     */
    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "getCountryRequest")
    @ResponsePayload
    public GetCountryResponse getCountry(@RequestPayload GetCountryRequest request) {
        GetCountryResponse response = new GetCountryResponse();
        response.setCountry(countryRepository.findCountry(request.getName()));

        return response;
    }
}
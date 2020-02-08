package ar.com.javacuriosities.ws.clients;


import ar.com.javacuriosities.consumingwebservice.wsdl.GetCountryRequest;
import ar.com.javacuriosities.consumingwebservice.wsdl.GetCountryResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.ws.client.core.support.WebServiceGatewaySupport;
import org.springframework.ws.soap.client.core.SoapActionCallback;


public class CountryClient extends WebServiceGatewaySupport {

    private static final Logger log = LoggerFactory.getLogger(CountryClient.class);

    private static final String URI = "http://localhost:8080/ws/countries";
    private static final String NAMESPACE = "http://javacuriosities.com.ar/spring/ws/GetCountryRequest";

    public GetCountryResponse getCountry(String country) {

        GetCountryRequest request = new GetCountryRequest();
        request.setName(country);

        log.info("Requesting location for " + country);

        GetCountryResponse response = (GetCountryResponse) getWebServiceTemplate()
                .marshalSendAndReceive(URI, request,
                        new SoapActionCallback(NAMESPACE));

        return response;
    }

}
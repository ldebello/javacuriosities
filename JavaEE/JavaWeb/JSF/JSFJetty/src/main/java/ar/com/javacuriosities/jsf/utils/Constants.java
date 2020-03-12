package ar.com.javacuriosities.jsf.utils;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Named;

@Named
@ApplicationScoped
public final class Constants {

    public static final String CLIENT_ID = "5186152251803358";
    public static final String CLIENT_SECRET = "aksDweICteKQAn9Q2WScK3TFli0UkmSB";
    public static final String REDIRECT = "http://localhost:8080/redirect";

    private Constants() {
    }

    public String authorizationUrl() {
        return "http://auth.mercadolibre.com.ar/authorization?response_type=code&amp;client_id=" + CLIENT_ID + "&amp;redirect_uri=" + REDIRECT;
    }

}

package ar.com.javacuriosities.oauth;

import ar.com.javacuriosities.oauth.model.BearerTokenResponse;
import ar.com.javacuriosities.oauth.utils.Constants;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Invocation;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.io.IOException;


public class OAuthRedirectServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        processRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        processRequest(request, response);
    }

    protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        String code = request.getParameter("code");
        String state = request.getParameter("state");
        String stateCookieValue = getStateCookieValue(request);

        if (stateCookieValue != null && stateCookieValue.equals(state)) {
            Client client = ClientBuilder.newClient();

            WebTarget webTarget = client.target("https://github.com/login/oauth/access_token?client_id=" + Constants.CLIENT_ID + "&client_secret=" + Constants.CLIENT_SECRET + "&code=" + code);
            Invocation.Builder tokenRequest = webTarget.request(MediaType.APPLICATION_JSON);
            Response tokenResponse = tokenRequest.get(Response.class);

            if (tokenResponse.getStatus() == 200) {
                // We should store the token in a safe place
                BearerTokenResponse bearerTokenResponse = tokenResponse.readEntity(BearerTokenResponse.class);

                RequestDispatcher rd = getServletContext().getRequestDispatcher("/fetcher");

                request.setAttribute("token", bearerTokenResponse);

                rd.forward(request, response);
            } else {
                throw new RuntimeException("Could not fetch access token");
            }
        } else {
            throw new RuntimeException("Invalid OAuth Callback");
        }
    }

    private String getStateCookieValue(HttpServletRequest request) {
        Cookie[] cookies = request.getCookies();
        for (Cookie cookie : cookies) {
            if (Constants.STATE_COOKIE.equals(cookie.getName())) {
                return cookie.getValue();
            }
        }
        return null;
    }
}

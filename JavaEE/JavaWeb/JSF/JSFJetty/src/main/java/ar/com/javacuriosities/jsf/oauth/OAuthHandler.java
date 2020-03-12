package ar.com.javacuriosities.jsf.oauth;

import ar.com.javacuriosities.jsf.utils.Constants;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Invocation;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.io.IOException;

@WebServlet(name = "OAuthServlet", urlPatterns = {"/redirect"})
public class OAuthHandler extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String code = request.getParameter("code");

        Client client = ClientBuilder.newClient();

        WebTarget tokenTarget = client.target("https://api.mercadolibre.com/oauth/token?grant_type=authorization_code&client_id=" + Constants.CLIENT_ID + "&client_secret=" + Constants.CLIENT_SECRET + "&code=" + code + "&redirect_uri=" + Constants.REDIRECT);
        Invocation.Builder tokenRequest = tokenTarget.request();
        Response tokenResponse = tokenRequest.post(null, Response.class);

        if (tokenResponse.getStatus() == 200) {
            MLTokenResponse mlTokenResponse = tokenResponse.readEntity(MLTokenResponse.class);

            WebTarget webTarget = client.target("https://api.mercadolibre.com/users/me?access_token=" + mlTokenResponse.getAccessToken());
            Invocation.Builder userRequest = webTarget.request(MediaType.APPLICATION_JSON);
            Response userResponse = userRequest.get(Response.class);

            if (userResponse.getStatus() == 200) {
                MLUserResponse MLUserResponse = userResponse.readEntity(MLUserResponse.class);

                HttpSession session = request.getSession();
                session.setAttribute("logged", true);
                session.setAttribute("username", MLUserResponse.getNickname());

                response.sendRedirect("welcome.xhtml");
            }
        }
    }
}

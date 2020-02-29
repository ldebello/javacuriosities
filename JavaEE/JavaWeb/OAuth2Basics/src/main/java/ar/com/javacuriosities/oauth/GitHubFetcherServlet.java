package ar.com.javacuriosities.oauth;

import ar.com.javacuriosities.oauth.model.BearerTokenResponse;
import ar.com.javacuriosities.oauth.model.GitHubUserResponse;

import javax.servlet.ServletException;
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

public class GitHubFetcherServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        processRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        processRequest(request, response);
    }

    protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        Client client = ClientBuilder.newClient();

        BearerTokenResponse bearerTokenResponse = (BearerTokenResponse) request.getAttribute("token");

        WebTarget userTarget = client.target("https://api.github.com/user");
        Invocation.Builder userRequest = userTarget.request(MediaType.APPLICATION_JSON);
        userRequest.header("Authorization", bearerTokenResponse.getTokenType() + " " + bearerTokenResponse.getAccessToken());

        Response userResponse = userRequest.get(Response.class);
        if (userResponse.getStatus() == 200) {
            GitHubUserResponse gitHubUser = userResponse.readEntity(GitHubUserResponse.class);

            response.sendRedirect("welcome.jsp?id=" + gitHubUser.getId() + "&login=" + gitHubUser.getLogin() + "&name=" + gitHubUser.getName());
        } else {
            throw new RuntimeException("Could not fetch GitHub user");
        }
    }
}

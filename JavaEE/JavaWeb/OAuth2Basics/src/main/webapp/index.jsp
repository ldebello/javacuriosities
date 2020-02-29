<%@ page import="ar.com.javacuriosities.oauth.utils.Constants" %>
<%@ page import="java.util.UUID" %>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
    <title>OAuth2 Example</title>
</head>
<body>
<%
    UUID state = UUID.randomUUID();
    Cookie stateCookie = new Cookie(Constants.STATE_COOKIE, state.toString());
    stateCookie.setHttpOnly(true);
    response.addCookie(stateCookie);
%>
<a href="https://github.com/login/oauth/authorize?client_id=<%= Constants.CLIENT_ID %>&redirect_uri=http://localhost:8080/OAuth2Basics/redirect&state=<%= state %>">GitHub
    Login</a>
</body>
</html>
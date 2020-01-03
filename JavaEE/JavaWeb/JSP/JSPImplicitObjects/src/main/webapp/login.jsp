<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
    <title>Login</title>
</head>
<body>
<%
    // Aquí utilizamos el objeto implícito "request"
    String name = request.getParameter("name");

    // Aquí utilizamos el objeto implícito "config"
    String defaultUser = config.getInitParameter("DefaultUser");

    // Aquí utilizamos el objeto implícito "out"
    out.print("Bienvenido " + name);
%>
<br/>

<%
    out.print("El usuario debería ser: " + defaultUser);
%>
</body>
</html>

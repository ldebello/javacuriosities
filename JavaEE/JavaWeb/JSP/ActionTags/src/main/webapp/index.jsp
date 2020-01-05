<%@page contentType="text/html" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
    <title>JSP Action Tags</title>
</head>
<body>
<jsp:forward page="login.jsp">
    <jsp:param name="message" value="Bienvenido"/>
</jsp:forward>
</body>
</html>

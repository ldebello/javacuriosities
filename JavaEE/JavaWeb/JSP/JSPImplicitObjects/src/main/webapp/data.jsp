<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
    <title>User Data</title>
</head>
<body>
<%
    ar.com.javacurisioties.jsp.model.UserData user = (ar.com.javacurisioties.jsp.model.UserData) session.getAttribute("user");

    String pageContextAttribute = (String) application.getAttribute("pageContextAttribute");

    out.print("Nombre: " + user.getName());
    out.print("<br/>");
    out.print("Edad: " + user.getAge());
    out.print("<br/>");
    out.print("Mail: " + user.getEmail());
    out.print("<br/>");
    out.print("Atributo:  " + pageContextAttribute);
%>
</body>
</html>

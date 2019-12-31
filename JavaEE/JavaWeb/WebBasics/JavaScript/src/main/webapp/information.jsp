<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
        <title>Información</title>
    </head>
    <BODY>
        Nombre: <%= request.getParameter("nombre") %> <BR>
        Edad: <%= request.getParameter("edad") %> <BR>
        Mail: <%= request.getParameter("mail") %> <BR>
    </BODY>
</html>

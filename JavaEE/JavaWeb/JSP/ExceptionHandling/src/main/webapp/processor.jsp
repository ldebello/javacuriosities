<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@ page errorPage="handler_page.jsp" %>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
    <title>Resultado</title>
</head>
<body>
<%
    /*
     * Nota de color: En Internet Explorer no visualizaremos la pagina de error
     * a no ser que el feature "Show Friendly Error Message(Mostrar mensajes de error HTTP descriptivos)" este desactivado,
     * otros browser no tienen necesidad de esto
     */
    String parameter1 = request.getParameter("number1");
    String parameter2 = request.getParameter("number2");

    int number1 = Integer.parseInt(parameter1);
    int number2 = Integer.parseInt(parameter2);
    int result = number1 / number2;
    out.print("El resultado es: " + result);
%>
</body>
</html>
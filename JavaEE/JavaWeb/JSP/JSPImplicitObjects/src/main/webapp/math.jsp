<!DOCTYPE html>
<!-- Indiciamos el JSP a cual se debe enviar en caso de error -->
<%@ page errorPage="error.jsp" %>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
    <title>Math</title>
</head>
<body>
<%
    String text1 = request.getParameter("text1");
    String text2 = request.getParameter("text2");

    int number1 = Integer.parseInt(text1);
    int number2 = Integer.parseInt(text2);
    int result = number1 / number2;
    out.print("El resultado es: " + result);
%>
</body>
</html>

<%-- Podemos definir un import simple o podemos separarlo por , para varias clases --%>
<%@page import="java.util.List"%>
<%@page import="java.util.Date, java.util.ArrayList"%>

<%-- Permite definir el contenido del metodo getServletInfo() --%>
<%@page info="Java Web - JavaCuriosities" %>

<%-- Definimos el tamaño del buffer--%>
<%@ page buffer="16kb" %>

<%-- Dentro podemos definir varios atributos --%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
    <head>
        <title>JSP Directive</title>
    </head>
    <body>
        <%@ include file="header.jsp" %>       
        <%
            Date currentDate = new Date();
            List<String> courses = new ArrayList<>();
        %>
        <h1>Directivas JSP</h1>
        <%@ include file="footer.jsp" %>
    </body>
</html>

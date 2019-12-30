<%-- 
    Document   : informacion.jsp
    Created on : 12/01/2014, 21:18:53
    Author     : ldebello
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<HTML>
    <HEAD>
        <META http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <TITLE>Informacion</TITLE>
    </HEAD>
    <BODY>
        Nombre: <%= request.getParameter("nombre") %> <BR>
        Edad: <%= request.getParameter("edad") %> <BR>
        Mail: <%= request.getParameter("mail") %> <BR>
    </BODY>
</HTML>

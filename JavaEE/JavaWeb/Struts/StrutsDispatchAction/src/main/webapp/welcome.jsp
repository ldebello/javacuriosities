<!DOCTYPE html>
<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<html:html lang="true">
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title><bean:message key="welcome.title"/></title>
        <html:base/>
    </head>
    <body style="background-color: white">

    <h2>Usuarios</h2>

    <html:link href="User.do?method=create">Crear Usuario</html:link>
    |
    <html:link href="User.do?method=delete">Borrar Usuario</html:link>
    |
    <html:link href="User.do?method=update">Actualizar Usuario</html:link>
    |
    <html:link href="User.do?method=block">Bloquear Usuario</html:link>

    </body>
</html:html>

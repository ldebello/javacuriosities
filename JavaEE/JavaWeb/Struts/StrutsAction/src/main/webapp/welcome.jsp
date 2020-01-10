<!DOCTYPE html>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
        <title>Bienvenido</title>
    </head>
    <body>
        <h1>Bienvenido <bean:write name="LoginForm" property="user"/></h1>
    </body>
</html>

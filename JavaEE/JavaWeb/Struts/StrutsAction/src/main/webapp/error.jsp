<!DOCTYPE html>
<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
    <title>Error</title>
</head>
<body>
<div style="color:red">
    <h1>Usuario Invalido: <bean:write name="LoginForm" property="user"/></h1>
</div>
</body>
</html>

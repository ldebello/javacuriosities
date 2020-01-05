<!DOCTYPE html>
<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
    <title>Struts</title>
</head>
<body>

<h1><bean:write name="strutsForm" property="message"/></h1>
<html:messages id="message" bundle="ExtraI18N"></html:messages>

<h1><bean:message key="label.welcome"/></h1>

<a href="data.do">Formulario Datos</a><br/>

<a href="createUser.do">Agregar Usuario</a><br/>
<a href="refreshUser.do">Refrescar Usuario</a><br/>
<a href="updateUser.do">Actualizar Usuario</a><br/>
<a href="deleteUser.do">Borrar Usuario</a><br/>

<a href="logic.do">Logic Ejemplos</a><br/>

<a href="handler.do">Exception Handler</a><br/>

<a href="language.do?method=english">Idiomas</a><br/>

<a href="redirect.do">Redirect</a><br/>
</body>
</html>
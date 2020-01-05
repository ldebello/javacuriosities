<%@page contentType="text/html" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
    <title>JSP Action Tags</title>
</head>
<body>
<h1>Perfil</h1>

<jsp:useBean id="person" class="ar.com.javacuriosities.action.tags.model.Person"/>

<jsp:setProperty name="person" property="*"/>

<i>Datos</i><br/>
Nombre:
<jsp:getProperty name="person" property="name"/>
<br/>
Apellido:
<jsp:getProperty name="person" property="lastName"/>
<br/>
</body>
</html>

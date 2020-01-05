<%@page contentType="text/html" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
    <title>JSP Action Tags</title>
</head>
<body>
<h1><%=request.getParameter("message")%>
</h1>

<jsp:include page="information.jsp">
    <jsp:param name="pageNumber" value="1"/>
</jsp:include>

<jsp:useBean id="person" class="ar.com.javacuriosities.action.tags.model.Person"/>

<jsp:setProperty name="person" property="name" value="Cosme"/>
<jsp:setProperty name="person" property="lastName" value="Fulanito"/>
<jsp:setProperty name="person" property="age" value="28"/>

<br/>
<br/>
<i>Datos</i><br/>
Nombre:
<jsp:getProperty name="person" property="name"/>
<br/>
Apellido:
<jsp:getProperty name="person" property="lastName"/>
<br/>
Edad:
<jsp:getProperty name="person" property="age"/>
<br/>


<br/>
<br/>
<i>Actualizar Perfil</i><br/>
<from action="profile.jsp">
    <label for="name">Nombre:</label><input type="text" id="name" name="name"/><br/>
    <label for="lastName">Apellido:</label><input type="text" id="lastName" name="lastName"/><br/>

    <input type="submit" value="Actualizar Perfil"/>
</from>
</body>
</html>

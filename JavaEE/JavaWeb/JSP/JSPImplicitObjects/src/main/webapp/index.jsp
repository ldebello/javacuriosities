<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
    <title>Implicit Objects</title>
</head>
<body>
<p>Out Object</p>
<h1>Out Implicit Object: <% out.print("La fecha es: " + new java.util.Date());%></h1>


<p>Request Object</p>
<form action="welcome.jsp">
    <label for="requestObjectName">Nombre:</label><input type="text" id="requestObjectName" name="name"/>
    <input type="hidden" name="session" value="false"/>
    <input type="submit" value="Entrar"/><br/>
</form>

<hr>

<p>Response Object</p>
<form action="redirect.jsp">
    <input type="submit" value="Google"><br/>
</form>

<HR>

<p>Config Object</p>
<form action="MyLogin">
    <label for="configObjectName">Nombre:</label><input type="text" id="configObjectName" name="name"/>
    <input type="submit" value="Login"/><br/>
</form>

<p>Application Object</p>
<form action="application.jsp">
    <input type="submit" value="Ver Application Name"/><br/>
</form>

<p>Session And Page Context Object</p>
<form action="welcome.jsp">
    <label for="sessionObjectName">Nombre:</label> <input type="text" id="sessionObjectName" name="name"/>
    <input type="hidden" name="session" value="true"/>
    <input type="submit" value="Entrar"><br/>
</form>

<p>Page Object</p>
<% out.print("El objeto page y this son los mismo: " + (page == this));%>

<p>Exception Object</p>
<form action="math.jsp">
    <label for="text1">Numero 1:</label><input type="text" id="text1" name="text1"/><br/>
    <label for="text2">Numero 2:</label><input type="text" id="text2" name="text2"/><br/>
    <input type="submit" value="Dividir"/>
</form>
</body>
</html>

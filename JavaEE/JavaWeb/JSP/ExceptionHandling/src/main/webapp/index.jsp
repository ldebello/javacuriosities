<%@page contentType="text/html" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
    <title>Exception Handler</title>
</head>
<body>
<form action="processor.jsp">
    <label for="number1">Numero 1:</label><input type="text" id="number1" name="number1"/><br/><br/>
    <label for="number2">Numero 2:</label><input type="text" id="number2" name="number2"/><br/><br/>
    <input type="submit" value="Dividir"/>
</form>

<br/>
<br/>
<form action="generate_error.jsp">
    <input type="submit" value="Enviar Error"/>
</form>

<br/>
<br/>
<form action="generate_exception.jsp">
    <input type="submit" value="Generar Exception"/>
</form>
</body>
</html>
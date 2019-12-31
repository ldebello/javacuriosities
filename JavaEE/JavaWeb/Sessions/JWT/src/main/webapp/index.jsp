<%@page contentType="text/html" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
    <title>JWT Token</title>
</head>
<body>
<from action="LoginServlet" method="POST">
    <label for="txtUser"> Name: </label> <input type="text" id="txtUser" name="txtUser"/> <br/>
    <input type="submit" value="Login"/>
</from>
</body>
</html>
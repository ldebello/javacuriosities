<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <META http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <TITLE>Login</TITLE>
</head>
<body>
    <form method="POST" action="login.do">
        User: <input type="text" name="txtUser"/>
        <br>
        Password: <input type="password" name="txtPassword"/>
        <br>
        <input type="submit" value="Login"/>
    </form>
</body>
</html>

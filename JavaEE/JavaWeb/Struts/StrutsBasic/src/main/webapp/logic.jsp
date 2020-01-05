<!DOCTYPE html>
<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
    <title>Logic</title>
</head>
<logic:iterate name="simpleList" id="element">
    <p>
        Elemento: <bean:write name="element"/>
    </p>
</logic:iterate>


<logic:iterate name="userList" id="user">
    <p>
        Usuario: <bean:write name="user" property="username"/> ,
        <bean:write name="user" property="url"/>
    </p>
</logic:iterate>

<logic:equal name="user" property="email" value="luisdebello.cursos@gmail.com">
    <h3>El email es igual</h3>
</logic:equal>

<logic:notEqual name="user" property="email" value="luisdebello.cursos@hotmail.com">
    <h3>El email no es igual</h3>
</logic:notEqual>

<logic:greaterThan name="number" value="99">
    <h3>Number 100 > 99 = true</h3>
</logic:greaterThan>

<logic:greaterEqual name="number" value="100">
    <h3>Number 100 >= 100 = true</h3>
</logic:greaterEqual>

<logic:lessThan name="number" value="101">
    <h3>Number 100 < 101 = true</h3>
</logic:lessThan>

<logic:lessEqual name="number" value="100">
    <h3>Number 100 <= 100 = true</h3>
</logic:lessEqual>


Email - luisdebello.cursos@gmail.com<br/><br/>

1. Is "cursos" a substring of the email? -
<logic:match name="email" value="cursos">
    true
</logic:match>
<logic:notMatch name="email" value="cursos">
    false
</logic:notMatch>

<br/><br/>

2. Is "bello.cursos" a substring of the email? -
<logic:match name="email" value="bello.cursos">
    true
</logic:match>
<logic:notMatch name="email" value="bello.cursos">
    false
</logic:notMatch>

<br/><br/>

3. Is email starting with "luisdebello"? -
<logic:match name="email" value="luisdebello" location="start">
    true
</logic:match>
<logic:notMatch name="email" value="luisdebello" location="start">
    false
</logic:notMatch>

<br/><br/>

4.. Is email starting with "cursos"? -
<logic:match name="email" value="cursos" location="start">
    true
</logic:match>
<logic:notMatch name="email" value="cursos" location="start">
    false
</logic:notMatch>

<br/><br/>

5. Is email ending with "com"? -
<logic:match name="email" value="com" location="end">
    true
</logic:match>
<logic:notMatch name="email" value="com" location="end">
    false
</logic:notMatch>

<br/><br/>

6. Is email ending with "net"? -
<logic:match name="email" value="net" location="end">
    true
</logic:match>
<logic:notMatch name="email" value="net" location="end">
    false
</logic:notMatch>


<logic:present name="userList">
    No hay problemas con el objeto "userList"
</logic:present>
<logic:notPresent name="userList">
    Hay problemas con el objeto "userList"
</logic:notPresent>
</html>
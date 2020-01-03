<%@page import="java.util.ArrayList"%>
<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
        <title>Expression Language(EL)</title>
    </head>
    <body>
        <%
            List<String> names = new ArrayList<>();
            names.add("Marcos");
            names.add("Juan");
            pageContext.setAttribute("names", names);
        %>

        <strong>EL con scope:</strong> ${requestScope.person}
        <br/><br/>
        <strong>EL sin scope:</strong> ${person}
        <br/><br/>
        <strong>EL usando el acceso []:</strong> ${applicationScope["Usuario.CookieApp"]}
        <br/><br/>
        <strong>EL encadenamiento:</strong> ${sessionScope.employee.address.street}
        <br/><br/>
        <strong>EL manejo lista:</strong> ${names[1]}
        <br/><br/>
        <strong>EL objeto implícito header:</strong> ${header["Accept-Encoding"]}
        <br/><br/>
        <strong>EL objeto implícito cookie:</strong> ${cookie["Usuario.MiCookie"].value}
        <br/><br/>
        <strong>EL pageContext:</strong> El método HTTP es ${pageContext.request.method}
        <br/><br/>
        <strong>EL Context param:</strong> ${initParam.Email}
        <br/><br/>
        <strong>Operador Aritmético EL:</strong> ${initParam.AppID + 200}
        <br/><br/>
        <strong>Operador Relacional EL:</strong> ${initParam.AppID < 200}
        <br/><br/>
        <strong>Operador Aritmético EL (Null Friendly):</strong> ${null + 11}
        <br/><br/>
        <strong>EL Operador(empty):</strong> ${empty names}
        <br/><br/>
        <strong>EL Operador(not):</strong> ${not empty names}
        <BR><BR>   
    </body>
</html>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %> 
<%@ taglib uri="http://java.sun.com/jsp/jstl/xml" prefix="x" %> 

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
        <title>XML Tags</title>
    </head>
    <body>
        <c:import url="database.xml" var="db" /> 
        <x:parse var="document" doc="${db}"/>
        
        <P>Detalle</P>
        <table border="1">
            
            <x:forEach var="current" select="$document/students/student">
                <TR><TD align="center">Nombre: </TD> <TD><x:out select="$current/name" /></TD></TR>
                <TR><TD align="center">Apellido: </TD> <TD><x:out select="$current/lastName" /></TD></TR>
                <TR><TD align="center">Edad: </TD> <TD><x:out select="$current/age" /></TD></TR>
            </x:forEach>
        </table>
    </body>
</html>

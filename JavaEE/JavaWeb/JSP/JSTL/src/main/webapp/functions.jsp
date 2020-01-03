<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Functions Tags</title>
</head>
<body>
<form action="functions.jsp" method="POST">
    <label for="name">Nombre:</label><input type="text" id="name" name="name"/>
    <input type="submit" value="Submit"/>
</form>

<c:if test="${fn:length(param.name) > 0}">
    <%@include file="fulldate.jsp" %>
</c:if>

<hr>

<c:set var="cadenaTemporal" value="Probando las funciones en JSTL"></c:set>
<c:set var="datos" value="${fn:split('Valor 1,Valor 2,Valor 3,Valor 4',',')}"/>

01- Obtener el size de una cadena, array o lista: ${fn:length(cadenaTemporal)}
<br>
02- Comprobar si el String contiene la cadena "probando": ${fn:contains(cadenaTemporal,"probando")}
<br>
03- Comprobar si el String contiene la cadena "probando" ignorando si son mayúsculas o
minúsculas: ${fn:containsIgnoreCase(cadenaTemporal, "probando")}
<br>
04- Convertir el String a mayúsculas: ${fn:toUpperCase(cadenaTemporal)}
<br>
05- Convertir el String a minúsculas: ${fn:toLowerCase(cadenaTemporal)}
<br>
<c:set var="split" value="${fn:split(cadenaTemporal,' ')}"/>
06- Dividir un String en un array de String indicando el caracter separador, por ejemplo el espacio: ${split}
<br>
Impresión del array anterior: <%= Arrays.toString((String[]) pageContext.getAttribute("split")) %>
<br>
<br>
07- Obtener la cantidad de Strings obtenidos después del split: ${fn:length(fn:split(cadenaTemporal,' '))}
<br>
08- Eliminar caracteres vacíos al final del String: ${fn:trim(cadenaTemporal)}
<br>
09- Comprobar si un String termina con el sufijo indicado: ${fn:endsWith(cadenaTemporal, "JSTL")}
<br>
10- Escapear los caracteres que podrían ser interpretados en formato XML: ${fn:escapeXml(cadenaTemporal)}
<br>
11- Devuelve el index donde aparece la primera ocurrencia del substring
especificado: ${fn:indexOf(cadenaTemporal, 'JSTL')}
<br>
12- Une todos los elementos de un array en un String con el separador provisto: ${fn:join(datos, '-')}
<br>
13- Devuelve el String resultado de remplazar en el String de entrada todas las ocurrencias del segundo String por el
tercero: ${fn:replace( cadenaTemporal, "en", "de")}
<br>
14- Comprueba si el String empieza por el segundo string especificado: ${fn:startsWith(cadenaTemporal, "comienzo")}
<br>
15- Devuelve un subconjunto del String: ${fn:substring(cadenaTemporal, 8, 20)}
<br>
16- Devuelve un subconjunto del string siguiente al String indicado: ${fn:substringAfter(cadenaTemporal, "funciones")}
<br>
17- Devuelve un subconjunto del string anterior al String indicado: ${fn:substringBefore(cadenaTemporal, "funciones")}
<%--



--%>
</body>
</html>
<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Format Tags</title>
</head>
<body>

<h2>Ejemplo FORMAT DATE</h2>
<%--
Para formatear una fecha se puede utilizar la propiedad 'pattern' del tag fmt:formatDate
Ejemplos de uso:

    Date and Time Pattern           Result
    ----------------------------------------------------------------
    "yyyy.MM.dd G 'at' HH:mm:ss z" 	2001.07.04 AD at 12:08:56 PDT
    "EEE, MMM d, ''yy"                  Wed, Jul 4, '01
    "h:mm a"                            12:08 PM
    "hh 'o''clock' a, zzzz"             12 o'clock PM, Pacific Daylight Time
    "K:mm a, z"                         0:08 PM, PDT
    "yyyyy.MMMMM.dd GGG hh:mm aaa" 	02001.July.04 AD 12:08 PM
    "EEE, d MMM yyyy HH:mm:ss Z" 	Wed, 4 Jul 2001 12:08:56 -0700
    "yyMMddHHmmssZ"                     010704120856-0700
    "yyyy-MM-dd'T'HH:mm:ss.SSSZ" 	2001-07-04T12:08:56.235-0700
---%>

<jsp:useBean id="today" class="java.util.Date"/>
Fecha: <fmt:formatDate value="${today}" dateStyle="full"/>
<br/>
<br/>
Fecha: <fmt:formatDate value="${today}" pattern="EEE, MMM d, yy"/>


</body>
</html>


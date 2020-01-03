<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page import="java.util.Locale" %>
<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
    <title>Format Tags(I18N)</title>
</head>
<body>
<h2>Ejemplo Internacionalización</h2>

<%
    String userLocale = request.getHeader("Accept-Language");
    out.println("Locale: " + userLocale);
    out.println("<br/>");
    Locale locale = request.getLocale();
    String userLanguage = locale.getDisplayLanguage();
    out.println("Lenguaje (Visual): " + userLanguage);
    out.println("<br/>");
    out.println("Lenguaje (ISO Code): " + locale.getLanguage());
    out.println("<br/>");
%>

<a href="?lang=en_US">Ingles</a>
<a href="?lang=es_AR">Español</a>
<a href="?lang=fr_FR">Frances</a>

<c:if test="${param['lang'] != null}">
    <fmt:setLocale value="${param['lang']}" scope="session"/>
</c:if>

<fmt:setBundle basename="Messages" scope="session"/>

<fmt:message key="LABEL.HELLO"/>
</body>
</html>


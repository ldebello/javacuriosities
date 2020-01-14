<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
    <title>CORE Tags</title>
</head>
<body>
<h2>Ejemplo SET & OUT & REMOVE</h2>
<c:set var="x" value="${1}"/>
<c:set var="y" value="${2}"/>
<c:set var="z" value="${x+y}"/>
<c:out value="X = ${x}"/><br/>
<c:out value="Y = ${y}"/><br/>
<c:remove var="x"/>
<c:out value="X (Despues del remove) = ${x}"/>br/>
<c:out value="Total (X + Y) = ${z}"/><br/>

<h2>Ejemplo FOREACH & CHOOSE</h2>
<c:forEach var="index" items="Lunes,Martes,Miercoles,Jueves,Viernes">
    # ${index}:
    <c:choose>
        <c:when test="${index == 'Lunes'}">
            Primer dia de la semana!<br/>
        </c:when>
        <c:when test="${index == 'Miercoles'}">
            Tercer dia de la semana!<br/>
        </c:when>
        <c:when test="${index =='Viernes'}">
            Quinto dia de la semana!<br/>
        </c:when>
        <c:otherwise>
            Dia desconocido?<br/>
        </c:otherwise>
    </c:choose>
</c:forEach>

<h2>Ejemplos FOREACH</h2>
<%-- Itera desde 10 al 20 saltando de a dos --%>
<c:forEach var="i" begin="10" end="20" step="2">
    <c:out value="${i}"/><br/>
</c:forEach>

<hr/>

<%-- Itera desde 10 al 20 saltando index y count--%>
<c:forEach var="i" begin="10" end="20" varStatus="status">
    indice: <c:out value="${status.index}"/>
    iteracion #: <c:out value="${status.count}"/> <br/>
</c:forEach>

<hr/>

<%-- Itera por los empleados de una empresa --%>
<jsp:useBean id="empresaBean" scope="request" class="ar.com.javacuriosities.jstl.CompanyBean"/>
<c:forEach var="e" items="${empresaBean.empleados}">
    <c:out value="${e.name}"/> | <c:out value="${e.lastName}"/><br/>
</c:forEach>

<h2>Ejemplo IF</h2>
<c:if test="${z == 3}">
    1- Resultado Correcto
</c:if>

<c:if test="${z == 4}">
    2- Resultado Incorrecto
</c:if>

<br/>
<h2>Ejemplo ELSE</h2>
<%-- Dado que no existe el ELSE en JSTL podemos usar el choose o un IF ternario --%>
<c:set var="numero" value="16"/>
<c:out value="${numero % 2 eq 0 ? 'Par' : 'Impar' }"/>

<br/>
<h2>Ejemplo Import</h2>
<c:import url="fulldate.jsp"/>
<hr/>

<h2>Ejemplo URL</h2>
<a href="/core_tags.jsp">URL relativa al server</a>
<br/>
<a href="core_tags.jsp">URL relativa a la pagina actual</a>
<br/>
<c:url value="/core_tags.jsp" var="url">
    <c:param name="txt" value="datos"/>
</c:url>
<br/>
<b>Rewritten URL:</b>
<c:out value="${url}"/>

<h2>Ejemplo CATCH</h2>
<c:catch var="iexcept">
    <% int x = 7 / 0;%>
</c:catch>
<c:if test="${iexcept!=null}">
    Exception:<c:out value="${iexcept}"/><br/><br/>
</c:if>
</body>
</html>

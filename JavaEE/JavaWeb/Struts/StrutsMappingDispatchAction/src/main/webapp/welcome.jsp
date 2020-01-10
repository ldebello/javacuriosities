<!DOCTYPE html>
<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<html:html lang="true">
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title><bean:message key="welcome.title"/></title>
        <html:base/>
    </head>
    <body style="background-color: white">

    <h4>HTML:LINK Tag</h4>
    <p>
        <html:link action="/xml"> Generar XML </html:link>
        |
        <html:link action="/excel"> Generar Excel </html:link>
    </p>
    <br/>
    <h4>HREF</h4>
    <p>
        <a href="xml.do"> Generar XML </a>
        |
        <a href="excel.do"> Generar Excel </a>
    </p>
    <br/>
    <h4>Wildcard</h4>
    <p>
        <a href="XmlData.do"> Generar XML </a>
        |
        <a href="ExcelData.do"> Generar Excel </a>
    </p>
    </body>
</html:html>

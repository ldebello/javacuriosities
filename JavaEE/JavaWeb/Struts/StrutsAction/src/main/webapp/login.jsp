<!DOCTYPE html>
<%@page contentType="text/html" %>
<%@page pageEncoding="UTF-8" %>
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
    <html:form action="/Login">
        Usuario:<html:text property="user"/> <br/>
        Password:<html:password property="password"/> <br/>
        <html:submit value="Login"/>
    </html:form>

    <div style="color:red">
        <html:errors/>
    </div>
    <hr>
    <html:errors property="user"/>
    </body>
</html:html>

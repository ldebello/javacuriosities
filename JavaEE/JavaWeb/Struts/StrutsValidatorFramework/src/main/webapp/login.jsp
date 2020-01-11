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

            <%--
                 Podemos agregar las mismas validaciones del lado cliente
                 usando JavaScript, ademas de esto debemos agregar al form

                 onsubmit="return validate<Valor del formName>(this)"

                 Ejemplo:
                 onsubmit="return validateLoginForm(this)"

                 <html:javascript formName="LoginForm"/>
             --%>
    </head>
    <body style="background-color: white">

    <h2>Validation Framework</h2>

    <html:form action="/registration">
        <bean:message key="label.user.name"/> :
        <html:text property="name" size="20"/>
        <br/>
        <bean:message key="label.user.password"/> :
        <html:text property="password" size="20"/>
        <br/>
        <bean:message key="label.user.passwordCheck"/> :
        <html:text property="passwordCheck" size="20"/>
        <br/>
        <bean:message key="label.user.email"/> :
        <html:text property="email" size="20"/>
        <br/><br/>
        <html:submit>
            <bean:message key="label.user.button.submit"/>
        </html:submit>

    </html:form>

    <p style="color:red;"><html:errors/></p>

    </body>
</html:html>

<!DOCTYPE html>
<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%-- Importa las tag libraries necesarias --%>
<%@taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<html>
<head>
    <title><bean:message bundle="ExtraI18N" key="data.title"></bean:message></title>
</head>
<body>
<h1></h1>
<hr/>

<%-- Etiqueta correspondiente a "<form>" transformada a Struts --%>
<html:form action="data">

    Por favor ingrese su nombre:

    <%-- Etiqueta correspondiente a "<input type=text>" transformada a Struts --%>
    <html:text property="name"/>

    <br/>
    Elija su nivel de acceso:

    <%-- Etiqueta correspondiente a "<select>" y "<option>" transformada a Struts --%>
    <html:select property="levelOfaccess">
        <html:option value="Alto"/>
        <html:option value="Medio"/>
    </html:select>

    <hr/>

    <html:hidden property="defaultOption" value="Select Item"/>

    <%-- Etiqueta correspondiente a "<input type=submit>" transformada a Struts --%>
    <html:submit value="Enviar"/>

    <html:reset>
        <bean:message key="label.form.reset"/>
    </html:reset>

    <hr/>

    <%-- Etiqueta propia de Struts para listar los errores de validacion (si los hay) --%>
    <html:errors/>

    <hr/>

    <html:messages id="message" property="name">
        <bean:write name="message"/>
    </html:messages>

    <hr/>
    <logic:messagesPresent>
        Hay errores en la pagina!!!
    </logic:messagesPresent>
    <logic:messagesNotPresent>
        No hay errores en la pagina!!! :)
    </logic:messagesNotPresent>

</html:form>
</body>
</html>
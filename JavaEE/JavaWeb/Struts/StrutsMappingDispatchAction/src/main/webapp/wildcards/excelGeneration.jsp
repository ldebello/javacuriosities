<!DOCTYPE html>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<%@taglib uri="http://struts.apache.org/tags-bean" prefix="bean"%>
<%@taglib uri="http://struts.apache.org/tags-logic" prefix="logic"%>
<html>
    <head>
    </head>
    <body>
        <h2>Generacion Excel</h2>

        <bean:write name="mensaje"/>
    </body>
</html>
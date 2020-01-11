<%@page contentType="text/html" pageEncoding="UTF-8"%>

<%@ taglib uri="http://struts.apache.org/tags-tiles" prefix="tiles" %>
<!DOCTYPE html>
<html>
    <head>
        <title></title>
    </head>
    <body>
        <tiles:insert attribute="header"/>

        <tiles:insert attribute="body"/> 

        <tiles:insert attribute="footer"/> 
    </body>
</html>
<!DOCTYPE html>
<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
    <title>Struts Level 2</title>
</head>
<body>
2-Hidden value : <bean:write name="dataForm" property="defaultOption"/>
</body>
</html>
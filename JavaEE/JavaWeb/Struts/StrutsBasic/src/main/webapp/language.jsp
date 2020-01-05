<!DOCTYPE html>
<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>

<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
</head>
<body>

Accept-Language: <%= request.getHeader("accept-language") %> <br/>
Locale: <%= request.getLocale().toString() %> <br/>
Language: <%= request.getLocale().getLanguage() %> <br/>
Country: <%= request.getLocale().getCountry()%> <br/>
Display-Name: <%= request.getLocale().getDisplayName()%> <br/><br/>

<html:link page="/language.do?method=english">English</html:link><br/>
<html:link page="/language.do?method=german">German</html:link><br/>
<html:link page="/language.do?method=france">France</html:link><br/>
<br/>
<bean:message key="label.common.hello"/>
</body>
</html>
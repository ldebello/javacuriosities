<%@page import="ar.com.javacurisioties.tags.model.Element" %>
<%@page import="ar.com.javacurisioties.tags.model.Person" %>
<%@page import="java.util.ArrayList" %>
<%@page import="java.util.List" %>
<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@taglib prefix="ex" uri="/WEB-INF/tlds/custom.tld" %>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
    <title>Custom Tag</title>
</head>
<body>
<%
    List<Element> people = new ArrayList<>();
    people.add(new Person(1L, "Pedro"));
    people.add(new Person(2L, "Marina"));
    people.add(new Person(3L, "Juan"));
%>

<ex:MyComboBox elements="<%= people %>">
    <H2>Titulo Intermedio</H2>
</ex:MyComboBox>


</body>
</html>

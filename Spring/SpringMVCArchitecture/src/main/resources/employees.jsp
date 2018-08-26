<%@page import="ar.com.javacuriosities.spring.app.model.Employee"%>
<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Employees</title>
</head>
<body>
    <h2>Employees:</h2>

    <%
        List<Employee> employees = (List<Employee>) request.getAttribute("employees");

        for (Employee employee : employees) {
            out.print("Name: " + employee.getName());
            out.print("<br>");
            out.print("Age: " + employee.getAge());
            out.print("<br>");
            out.print("<hr>");
        }
    %>
</body>
</html>

<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
    <title>Application</title>
</head>
<body>
<%
    // Aquí utilizamos el objeto implícito "application"
    String applicationName = application.getInitParameter("ParameterWebApp");
    out.print(applicationName);
%>
</body>
</html>

<!DOCTYPE html>
<!-- Si no indicamos que la pagina es de error no podemos usar el objeto implícito "exception" -->
<%@ page isErrorPage="true" %>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
    <title>Error</title>
</head>
<body>
<br/>
<b>Ocurrió el siguiente error:</b> <%= exception%><BR>

<br/>
<b>Excepción capturada:</b> <%=exception.getClass().getName()%><BR>
<b>Mensaje de error:</b> <%=exception.getMessage()%><BR>

<br/>
<b>Mayor detalle:</b>
<% printDetails(out, request);%>

<%!
    private void printDetails(JspWriter out, HttpServletRequest request) throws Exception {
        String[] atributos = {
                "javax.servlet.error.status_code",
                "javax.servlet.error.exception_type",
                "javax.servlet.error.message",
                "javax.servlet.error.exception",
                "javax.servlet.error.request_uri"
        };

        out.println("<table border=1>");

        for (String atributo : atributos) {
            out.println("<tr>");
            out.println("<td>");
            out.println(atributo);
            out.println("</td>");
            out.println("<td>");
            out.println(request.getAttribute(atributo));
            out.println("</td>");
            out.println("</tr>");
        }

        out.println("</table>");
    }
%>
</body>
</html>

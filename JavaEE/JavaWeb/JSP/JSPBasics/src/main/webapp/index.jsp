<%@page import="java.util.Date"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
        <title>Introducción JSP</title>
    </head>
    <body>
        <!-- Aquí usamos un tag de Scriplet -->
        <!-- En este scriptlet estamos usando el objeto out, el cual es un objeto implícito de JSP -->
        <h1>Fecha y hora dinámica: <% out.print(new Date()); %> </h1>
        <hr>
        
        <%!
            // Definimos un atributo que sirve como contador
            int counter = 0;
            
            // Definimos un atributo con la fecha en la cual es Servlet fue creado
            java.util.Date currentDate = new java.util.Date();
            
            
            // Definimos un método
            public java.util.Date getCurrentDate() {
                return currentDate;
            }
        %>
        
         <!-- Aquí estamos invocando un método que fue declarado mas arriba en el tag Declaration -->
        <H1>Fecha y hora estática: <% out.print(getCurrentDate()); %> </H1>
        <HR>
        
        <!-- Aquí usamos el tag expression el cual ejecuta un bloque de código Java y lo muestra en el resultado -->
        <H1>Contador: <%= counter+=1 %> </H1>
        <HR>
    </body>
</html>

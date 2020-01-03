<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
    <title>Welcome</title>
</head>
<body>
<%!
    public int calculateAge(java.util.Calendar dateOfBirth, java.util.Calendar currentDate) {
        int currentYear = currentDate.get(java.util.Calendar.YEAR);
        int yearOfBirth = dateOfBirth.get(java.util.Calendar.YEAR);
        int age = currentYear - yearOfBirth;
        int currentMonth = currentDate.get(java.util.Calendar.MONTH);
        int monthOfBirth = dateOfBirth.get(java.util.Calendar.MONTH);
        if (monthOfBirth > currentMonth) {
            age--;
        } else if (currentMonth == monthOfBirth) {
            int day1 = currentDate.get(java.util.Calendar.DAY_OF_MONTH);
            int day2 = dateOfBirth.get(java.util.Calendar.DAY_OF_MONTH);
            if (day2 > day1) {
                age--;
            }
        }

        return age;
    }
%>
<%
    // Aquí utilizamos el objeto implícito "request"
    String name = request.getParameter("name");

    String sessionValue = request.getParameter("session");

    if ("true".equals(sessionValue)) {
        // Aquí utilizamos el objeto implícito "out"
        out.print("Bienvenido " + name);

        ar.com.javacurisioties.jsp.model.UserData user = new ar.com.javacurisioties.jsp.model.UserData();

        java.util.Calendar dateOfBirth = java.util.Calendar.getInstance();
        dateOfBirth.set(1985, 4, 6);

        user.setName(name);
        user.setAge(calculateAge(dateOfBirth, java.util.Calendar.getInstance()));
        user.setEmail("luisdebello.cursos@gmail.com");

        // Usamos el objeto implícito "session" para agregar un atributo
        session.setAttribute("user", user);

        // Usamos el objeto implícito "pageContext" para agregar un atributo pero podemos definir el scope del del mismo
        // Podemos usar 4 scope posibles page, request, session, application el scope por default es page
        pageContext.setAttribute("pageContextAttribute", "Application Scope", PageContext.APPLICATION_SCOPE);

        out.print("<br/><a href = 'data.jsp'>Información de Usuario</a>");
    } else {
        // Aquí utilizamos el objeto implícito "out"
        out.print("Bienvenido " + name);
    }
%>
</body>
</html>

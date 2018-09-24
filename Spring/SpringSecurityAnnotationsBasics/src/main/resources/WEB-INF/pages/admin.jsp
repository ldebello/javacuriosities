<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Spring Security</title>

    <style type="text/css">
        #titleSection {
            color: red;
            font-size: 34px;
            margin: 36px 0px 15px 0px;
        }
        #messageSection {
            font-size: 24px;
            margin: 15px 0px 15px 0px;
        }

        #welcomeSection {
            color: green;
            font-size: 20px;
        }
        #welcomeText {
            text-transform: capitalize;
        }
    </style>
</head>
<body>
    <div align="center">
        <div id="titleSection">${title}</div>
        <div id="messageSection">${message}</div>

        <div id="welcomeSection">
            <span id="welcomeText">Welcome: ${pageContext.request.userPrincipal.name}</span>

            <form method="post" action="logout">
                <input type="submit" value="Logout">
                <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
            </form>
        </div>
    </div>
</body>
</html>
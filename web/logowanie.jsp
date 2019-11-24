<%--
  Created by IntelliJ IDEA.
  User: ppisk
  Date: 24.11.2019
  Time: 15:17
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>MainPage</title>
</head>
<body>

<form method="post" action="waliduj.jsp" onsubmit="code(this)">
    Podaj swój login: <br />
    <input type="text" name="name" /><br />
    Podaj swoje hasło: <br />
    <input type="text" name="password" /><br />

    <input type="submit" value="zaloguj">
</form>
<form method="post" action="rejestracja.jsp">
    nie masz konta? zarejestruj sie: <input type="submit" value="rejestracja">
</form>

</body>
</html>

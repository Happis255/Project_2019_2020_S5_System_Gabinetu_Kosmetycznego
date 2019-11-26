<%--
  Created by IntelliJ IDEA.
  User: ppisk
  Date: 25.11.2019
  Time: 16:30
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<form method="post" action="UserRegister" onsubmit="code(this)">
    <br /> <input type="text" name="username" placeholder="e_mail"/><br />
    <br /> <input type="password" name="password" placeholder="haslo"/><br />
    <br /> <input type="password" name="repeatPassword" placeholder="powtorz haslo"/><br />
    <input type="submit" value="zarejestruj">
</form>
</body>
</html>

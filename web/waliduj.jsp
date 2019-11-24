<%--
  Created by IntelliJ IDEA.
  User: ppisk
  Date: 24.11.2019
  Time: 14:11
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>

<body>
    <jsp:useBean id="user" class="myPage.User" scope="session"/>
    <jsp:setProperty property="*" name="user" />
    <jsp:useBean id="dataSource" class="myPage.DataSource" scope="session"/>

    Nazwa: <%= user.getName() %><br />

    <%
        out.println(user.getPassword());
        String result = "Dane niepoprawne";

        if(dataSource.checkPassword(user.getName(), user.getPassword())) {
            result = "Poprawny użytkownik oraz hasło";
        }
    %>

    <%= result %>

    <form method="post" action="index.jsp">
        <input type="submit" value="powrót">
    </form>
</body>

</html>

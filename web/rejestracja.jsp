<%--
  Created by IntelliJ IDEA.
  User: ppisk
  Date: 24.11.2019
  Time: 14:43
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Rejestracja</title>
</head>
<body>
    <jsp:useBean id="newUser" class="myPage.User" scope="request"/>
    <jsp:setProperty property="*" name="newUser" />
    <jsp:useBean id="dataSource" class="myPage.DataSource" scope="session"/>

    <%
        if(newUser.getName() != null) {
            if(dataSource.userExist(newUser.getName())){
                out.println("nick użytkownika już istnieje");
            }else{
                if(newUser.getPassword().compareTo(newUser.getPasswordRepeat()) == 0) {
                    dataSource.addUser(newUser.getName(), newUser.getPassword());
                    response.sendRedirect("index.jsp");
                }else{
                    out.println("hasła nie są takie same, spróbuj jeszcze raz:");
                }
            }
        }
    %>

    <form method="post" action="rejestracja.jsp">
        login: <br /> <input type="text" name="name" /><br />
        haslo: <br /> <input type="text" name="password" /><br />
        wprowadz haslo ponownie: <br /> <input type="text" name="passwordRepeat" /><br />
        <input type="submit" value="zarejestruj">
    </form>
</body>

</html>

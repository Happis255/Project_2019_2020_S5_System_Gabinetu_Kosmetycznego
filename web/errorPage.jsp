<%@ page import="myPage.data.others.ErrorMessage" %>
<%@ page import="myPage.others.HTMLFilter" %><%--
  Created by IntelliJ IDEA.
  User: ppisk
  Date: 30.11.2019
  Time: 17:19
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Error Occured</title>
</head>
<body>
    Wystąpił Bład <br>
    Skontaktuj się z Administratorem

    <%
        ErrorMessage errorMessage = (ErrorMessage)session.getAttribute("errorMessage");

        if(errorMessage != null){
            out.println(" podając następującą wiadomosć i kod:" + HTMLFilter.addBR());
            out.println("WIADOMOSC:" + HTMLFilter.addBR());
            out.println(errorMessage.getException().getMessage());
            out.println(HTMLFilter.addBR() + HTMLFilter.addBR());
            out.println("KOD:" + HTMLFilter.addBR());
            errorMessage.getException().printStackTrace();
            session.removeAttribute("errorMessage");
        }
    %>
    <br><br>
    <a href="index.jsp">POWRÓT DO STRONY GŁOWNEJ</a>
</body>
</html>

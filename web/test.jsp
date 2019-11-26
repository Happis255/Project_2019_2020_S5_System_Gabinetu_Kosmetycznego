<%--
  Created by IntelliJ IDEA.
  User: ppisk
  Date: 25.11.2019
  Time: 16:16
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import = "java.io.*,java.util.*" %>
<%@ page import="myPage.User" %>
<%@ page import="myPage.SessionData" %>
<html>
<head>
    <title>TestLogowania</title>
</head>
<body>
<%
    SessionData sData;

    sData = (SessionData)session.getAttribute("userData");
    if(sData == null)
        out.println("Nie jestes zalogowany");
    else
        out.println("Zalogowany jako: " + sData.getNick());
%>
<br/>
<a href="index.jsp"><input type="button" value="return"/></a>
</body>

</html>

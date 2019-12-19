<%--
  Created by IntelliJ IDEA.
  User: ppisk
  Date: 27.11.2019
  Time: 18:42
  To change this template use File | Settings | File Templates.
--%>

<%@ page import="myPage.basicObjects.User" %>
<%@ page import="myPage.data.dataBase.*" %>
<%@ page import="myPage.data.others.ErrorMessage" %>
<%@ page import="myPage.data.others.SessionData" %>
<%@ page import="myPage.data.others.TypKonta" %>
<%@ page import="myPage.exceptions.CannotInstanciateException" %>
<%@ page import="myPage.exceptions.NoResultsException" %>
<%@ page import="myPage.others.HTMLFilter" %>
<%@ page import="java.sql.SQLException" %>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.*" %>
<%@ page import="myPage.data.others.AccountPage" %>
<%
    response.setCharacterEncoding("UTF-8");
    request.setCharacterEncoding("UTF-8");
%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Konto</title>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/4.0.0/css/bootstrap.min.css">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/ionicons/2.0.1/css/ionicons.min.css">
    <link rel="stylesheet" href="../assets/css/styles.min.css?v=1.1">
    <script src="https://code.jquery.com/jquery-1.10.2.js"></script>
</head>
<body>
<%
    SessionData sessionData = (SessionData)session.getAttribute("userData");
    AccountPage accountPage = sessionData.getAccountPage();
    Object[] content = accountPage.getContent();
    System.out.println(accountPage.getNavBar());
    System.out.println(accountPage.getFooter());
    for(Object o : content)
        System.out.println((String)o);
%>

<!--Nav bar-->
<div id="navbar-placeholder"></div>
<script>
    $("#navbar-placeholder").load("<%out.print(accountPage.getNavBar());%>");
</script>
<!--end of Nav bar-->

<!--content-->
<script>
    function placeContent(where, what){
        $(where).load(what);
    }
</script>
<%
    String strPlaceholder = "content-placeholder";
    for(int i = 0; i < content.length; ++i){
        out.print("<div id=\"" + strPlaceholder + i + "\"></div>");
%> <script> placeContent("#<%out.print(strPlaceholder + i);%>", "<%out.print((String)content[i]);%>"); </script> <%
    }
%>
<!--end of content-->

<!--Footer bar-->
<div id="footer-placeholder"></div>
<script>
    $("#footer-placeholder").load("<%out.print(accountPage.getFooter());%>");
</script>
<!--end of Footer bar-->

<script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/4.0.0/js/bootstrap.bundle.min.js"></script>

</body>
</html>

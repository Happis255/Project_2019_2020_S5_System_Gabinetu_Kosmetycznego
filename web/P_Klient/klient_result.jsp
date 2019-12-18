<%@ page import="myPage.data.others.SessionData" %><%--
  Created by IntelliJ IDEA.
  User: Huber
  Date: 25.11.2019
  Time: 15:43
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>

<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Gabinet Gracja</title>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/4.0.0/css/bootstrap.min.css">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/ionicons/2.0.1/css/ionicons.min.css">
    <link rel="stylesheet" href="../assets/css/styles.min.css?v=1.1">
    <script src="https://code.jquery.com/jquery-1.10.2.js"></script>
</head>

<body>
<% SessionData sessionData = (SessionData)session.getAttribute("userData"); %>

<!--Nav bar-->
<div id="navbar-placeholder"></div>
<script> $("#navbar-placeholder").load("../P_Klient/navbar_klient.jsp"); </script>
<!--end of Nav bar-->

<div id="content-placeholder"></div>
<script>
    <% switch(sessionData.getPage()){
        case "powiadomienia": %>
    <% break; case "wizty": %>
    <% break; case "sklep": %>
    <% break; case "twojebonusy": %>
    <% break; case "konto": %>
        $("#content-placeholder").load("../P_Klient/account_klient.jsp");
    <% break; default: %>
    <% break; } %>
</script>

<!--Footer bar-->
<div id="footer-placeholder"></div>
<script> $("#footer-placeholder").load("../P_Klient/footer_klient.jsp"); </script>
<!--end of Footer bar-->

<script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/4.0.0/js/bootstrap.bundle.min.js"></script>
</body>

</html>
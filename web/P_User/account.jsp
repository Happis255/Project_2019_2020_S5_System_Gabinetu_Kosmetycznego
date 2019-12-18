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
<% SessionData sessionData = (SessionData)session.getAttribute("userData"); %>

<!--Nav bar-->
<div id="navbar-placeholder"></div>

<script>
    function loadNavbar(navbarName){
        $("#navbar-placeholder").load(navbarName);
    };

    <% if(sessionData.getAccoutType() == TypKonta.KLIENT){ %>
        loadNavbar("../P_Klient/navbar_klient.jsp");
    <% }else if(sessionData.getAccoutType() == TypKonta.PRACOWNIK){ %>
        loadNavbar("../P_Pracownik/navbar_pracownik.jsp");
    <% }else if(sessionData.getAccoutType() == TypKonta.ADMINISTRATOR){ %>
        loadNavbar("../P_Administrator/navbar_administrator.jsp");
    <% } %>
</script>
<!--end of Nav bar-->

<!--content-->
<div id="content-placeholder"></div>

<script>
    function loadContent(contentName){
        $("#content-placeholder").load(contentName);
    };

    <% if(sessionData.getAccoutType() == TypKonta.KLIENT){ %>
    loadContent("../P_Klient/content_klient.jsp");
    <% }else if(sessionData.getAccoutType() == TypKonta.PRACOWNIK){ %>

    <% }else if(sessionData.getAccoutType() == TypKonta.ADMINISTRATOR){ %>

    <% } %>
</script>
<!--end of content-->

<!--Footer bar-->
<div id="footer-placeholder"></div>

<script>
    function loadFooter(footerName){
        $("#footer-placeholder").load(footerName);
    };

    <% if(sessionData.getAccoutType() == TypKonta.KLIENT){ %>
        loadFooter("../P_Klient/footer_klient.jsp");
    <% }else if(sessionData.getAccoutType() == TypKonta.PRACOWNIK){ %>
        loadFooter("../P_Pracownik/footer_pracownik.jsp");
    <% }else if(sessionData.getAccoutType() == TypKonta.ADMINISTRATOR){ %>
        loadFooter("../P_Administrator/footer_administrator.jsp");
    <% } %>
</script>
<!--end of Footer bar-->

<script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/4.0.0/js/bootstrap.bundle.min.js"></script>

</body>
</html>

<%@ page import="myPage.data.others.SessionData" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="myPage.data.others.VisitPage" %>
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
    VisitPage visitPage = (VisitPage)session.getAttribute("VisitPage");
    Object[] content = visitPage.getContent();
    System.out.println("VISITS_MANAGER:");
    for(Object o : content)
        System.out.println((String)o);
%>

<!--Nav bar-->
<div id="navbar-placeholderVisits"></div>
<script>
    $("#navbar-placeholderVisits").load("<%out.print(visitPage.getNavBar());%>");
</script>
<!--end of Nav bar-->

<!--content-->
<section id="wizyty_klient" class="bg-light-gray" style="margin:0;background-color:rgba(0,0,0,0.11);color:#ffffff;padding-bottom:20px;padding-top:20px;max-width:1140px;margin-right:auto;margin-left:auto;border-radius:20px;margin-bottom:30px;">
    <h2 class="text-center" style="height:79px;">Wizyty</h2>
</section>

<script>
    function placeContent(where, what){
        $(where).load(what);
    }
</script>
<%
    String strPlaceholderVisits = "content-placeholder-visits";
    for(int i = 0; i < content.length; ++i){
        out.print("<div id=\"" + strPlaceholderVisits + i + "\"></div>");
%> <script> placeContent("#<%out.print(strPlaceholderVisits + i);%>", "<%out.print((String)content[i]);%>"); </script> <%
    }
%>
<!--end of content-->

<!--Footer bar-->
<div id="footer-placeholderVisits"></div>
<script>
    $("#footer-placeholderVisits").load("<%out.print(visitPage.getFooter());%>");
</script>
<!--end of Footer bar-->

<script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/4.0.0/js/bootstrap.bundle.min.js"></script>

</body>
</html>
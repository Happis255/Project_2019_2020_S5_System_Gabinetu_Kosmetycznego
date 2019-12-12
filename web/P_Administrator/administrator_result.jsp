<%--
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
    <link rel="stylesheet" href="assets/css/styles.min.css?v=1.1">
    <script src="https://code.jquery.com/jquery-1.10.2.js"></script>
</head>

<body>

<div id="navbar-placeholder">

</div>

<nav class="navbar navbar-dark navbar-expand-md" style="background-color:#2d2d2d;color:rgb(255,255,255);font-weight:100;text-transform:uppercase;margin:0;margin-bottom:30px;">
    <div class="container"><a class="navbar-brand" href="#"><img src="assets/img/LOGO Administrator.png" style="margin:-46px;"></a><button class="navbar-toggler" data-toggle="collapse" data-target="#navcol-1"><span class="sr-only">Toggle navigation</span></button>
        <div class="collapse navbar-collapse"
             id="navcol-1">
            <ul class="nav navbar-nav ml-auto" style="max-width:641px;">
                <li class="nav-item" role="presentation"><a class="nav-link" href="#" style="color:#ffffff;">Powiadomienia</a></li>
                <li class="nav-item" role="presentation"><a class="nav-link" href="#" style="color:#ffffff;">Pracownicy</a></li>
                <li class="nav-item" role="presentation"><a class="nav-link" href="#" style="color:#ffffff;">Wydarzenia</a></li>
                <li class="nav-item" role="presentation"><a class="nav-link" href="#" style="color:#ffffff;">Wizyty</a></li>
                <li class="nav-item" role="presentation"><a class="nav-link" href="#" style="color:#ffffff;">promocje</a></li>
                <li class="nav-item" role="presentation"><a class="nav-link" href="#" style="color:#ffffff;">aktualności</a></li>
                <li class="nav-item" role="presentation"><a class="nav-link" href="#" style="color:#ffffff;margin-left:27px;">usługi</a></li>
                <li class="nav-item" role="presentation"><a class="nav-link" href="#" style="color:#ffffff;">produkty</a></li>
                <li class="nav-item" role="presentation"><a class="nav-link" href="#" style="color:#ffffff;">raporty</a></li>
                <li class="nav-item" role="presentation"><a class="nav-link" href="#" style="color:#ffffff;">sprzęt</a></li>
                <li class="nav-item" role="presentation"><a class="nav-link" href="#" style="color:#ffffff;">odpady</a></li>
                <li class="nav-item" role="presentation"><a class="nav-link" href="#" style="color:#ffffff;">sklep online</a></li>
                <li class="nav-item" role="presentation"><a class="nav-link" href="UserLogin" style="color:#ffffff;">wyloguj</a></li>
            </ul>
        </div>
    </div>
</nav>

<section id="kontakt" class="bg-light-gray" style="margin:0;background-color:rgba(0,0,0,0.11);color:#ffffff;padding-bottom:20px;padding-top:20px;max-width:800px;margin-right:auto;margin-left:auto;border-radius:20px;margin-bottom:219px;margin-top:157px;">

    <div>${requestScope.message}</div>

</section>

<!--Footer bar-->
<div id="footer-placeholder">

</div>

<script>
    $(function(){
        $("#footer-placeholder").load("P_Pracownik/footer_pracownik.jsp");
    });
</script>
<!--end of Footer bar-->

<script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/4.0.0/js/bootstrap.bundle.min.js"></script>
</body>

</html>
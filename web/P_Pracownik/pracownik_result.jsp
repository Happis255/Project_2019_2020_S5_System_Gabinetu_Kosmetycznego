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

<nav class="navbar navbar-dark navbar-expand-md" style="background-color:#2d2d2d;color:rgb(255,255,255);font-weight:100;text-transform:uppercase;margin:0;margin-bottom:30px;min-height: 66px">
    <div class="container"><a class="navbar-brand" href="#"><img src="assets/img/LOGO.png" style="margin:-63px;"></a><button class="navbar-toggler" data-toggle="collapse" data-target="#navcol-1"><span class="sr-only">Toggle navigation</span></button>
        <div class="collapse navbar-collapse"
             id="navcol-1">
            <ul class="nav navbar-nav ml-auto" style="max-width:895px;">
                <li class="nav-item" role="presentation"><a class="nav-link" href="#" style="color:#ffffff;">Powiadomienia</a></li>
                <li class="nav-item" role="presentation"><a class="nav-link" href="#" style="color:#ffffff;">Konto</a></li>
                <li class="nav-item" role="presentation"><a class="nav-link" href="#" style="color:#ffffff;">Wydarzenia</a></li>
                <li class="nav-item" role="presentation"><a class="nav-link" href="P_Pracownik/news_menager.jsp" style="color:#ffffff;">aktualności</a></li>
                <li class="nav-item" role="presentation"><a class="nav-link" href="#" style="color:#ffffff;">wizyty</a></li>
                <li class="nav-item" role="presentation"><a class="nav-link" href="#" style="color:#ffffff;">produkty</a></li>
                <li class="nav-item" role="presentation"><a class="nav-link" href="#" style="color:#ffffff;">raporty</a></li>
                <li class="nav-item" role="presentation"><a class="nav-link" href="#" style="color:#ffffff;">sklep online</a></li>
                <li class="nav-item" role="presentation"><a class="nav-link" href="UserLogin" style="color:#ffffff;">wyloguj</a></li>
            </ul>
        </div>
    </div>
</nav>

<section id="kontakt" class="bg-light-gray" style="margin:0;background-color:rgba(0,0,0,0.11);color:#ffffff;padding-bottom:20px;padding-top:20px;max-width:800px;margin-right:auto;margin-left:auto;border-radius:20px;margin-bottom:219px;margin-top:157px;">

    <div>${requestScope.message}</div>

</section>

<div class="footer-clean" style="color:rgb(0,0,0);">
    <footer>
        <div class="container">
            <div class="row justify-content-center">
                <div class="col-sm-4 col-md-3 item">
                    <h3>Przejdź do</h3>
                    <ul>
                        <li><a href="#">Panel główny</a></li>
                        <li><a href="#">Powiadomienia</a></li>
                        <li><a href="#">Konto</a></li>
                        <li><a href="#">Wizyty</a></li>
                    </ul>
                </div>
                <div class="col-sm-4 col-md-3 item">
                    <h3>Przejdź do</h3>
                    <ul>
                        <li><a href="#">Wydarzenia</a></li>
                        <li><a href="P_Pracownik/news_manager.jsp">Aktualności</a></li>
                        <li><a href="#">Produkty</a></li>
                        <li><a href="#">Raporty</a></li>
                    </ul>
                </div>
                <div class="col-sm-4 col-md-3 item">
                    <h3>Gabinet Kosmetyczny "Gracja"</h3>
                    <ul>
                        <li>Al. Jana Pawla II 13a/2</li>
                        <li>37-450 Stalowa Wola</li>
                        <li>tel.: (15) 842 94 19</li>
                        <li>10.00-18.00 pn-pt<br></li>
                    </ul>
                </div>
                <div class="col-lg-3 item social"><a href="https://www.facebook.com/Gracja-Gabinet-Kosmetyczny-255745234516172/"><i class="icon ion-social-facebook"></i></a><a href="https://www.instagram.com/gabinet.gracja/"><i class="icon ion-social-instagram-outline"></i></a>
                    <a href="mailto:info@gabinetgracja.com.pl"><i class="icon ion-email"></i></a><a href="https://www.messenger.com/t/255745234516172"><i class="icon ion-android-mail"></i></a>
                    <p class="copyright" style="width:281px;">Gabinet Kosmetyczny "Gracja" Anna Wasik</p>
                </div>
            </div>
        </div>
    </footer>
</div>
<script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/4.0.0/js/bootstrap.bundle.min.js"></script>
</body>

</html>
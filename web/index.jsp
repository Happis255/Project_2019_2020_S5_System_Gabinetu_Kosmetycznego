<%--
  Created by IntelliJ IDEA.
  User: ppisk
  Date: 24.11.2019
  Time: 14:09
  To change this template use File | Settings | File Templates.
--%>
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
    <title>Gabinet Gracja</title>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/4.0.0/css/bootstrap.min.css">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/ionicons/2.0.1/css/ionicons.min.css">
    <link rel="stylesheet" href="assets/css/styles.min.css?v=1.1">
    <script src="https://code.jquery.com/jquery-1.10.2.js"></script>
</head>

<body>

<div id="navbar-placeholder">

</div>

<script>
    $(function(){
        $("#navbar-placeholder").load("navbar_index.jsp");
    });
</script>

<section style="margin-bottom:30px;">
    <div id="promo-section" style="height:290px;margin-top:-42px;margin-bottom:-40px;"><img src="assets/img/Gracja LOGO.png" style="width:160px;margin-bottom:-306px;margin-top:-53px;margin-left:-991px;">
        <div class="jumbotron" style="background-color:rgba(0,0,0,0.11);color:#ffffff;padding-bottom:20px;padding-top:20px;max-width:800px;margin-right:auto;margin-left:auto;">
            <h1 style="color:rgb(255,255,255);height:37px;">Gabinet Kosmetyczny "Gracja"</h1>
            <p style="min-height:0px;">Gabinet "Gracja" w ciągu 15 lat zdobył zaufanie wielu stałych klientek. Nasza kadra wciąż podnosi swoje kwalifikacje co w połączeniu z wieloletnim doświadczeniem pozwala na profesjonalne wykonywanie wszystkich naszych zabiegów.</p>
        </div>
    </div>
</section>
<section id="team" class="bg-light-gray" style="height:400px;max-height:341px;background-color:rgba(0,0,0,0.11);color:#ffffff;padding-bottom:0;padding-top:20px;margin-top: 35px;;max-width:800px;margin-right:auto;margin-left:auto;border-radius:20px;margin-bottom:30px;">
    <h2 class="section-heading col-xl-4" style="color:rgb(255,255,255);font-size: 30px;margin-right:auto;margin-left:auto;text-align:center;">Nasz zespół</h2>
    <div class="container">
        <div class="row">
            <div class="col-sm-4">
                <div class="team-member"><img class="rounded-circle img-fluid" src="assets/img/Aleksandra_Krzys_Profil.jpg">
                    <h4 style="font-weight: 100;">Aleksandra Krzyś</h4>
                </div>
            </div>
            <div class="col-sm-4">
                <div class="team-member"><img class="rounded-circle img-fluid" src="assets/img/Anna%20Wąsik.jpg">
                    <h4 style="font-weight: 100;">Anna Wąsik</h4>
                </div>
            </div>
            <div class="col-sm-4">
                <div class="team-member"><img class="rounded-circle img-fluid" src="assets/img/Joanna%20Lewioda.jpg">
                    <h4 style="font-weight: 100;">Joanna Lewioda</h4>
                </div>
            </div>
        </div>
    </div>
</section>

<section id="uslugi" class="bg-light-gray" style="height:400px;max-height:341px;margin-top:-128px;background-color:rgba(0,0,0,0.11);color:#ffffff;padding-bottom:20px;padding-top:20px;margin-top:0;max-width:800px;margin-right:auto;margin-left:auto;border-radius:20px;margin-bottom:30px;">
    <h2 class="section-heading" style="color:rgb(255,255,255);margin-right:auto;font-size: 30px;margin-left:auto;text-align:center;margin-bottom:28px;">Najczęściej wybierane usługi</h2>
    <div class="container">
        <div class="row">
            <div class="col-sm-4">
                <div class="uslugi"><img class="img-thumbnail img-fluid" src="assets/img/u1.jpg" id="usluga">
                    <h4 style="font-weight: 100;font-size: 20px;">Kosmetyka&nbsp;dłoni i ciała<br></h4>
                </div>
            </div>
            <div class="col-sm-4">
                <div class="uslugi"><img class="img-thumbnail img-fluid" src="assets/img/u2.jpg" id="usluga">
                    <h4 style="font-weight: 100;font-size: 20px;">Karboksyterapia</h4>
                </div>
            </div>
            <div class="col-sm-4">
                <div class="uslugi"><img class="img-thumbnail img-fluid" src="assets/img/u3.jpg" id="usluga">
                    <h4 style="font-weight: 100;font-size: 20px;">Makijaże okolicznościowe<br></h4>
                </div>
            </div>
        </div>
    </div>
</section>
<a id="kontakt_gracja"></a>
<section id="kontakt" class="bg-light-gray" style="margin:0;background-color:rgba(0,0,0,0.11);color:#ffffff;padding-bottom:20px;padding-top:20px;max-width:800px;margin-right:auto;margin-left:auto;border-radius:20px;margin-bottom:30px;">
    <form form action="SendMailAttachServlet_N" method="post" enctype="multipart/form-data">
        <h2 class="text-center" style="height:53px;">Skontaktuj się z nami</h2>
        <div class="form-group"><input class="form-control" type="text" name="imie" required="" placeholder="Imię i nazwisko" style="margin:0;width:500px;margin-left:145px;"></div>
        <div class="form-group"><input class="form-control" type="email" name="email" required="" placeholder="Twój e-mail" style="margin:0;width:500px;margin-left:145px;"></div>
        <div class="form-group"><textarea class="form-control" rows="6" name="tresc" required="" placeholder="Wiadomość" minlength="10" style="margin:0;width:500px;margin-left:145px;"></textarea></div>
        <div class="form-group"><button class="btn btn-primary" type="submit" style="margin:0;width:265px;margin-left:267px;">Wyślij</button></div>
    </form>
</section>

<!--Footer bar-->
<div id="footer-placeholder">

</div>

<script>
    $(function(){
        $("#footer-placeholder").load("P_Klient/footer_klient.jsp");
    });
</script>
<!--end of Footer bar-->

<script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/4.0.0/js/bootstrap.bundle.min.js"></script>

</body>
</html>
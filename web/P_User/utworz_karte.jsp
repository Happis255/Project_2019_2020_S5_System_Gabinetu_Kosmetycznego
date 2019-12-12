<%--
  Created by IntelliJ IDEA.
  User: ppisk
  Date: 25.11.2019
  Time: 16:30
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
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

<%
    response.setCharacterEncoding("UTF-8");
    request.setCharacterEncoding("UTF-8");
%>

<!--Nabar bar-->
<div id="navbar-placeholder">

</div>

<script>
    $(function(){
        $("#navbar-placeholder").load("navbar_user.jsp");
    });
</script>
<!--end of navbar bar-->

<script language='javascript' type='text/javascript'>
    function checkPasswordMatch() {
        var a = $("#haslo").val();
        var b = $("#powtorz-haslo").val();

        if (a === b)
            $("#divCheckPasswordMatch").html("Wpisane hasła są poprawne.");
        else
            $("#divCheckPasswordMatch").html("Wpisane hasła się nie zgadzają!");
    }

    $(document).ready(function () {
        $("#haslo, #powtorz-haslo").keyup(checkPasswordMatch);
    });

</script>

<section id="kartaklienta" class="bg-light-gray" style="margin:0;background-color:rgba(0,0,0,0.11);color:#ffffff;padding-bottom:20px;padding-top:20px;max-width:800px;margin-right:auto;margin-left:auto;border-radius:20px;margin-bottom:30px;">
    <form method="post" action="${pageContext.request.contextPath}/UserCardMaker" onsubmit="code(this)">
        <h2 class="text-center" style="height:79px;">Tworzenie karty informacyjnej</h2>
        <h5 class="text-center" style="height:21px;margin-right:50px;margin-left:50px;">Po wypełnieniu i zatwierdzeniu danych utworzone zostanie konto.</h5>
        <h6 class="text-center" id="RODO" style="height:44px;margin-right:50px;margin-left:50px;">Zapoznaj się również z &nbsp;podstawowymi informacjami o przetwarzaniu&nbsp;<br>Twoich danych osobowych -&nbsp;<a href="rodo_info.jsp">(przeczytaj)</a><br></h6>
        <div class="form-group" style="margin-bottom:-2px;"><label style="font-size:17px;margin-left:101px;">Czy ma Pan/-i rozrusznik serca?</label><select class="form-control" name="p1" value="p1" required="" id="p1" style="margin:0;margin-left:145px;width:500px;margin-bottom:16px;"><option value="false">Nie</option><option value="true">Tak</option></select></div>
        <div class="form-group" style="margin-bottom:-2px;"><label style="font-size:17px;margin-left:101px;">Czy cierpi Pan/-i na hemofilię?</label><select class="form-control" name="p2" value="p2" required="" id="p2" style="margin:0;margin-left:145px;width:500px;margin-bottom:16px;"><option value="false">Nie</option><option value="true">Tak</option></select></div>
        <div class="form-group" style="margin-bottom:-2px;"><label style="font-size:17px;margin-left:101px;">Czy cierpi Pan/-i na łuszczycę?</label><select class="form-control" name="p3" value="p3" required="" id="p3" style="margin:0;margin-left:145px;width:500px;margin-bottom:16px;"><option value="false">Nie</option><option value="true">Tak</option></select></div>
        <div class="form-group" style="margin-bottom:-2px;"><label style="font-size:17px;margin-left:101px;">Czy jest Pan/-i alergikiem?</label><select class="form-control" name="p4" value="p4" required="" id="p4" style="margin:0;margin-left:145px;width:500px;margin-bottom:16px;"><option value="false">Nie</option><option value="true">Tak</option></select></div>
        <div class="form-group" style="margin-bottom:-2px;"><label style="font-size:17px;margin-left:101px;">Czy posiada Pan/-i skłonność do przebarwień / blizn?</label><select class="form-control" name="p5" value="p5" required="" id="p5" style="margin:0;margin-left:145px;width:500px;margin-bottom:16px;"><option value="false">Nie</option><option value="true">Tak</option></select></div>
        <div class="form-group" style="margin-bottom:-2px;"><label style="font-size:17px;margin-left:101px;">Czy cierpi Pan/-i na jakąś chorobę zakaźną?</label><select class="form-control" name="p6" value="p6" required="" id="p6" style="margin:0;margin-left:145px;width:500px;margin-bottom:16px;"><option value="false">Nie</option><option value="true">Tak</option></select></div>
        <div class="form-group" style="margin-bottom:-2px;"><label style="font-size:17px;margin-left:101px;">Czy cierpi Pan/-i na zaburzenia ukrwienia?</label><select class="form-control" name="p7" value="p7" required="" id="p7" style="margin:0;margin-left:145px;width:500px;margin-bottom:16px;"><option value="false">Nie</option><option value="true">Tak</option></select></div>
        <div class="form-group" style="margin-bottom:-2px;"><label style="font-size:17px;margin-left:101px;">Czy cierpi Pan/-i na opryszczkę, gorączkę, osłabienie?</label><select class="form-control" name="p8" value="p8" required="" id="p8" style="margin:0;margin-left:145px;width:500px;margin-bottom:16px;"><option value="false">Nie</option><option value="true">Tak</option></select></div>
        <div class="form-group" style="margin-bottom:-2px;"><label style="font-size:17px;margin-left:101px;">Czy cierpi Pani w ciąży?</label><select class="form-control" name="p9" value="p9" required="" id="p9" style="margin:0;margin-left:145px;width:500px;margin-bottom:16px;"><option value="false">Nie</option><option value="true">Tak</option></select></div>
        <div class="form-group"><label style="font-size:17px;margin-left:101px;">Jak oceniasz stan swojej skóry?</label><input maxlength="255" class="form-control" type="text" name="ocena-skory" required="" placeholder="Opis w kilku słowach" id="ocena-skory"
                                                                                                                                  style="margin:0;width:500px;margin-left:145px;"></div>
        <div class="form-group"><label style="font-size:17px;margin-left:101px;">Jakiego typu jest twoja skóra?</label><input maxlength="255" class="form-control" type="text" name="typ-skory" required="" placeholder="Np.: Normalna / Sucha / Tłusta / Mieszana"
                                                                                                                              id="typ-skory" style="margin:0;width:500px;margin-left:145px;"></div>
        <div class="form-group"><label style="font-size:17px;margin-left:101px;">Czy twoja skóra jest wrażliwa? W jakich miejscach &nbsp;najbardziej?</label><input maxlength="255" class="form-control" type="text" name="wrazliwosc" required="" placeholder="Opis w kilku słowach"
                                                                                                                                                                    id="wrazliwosc" style="margin:0;width:500px;margin-left:145px;"></div>
        <div class="form-group"><label style="font-size:17px;margin-left:101px;">Czy masz jakieś uwagi wobec swojej skóry?</label><input maxlength="5000" class="form-control" type="text" name="inne" placeholder="Pole opcjonalne" id="inne" style="margin:0;width:500px;margin-left:145px;"></div>
        <div class="form-group">
            <div id="registrationFormAlert" class="registrationFormAlert"></div>
        </div>
        <div class="form-group"><button class="btn btn-primary" type="submit" style="margin:0;width:265px;background-color:#2d2d2d;margin-left:267px;">Utwórz konto</button></div>
    </form>
</section>

<!--Footer bar-->
<div id="footer-placeholder">

</div>

<script>
    $(function(){
        $("#footer-placeholder").load("footer_user.jsp");
    });
</script>
<!--end of Footer bar-->

<script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/4.0.0/js/bootstrap.bundle.min.js"></script>

</body>
</html>

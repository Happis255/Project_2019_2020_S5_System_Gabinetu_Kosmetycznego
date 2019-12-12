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

<section id="rejestracja" class="bg-light-gray" style="margin:0;background-color:rgba(0,0,0,0.11);color:#ffffff;padding-bottom:20px;padding-top:20px;max-width:800px;margin-right:auto;margin-left:auto;border-radius:20px;margin-bottom:30px;">
<form method="post" action="${pageContext.request.contextPath}/UserRegister" onsubmit="code(this)">
    <h2 class="text-center" style="height:79px;">Rejestracja użytkownika</h2>
    <h5 class="text-center" style="height:21px;margin-right:50px;margin-left:50px;">Aby założyć konto, wypełnij poniższe pola.<br><br></h5>
    <h6 class="text-center" id="RODO" style="height:44px;margin-right:50px;margin-left:50px;">Zapoznaj się również z &nbsp;podstawowymi informacjami o przetwarzaniu&nbsp;<br>Twoich danych osobowych -&nbsp;<a href="rodo_info.jsp">(przeczytaj)</a><br></h6>
    <div class="form-group"><label style="font-size:17px;margin-left:101px;">Twoje dane</label><input class="form-control" type="text" name="imie" required="" placeholder="Imię" maxlength="255" minlength="2" style="margin:0;width:500px;margin-left:145px;"><input class="form-control" type="text" name="nazwisko" required="" placeholder="Nazwisko" maxlength="255" minlength="2" style="margin:0;width:500px;margin-left:145px;margin-top:15px;"><input class="form-control" type="date" name="data-urodzenia" required="" style="margin:0;width:500px;margin-left:145px;margin-top:15px;" requiredplaceholder="Data urodzenia"></div>
    <div class="form-group"><label style="font-size:17px;margin-left:101px;">Dane kontaktowe</label><input class="form-control" type="text" name="e-mail" required="" placeholder="E-Main" maxlength="255" style="margin:0;width:500px;margin-left:145px;"></div>
    <div class="form-group"><input class="form-control" type="tel" name="telefon" placeholder="Numer telefonu" maxlength="10" minlength="6" style="width:500px;margin:0;margin-left:145px;"></div>
    <div class="form-group"><label style="font-size:17px;margin-left:101px;">Adres zamieszkania</label><input class="form-control" type="text" name="ulica" required="" placeholder="Ulica" maxlength="255" style="margin:0;width:500px;margin-left:145px;"></div>
    <div class="form-group"><input class="form-control" type="text" name="kod" required="" placeholder="Kod pocztowy" style="margin:0;width:500px;margin-left:145px;"></div>
    <div class="form-group"><input class="form-control" type="text" name="miejscowosc" required="" placeholder="Miejscowość" maxlength="255" style="margin:0;width:500px;margin-left:145px;"></div>
    <div class="form-group"><label style="font-size:17px;margin-left:101px;">Hasło dla twojego konta</label>
        <input class="form-control" required="" type="password" name="haslo" placeholder="Hasło" style="width:500px;margin:0;margin-left:145px;margin-bottom:15px;">
        <input onChange="checkPasswordMatch(this);" required="" class="form-control" type="password" name="powtorz-haslo" placeholder="Powtórz hasło" style="width:500px;margin:0;margin-left:145px;margin-bottom:15px;">
        <div class="registrationFormAlert" id="divCheckPasswordMatch" style="height:82px;margin:0px;margin-right:50px;margin-left:50px;margin-bottom:-38px;font-weight: 300;text-align: center!important;">
            Oba hasła muszą być identyczne.
        </div>
    </div>
    <div class="form-group"><a href="utworz_karte.jsp"><button class="btn btn-primary" type="submit" style="margin:0;width:265px;margin-left:267px;">Zatwierdź i przejdź dalej</button></a></div>
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

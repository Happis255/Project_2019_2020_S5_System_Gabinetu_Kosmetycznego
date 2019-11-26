<%--
  Created by IntelliJ IDEA.
  User: Huber
  Date: 25.11.2019
  Time: 15:39
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html; charset=UTF-8" language="java" %>
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
<!--Nabar bar-->
<div id="navbar-placeholder">

</div>

<script>
    $(function(){
        $("#navbar-placeholder").load("navbar.jsp");
    });
</script>
<!--end of navbar bar-->

<section id="kontakt" class="bg-light-gray" style="margin:0;background-color:rgba(0,0,0,0.11);color:#ffffff;padding-bottom:20px;padding-top:20px;max-width:800px;margin-right:auto;margin-left:auto;border-radius:20px;margin-bottom:30px;">
    <form form action="SendMailAttachServlet" method="post" enctype="multipart/form-data">
        <h2 class="text-center" style="height:53px;">Wyślij swoje CV</h2>
        <h5 class="text-center" style="height:99px;margin-right:50px;margin-left:50px;"><br>Wypełnij krótki formularz poniżej, załącz swoje CV.<br>Twoje dane osobowe nie będą przetrzymywane w naszej bazie. <br><br></h5>
        <div class="form-group"><label style="font-size:17px;margin-left:101px;">Imię i nazwisko</label><input class="form-control" type="text" name="imie-nazwisko" required="" placeholder="Imię i nazwisko" style="margin:0;width:500px;margin-left:145px;"></div>
        <div class="form-group"><label style="font-size:17px;margin-left:101px;">Data urodzenia</label><input class="form-control" type="date" name="data-urodzenia" style="margin:0;width:500px;margin-left:145px;" requiredplaceholder="Data urodzenia"></div>
        <div class="form-group"><label style="font-size:17px;margin-left:101px;">Dane kontaktowe</label><input class="form-control" type="text" name="e-mail" required="" placeholder="E-Main" style="margin:0;width:500px;margin-left:145px;"></div>
        <div class="form-group"><input class="form-control" type="text" name="telefon" required="" placeholder="Telefon kontaktowy" style="margin:0;width:500px;margin-left:145px;"></div>
        <div class="form-group"><label style="font-size:17px;margin-left:101px;">Adres zamieszkania</label><input class="form-control" type="text" name="ulica" required="" placeholder="Ulica" style="margin:0;width:500px;margin-left:145px;"></div>
        <div class="form-group"><input class="form-control" type="text" name="Numer-domu" required="" placeholder="Numer domu / Numer mieszkania" style="margin:0;width:500px;margin-left:145px;"></div>
        <div class="form-group"><input class="form-control" type="text" name="miejscowosc" required="" placeholder="Miejscowość" style="margin:0;width:500px;margin-left:145px;"></div>
        <div class="form-group"><input class="form-control" type="text" name="kod-pocztowy" required="" placeholder="Kod pocztowy" style="margin:0;width:500px;margin-left:145px;"></div>
        <div class="form-group"><label style="font-size:17px;margin-left:101px;">Twoje CV</label></div>
        <div class="form-group"><input type="file" name="file" id="fileToUpload" style="margin:0px;margin-left:146px;" accept="application/pdf, application/msword, application/rtf, text/rtf"></div>
        <div class="form-group"><button class="btn btn-primary" name="submit" type="submit" style="margin:0;width:265px;margin-left:267px;">Wyślij</button></div>
    </form>
</section>

<!--Footer bar-->
<div id="footer-placeholder">

</div>

<script>
    $(function(){
        $("#footer-placeholder").load("footer.jsp");
    });
</script>
<!--end of Footer bar-->
<script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/4.0.0/js/bootstrap.bundle.min.js"></script>
</body>
</html>
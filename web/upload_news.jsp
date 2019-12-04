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

<section id="upload_news" class="bg-light-gray" style="margin:0;background-color:rgba(0,0,0,0.11);color:#ffffff;padding-bottom:20px;padding-top:20px;max-width:800px;margin-right:auto;margin-left:auto;border-radius:20px;margin-bottom:30px;">
    <form action="UploadNewsWithJPG" method="post" enctype="multipart/form-data">
        <h2 class="text-center" style="height:79px;">Dodaj aktualność</h2>
        <h5 class="text-center" style="height:21px;margin-right:50px;margin-left:50px;">Wprowadź tytuł aktualności, opis oraz dodaj grafikę.</h5>
        <h6 class="text-center" id="Informacja_Upload" style="font-weight:300;height:44px;margin-right:50px;margin-left:50px;">Uploadowana grafika nie powinna przekraczać rozmiaru 50 MB<br>
            oraz posiadać format JPEG, JPG bądź JPE.</h6>
        <div class="form-group">
            <input class="form-control" type="text" name="tytul" required="" placeholder="Tytuł aktualności" maxlength="255" minlength="10" style="margin:0;width:500px;margin-left:145px;">
            <input class="form-control" type="text" name="opis" required="" placeholder="Opis aktualności" maxlength="5000" minlength="10" style="margin:0;width:500px;margin-left:145px;margin-top:15px;"><label style="font-size:17px;margin-left:101px;margin-top:17px;">Data od</label>
            <input class="form-control" type="date" name="data-od" required="" style="margin:0;width:500px;margin-left:145px;margin-top:-7px;"><label style="font-size:17px;margin-left:101px;margin-top:17px;">Data do</label>
            <input class="form-control" type="date" name="data-do" required="" style="margin:0;margin-bottom:13px;width:500px;margin-left:145px;margin-top:-7px;">
            <div class="form-group"><input type="file" name="file" id="fileToUpload" required="" style="margin-top:12px;margin:0px;margin-left:146px;" accept="image/jpeg"></div>
            <div class="form-group"><button class="btn btn-primary" type="submit" style="margin:0;width:265px;background-color:#2d2d2d;margin-left:267px;">Wyślij</button></div>
        </div>
    </form>
</section>

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
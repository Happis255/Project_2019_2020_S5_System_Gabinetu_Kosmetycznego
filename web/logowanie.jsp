<%--
  Created by IntelliJ IDEA.
  User: Huber
  Date: 25.11.2019
  Time: 15:39
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
    <link rel="stylesheet" href="assets/css/styles.min.css">
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

    <section id="logowanie" class="bg-light-gray" style="margin:0;background-color:rgba(0,0,0,0.11);color:#ffffff;padding-bottom:20px;padding-top:20px;max-width:800px;margin-right:auto;margin-left:auto;border-radius:20px;margin-bottom:155px;margin-top:60px;">
        <form method="post" action="waliduj.jsp" onsubmit="code(this)">
            <h2 class="text-center" style="height:53px;">Zaloguj się do systemu</h2>
            <div class="form-group"><label style="font-size:17px;margin-left:123px;">E-Mail</label><input class="form-control" type="email" name="username" required="" placeholder="E-Mail" style="width:500px;margin:0px;margin-left:145px;"><label style="font-size:17px;margin-left:123px;margin-top:16px;">Hasło</label>
                <input class="form-control" type="password" name="password" placeholder="Hasło" required="" style="margin:0;margin-left:145px;width:500px;">
                <button class="btn btn-primary" type="submit" style="margin:0;width:265px;margin-left:267px;margin-top:50px;">Zaloguj się</button>
            </div>
        </form>
        <div>
        <form method="post" action="rejestracja.jsp">
            <button class="btn btn-primary" type="submit" style="margin:0;width:265px;margin-left:267px;margin-top:9px;">Zarejestruj się</button>
        </form>
         <form method="post" action="index.jsp">
             <button class="btn btn-primary" type="submit" style="margin:0;width:265px;margin-left:267px;margin-top:9px;">Przywróć hasło</button>
         </form>
        </div>
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
<%@ page import="myPage.dataSourceDB.DataSource" %>
<%@ page import="myPage.data.others.SessionData" %>
<%@ page import="myPage.exceptions.DBReadWriteException" %>
<%@ page import="java.sql.SQLException" %>
<%@ page import="myPage.data.dataBase.Aktualnosci" %>

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

<section id="aktualnosci" class="bg-light-gray" style="margin:0;background-color:rgba(0,0,0,0.11);color:#ffffff;padding-bottom:20px;padding-top:20px;max-width:800px;margin-right:auto;margin-left:auto;border-radius:20px;margin-bottom:30px;">
    <h2 class="text-center" style="height:44px;">Aktualności</h2>
    <h5 class="text-center" style="height:auto;margin-right:50px;margin-left:50px;font-weight:200;margin-bottom:31px;">W poniższej sekcji znajdziesz wszystkie&nbsp;<br>aktualne informacje dotyczące naszego gabinetu!</h5>
    <hr id="linia" style="color: white;border: solid 2px;border-radius: 100px;width:635px;margin-top:auto;margin-bottom:29px;">
    <section class="news" style="text-align: center!important; ">

        <!--newsy generowane na bieżąco-->

        <%
            Aktualnosci aktualnosci = new Aktualnosci();
            DataSource dataSource = new DataSource();

            try {
                aktualnosci = dataSource.getAktualnosci();
            } catch (DBReadWriteException e) {
                e.printStackTrace();
            } catch (SQLException e) {
                e.printStackTrace();
            }

            while (!aktualnosci.getStos_tresci().empty()){

                out.println("<div class=\"col-sm-6 col-md-4 col-lg-9 item\" style=\"margin-right:auto;margin-left:auto;\">" +
                        "<h3 class=\"tytul\" style=\"margin-bottom:24px;\">" +
                        aktualnosci.getTytul() +
                        "</h3><img class=\"img-fluid\" src=\"assets/aktualnosci_grafika/");

                /* Sprawdzamy, czy aktualnosc ma swoją grafikę na serwerze - jeśli nie, wyświetli defualt */
                out.println(aktualnosci.getFileName());

                out.println("\" style=\"border: solid 3px;border-radius: 6px;width:auto;height:auto;\">");

                out.println("<p class=\"opis-newsa\"><br>" +
                        aktualnosci.getTresc() +
                        "</div>\n" +
                        " <hr id=\"linia\" style=\"color: white;border: solid 2px;border-radius: 100px;width:635px;margin-top:auto;margin-bottom:29px;\">" );
            }
        %>

        <!--newsy generowane na bieżąco <div class="d-flex">
            <div class="col-sm-6 col-md-4 col-lg-9 item" style="margin-right:auto;margin-left:auto;">
                <h3 class="tytul" style="margin-bottom:24px;">Konkurs w Gabinecie Gracja!</h3><a href="#"><img class="img-fluid" src="assets/img/1.jpg" style="width:auto;height:auto;"></a>
                <p class="opis-newsa"><br>Wygraną jest zaproszenie na makijaż andrzejkowy o wartości 100PLN&nbsp;<br>dla Ciebie bądź dla bliskiej Ci osoby&nbsp;✨🤩🤩💄<br><br>💜Konkurs trwa do piątku do godziny 18:00!<br>💜Kontaktujemy się z wygraną osobą w wiadomości
                    prywatnej,&nbsp;<br>godziny realizacji dostosowujemy do Ciebie!<br><br>Nie zapomnij oznaczyć znajomego w komentarzu na Facebooku!<br><br></p><a href="#" class="action"></a></div>
        </div>
        <hr id="linia" style="color: white;border: solid 2px;border-radius: 100px;width:635px;margin-top:auto;margin-bottom:29px;">

        -->


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
<%@ page import="myPage.exceptions.DBReadWriteException" %>
<%@ page import="java.sql.SQLException" %>
<%@ page import="myPage.data.dataBase.UslugaData" %>
<%@ page import="myPage.basicObjects.Usluga" %>

<!DOCTYPE html>
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

<!--Nabar bar-->
<div id="navbar-placeholder">

</div>

<script>
    $(function(){
        $("#navbar-placeholder").load("navbar_user.jsp");
    });
</script>
<!--end of navbar bar-->

        <!--newsy generowane na bieżąco-->
        <%
            Usluga usluga = new Usluga();
            UslugaData temp;
            try {
                usluga.getEverything();
            } catch (DBReadWriteException | SQLException e) {
                e.printStackTrace();
            }

            if (usluga.uslugiEmpty() == true) {
                out.println(
                        "<section id=\"uslugi\" class=\"bg-light-gray\" style=\"margin:0;background-color:rgba(0,0,0,0.11);color:#ffffff;padding-bottom:20px;padding-top:20px;max-width:800px;margin-right:auto;margin-left:auto;border-radius:20px;margin-bottom:314px;\">" +
                        "<h2 class=\"text-center\" style=\"height:44px;\">Cennik usług</h2>" +
                        "<h5 class=\"text-center\" style=\"height:auto;margin-right:50px;margin-left:50px;font-weight:200;margin-bottom:31px;\">W poniższej sekcji znajdziesz wszystkie&nbsp;<br>usługi wykonywane w naszym gabinecie!</h5>" +
                        "<hr id=\"linia\" style=\"color: white;border: solid 2px;border-radius: 100px;width:635px;margin-top:auto;margin-bottom:29px;\">" +
                        "<section class=\"news\" style=\"text-align: center!important; \">" +
                        "<div class=\"col-sm-6 col-md-4 col-lg-9 item\" style=\"margin-right:auto;margin-left:auto;\">" +
                        "<h3 class=\"tytul\" style=\"margin-bottom:24px;\">" +
                        "W chwili obecnej trwają prace konserwacyjne." + "<p class=\"opis-newsa\"><br>" +
                        "</div>\n");

            } else

                out.println(    "<section id=\"aktualnosci\" class=\"bg-light-gray\" style=\"margin:0;background-color:rgba(0,0,0,0.11);color:#ffffff;padding-bottom:20px;padding-top:20px;max-width:800px;margin-right:auto;margin-left:auto;border-radius:20px;margin-bottom:30px;\">" +
                                "<h2 class=\"text-center\" style=\"height:44px;\">Cennik usług</h2>" +
                        "<h5 class=\"text-center\" style=\"height:auto;margin-right:50px;margin-left:50px;font-weight:200;margin-bottom:31px;\">W poniższej sekcji znajdziesz wszystkie&nbsp;<br>usługi wykonywane w naszym gabinecie!</h5>" +
                        "<section class=\"news\" style=\"text-align: center!important; \">");
                String ostatni_typ = " ";
                while (!usluga.uslugiEmpty()){
                temp = usluga.uslugaPop();
                String typ_uslugi = temp.getTyp_uslugi();

                if (!typ_uslugi.equals(ostatni_typ)) {
                    /* Wczytano nowy typ uslugi, tworzmy tytul + srednik */
                    ostatni_typ = typ_uslugi;
                    out.println("<hr id=\"linia\" style=\"color: white;border: solid 2px;border-radius: 100px;width:635px;margin-top:auto;margin-bottom:29px;\">");
                    out.println("<div class=\"col-sm-6 col-md-4 col-lg-9 item\" style=\"margin-right:auto;margin-left:auto;\">" +
                            "<h3 class=\"tytul\" style=\"margin-bottom:24px;\">" +
                            temp.getTyp_uslugi() + "</h3>");
                }

                out.println("<p class=\"opis-newsa\">- " + temp.getNazwa() + ", " + temp.getOpis() + " - " + temp.getCena() + "PLN, " + temp.getCzas_trwania() + " min.</div>" );
            }
        %>
    </section>
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
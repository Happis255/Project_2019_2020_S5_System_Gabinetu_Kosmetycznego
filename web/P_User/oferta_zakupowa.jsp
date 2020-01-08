<%@ page import="java.sql.SQLException" %>
<%@ page import="myPage.basicObjects.Produkt" %>
<%@ page import="myPage.data.dataBase.ProduktSprzedazowyData" %>
<%@ page import="myPage.basicObjects.Promocje" %>
<%@ page import="myPage.data.dataBase.PromocjaData" %>

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

<div>
        <!--newsy generowane na bieżąco-->
        <%
            Produkt produkty = new Produkt();
            ProduktSprzedazowyData temp;

            Promocje promocje = new Promocje();
            PromocjaData temp2;

            try {
                produkty.get_all_sellingProducts();
            } catch (SQLException e) {
                e.printStackTrace();
            }

            if (produkty.produkt_sprzedazowyListaEmpty()) {
                out.println(
                        "<section id=\"aktualnosci\" class=\"bg-light-gray\" style=\"margin:0;background-color:rgba(0,0,0,0.11);color:#ffffff;padding-bottom:20px;padding-top:20px;max-width:800px;margin-right:auto;margin-left:auto;border-radius:20px;\">" +
                        "<h2 class=\"text-center\" style=\"height:44px;\">Oferta sprzedażowa</h2>" +
                        "<h5 class=\"text-center\" style=\"height:auto;margin-right:50px;margin-left:50px;font-weight:200;margin-bottom:31px;\">W poniższej sekcji znajdziesz wszystkie&nbsp;<br>aktualne produkty możliwe do nabycia w naszym gabinecie!</h5>" +
                        "<hr id=\"linia\" style=\"color: white;border: solid 2px;border-radius: 100px;width:635px;margin-top:auto;margin-bottom:29px;\">" +
                        "<section class=\"news\" style=\"text-align: center!important; \">" +
                        "<div class=\"col-sm-6 col-md-4 col-lg-9 item\" style=\"margin-right:auto;margin-left:auto;\">" +
                        "<h3 class=\"tytul\" style=\"margin-bottom:24px;\">" +
                        "Niestety, w chwili nie jest prowadzona sprzedaż produktów.<br>Wróć do nas później." + "<p class=\"opis-newsa\"><br>" +
                        "</div></section></section></div>\n");

            } else {

                out.println(
                        "<div>\n" +
"    <!--newsy generowane na bieżąco-->\n" +
"    <section id=\"aktualnosci\" class=\"bg-light-gray\" style=\"margin:0;background-color:rgba(0,0,0,0.11);color:#ffffff;padding-bottom:20px;padding-top:20px;max-width:800px;margin-right:auto;margin-left:auto;border-radius:20px;\">\n" +
"        <h2 class=\"text-center\" style=\"height:44px;\">Oferta sprzedażowa</h2>\n" +
"        <h5 class=\"text-center\" style=\"height:auto;margin-right:50px;margin-left:50px;font-weight:200;margin-bottom:31px;\">W poniższej sekcji znajdziesz wszystkie&nbsp;<br>aktualne produkty możliwe do nabycia w naszym gabinecie!</h5>\n" +
"        <hr id=\"linia\" style=\"color: white;border: solid 2px;border-radius: 100px;width:635px;margin-top:25px;margin-bottom:25px;\">\n" +
"        <section class=\"news\" style=\"text-align: center!important; \">");

                while (!produkty.produkt_sprzedazowyListaEmpty()) {
                    temp = produkty.produkt_sprzedazowyListaPop();

                    /* Sprawdzamy, czy produkt posiada obowiązującą promocję */
                    try {
                        if (promocje.checkPromoToday(temp.getId_promocji())) {
                            temp2 = promocje.get_promo_ID(temp.getId_promocji());

                            /* Produkt posiada promocję */
                            out.println("" +

                                "<div class=\"d-flex\">\n" +
                                "<div class=\"col-sm-6 col-md-4 col-lg-9 item\" style=\"margin-right:auto;margin-left:auto;padding:0px;\">\n" +
                                "<h1 style=\"font-size:20px;margin-bottom:32px;\">" +
                                 "" + temp2.getOpis() + ", kupując ten produkt <br> oszczędzisz ");

                            if (temp2.getZnizka_kwotowa() > 0 && temp2.getZnizka_proc() > 0)
                                out.println(temp2.getZnizka_kwotowa() + " PLN oraz " + temp2.getZnizka_proc() + " % kwoty produktu!");
                            else if (temp2.getZnizka_proc() > 0 && temp2.getZnizka_kwotowa() <= 0)
                                out.println(temp2.getZnizka_proc() + " % kwoty produktu!");
                            else
                                out.println(temp2.getZnizka_kwotowa() + " PLN!");

                            out.print("<br></h1></h1>\n" +
"                               <div><img class=\"img-fluid\" src=\"../assets/produkty_grafika/" +
                                temp.getFileName() + "\" style=\"width:175px;height:auto;float:left;margin-bottom:auto;margin-right:43px;\"></div>\n" +
"                               <h1 style=\"font-size:20px;margin-top:42px;margin-bottom:16px;\">" +
                                temp.getNazwa() + "<br></h1>\n" +
"                               <h1 style=\"font-size:15px;margin-top:4px;margin-bottom:25px;font-weight:100;\">Cena: " +
                                temp.getCena() + " PLN, liczba dostępnych sztuk: " +
                                temp.getIlosc() + "<br></h1>\n" +
"                               <h1 style=\"font-size:15px;margin-top:4px;font-weight:100;\">" +
                                temp.getOpis() + "</div></div><hr id=\"linia\" style=\"width:635px;margin-top:25px;margin-bottom:29px;\">"
                            );

                        } else {

                            /* Produkt nie posiada promocji */
                            out.println(
                                    "<div class=\"d-flex\">\n" +
"                                   <div class=\"col-sm-6 col-md-4 col-lg-9 item\" style=\"margin-right:auto;margin-left:auto;padding:0px;\">\n" +
"                                   <div><img class=\"img-fluid\" src=\"../assets/produkty_grafika/" +
                                    temp.getFileName() + "\" style=\"width:175px;height:auto;float:left;margin-bottom:auto;margin-right:43px;\"></div>\n" +
"                                   <h1 style=\"font-size:20px;margin-top:11px;margin-bottom:16px;\">" +
                                    temp.getNazwa() + "<br></h1>\n" +
"                                   <h1 style=\"font-size:15px;margin-top:4px;margin-bottom:25px;font-weight:100;\">Cena: " +
                                    temp.getCena() + " PLN, liczba dostępnych sztuk: " +
                                    temp.getIlosc() + "<br></h1>\n" +
"                                   <h1 style=\"font-size:15px;margin-top:4px;font-weight:100;\">" +
                                    temp.getOpis() + "</h1>\n" +
                                    "                </div>\n" +
                                    "            </div>" +
                                    "<hr id=\"linia\" style=\"width:635px;margin-top:25px;margin-bottom:29px;\">"
                            );
                        }
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                }
                out.print("</section></section></div>");
            }
        %>

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

</html>
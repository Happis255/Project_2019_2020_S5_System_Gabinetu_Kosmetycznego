<%@ page import="myPage.basicObjects.Klient" %>
<%@ page import="myPage.data.dataBase.KlientData" %>
<%@ page import="myPage.data.others.ErrorMessage" %>
<%@ page import="myPage.data.others.SessionData" %>
<%@ page import="myPage.exceptions.NoResultsException" %>
<%@ page import="java.sql.SQLException" %>
<%@ page import="myPage.data.dataBase.KartaKlientaData" %>
<%@ page import="myPage.basicObjects.KartaKlienta" %>
<%@ page import="java.text.DateFormat" %>
<%@ page import="java.text.SimpleDateFormat" %>
<%--
  Created by IntelliJ IDEA.
  User: ppisk
  Date: 18.12.2019
  Time: 16:22
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<% SessionData sessionData = (SessionData)session.getAttribute("userData"); %>

<section id="baza_pracownikow" class="bg-light-gray" style="margin:0;background-color:rgba(0,0,0,0.11);color:#ffffff;padding-bottom:20px;padding-top:20px;max-width:1140px;margin-right:auto;margin-left:auto;border-radius:20px;margin-bottom:30px;">
    <h2 class="text-center" style="height:79px;">Witamy w systemie Gabinetu Kosmetycznego Gracja!</h2>
    <h5 class="text-center" style="height:21px;margin-right:50px;margin-left:50px;">Poniżej zamieszczone są informacje dotycząca twoich danych konta.</h5>
    <h6 class="text-center" id="informacja" style="height:44px;margin-right:50px;margin-left:50px;font-weight: 100;">W celu przejścia do danej funkcji,<br>wybierz odpowiednią opcję z listy powyżej.</h6>
</section>
<form action="${pageContext.request.contextPath}/ControllerClientRemover" method="post">
<%
    /* Pobieramy informacje o kliencie zalogowanym w systemie */
    Klient klient_w_systemie = new Klient(sessionData.getId());
    KlientData klient = null;

    KartaKlienta kartaKlienta = new KartaKlienta();
    KartaKlientaData klient_kartaKlienta;

    String status = null;

    try {
        klient = klient_w_systemie.getData();
        status = klient_w_systemie.getAccountStatusName(klient.getId_statusu());

        /* Odczytujemy karte klienta zalogowanego w systemie */
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        klient_kartaKlienta = kartaKlienta.KartaKlientaData_get(klient.getId());

        /* Generujemy stronę klientowi */
        out.print("    <section id=\"pracownik\" class=\"bg-light-gray\" style=\"margin:0;background-color:rgba(0,0,0,0.11);color:#ffffff;padding-bottom:20px;padding-top:20px;max-width:1140px;margin-right:auto;margin-left:auto;border-radius:20px;margin-bottom:10px;\">\n" +
                "        <h2 class=\"text-center\">Witaj " + klient.getImie() + " " + klient.getNazwisko() + "!</h2>\n" +
                "        <br>\n" +
                "        <table class=\"table\" cellspacing=\"0\" width=\"100%\" style=\"margin-top: -18px;text-align: center;margin-bottom: 0;width: 55%;/* max-width: 100%; */margin-left: 16%;background-color: transparent;border-collapse: collapse;min-width: 760px;\"\">\n" +
                "            <tr>\n" +
                "                <td><h5 class=\"text-center\" style=\"margin-bottom: 1px;\">Dane osobowe</h5></td>\n" +
                "                <td><h5 class=\"text-center\" style=\"margin-bottom: 1px;\">Dane karty klienta</h5></td>\n" +
                "            </tr>\n" +
                "            <tr>\n" +
                "                <td style=\" text-align: center; vertical-align: middle;\">\n" +
                "                    <h6 class=\"text-center\" style=\"margin-right:50px;margin-left:50px;font-weight: 100;\">Ulica: " + klient.getUlica() + "</h6>\n" +
                "                    <h6 class=\"text-center\" style=\"margin-right:50px;margin-left:50px;font-weight: 100;\">Kod pocztowy: " + klient.getKod_pocztowy() + "</h6>\n" +
                "                    <h6 class=\"text-center\" style=\"margin-right:50px;margin-left:50px;font-weight: 100;\">Miejscowość: " + klient.getMiejscowosc() + "</h6>\n" +
                "                    <h6 class=\"text-center\" style=\"margin-right:50px;margin-left:50px;font-weight: 100;\">Data urodzenia: "  + dateFormat.format(klient.getData_urodzenia()) + "</h6>\n" +
                "                    <h6 class=\"text-center\" style=\"margin-right:50px;margin-left:50px;font-weight: 100;\">Telefon: "  + klient.getTelefon() + "</h6>\n" +
                "                    <h6 class=\"text-center\" style=\"margin-right:50px;margin-left:50px;font-weight: 100;\">E-Mail: "  + klient.getE_mail() + "</h6>\n" +
                "                    <h6 class=\"text-center\" style=\"margin-right:50px;margin-left:50px;font-weight: 100;\">Status konta: "  + status + "</h6>\n" +
                "                    <h6 class=\"text-center\" style=\"margin-right:50px;margin-left:50px;font-weight: 100;\">Punkty: "  + klient.getIlosc_punktow() + "pkt.</h6>\n" +
                "                </td>\n" +
                "                <td style=\" text-align: center; vertical-align: middle;\">\n" +
                "                    <h6 class=\"text-center\" style=\"margin-right:50px;margin-left:50px;font-weight: 100;\">Rozrusznik serca: " + klient_kartaKlienta.toStringp1() +"</h6>\n" +
                "                    <h6 class=\"text-center\" style=\"margin-right:50px;margin-left:50px;font-weight: 100;\">Hermofilia: "+ klient_kartaKlienta.toStringp2() +"</h6>\n" +
                "                    <h6 class=\"text-center\" style=\"margin-right:50px;margin-left:50px;font-weight: 100;\">Łuszczyca: "+ klient_kartaKlienta.toStringp3() +"</h6>\n" +
                "                    <h6 class=\"text-center\" style=\"margin-right:50px;margin-left:50px;font-weight: 100;\">Alergie: "+ klient_kartaKlienta.toStringp4() +"</h6>\n" +
                "                    <h6 class=\"text-center\" style=\"margin-right:50px;margin-left:50px;font-weight: 100;\">Przebarwienia: "+ klient_kartaKlienta.toStringp5() +"</h6>\n" +
                "                    <h6 class=\"text-center\" style=\"margin-right:50px;margin-left:50px;font-weight: 100;\">Choroby zakaźne: "+ klient_kartaKlienta.toStringp6() +"</h6>\n" +
                "                    <h6 class=\"text-center\" style=\"margin-right:50px;margin-left:50px;font-weight: 100;\">Zaburzenia ukrwienia: "+ klient_kartaKlienta.toStringp7() +"</h6>\n" +
                "                    <h6 class=\"text-center\" style=\"margin-right:50px;margin-left:50px;font-weight: 100;\">Opryszczka, osłabuienia, opryszczka: "+ klient_kartaKlienta.toStringp8() +"</h6>\n" +
                "                    <h6 class=\"text-center\" style=\"margin-right:50px;margin-left:50px;font-weight: 100;\">Ciąża: "+ klient_kartaKlienta.toStringp9() +"</h6>\n" +

                "                    <h6 class=\"text-center\" style=\"margin-right:50px;margin-left:50px;font-weight: 100;\">Ocena skóry: "+ klient_kartaKlienta.getOcena_skory() +"</h6>\n" +
                "                    <h6 class=\"text-center\" style=\"margin-right:50px;margin-left:50px;font-weight: 100;\">Typ skóry: "+ klient_kartaKlienta.getRodzaj_jakos() +"</h6>\n" +
                "                    <h6 class=\"text-center\" style=\"margin-right:50px;margin-left:50px;font-weight: 100;\">Wrażliwości skóry: "+ klient_kartaKlienta.getWrazliwosc() +"</h6>\n" +
                "                    <h6 class=\"text-center\" style=\"margin-right:50px;margin-left:50px;font-weight: 100;\">Uwagi dotyczące skóry: "+ klient_kartaKlienta.getInne_uwagi() +"</h6>\n" +
                "                </td>\n" +
                "            </tr>\n" +
                "        </table>\n" +
                "        <br>    " +
                "<div class=\"row\">\n" +
                "        <a href=\"../ControllerAccount?page=konto_edit\"><button type=\"button\" class=\"btn btn-primary float-none align-self-center\" style=\"width:265px;margin-left: 170px;margin-top:20px;\">Edytuj dane konta</button></a>\n" +
                "        <a href=\"../ControllerAccount?page=card_edit\"><button type=\"button\" class=\"btn btn-primary float-none align-self-center\" style=\"width:265px;margin-left: 17px;margin-top:20px;\">Edytuj dane książeczki</button></a>\n" +
                "        <a><button class=\"btn btn-primary float-none align-self-center\" type=\"submit\" name=\"button1\" value=\"Button 1\" style=\"width:265px;margin-left: 17px;margin-top:20px;\">Usuń konto</button></a>\n" +
                "</div>" +
                "</section>");
    } catch (SQLException | NoResultsException e) {
        e.printStackTrace();
    }
%>
</form>
</html>

<%@ page import="myPage.basicObjects.Sprzet" %>
<%@ page import="myPage.data.dataBase.SprzetData" %>
<%@ page import="java.sql.SQLException" %>
<%@ page import="java.text.SimpleDateFormat" %>
<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<form action="${pageContext.request.contextPath}/AddPrzeglad" method="post">
<section id="add_service" class="bg-light-gray" style="margin:0;background-color:rgba(0,0,0,0.11);color:#ffffff;padding-bottom:20px;padding-top:20px;max-width:800px;margin-right:auto;margin-left:auto;border-radius:20px;margin-bottom:30px;">

        <h2 class="text-center" style="height:79px;">Dodaj Przegląd</h2>
        <h5 class="text-center" style="height:21px;margin-right:50px;margin-left:50px;">Zaznacz wybrany sprzęt i wprowadź dane przeglądu.</h5>
        <h6 class="text-center" id="Informacja_Upload" style="font-weight:300;height:44px;margin-right:50px;margin-left:50px;">Pamiętaj, aby zaznaczyć tylko jedno urządzenie!</h6>
        <div class="form-group">
            <label style="font-size:17px;margin-left:101px;margin-top:17px;">Tytuł i treść</label>
            <input class="form-control" type="text" name="tytul_przegladu" required="" placeholder="Tytuł raportu" maxlength="255" minlength="5" style="margin:0;width:500px;margin-left:145px;margin-top: 0px;">
            <input class="form-control" type="text" name="opis_przegladu" required="" placeholder="Treść raportu" maxlength="5000" minlength="10" style="margin:0;width:500px;margin-left:145px;margin-top: 20px;">
            <div class="form-group"><button class="btn btn-primary" type="submit" style="margin:0;width:265px;margin-left:267px;margin-top: 59px;">Dodaj raport</button></div>
        </div>

</section>

<section id="baza_sprzetu" class="bg-light-gray" style="margin:0;background-color:rgba(0,0,0,0.11);color:#ffffff;padding-bottom:20px;padding-top:20px;max-width:1140px;margin-right:auto;margin-left:auto;border-radius:20px;margin-bottom:30px;">
    <h2 class="text-center" style="height:79px;">Lista sprzętu</h2>
    <h5 class="text-center" style="height:21px;margin-right:50px;margin-left:50px;">Poniżej zamieszczona jest lista sprzętów użytkowych znajdujących się w gabinecie.</h5>
    <h6 class="text-center" id="info" style="height:44px;margin-right:50px;margin-left:50px;font-weight: 100;">Zaznacz sprzęt, na rzecz którego chcesz dodać raport.</h6>
    <table id="tablica_sprzetu" class="table" cellspacing="0" width="100%" style="text-align: center;margin-bottom: 0;border: 3px solid #FFFFFF;width: 98%;max-width: 98%;margin-left: 12px;background-color: transparent;border-collapse: collapse;">
        <thead>
        <tr>
            <th></th>
            <th>ID</th>
            <th>NAZWA</th>
            <th>OPIS</th>
            <th>DATA ZAKUPU</th>
            <th>DATA GWARANCJI</th>
            <th>UWAGI</th>
        </tr>
        </thead>
        <tbody>

        <%
            Sprzet sprzet_w_gabinecie = new Sprzet();
            SprzetData odczytany_sprzet;
            try {
                sprzet_w_gabinecie.getEverything();
            } catch (SQLException e) {
                e.printStackTrace();
            }

            SimpleDateFormat formatter = new SimpleDateFormat("MM/dd/yyyy");

            while (!sprzet_w_gabinecie.sprzetyListaEmpty()){
                odczytany_sprzet = sprzet_w_gabinecie.uslugaPop();

                out.println("<tr>" +
                        "<td> <input type=\"Checkbox\" Name=\"do_przegladu\" Value =\"" + odczytany_sprzet.getId_sprzetu() + "\"></td>" +
                        "<td>" + odczytany_sprzet.getId_sprzetu() + "</td>" +
                        "<td>" + odczytany_sprzet.getNazwa_sprzetu() + "</td>" +
                        "<td>" + odczytany_sprzet.getOpis_sprzetu() + "</td>" +
                        "<td>" + formatter.format(odczytany_sprzet.getData_zakupu()) + "</td>" +
                        "<td>" + formatter.format(odczytany_sprzet.getData_gwarancji()) + "</td>" +
                        "<td>" + odczytany_sprzet.getUwagi() + "</td></tr>");
            }
        %>

        </tbody>
    </table>
</section>
</form>
</html>
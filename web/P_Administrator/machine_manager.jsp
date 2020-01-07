<%@ page import="java.sql.SQLException" %>
<%@ page import="myPage.basicObjects.Sprzet" %>
<%@ page import="myPage.data.dataBase.SprzetData" %>
<%@ page import="java.text.SimpleDateFormat" %>
<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
    <section id="baza_sprzetu" class="bg-light-gray" style="margin:0;background-color:rgba(0,0,0,0.11);color:#ffffff;padding-bottom:20px;padding-top:20px;max-width:1140px;margin-right:auto;margin-left:auto;border-radius:20px;margin-bottom:30px;">
        <form action="${pageContext.request.contextPath}/ControllerRemoveMachine" method="post">
            <h2 class="text-center" style="height:79px;">Sprzęt</h2>
            <h5 class="text-center" style="height:21px;margin-right:50px;margin-left:50px;">Poniżej zamieszczona jest lista sprzętów użytkowych znajdujących się w gabinecie.</h5>
            <h6 class="text-center" id="informacja" style="height:44px;margin-right:50px;margin-left:50px;font-weight: 100;">Zaznacz dany sprzęt, aby go usunąć.</h6>

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

            <!--wyniki generowane na bieżąco

             <tr>
                <td> <input type="Checkbox" Name="do_usuniecia" Value ="ID_SPRZETU"></td>
                <th>ID</th>
                <th>NAZWA</th>
                <th>OPIS</th>
                <th>DATA ZAKUPU</th>
                <th>DATA GWARANCJI</th>
                <th>UWAGI</th>
            </tr>

            -->

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
                            "<td> <input type=\"Checkbox\" Name=\"do_usuniecia\" Value =\"" + odczytany_sprzet.getId_sprzetu() + "\"></td>" +
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
            <button class="btn btn-primary float-none align-self-center" type="submit" style="width:265px;position:static;text-align:center!important;margin-left:437px;margin-top:20px;">Usuń sprzęt</button>
            </form>
            <a href="../ControllerAccount?page=dodaj_sprzet"><button class="btn btn-primary float-none align-self-center" type="button" style="width:265px;position:static;text-align:center!important;margin-left:437px;margin-top:20px;">Dodaj sprzęt</button></a>
    </section>
</html>
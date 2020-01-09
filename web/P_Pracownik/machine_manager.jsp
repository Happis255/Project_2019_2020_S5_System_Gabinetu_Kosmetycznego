<%@ page import="java.sql.SQLException" %>
<%@ page import="myPage.basicObjects.Sprzet" %>
<%@ page import="myPage.data.dataBase.SprzetData" %>
<%@ page import="java.text.SimpleDateFormat" %>
<%@ page import="myPage.data.others.SessionData" %>
<%@ page import="myPage.basicObjects.Services" %>
<%@ page import="myPage.data.dataBase.ServiceData" %>
<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
    <section id="baza_sprzetu" class="bg-light-gray" style="margin:0;background-color:rgba(0,0,0,0.11);color:#ffffff;padding-bottom:20px;padding-top:20px;max-width:1140px;margin-right:auto;margin-left:auto;border-radius:20px;margin-bottom:30px;">
            <h2 class="text-center" style="height:79px;">Sprzęt</h2>
            <h5 class="text-center" style="height:21px;margin-right:50px;margin-left:50px;">Poniżej zamieszczona jest lista sprzętów użytkowych znajdujących się w gabinecie.</h5>
            <h6 class="text-center" id="info" style="height:44px;margin-right:50px;margin-left:50px;font-weight: 100;">Zaznacz dany sprzęt, aby go usunąć.</h6>

            <table id="tablica_sprzetu" class="table" cellspacing="0" width="100%" style="text-align: center;margin-bottom: 0;border: 3px solid #FFFFFF;width: 98%;max-width: 98%;margin-left: 12px;background-color: transparent;border-collapse: collapse;">
            <thead>
            <tr>
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

                    out.println(
                            "<tr><td>" + odczytany_sprzet.getId_sprzetu() + "</td>" +
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

    <form action="${pageContext.request.contextPath}/RemoveServices" method="post">
        <section id="przeglady_data" class="bg-light-gray" style="margin:0;background-color:rgba(0,0,0,0.11);color:#ffffff;padding-bottom:20px;padding-top:20px;max-width:1140px;margin-right:auto;margin-left:auto;border-radius:20px;margin-bottom:30px;">
            <h2 class="text-center" style="height:79px;">Przeglądy</h2>
            <h5 class="text-center" style="height:21px;margin-right:50px;margin-left:50px;">W poniższej sekcji znajdują się dodane przez Ciebie raporty z przeglądów.</h5>
            <h6 class="text-center" id="informacja" style="height:44px;margin-right:50px;margin-left:50px;font-weight: 100;">Jeśli w raporcie został popełniony błąd, zaznacz go aby go usunąć lub dodaj nowy raport.</h6>
        </section>
        <%
            SessionData sessionData = (SessionData)session.getAttribute("userData");

            Services przeglad = new Services();
            ServiceData temp;
            try {
                przeglad.getPracownikPrzeglady(sessionData.getId());
            } catch (Exception e) {
                e.printStackTrace();
            }

            while (!przeglad.serviceEmpty()){
                temp = przeglad.servicePop();

                SimpleDateFormat formate = new SimpleDateFormat("MM/dd/yyyy");
                String date = formate.format(temp.getData());

                out.println("    <section id=\"pracownik\" class=\"bg-light-gray\" style=\"margin:0;background-color:rgba(0,0,0,0.11);color:#ffffff;padding-bottom:20px;padding-top:20px;max-width:1140px;margin-right:auto;margin-left:auto;border-radius:20px;margin-bottom:30px;\">\n" +
                        "        <br>\n" +
                        "        <table class=\"table\" cellspacing=\"0\" width=\"100%\" style=\"margin-top: -18px;text-align: center;margin-bottom: 0;width: 55%;/* max-width: 100%; */margin-left: 16%;background-color: transparent;border-collapse: collapse;min-width: 760px;\"\">\n" +
                        "            <tr>\n" +
                        "                <td><h5 class=\"text-center\" style=\"margin-bottom: 1px;\">Treść</h5></td>\n" +
                        "            </tr>\n" +
                        "            <tr>\n" +
                        "                <td style=\" text-align: center; vertical-align: middle;\">\n" +
                        "                    <h6 class=\"text-center\" style=\"margin-right:50px;margin-left:50px;font-weight: 100;\">" + temp.getOpis_przegladu() + "</h6>\n" +
                        "                </td>\n" +
                        "            </tr>\n" +
                        "        </table>\n" +
                        "        <br>\n" +
                        "        <br>\n" +
                        "        <h5 class=\"text-center\" style=\"height:30px;margin-right:50px;margin-left:50px;\">Dane raportu</h5>\n" +
                        "        <table class=\"table\" cellspacing=\"0\" width=\"100%\" style=\"text-align: center;margin-bottom: 0;border: 3px solid #FFFFFF;width: 55%;max-width: 55%;margin-left: 259px;background-color: transparent;border-collapse: collapse;\">\n" +
                        "            <thead>\n" +
                        "            <tr>\n" +
                        "                <th></th>\n" +
                        "                <th style=\"width: 50%\">ID</th>\n" +
                        "                <th style=\"width: 50%\">TYTUŁ RAPORTU</th>\n" +
                        "                <th style=\"width: 50%\">DATA</th>\n" +
                        "            </tr>\n" +
                        "            </thead>\n" +
                        "            <tbody>\n" +
                        "            <tr>\n" +
                        "                <td> <input type=\"Checkbox\" name=\"do_usuniecia\" value=\"" + temp.getId_przegladu() + "\"></td>\n" +
                        "                <td>" + temp.getId_przegladu()  +"</td>\n" +
                        "                <td>"  + temp.getTytul_przegladu() +"</td>\n" +
                        "                <td>"  + date +"</td>\n" +
                        "            </tr>\n" +
                        "            </tbody>\n" +
                        "        </table>\n" +
                        "    </section>");
            }
        %>
        <section id="przyciski" class="bg-light-gray" style="margin:0;background-color:rgba(0,0,0,0.11);color:#ffffff;padding-bottom:20px;max-width:1140px;margin-right:auto;margin-left:auto;border-radius:20px;margin-bottom:30px;">
            <button class="btn btn-primary float-none align-self-center" type="submit" style="width:265px;position:static;text-align:center!important;margin-left:437px;margin-top:20px;">Usuń Raport</button>

            <a href="../ControllerAccount?page=przeglad_upload"><button type="button" class="btn btn-primary float-none align-self-center" style="width:265px;position:static;text-align:center!important;margin-left:437px;margin-top:20px;">Dodaj Raport</button></a>
        </section>
    </form>

</html>
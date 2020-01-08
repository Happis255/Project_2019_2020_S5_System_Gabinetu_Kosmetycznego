<%@ page import="myPage.basicObjects.Nieobecnosc" %>
<%@ page import="myPage.data.dataBase.NieobecnoscData" %>
<%@ page import="java.text.SimpleDateFormat" %>
<%@ page import="myPage.data.others.SessionData" %>
<%@ page import="myPage.basicObjects.Usluga" %>
<%@ page import="myPage.data.dataBase.UslugaData" %>
<%@ page import="myPage.basicObjects.Wizyta" %>
<%@ page import="myPage.data.dataBase.WizytaData" %>
<%@ page import="myPage.basicObjects.Pracownik" %>
<%@ page import="java.awt.print.PrinterAbortException" %>
<%@ page import="myPage.data.dataBase.PracownikData" %>
<!DOCTYPE html>
<html>
    <section id="wizyty_klient" class="bg-light-gray" style="margin:0;background-color:rgba(0,0,0,0.11);color:#ffffff;padding-bottom:20px;padding-top:20px;max-width:1140px;margin-right:auto;margin-left:auto;border-radius:20px;margin-bottom:30px;">
        <form action="#" method="post">
            <h2 class="text-center" style="height:79px;">Wizyty</h2>

            <h6 class="text-center" id="informacja" style="height:44px;margin-right:50px;margin-left:50px;font-weight: 100;">Wybierz usługę</h6>
            <table id="tablica_uslug" class="table" cellspacing="0" width="100%" style="text-align: center;margin-bottom: 0;border: 3px solid #FFFFFF;width: 98%;max-width: 98%;margin-left: 12px;background-color: transparent;border-collapse: collapse;">
                <thead>
                <tr>
                    <th></th>
                    <th>TYP USŁUGI</th>
                    <th>NAZWA</th>
                    <th>OPIS</th>
                    <th>CENA</th>
                    <th>CZAS TRWANIA</th>
                </tr>
                </thead>
                <tbody>

                <%
                    SessionData sessionData = (SessionData)session.getAttribute("userData");
                    Usluga usluga = new Usluga();
                    UslugaData temp;
                    try {
                        usluga.getEverything();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }

                    while (!usluga.uslugiEmpty()){
                        temp = usluga.uslugaPop();

                        out.println("<tr><td> <input type=\"radio\" Name=\"wybrana_usluga\" Value =\"" + temp.getId_uslugi() + "\"></td>" +
                                "<td>" + temp.getTyp_uslugi() + "</td>" +
                                "<td>" + temp.getNazwa() + "</td>" +
                                "<td>" + temp.getOpis() + "</td>" +
                                "<td>" + temp.getCena() + "</td>" +
                                "<td>" + temp.getCzas_trwania() + "</td>" +
                                "</tr>");
                    }
                %>
                </tbody>
            </table>

            <h6 class="text-center" id="informacja2" style="height:44px;margin-right:50px;margin-left:50px;font-weight: 100;">Wybierz Pracownika</h6>
            <table id="tablica_pracownikow" class="table" cellspacing="0" width="100%" style="text-align: center;margin-bottom: 0;border: 3px solid #FFFFFF;width: 98%;max-width: 98%;margin-left: 12px;background-color: transparent;border-collapse: collapse;">
                <thead>
                <tr>
                    <th></th>
                    <th>IMIE</th>
                    <th>NAZWISKO</th>
                    <th>CERTYFIKATY</th>
                </tr>
                </thead>
                <tbody>

                <%
                    Pracownik pracownik = new Pracownik();
                    PracownikData temp2;
                    try {
                        pracownik.getAllData();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }

                    while (!pracownik.emptyPracownik()){
                        temp2 = pracownik.popPracownik();

                        out.println("<tr><td> <input type=\"radio\" Name=\"wybrany_pracownik\" Value =\"" + temp2.getId() + "\"></td>" +
                                "<td>" + temp2.getImie() + "</td>" +
                                "<td>" + temp2.getNazwisko() + "</td>" +
                                "<td>" + temp2.getCertyfikaty() + "</td>" +
                                "</tr>");
                    }
                %>
                </tbody>
            </table>

            <button class="btn btn-primary float-none align-self-center" type="submit" style="width:265px;position:static;text-align:center!important;margin-left:437px;margin-top:20px;">Przejdź do wyboru terminu</button>
        </form>
        </section>
</html>
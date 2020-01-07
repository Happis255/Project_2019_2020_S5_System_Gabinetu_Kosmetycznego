<%@ page import="myPage.basicObjects.Nieobecnosc" %>
<%@ page import="myPage.data.dataBase.NieobecnoscData" %>
<%@ page import="java.text.SimpleDateFormat" %>
<%@ page import="myPage.data.others.SessionData" %>
<!DOCTYPE html>
<html>
    <section id="nieobecnosci_pracownik" class="bg-light-gray" style="margin:0;background-color:rgba(0,0,0,0.11);color:#ffffff;padding-bottom:20px;padding-top:20px;max-width:1140px;margin-right:auto;margin-left:auto;border-radius:20px;margin-bottom:30px;">
        <form action="${pageContext.request.contextPath}/ControllerAbsenceRemover" method="post">
            <h2 class="text-center" style="height:79px;">Nieobecności</h2>
            <h5 class="text-center" style="height:21px;margin-right:50px;margin-left:50px;">W poniższej sekcji znajdują się wszystkie zgłoszone przez Ciebie nieobecności.</h5>
            <h6 class="text-center" id="informacja" style="height:44px;margin-right:50px;margin-left:50px;font-weight: 100;">Zaznacz wybraną nieobecność, by ją następnie usunąć, bądź dodaj nową.</h6>
            <table id="tablica_nieobecnosc_worker" class="table" cellspacing="0" width="100%" style="text-align: center;margin-bottom: 0;border: 3px solid #FFFFFF;width: 98%;max-width: 98%;margin-left: 12px;background-color: transparent;border-collapse: collapse;">
            <thead>
            <tr>
                <th></th>
                <th>ID</th>
                <th>IMIE</th>
                <th>NAZWISKO</th>
                <th>DATA OD</th>
                <th>DATA DO</th>
                <th>POWOD</th>
                <th>STATUS</th>
            </tr>
            </thead>
            <tbody>

            <!--wyniki generowane na bieżąco

             <tr>
                <td> <input type="Checkbox" Name="do_usuniecia" Value ="01"></td>
                <td>0001</td>
                <td>WERONIKA</td>
                <td>STALOWOWOLSKA</td>
                <td>2019-02-13</td>
                <td>2019-02-24</td>
                <td>Zwolnienie lekarskie</td>
                <td>NIEPOTWIERDZONE</td>
            </tr>

            -->

            <%
                SessionData sessionData = (SessionData)session.getAttribute("userData");
                Nieobecnosc nieobecnosci = new Nieobecnosc();
                NieobecnoscData temp;
                try {
                    nieobecnosci.getPracownikNieobecnosc(sessionData.getId());
                } catch (Exception e) {
                    e.printStackTrace();
                }

                while (!nieobecnosci.nieobecnoscEmpty()){
                    temp = nieobecnosci.nieobecnoscPop();

                    SimpleDateFormat formatter = new SimpleDateFormat("MM/dd/yyyy");
                    String date_od = formatter.format(temp.getData_od());
                    String date_do = formatter.format(temp.getData_do());

                    out.println("<tr><td> <input type=\"Checkbox\" Name=\"do_usuniecia\" Value =\"" + temp.getId_nieobecnosci() + "\"></td>" +
                                "<td>" + temp.getId_nieobecnosci() + "</td>" +
                                "<td>" + temp.getImie_pracownika() + "</td>" +
                                "<td>" + temp.getNazwisko_pracownika() + "</td>" +
                                "<td>" + date_od + "</td>" +
                                "<td>" + date_do + "</td>" +
                                "<td>" + temp.getPowod() + "</td>" +
                                "<td>" + temp.getStatus() + "</td>" +
                                "</tr>");
                }
            %>

            </tbody>
            </table>
            <button class="btn btn-primary float-none align-self-center" type="submit" style="width:265px;position:static;text-align:center!important;margin-left:437px;margin-top:20px;">Odwołaj nieobecność</button>
        </form>
        <a href="../ControllerAccount?page=nieobecnosc_upload"><button class="btn btn-primary float-none align-self-center" style="width:265px;position:static;text-align:center!important;margin-left:437px;margin-top:20px;">Zgłoś nieobecność</button></a>
        </section>
</html>
<%@ page import="myPage.basicObjects.Aktualnosci" %>
<%@ page import="myPage.data.dataBase.AktualnoscData" %>
<%@ page import="myPage.exceptions.DBReadWriteException" %>
<%@ page import="java.sql.SQLException" %>
<%@ page import="java.text.SimpleDateFormat" %>
<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
    <section id="baza_aktualnosci" class="bg-light-gray" style="margin:0;background-color:rgba(0,0,0,0.11);color:#ffffff;padding-bottom:20px;padding-top:20px;max-width:1140px;margin-right:auto;margin-left:auto;border-radius:20px;margin-bottom:30px;">
        <form action="${pageContext.request.contextPath}/RemoveNewsWithJPG" method="post">
            <h2 class="text-center" style="height:79px;">Aktualności</h2>
            <h5 class="text-center" style="height:21px;margin-right:50px;margin-left:50px;">Poniżesz zamieszczona jest lista aktualności znajdująca się w systemie</h5>
            <h6 class="text-center" id="informacja" style="height:44px;margin-right:50px;margin-left:50px;font-weight: 100;">Zaznacz daną aktualność, by ją usunąć.</h6><table id="tablica_ogloszen" class="table" cellspacing="0" width="100%" style="text-align: center;margin-bottom: 0;border: 3px solid #FFFFFF;width: 98%;max-width: 98%;margin-left: 12px;background-color: transparent;border-collapse: collapse;">
            <thead>
            <tr>
                <th></th>
                <th>ID</th>
                <th>TYTUŁ</th>
                <th>TREŚĆ</th>
                <th>DATA OD</th>
                <th>DATA DO</th>
                <th>PRACOWNIK</th>

            </tr>
            </thead>
            <tbody>

            <!--wyniki generowane na bieżąco

             <tr>
                <td> <input type="Checkbox" Name="do_usuniecia" Value ="01"></td>
                <td>0001</td>
                <td>Walentynki w gracji</td>
                <td>Wpadnij i sam się przekonaj o naszych usługach!</td>
                <td>2019-02-13</td>
                <td>2019-02-24</td>
                <td>Wąsik</td>
            </tr>

            -->

            <%
                Aktualnosci aktualnosci = new Aktualnosci();
                AktualnoscData temp;
                try {
                    aktualnosci.getEverything();
                } catch (DBReadWriteException | SQLException e) {
                    e.printStackTrace();
                }

                while (!aktualnosci.aktualnoscEmpty()){
                    temp = aktualnosci.AktualnoscPop();
                    SimpleDateFormat formatter = new SimpleDateFormat("MM/dd/yyyy");
                    String date_od = formatter.format(temp.getData_od());
                    String date_do = formatter.format(temp.getData_do());

                    out.println("<tr>" +
                                "<td> <input type=\"Checkbox\" Name=\"do_usuniecia\" Value =\"" + temp.getId_aktualnosci() + "\"></td>" +
                                "<td>" + temp.getId_aktualnosci() + "</td>" +
                                "<td>" + temp.getTytul() + "</td>" +
                                "<td>" + temp.getTresc() + "</td>" +
                                "<td>" + date_od + "</td>" +
                                "<td>" + date_do + "</td>" +
                                "<td>" + temp.getNazwisko() + "</td></tr>");
                }
            %>

            </tbody>
            </table>
            <button class="btn btn-primary float-none align-self-center" type="submit" style="width:265px;position:static;text-align:center!important;margin-left:437px;margin-top:20px;">Usuń aktualność</button>
            </form>
            <a href="../ControllerAccount?page=aktualnosci_upload"><button class="btn btn-primary float-none align-self-center" style="width:265px;position:static;text-align:center!important;margin-left:437px;margin-top:20px;">Dodaj aktualność</button></a>
        </section>
</html>
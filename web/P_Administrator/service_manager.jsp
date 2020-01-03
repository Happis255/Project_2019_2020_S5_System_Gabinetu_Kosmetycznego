<%@ page import="myPage.exceptions.DBReadWriteException" %>
<%@ page import="java.sql.SQLException" %>
<%@ page import="myPage.basicObjects.Usluga" %>
<%@ page import="myPage.data.dataBase.UslugaData" %>
<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
    <section id="baza_uslug" class="bg-light-gray" style="margin:0;background-color:rgba(0,0,0,0.11);color:#ffffff;padding-bottom:20px;padding-top:20px;max-width:1140px;margin-right:auto;margin-left:auto;border-radius:20px;margin-bottom:30px;">
        <form action="${pageContext.request.contextPath}/RemoveService" method="post">
            <h2 class="text-center" style="height:79px;">Usługi</h2>
            <h5 class="text-center" style="height:21px;margin-right:50px;margin-left:50px;">Poniżesz zamieszczona jest lista usług znajdujących się w systemie.</h5>
            <h6 class="text-center" id="informacja" style="height:44px;margin-right:50px;margin-left:50px;font-weight: 100;">Zaznacz daną usługę, by ją usunąć.</h6>

            <table id="tablica_uslugGabinetu" class="table" cellspacing="0" width="100%" style="text-align: center;margin-bottom: 0;border: 3px solid #FFFFFF;width: 98%;max-width: 98%;margin-left: 12px;background-color: transparent;border-collapse: collapse;">
            <thead>
            <tr>
                <th></th>
                <th>ID</th>
                <th>TYP USLUGI</th>
                <th>NAZWA</th>
                <th>CENA</th>
                <th>CZAS TRWANIA</th>
            </tr>
            </thead>
            <tbody>

            <!--wyniki generowane na bieżąco

             <tr>
                <td> <input type="Checkbox" Name="do_usuniecia" Value ="01"></td>
                <td>0001</td>
                <td>Kosmetyka twarzy</td>
                <td>Regulacja brwi</td>
                <td>10 PLN</td>
                <td>15 min.</td>
            </tr>

            -->

            <%
                Usluga usluga = new Usluga();
                UslugaData temp;
                try {
                    usluga.getEverything();
                } catch (DBReadWriteException | SQLException e) {
                    e.printStackTrace();
                }

                while (!usluga.uslugiEmpty()){
                    temp = usluga.uslugaPop();

                    out.println("<tr>" +
                                "<td> <input type=\"Checkbox\" Name=\"do_usuniecia\" Value =\"" + temp.getId_uslugi() + "\"></td>" +
                                "<td>" + temp.getId_uslugi() + "</td>" +
                                "<td>" + temp.getTyp_uslugi() + "</td>" +
                                "<td>" + temp.getNazwa() + "</td>" +
                                "<td>" + temp.getCena() + " PLN</td>" +
                                "<td>" + temp.getCzas_trwania() + " min.</td></tr>");
                }
            %>

            </tbody>
            </table>
            <button class="btn btn-primary float-none align-self-center" type="submit" style="width:265px;position:static;text-align:center!important;margin-left:437px;margin-top:20px;">Usuń usługę</button>
            </form>
            <button class="btn btn-primary float-none align-self-center" type="submit" style="width:265px;position:static;text-align:center!important;margin-left:437px;margin-top:20px;">
                <a href="../ControllerAccount?page=uslugi_upload">Dodaj usługę</a></button>
    </section>
</html>
<%@ page import="myPage.data.dataBase.StatusData" %>
<%@ page import="myPage.basicObjects.Status_Klienta" %>
<%@ page import="java.sql.SQLException" %>
<!DOCTYPE html>
<html>
    <section id="promo_status_setter" class="bg-light-gray" style="margin:0;background-color:rgba(0,0,0,0.11);color:#ffffff;padding-bottom:20px;padding-top:20px;max-width:1140px;margin-right:auto;margin-left:auto;border-radius:20px;margin-bottom:30px;">
            <h2 class="text-center" style="height:79px;">Status klienta i promocje</h2>
            <h5 class="text-center" style="height:21px;margin-right:50px;margin-left:50px;">W poniższej sekcji znajdują się statusy wykorzystywane w systemie.</h5>
            <h6 class="text-center" id="informacja" style="height:44px;margin-right:50px;margin-left:50px;font-weight: 100;">Zaznacz wybrany status, by go zmodyfikować. Pamiętaj - statusów nie da się usunąć.</h6>
            <table id="tablica_status" class="table" cellspacing="0" width="100%" style="text-align: center;margin-bottom: 0;border: 3px solid #FFFFFF;width: 98%;max-width: 98%;margin-left: 12px;background-color: transparent;border-collapse: collapse;">
            <thead>
            <tr>
                <th>ID</th>
                <th>NAZWA STATUSU</th>
                <th>PUNKTY OD</th>
                <th>PUNKTY DO</th>
                <th>ZNIŻKA PROCEN.</th>
                <th>ZNIŻKA KWOTOWA</th>
                <th>LICZBA KLIENTÓW</th>
            </tr>
            </thead>
            <tbody>

            <%
                Status_Klienta statusy = new Status_Klienta();
                StatusData temp;
                try {
                    statusy.getStatusy();
                } catch (Exception e) {
                    e.printStackTrace();
                }

                int liczba = 0;
                while (!statusy.statusyEmpty()){
                    temp = statusy.statusyPop();

                    try {
                        liczba =  statusy.countStatus(temp.getId_statusu());
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }

                    out.println("<tr>" +
                                "<td>" + temp.getId_statusu() + "</td>" +
                                "<td>" + temp.getNazwa() + "</td>" +
                                "<td>" + temp.getPunkty_od() + "</td>" +
                                "<td>" + temp.getPunkty_do() + "</td>" +
                                "<td>" + temp.getZnizka_proc() + " %</td>" +
                                "<td>" + temp.getZnizka_kwot() + " PLN</td>" +
                                "<td>" + liczba + "</td>" +
                                "</tr>");
                }
            %>

            </tbody>
            </table>
            <a href="../ControllerAccount?page=dodaj_status"><button class="btn btn-primary float-none align-self-center" type="button" style="width:265px;position:static;text-align:center!important;margin-left:437px;margin-top:20px;">Dodaj status</button></a>
            <a href="../ControllerAccount?page=edytuj_status"><button class="btn btn-primary float-none align-self-center" type="button" style="width:265px;position:static;text-align:center!important;margin-left:437px;margin-top:20px;">Edytuj status</button></a>
        </section>
</html>
<%@ page import="java.text.SimpleDateFormat" %>
<%@ page import="myPage.basicObjects.ZadanieGospodarcze" %>
<%@ page import="myPage.data.dataBase.ZadanieGospodarczeData" %>
<%@ page import="java.sql.SQLException" %>
<%@ page import="myPage.data.others.SessionData" %>
<!DOCTYPE html>
<html>
<% SessionData sessionData = (SessionData)session.getAttribute("userData"); %>
    <section id="nieobecnosci_pracownik" class="bg-light-gray" style="margin:0;background-color:rgba(0,0,0,0.11);color:#ffffff;padding-bottom:20px;padding-top:20px;max-width:1140px;margin-right:auto;margin-left:auto;border-radius:20px;margin-bottom:30px;">
            <h2 class="text-center" style="height:79px;">Zadania gospodarcze</h2>
            <h5 class="text-center" style="height:21px;margin-right:50px;margin-left:50px;">W poniższej sekcji znajdują się wszystkie zadania gospodarcze obowiązujące pracowników.</h5>
            <h6 class="text-center" id="informacja" style="height:44px;margin-right:50px;margin-left:50px;font-weight: 100;">Zaznacz wybrane zadanie, by je usunąć, bądź dodaj nową i przypisz ją pracownikowi odpowiednią opcją.</h6>
            <table id="tablica_zadan" class="table" cellspacing="0" width="100%" style="text-align: center;margin-bottom: 0;border: 3px solid #FFFFFF;width: 98%;max-width: 98%;margin-left: 12px;background-color: transparent;border-collapse: collapse;">
            <thead>
            <tr>
                <th></th>
                <th>ID</th>
                <th>NAZWA</th>
                <th>OPIS</th>
                <th>DATA OD</th>
                <th>DATA DO</th>
            </tr>
            </thead>
            <tbody>

            <%
                ZadanieGospodarcze zadania = new ZadanieGospodarcze();
                ZadanieGospodarczeData temp;

                try {
                    zadania.getAllZadania(sessionData.getId());
                } catch (SQLException e) {
                    e.printStackTrace();
                }

                while (!zadania.listaZadanEmpty()) {
                    temp = zadania.listaPop();
                    SimpleDateFormat formatter = new SimpleDateFormat("MM/dd/yyyy");
                    String date_od = formatter.format(temp.getData_od());
                    String date_do = formatter.format(temp.getData_do());

                    out.println("<tr><td> <input type=\"Checkbox\" Name=\"do_usuniecia\" Value =\"" + temp.getId_zadania() + "\"></td>" +
                            "<td>" + temp.getId_zadania() + "</td>" +
                            "<td>" + temp.getTytul() + "</td>" +
                            "<td>" + temp.getTresc() + "</td>" +
                            "<td>" + date_od + "</td>" +
                            "<td>" + date_do + "</td>" +
                            "</tr>");
                }
            %>

            </tbody>
            </table>
        </section>
</html>
<%@ page import="myPage.basicObjects.Pracownik" %>
<%@ page import="myPage.data.dataBase.PracownikData" %>
<%@ page import="myPage.basicObjects.Usluga" %>
<%@ page import="myPage.data.others.VisitPage" %>
<!DOCTYPE html>
<html>
<section id="wizyty_pracownik" class="bg-light-gray" style="margin:0;background-color:rgba(0,0,0,0.11);color:#ffffff;padding-bottom:20px;padding-top:20px;max-width:1140px;margin-right:auto;margin-left:auto;border-radius:20px;margin-bottom:30px;">
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
            VisitPage visitPage = (VisitPage)request.getSession().getAttribute("VisitPage");
            Integer idUslugi = visitPage.getIdUslugi();
            PracownikData temp2 = null;
            String row;
            try {
                pracownik.getPracownicyOdUslugi(idUslugi);
            } catch (Exception e) {
                e.printStackTrace();
            }

            if(pracownik.emptyPracownik()){
                out.println("<tr><td>-</td><td>-</td><td>-</td><td>-</td></tr>");
            }

            while (!pracownik.emptyPracownik()){
                temp2 = pracownik.popPracownik();

                row = "<tr><td> <input type=\"radio\" Name=\"pracownik\" Value =\"" + temp2.getId() + "\"";
                if(session.getAttribute("ID_PRACOWNIKA") != null && (int)session.getAttribute("ID_PRACOWNIKA") == temp2.getId())
                    row += "checked";
                row += "><td>" + temp2.getImie() + "</td>" +
                        "<td>" + temp2.getNazwisko() + "</td>" +
                        "<td>" + temp2.getCertyfikaty() + "</td>" +
                        "</tr>";

                out.println(row);
            }
        %>
        </tbody>
    </table>
    <a href="../ControllerVisitsAdder?visitSubpage=visits_timePick&workerID=<%out.print(temp2.getId());%>"><button class="btn btn-primary float-none align-self-center" type="button" style="width:265px;position:static;text-align:center!important;margin-left:437px;margin-top:20px;">szukaj terminu</button></a>
</section>
</html>
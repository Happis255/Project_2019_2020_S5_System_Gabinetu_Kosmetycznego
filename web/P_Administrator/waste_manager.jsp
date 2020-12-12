<%@ page import="java.text.SimpleDateFormat" %>
<%@ page import="myPage.data.others.SessionData" %>
<%@ page import="myPage.basicObjects.Waste" %>
<%@ page import="myPage.data.dataBase.WasteData" %>
<!DOCTYPE html>
<html>

<form action="${pageContext.request.contextPath}/RemoveWaste" method="post">
    <section id="raporty_data" class="bg-light-gray" style="margin:0;background-color:rgba(0,0,0,0.11);color:#ffffff;padding-bottom:20px;padding-top:20px;max-width:1140px;margin-right:auto;margin-left:auto;border-radius:20px;margin-bottom:30px;">
        <h2 class="text-center" style="height:79px;">Raporty Odpadów</h2>
        <h5 class="text-center" style="height:21px;margin-right:50px;margin-left:50px;">W poniższej sekcji znajdują się raporty zapisane w bazie danych.</h5>
        <h6 class="text-center" id="informacja" style="height:44px;margin-right:50px;margin-left:50px;font-weight: 100;">Jeśli w raporcie został popełniony błąd, zaznacz go aby go usunąć lub dodaj nowy raport.</h6>
        <table id="tablica_waste" class="table" cellspacing="0" width="100%" style="text-align: center;margin-bottom: 0;border: 3px solid #FFFFFF;width: 98%;max-width: 98%;margin-left: 12px;background-color: transparent;border-collapse: collapse;">
            <thead>
            <tr>
                <th></th>
                <th>ID</th>
                <th>TYTUŁ</th>
                <th>TYP ODPADÓW</th>
                <th>DATA</th>
                <th>ILOŚĆ</th>
                <th>KOSZT</th>
            </tr>
            </thead>
            <tbody>

            <%
                SessionData sessionData = (SessionData)session.getAttribute("userData");

                Waste waste = new Waste();
                WasteData temp;
                try {
                    waste.getWaste();
                } catch (Exception e) {
                    e.printStackTrace();
                }

                while (!waste.wasteEmpty()){
                    temp = waste.wastePop();

                    SimpleDateFormat formatter = new SimpleDateFormat("MM/dd/yyyy");
                    String date = formatter.format(temp.getData());

                    out.println("<tr><td> <input type=\"Checkbox\" Name=\"do_usuniecia\" Value =\"" + temp.getId_raportu() + "\"></td>" +
                            "<td>" + temp.getId_raportu() + "</td>" +
                            "<td>" + temp.getTytul_raportu() + "</td>" +
                            "<td>" + temp.getTyp_odpadow() + "</td>" +
                            "<td>" + date + "</td>" +
                            "<td>" + temp.getIlos() + "</td>" +
                            "<td>" + temp.getKoszt() + "</td>" +
                            "</tr>");
                }
            %>

            </tbody>
        </table>
    </section>
    <section id="przyciski" class="bg-light-gray" style="margin:0;background-color:rgba(0,0,0,0.11);color:#ffffff;padding-bottom:20px;max-width:1140px;margin-right:auto;margin-left:auto;border-radius:20px;margin-bottom:30px;">
        <button class="btn btn-primary float-none align-self-center" type="submit" style="width:265px;position:static;text-align:center!important;margin-left:437px;margin-top:20px;">Usuń Raport</button>
        <a href="../ControllerAccount?page=odpady_upload"><button type="button" class="btn btn-primary float-none align-self-center" style="width:265px;position:static;text-align:center!important;margin-left:437px;margin-top:20px;">Dodaj Raport</button></a>
    </section>
</form>

</html>
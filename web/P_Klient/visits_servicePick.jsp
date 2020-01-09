<%@ page import="myPage.basicObjects.Usluga" %>
<%@ page import="myPage.data.dataBase.UslugaData" %>
<%@ page import="myPage.data.others.SessionData" %>
<!DOCTYPE html>
<html>
<section id="wizyty_klient" class="bg-light-gray" style="margin:0;background-color:rgba(0,0,0,0.11);color:#ffffff;padding-bottom:20px;padding-top:20px;max-width:1140px;margin-right:auto;margin-left:auto;border-radius:20px;margin-bottom:30px;">
    <h6 class="text-center" id="informacja" style="height:44px;margin-right:50px;margin-left:50px;font-weight: 100;">Wybierz usługę</h6>
    <table id="tablica_uslug" class="table" cellspacing="0" width="100%"
           style="text-align: center;margin-bottom: 0;border: 3px solid #FFFFFF;width: 98%;max-width: 98%;margin-left: 12px;background-color: transparent;border-collapse: collapse;">
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
            UslugaData temp = null;
            String row;
            try {
                usluga.getEverything();
            } catch (Exception e) {
                e.printStackTrace();
            }

            while (!usluga.uslugiEmpty()){
                temp = usluga.uslugaPop();

                row = "<tr><td> <input onchange=\"\" type=\"radio\" name=\"usluga\" Value =\"" + temp.getId_uslugi() + "\" ";
                if(session.getAttribute("ID_USLUGI") != null && (int)session.getAttribute("ID_USLUGI") == temp.getId_uslugi())
                    row += "checked";
                row += "></td>" + "<td>" + temp.getTyp_uslugi() + "</td>" +
                        "<td>" + temp.getNazwa() + "</td>" +
                        "<td>" + temp.getOpis() + "</td>" +
                        "<td>" + temp.getCena() + "</td>" +
                        "<td>" + temp.getCzas_trwania() + "</td>" +
                        "</tr>";

                out.println(row);

            }
        %>
        </tbody>
    </table>
    <a href="../ControllerVisitsAdder?visitSubpage=visits_workerPick&userID=<%out.print(temp.getId_uslugi());%>"><button class="btn btn-primary float-none align-self-center" type="button" style="width:265px;position:static;text-align:center!important;margin-left:437px;margin-top:20px;">szukaj pracowników</button></a>
</section>
</html>
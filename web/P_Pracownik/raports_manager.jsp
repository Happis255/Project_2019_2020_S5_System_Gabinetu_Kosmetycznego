<%@ page import="myPage.basicObjects.Raport" %>
<%@ page import="myPage.data.dataBase.RaportData" %>
<%@ page import="java.text.SimpleDateFormat" %>
<%@ page import="myPage.data.others.SessionData" %>
<!DOCTYPE html>
<html>
<section id="raporty_data" class="bg-light-gray" style="margin:0;background-color:rgba(0,0,0,0.11);color:#ffffff;padding-bottom:20px;padding-top:20px;max-width:1140px;margin-right:auto;margin-left:auto;border-radius:20px;margin-bottom:30px;">
    <form action="${pageContext.request.contextPath}/RemoveRaport" method="post">
        <h2 class="text-center" style="height:79px;">Raporty</h2>
        <h5 class="text-center" style="height:21px;margin-right:50px;margin-left:50px;">W poniższej sekcji znajdują się dodane przez Ciebie raporty.</h5>
        <h6 class="text-center" id="informacja" style="height:44px;margin-right:50px;margin-left:50px;font-weight: 100;">Jeśli w raporcie został popełniony błąd, zaznacz go aby go usunąć lub dodaj nowy raport.</h6>
        <table id="tablica_raport" class="table" cellspacing="0" width="100%" style="text-align: center;margin-bottom: 0;border: 3px solid #FFFFFF;width: 98%;max-width: 98%;margin-left: 12px;background-color: transparent;border-collapse: collapse;">
            <thead>
            <tr>
                <th></th>
                <th>ID</th>
                <th>TYTUŁ RAPORTU</th>
                <th>TYP RAPORTU</th>
                <th>DATA</th>
                <th>TRESC</th>
            </tr>
            </thead>
            <tbody>

            <%
                SessionData sessionData = (SessionData)session.getAttribute("userData");

                System.out.println(sessionData.getId());

                Raport raport = new Raport();
                RaportData temp;
                try {
                    raport.getPracownikRaporty(sessionData.getId());
                } catch (Exception e) {
                    e.printStackTrace();
                }

                while (!raport.raportyEmpty()){
                    temp = raport.raportyPop();

                    SimpleDateFormat formatter = new SimpleDateFormat("MM/dd/yyyy");
                    String date = formatter.format(temp.getData());

                    out.println("<tr><td> <input type=\"Checkbox\" Name=\"do_usuniecia\" Value =\"" + temp.getId_sprawozdania() + "\"></td>" +
                            "<td>" + temp.getId_sprawozdania() + "</td>" +
                            "<td>" + temp.getTytul() + "</td>" +
                            "<td>" + temp.getTyp() + "</td>" +
                            "<td>" + date + "</td>" +
                            "<td>" + temp.getTresc() + "</td>" +
                            "</tr>");
                }
            %>

            </tbody>
        </table>
        <button class="btn btn-primary float-none align-self-center" type="submit" style="width:265px;position:static;text-align:center!important;margin-left:437px;margin-top:20px;">Usuń Raport</button>
    </form>
    <a href="../ControllerAccount?page=raport_upload"><button type="button" class="btn btn-primary float-none align-self-center" style="width:265px;position:static;text-align:center!important;margin-left:437px;margin-top:20px;">Dodaj Raport</button></a>
</section>
</html>
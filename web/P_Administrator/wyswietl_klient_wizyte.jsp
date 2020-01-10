<%@ page import="myPage.data.others.SessionData" %>
<%@ page import="myPage.data.others.TypKonta" %>
<%@ page import="myPage.basicObjects.Klient" %>
<%@ page import="myPage.data.dataBase.KlientData" %>
<%@ page import="java.sql.SQLException" %>
<%@ page contentType="text/html; charset=UTF-8" language="java" %>

<!DOCTYPE html>
<html>

<%
    session = request.getSession();
    SessionData sessionData = (SessionData) session.getAttribute("userData");
    if(sessionData.getAccoutType() == TypKonta.ADMINISTRATOR)
        out.print("    <section class=\"bg-light-gray\" style=\"margin:0;background-color:rgba(0,0,0,0.11);color:#ffffff;padding-bottom:20px;padding-top:20px;max-width:1140px;margin-right:auto;margin-left:auto;border-radius:20px;    margin-bottom: 65px;\">\n");
    else
        out.print("    <section class=\"bg-light-gray\" style=\"margin:0;background-color:rgba(0,0,0,0.11);color:#ffffff;padding-bottom:20px;padding-top:20px;max-width:1140px;margin-right:auto;margin-left:auto;border-radius:20px;    margin-bottom: 65px;\">\n");
%>

        <h2 class="text-center" style="height:79px;">Podgląd wizyt klienta</h2>
        <h5 class="text-center" style="height:21px;margin-right:50px;margin-left:50px;">Poniżej zamieszczona jest lista klientów gabinetu <br> znajdujących się w bazie systemu.</h5>

        <form action="${pageContext.request.contextPath}/ControllerGetWizytyKlient" method="post">
            <h6 class="text-center" id="informacja" style="height:44px;margin-right:50px;margin-left:50px;font-weight: 100;margin-top: 88px;">Wybierz imię i nazwisko klienta, a następnie zatwierdź operację by sprawdzić jego wizyty.</h6>

            <div class="form-group" style="margin-bottom:-2px;">
                <select class="form-control" name="klient_id" required="" id="klient_id" style="margin:0;margin-left:437px;width:265px;margin-bottom:16px;margin-top: -17px;">
                    <%
                        Klient klienci = new Klient();
                        KlientData temp_klient;

                        try {
                            klienci.getAllData();
                        } catch (SQLException e) {
                            e.printStackTrace();
                        }

                        while (!klienci.KlientEmpty()) {
                            temp_klient = klienci.KlientPop();
                            out.print("<option value=\"" + temp_klient.getId() + "\">" + temp_klient.getNazwisko() + " " + temp_klient.getImie() + "</option>");
                        }
                    %>
                </select>
            </div>
            <label style="font-size:17px;margin-left:404px;">Data od</label>
            <input class="form-control" type="date" name="data_od" required="" style="margin:0;    width: 265px;margin-left: 437px;margin-bottom: 10px;">
            <label style="font-size:17px;margin-left:404px;">Data do</label>
            <input class="form-control" type="date" name="data_do" required="" style="margin:0;    width: 265px;margin-left: 437px;">
            <button type="submit" class="btn btn-primary float-none align-self-center" style="margin-top: 20px;width:265px;margin-left: 437px;">Załaduj wizyty</button></a>
        </form>
    </section>
</html>
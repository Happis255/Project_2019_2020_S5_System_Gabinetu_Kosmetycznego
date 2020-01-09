<%@ page import="myPage.basicObjects.Klient" %>
<%@ page import="myPage.data.dataBase.KlientData" %>
<%@ page import="java.sql.SQLException" %>
<%@ page import="myPage.data.others.SessionData" %>
<%@ page import="myPage.data.others.TypKonta" %>
<%@ page contentType="text/html; charset=UTF-8" language="java" %>

<!DOCTYPE html>
<html>

<%
    session = request.getSession();
    SessionData sessionData = (SessionData) session.getAttribute("userData");
    if(sessionData.getAccoutType() == TypKonta.ADMINISTRATOR)
        out.print("    <section class=\"bg-light-gray\" style=\"margin:0;background-color:rgba(0,0,0,0.11);color:#ffffff;padding-bottom:20px;padding-top:20px;max-width:1140px;margin-right:auto;margin-left:auto;border-radius:20px;margin-bottom: 217px;\">\n");
    else
        out.print("    <section class=\"bg-light-gray\" style=\"margin:0;background-color:rgba(0,0,0,0.11);color:#ffffff;padding-bottom:20px;padding-top:20px;max-width:1140px;margin-right:auto;margin-left:auto;border-radius:20px;margin-bottom: 241px;\">\n");
%>

        <h2 class="text-center" style="height:79px;">Klienci Gabinetu</h2>
        <h5 class="text-center" style="height:21px;margin-right:50px;margin-left:50px;">Poniżej zamieszczona jest lista klientów gabinetu <br> znajdujących się w bazie systemu.</h5>

        <form action="${pageContext.request.contextPath}/ControllerClientGetter" method="post">
            <h6 class="text-center" id="informacja" style="height:44px;margin-right:50px;margin-left:50px;font-weight: 100;margin-top: 88px;">Wybierz imię i nazwisko klienta, by pobrać podgląd jego danych.</h6>

            <div class="form-group" style="margin-bottom:-2px;">
                <select class="form-control" name="klient_id" required="" id="klient_id" style="margin:0;margin-left:437px;width:265px;margin-bottom:16px;margin-top: -17px;">
                    <%
                        Klient klient = new Klient();
                        KlientData temp_klient;

                        try {
                            klient.getAllData();
                        } catch (SQLException e) {
                            e.printStackTrace();
                        }

                        while (!klient.KlientEmpty()) {
                            temp_klient = klient.KlientPop();
                            out.print("<option value=\"" + temp_klient.getId() + "\">" + temp_klient.getImie() + " " + temp_klient.getNazwisko() + "</option>");
                        }
                    %>
                </select>
            </div>

            <button type="submit" class="btn btn-primary float-none align-self-center" style="width:265px;margin-left: 437px;">Załaduj dane</button></a>
        </form>
    </section>
</html>
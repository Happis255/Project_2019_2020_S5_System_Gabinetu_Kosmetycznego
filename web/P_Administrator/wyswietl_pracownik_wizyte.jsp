<%@ page import="myPage.data.others.SessionData" %>
<%@ page import="myPage.data.others.TypKonta" %>
<%@ page import="myPage.basicObjects.Pracownik" %>
<%@ page import="myPage.data.dataBase.PracownikData" %>
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

        <h2 class="text-center" style="height:79px;">Podgląd wizyt pracownika</h2>
        <h5 class="text-center" style="height:21px;margin-right:50px;margin-left:50px;">Poniżej zamieszczona jest lista pracowników gabinetu <br> znajdujących się w bazie systemu.</h5>

        <form action="${pageContext.request.contextPath}/ControllerLoadWorkerMeetings" method="post">
            <h6 class="text-center" id="informacja" style="height:44px;margin-right:50px;margin-left:50px;font-weight: 100;margin-top: 88px;">Wybierz imię i nazwisko pracownika, a następnie zatwierdź operację by sprawdzić jego wizyty.</h6>

            <div class="form-group" style="margin-bottom:-2px;">
                <select class="form-control" name="pracownik_id" required="" id="klient_id" style="margin:0;margin-left:437px;width:265px;margin-bottom:16px;margin-top: -17px;">
                    <%
                        Pracownik pracownik = new Pracownik();
                        PracownikData temp_pracownik;

                        pracownik.getAllData();

                        while (!pracownik.emptyPracownik()) {
                            temp_pracownik = pracownik.popPracownik();
                            out.print("<option value=\"" + temp_pracownik.getId() + "\">" + temp_pracownik.getNazwisko() + " " + temp_pracownik.getImie() + "</option>");
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
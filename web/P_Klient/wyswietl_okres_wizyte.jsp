<%@ page import="myPage.data.others.SessionData" %>
<%@ page import="myPage.data.others.TypKonta" %>
<%@ page contentType="text/html; charset=UTF-8" language="java" %>

<!DOCTYPE html>
<html>

<%
    session = request.getSession();
    SessionData sessionData = (SessionData) session.getAttribute("userData");
    if(sessionData.getAccoutType() == TypKonta.ADMINISTRATOR)
        out.print("    <section class=\"bg-light-gray\" style=\"margin:0;background-color:rgba(0,0,0,0.11);color:#ffffff;padding-bottom:20px;padding-top:20px;max-width:1140px;margin-right:auto;margin-left:auto;border-radius:20px;    margin-bottom: 147px;\">\n");
    else if(sessionData.getAccoutType() == TypKonta.KLIENT)
        out.print("    <section class=\"bg-light-gray\" style=\"margin:0;background-color:rgba(0,0,0,0.11);color:#ffffff;padding-bottom:20px;padding-top:20px;max-width:1140px;margin-right:auto;margin-left:auto;border-radius:20px;    margin-bottom: 178px;\">\n");
    else
        out.print("    <section class=\"bg-light-gray\" style=\"margin:0;background-color:rgba(0,0,0,0.11);color:#ffffff;padding-bottom:20px;padding-top:20px;max-width:1140px;margin-right:auto;margin-left:auto;border-radius:20px;    margin-bottom: 147px;\">\n");
%>
        <h2 class="text-center" style="height:79px;">Podgląd wizyt w danym okresie</h2>
        <h5 class="text-center" style="height:21px;margin-right:50px;margin-left:50px;">Wybierz przedział czasowy wizyt.</h5>
        <h6 class="text-center" id="informacja" style="height:44px;margin-right:50px;margin-left:50px;font-weight: 100;margin-bottom: 40px;">Nazstępnie zatwierdź operację, by załadować listę wizyt w wybranym przedziale czasowym.</h6>
        <form action="${pageContext.request.contextPath}/ControllerGetWizytyDataKlient" method="post">
            <label style="font-size:17px;margin-left:404px;">Data od</label>
            <input class="form-control bdinput"  type="date" name="data_od" required="" style="margin:0;    width: 265px;margin-left: 437px;margin-bottom: 10px;">
            <label style="font-size:17px;margin-left:404px;">Data do</label>
            <input  class="form-control bdinput2" type="date" name="data_do" required="" style="margin:0;    width: 265px;margin-left: 437px;">
            <a><button class="btn btn-primary float-none align-self-center" type="submit" name="opcja" value="potwierdz" style="width:265px;margin-left: 437px;margin-top:20px;">Potwierdź wizytę</button></a>
        </form>
    </section>
</html>
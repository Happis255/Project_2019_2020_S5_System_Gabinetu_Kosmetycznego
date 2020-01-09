<%@ page import="myPage.basicObjects.Nieobecnosc" %>
<%@ page import="myPage.data.dataBase.NieobecnoscData" %>
<%@ page import="java.text.SimpleDateFormat" %>
<%@ page import="myPage.data.others.SessionData" %>
<%@ page import="myPage.basicObjects.Usluga" %>
<%@ page import="myPage.data.dataBase.UslugaData" %>
<%@ page import="myPage.basicObjects.Wizyta" %>
<%@ page import="myPage.data.dataBase.WizytaData" %>
<%@ page import="myPage.basicObjects.Pracownik" %>
<%@ page import="java.awt.print.PrinterAbortException" %>
<%@ page import="myPage.data.dataBase.PracownikData" %>
<%@ page import="java.util.HashMap" %>
<%@ page import="java.util.Date" %>
<!DOCTYPE html>
<html>
<section id="wizyty_czas" class="bg-light-gray" style="margin:0;background-color:rgba(0,0,0,0.11);color:#ffffff;padding-bottom:20px;padding-top:20px;max-width:1140px;margin-right:auto;margin-left:auto;border-radius:20px;margin-bottom:30px;">
    <form if="datePicker" action="${pageContext.request.contextPath}/ControllerVisitsAdder?visitSubpage=visits_hoursPick">
        <input id="inputData" class="form-control" type="date" name="data-wizyty" required="" style="margin:0;width:500px;margin-left:145px;margin-top:15px;">
        <button type="submit"  style="width:265px;position:static;text-align:center!important;margin-left:437px;margin-top:20px;" class="btn btn-primary float-none align-self-center" type="button">szykaj terminow</button>
    </form>
</section>
</html>
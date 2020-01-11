<%@ page import="myPage.basicObjects.Usluga" %>
<%@ page import="myPage.data.dataBase.UslugaData" %>
<%@ page import="myPage.data.others.SessionData" %>
<%@ page import="myPage.data.others.VisitPage" %>
<!DOCTYPE html>
<html>
<section id="wizyty_klient" class="bg-light-gray" style="margin:0;background-color:rgba(0,0,0,0.11);color:#ffffff;padding-bottom:20px;padding-top:20px;max-width:1000px;margin-right:auto;margin-left:auto;border-radius:20px;margin-top: 150px;margin-bottom: 180px;">
    <h2 class="text-center" style="height:79px;">Tworzenie wizyty</h2>
    <h5 class="text-center" style="height:21px;margin-right:50px;margin-left:50px;">Aby utworzyć wizytę, wybierz wpierw interesującą Cię usługę.</h5>
    <h6 class="text-center" id="RODO" style="height:44px;margin-right:50px;margin-left:50px;">Następnie wybierz pracownika, który jest dostępny oraz datę i godzinę wizyty.
        <br>Jeśli wizyta zostanie pomyślnie utworzona, oczekuj na potwierdzenie e-mail.</h6>
    <form id="datePicker" action="${pageContext.request.contextPath}/ControllerVisitsAdderServicePick" method="post">
            <%
                SessionData sessionData = (SessionData)session.getAttribute("userData");
                VisitPage visitPage = (VisitPage)request.getSession().getAttribute("VisitPage");
                Usluga usluga = new Usluga();
                UslugaData temp = null;
                String row;
                try {
                    usluga.getEverything();
                } catch (Exception e) {
                    e.printStackTrace();
                }

                row ="<select class=\"form-control\" name=\"usluga\" value=\"\" required=\"\" style=\"    margin: 0;\n" +
                        "    margin-bottom: 16px;\n" +
                        "    width: 500px;\n" +
                        "    position: static;\n" +
                        "    text-align: center!important;\n" +
                        "    margin-left: 250px;\n" +
                        "    margin-top: 20px;\">\n";
                out.println(row);
                while (!usluga.uslugiEmpty()){
                    temp = usluga.uslugaPop();
                    row = "<option value=\"" + temp.getId_uslugi() +"\">" +temp.getTyp_uslugi() +", " + temp.getNazwa() +", " + temp.getCena() +" PLN</option>\n";
                    out.println(row);
                }
            %>
        </select>
        <button class="btn btn-primary" type="submit" style="    width: 265px;position: static;text-align: center!important;margin-left: 367px;margin-top: 20px;">Wybierz Usługę</button>
    </form>
</section>
</html>
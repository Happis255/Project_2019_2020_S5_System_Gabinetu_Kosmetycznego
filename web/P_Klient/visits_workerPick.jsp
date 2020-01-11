<%@ page import="myPage.basicObjects.Pracownik" %>
<%@ page import="myPage.data.dataBase.PracownikData" %>
<%@ page import="myPage.data.others.VisitPage" %>
<!DOCTYPE html>
<html>
<section id="wizyty_pracownik" class="bg-light-gray" style="margin:0;background-color:rgba(0,0,0,0.11);color:#ffffff;padding-bottom:20px;padding-top:20px;max-width:1000px;margin-right:auto;margin-left:auto;border-radius:20px;margin-top: 77px;margin-bottom: 93px;">
    <h2 class="text-center" style="height:79px;">Tworzenie wizyty</h2>
    <h5 class="text-center" style="height:21px;margin-right:50px;margin-left:50px;">Aby utworzyć wizytę, wybierz wpierw interesującą Cię usługę.</h5>
    <h6 class="text-center" id="RODO" style="height:44px;margin-right:50px;margin-left:50px;">Następnie wybierz pracownika, który jest dostępny oraz datę i godzinę wizyty.
        <br>Jeśli wizyta zostanie pomyślnie utworzona, oczekuj na potwierdzenie e-mail.</h6>
    <form id="datePicker" action="${pageContext.request.contextPath}/ControllerVisitsAdderWorkerPick" method="post">
        <label style="margin-top: 12px;font-size: 17px;margin-left:327px;">Pracownik</label>
            <%

                String row ="<select class=\"form-control\" name=\"pracownik\" required=\"\" style=\"    width: 265px;\n" +
                        "    position: static;\n" +
                        "    margin-left: 367px;\">";
                out.println(row);

                Pracownik pracownicy = new Pracownik();
                VisitPage visitPage = (VisitPage)request.getSession().getAttribute("VisitPage");
                Integer idUslugi = visitPage.getIdUslugi();
                PracownikData temp2 = null;

                try {
                    System.out.println(idUslugi);
                    pracownicy.getPracownicyOdUslugi(idUslugi);
                } catch (Exception e) {
                    e.printStackTrace();
                }

                if(pracownicy.emptyPracownik()){
                    out.println("</select>" +
                            "    <h5 class=\"text-center\" style=\"height:21px;margin-right:50px;margin-left:50px;\">Brak dostępnych pracowników.</h5>");
                }

                while (!pracownicy.emptyPracownik()){
                    temp2 = pracownicy.popPracownik();
                    row = "<option value=\"" + temp2.getId() +"\">" +temp2.getNazwisko() +", " + temp2.getImie() +" </option>\n";
                    out.println(row);
                }
            %>
        </select>
        <label style="margin-top: 12px;font-size: 17px;margin-left:327px;">Data wizyty</label>
        <input id="data" class="form-control" type="date" name="data-wizyty" required="" style="width:265px;position:static;margin-left:367px;">
        <button class="btn btn-primary" type="submit" style="    width: 265px;position: static;text-align: center!important;margin-left: 367px;margin-top: 20px;">Wybierz godzinę</button>
    </form>
    <a href="../ControllerAccount?page=wizytyService"><button class="btn btn-primary float-none align-self-center" type="button" style="    width: 265px;position: static;text-align: center!important;margin-left: 367px;margin-top: 20px;">Wróć do wyboru usługi</button></a>
</section>
</html>
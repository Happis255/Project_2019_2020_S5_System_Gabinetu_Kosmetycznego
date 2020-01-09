<%@ page import="myPage.basicObjects.Sprzet" %>
<%@ page import="myPage.data.dataBase.SprzetData" %>
<%@ page import="java.sql.SQLException" %>
<%@ page import="myPage.data.others.SessionData" %>
<%@ page import="myPage.data.others.TypKonta" %>
<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<form action="${pageContext.request.contextPath}/AddPrzeglad" method="post">

    <%
        session = request.getSession();
        SessionData sessionData = (SessionData) session.getAttribute("userData");
        if(sessionData.getAccoutType() == TypKonta.ADMINISTRATOR)
            out.print("<section id=\"add_service\" class=\"bg-light-gray\" style=\"margin:0;background-color:rgba(0,0,0,0.11);color:#ffffff;padding-bottom:20px;padding-top:20px;max-width:800px;margin-right:auto;margin-left:auto;border-radius:20px;margin-bottom:30px;\">\n");
        else
            out.print("<section id=\"add_service\" class=\"bg-light-gray\" style=\"margin:0;background-color:rgba(0,0,0,0.11);color:#ffffff;padding-bottom:20px;padding-top:20px;max-width:800px;margin-right:auto;margin-left:auto;border-radius:20px;margin-bottom:54px;\">\n");
    %>
        <h2 class="text-center" style="height:79px;">Dodaj przegląd</h2>
        <h5 class="text-center" style="height:21px;margin-right:50px;margin-left:50px;">Wybierz sprzęt i wprowadź dane przeglądu.</h5>
        <h6 class="text-center" id="Informacja_Upload" style="font-weight:300;height:44px;margin-right:50px;margin-left:50px;">Następnie upewnij się, czy dane zostały wypełnione prawidłowo i zatwierdź przegląd.</h6>
        <div class="form-group">
            <label style="font-size:17px;margin-left:101px;margin-top:19px">Wybierz sprzęt</label>
            <select class="form-control" name="do_przegladu" value="do_przegladu" required="" id="do_przegladu" style="margin:0;margin-left:145px;width:500px;margin-bottom:16px;">
                <%
                    Sprzet sprzet_w_gabinecie = new Sprzet();
                    SprzetData odczytany_sprzet;
                    try {
                        sprzet_w_gabinecie.getEverything();
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }

                    while (!sprzet_w_gabinecie.sprzetyListaEmpty()) {
                        odczytany_sprzet = sprzet_w_gabinecie.sprzetPop();
                        out.print("<option value=\"" + odczytany_sprzet.getId_sprzetu() + "\">" + odczytany_sprzet.getNazwa_sprzetu() + "</option>");
                    }
                %>
            </select>
            <label style="font-size:17px;margin-left:101px;">Tytuł i treść</label>
            <input class="form-control" type="text" name="tytul_przegladu" required="" placeholder="Tytuł raportu" maxlength="255" minlength="5" style="margin:0;width:500px;margin-left:145px;margin-top: 0px;">
            <input class="form-control" type="text" name="opis_przegladu" required="" placeholder="Treść raportu" maxlength="5000" minlength="10" style="margin:0;width:500px;margin-left:145px;margin-top: 20px;">
            <div class="form-group"><button class="btn btn-primary" type="submit" style="margin:0;width:265px;margin-left:267px;margin-top: 51px;">Dodaj raport</button></div>
        </div>
</section>
</form>
</html>
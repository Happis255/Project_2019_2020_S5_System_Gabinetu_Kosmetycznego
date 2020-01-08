<%@ page import="myPage.basicObjects.Pracownik" %>
<%@ page import="myPage.data.dataBase.PracownikData" %>
<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
    <section id="add_absence" class="bg-light-gray" style="margin:0;background-color:rgba(0,0,0,0.11);color:#ffffff;padding-bottom:20px;padding-top:20px;max-width:800px;margin-right:auto;margin-left:auto;border-radius:20px;margin-bottom:30px;">
        <form action="${pageContext.request.contextPath}/ControllerAddZadanie" method="post">
            <h2 class="text-center" style="height:79px;">Dodaj zadanie gospodarcze</h2>
            <h5 class="text-center" style="height:21px;margin-right:50px;margin-left:50px;">Wprowadź dane dotyczące dodawanego zadania gospodarczego oraz wybierz odpowiedzialnego pracownika.</h5>
            <div class="form-group">
                <input class="form-control" type="text" name="nazwa" required="" placeholder="Nazwa zadania" maxlength="255" minlength="1" style="margin:0;width:500px;margin-left:145px;margin-top: 94px;">
                <input class="form-control" type="text" name="opis" required="" placeholder="Opis zadania" maxlength="255" minlength="1" style="margin:0;width:500px;margin-left:145px;margin-top: 15px;">
                <label style="font-size:17px;margin-left:101px;margin-top:17px;">Data rozpoczęcia obowiązku</label>
                <input class="form-control" type="date" name="data_od" required="" style="margin:0;width:500px;margin-left:145px;margin-top:-7px;">
                <label style="font-size:17px;margin-left:101px;margin-top:17px;">Data zakończenia obowiązku</label>
                <input class="form-control" type="date" name="data_do" required="" style="margin:0;width:500px;margin-left:145px;margin-top:-7px;">
                <label style="font-size:17px;margin-left:101px;margin-top:19px">Wybierz pracownika</label>
                <select class="form-control" name="id_pracownika" value="id_pracownika" required="" id="id_pracownika" style="margin:0;margin-left:145px;width:500px;margin-bottom:16px;">
                    <%
                        /* Pobieramy listę produktów gabinetu */
                        Pracownik pracownik = new Pracownik();
                        PracownikData temp;
                        pracownik.getAllData();

                        while (!pracownik.emptyPracownik()) {
                            temp = pracownik.popPracownik();
                            out.print("<option value=\"" + temp.getId() + "\">" + temp.getImie() + " " + temp.getNazwisko() + "</option>");
                        }
                    %>
                </select>
                <div class="form-group"><button class="btn btn-primary" type="submit" style="margin:0;width:265px;margin-left:267px;">Dodaj zadanie</button></div>
            </div>
        </form>
    </section>
</html>
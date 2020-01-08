<%@ page import="myPage.basicObjects.Pracownik" %>
<%@ page import="myPage.data.dataBase.PracownikData" %>
<%@ page import="myPage.basicObjects.ZadanieGospodarcze" %>
<%@ page import="myPage.data.dataBase.ZadanieGospodarczeData" %>
<%@ page import="java.sql.SQLException" %>
<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
    <section id="add_absence" class="bg-light-gray" style="margin:0;background-color:rgba(0,0,0,0.11);color:#ffffff;padding-bottom:20px;padding-top:20px;max-width:800px;margin-right:auto;margin-left:auto;border-radius:20px;margin-bottom:94px;">
        <form action="${pageContext.request.contextPath}/ControllerAssignZadanie" method="post">
            <h2 class="text-center" style="height:79px;">Dodaj zadanie gospodarcze</h2>
            <h5 class="text-center" style="height:21px;margin-right:50px;margin-left:50px;">Wprowadź dane dotyczące dodawanego zadania gospodarczego oraz wybierz odpowiedzialnego pracownika.</h5>
            <div class="form-group">
                <label style="font-size:17px;margin-left:101px;margin-top:100px">Wybierz pracownika</label>
                <select class="form-control" name="id_pracownika" value="id_pracownika" required="" id="id_pracownika" style="margin:0;margin-left:145px;width:500px;margin-bottom:16px;">
                    <%
                        Pracownik pracownik = new Pracownik();
                        PracownikData temp;
                        pracownik.getAllData();

                        while (!pracownik.emptyPracownik()) {
                            temp = pracownik.popPracownik();
                            out.print("<option value=\"" + temp.getId() + "\">" + temp.getImie() + " " + temp.getNazwisko() + "</option>");
                        }
                    %>
                </select>
                <label style="font-size:17px;margin-left:101px;">Wybierz zadanie</label>
                <select class="form-control" name="id_zadania" value="id_zadania" required="" id="id_zadania" style="margin:0;margin-left:145px;width:500px;margin-bottom:16px;">
                    <%
                        ZadanieGospodarcze zadanie = new ZadanieGospodarcze();
                        ZadanieGospodarczeData temp2;
                        try {
                            zadanie.getAllZadania();
                            while (!zadanie.listaZadanEmpty()) {
                                temp2 = zadanie.listaPop();
                                out.print("<option value=\"" + temp2.getId_zadania() + "\">" + temp2.getTytul() + " : " + temp2.getTresc() + "</option>");
                            }
                        } catch (SQLException e) {
                            e.printStackTrace();
                        }
                    %>
                </select>
                <div class="form-group"><button class="btn btn-primary" type="submit" style="margin:0;width:265px;margin-left:267px;">Przypisz zadanie</button></div>
            </div>
        </form>
    </section>
</html>
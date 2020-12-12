<%@ page import="myPage.basicObjects.Pracownik" %>
<%@ page import="myPage.data.dataBase.PracownikData" %>
<%@ page import="myPage.basicObjects.Usluga" %>
<%@ page import="java.sql.SQLException" %>
<%@ page import="myPage.exceptions.DBReadWriteException" %>
<%@ page import="myPage.data.dataBase.UslugaData" %>
<section id="service_approver" class="bg-light-gray" style="margin:0;background-color:rgba(0,0,0,0.11);color:#ffffff;padding-bottom:20px;padding-top:20px;max-width:800px;margin-right:auto;margin-left:auto;border-radius:20px;margin-bottom: 82px;margin-top: 43px;">
    <form method="post" action="${pageContext.request.contextPath}/ControllerWorkerServiceAdder">
    <h2 class="text-center" style="height:79px;">Nadawanie uprawnienia pracownikowi</h2>
    <h5 class="text-center" style="height:21px;margin-right:50px;margin-left:50px;">Aby nadać pracownikowi możliwość wykonywania danego zabiegu,<br>wybierz odpowiedniego pracownika.<br><br></h5>
    <h6 class="text-center" id="RODO" style="margin-top: 33px;height:44px;margin-right:50px;margin-left:50px;">Następnie wybierz usługę z bazy usług i zatwierdź operację.</h6><br>
    <div class="form-group"><label style="font-size:17px;margin-left:101px;">Wybierz pracownika</label>
        <div class="form-group" style="margin-bottom:-2px;">
            <select class="form-control" name="pracownik_nadaj" value="pracownik_nadaj" required="" id="pracownik_nadaj" style="margin:0;margin-left:145px;width:500px;margin-bottom:16px;">
                <%
                    /* Pobieramy listę pracowników gabinetu - pracujących */
                    Pracownik pracownicy_gabinetu = new Pracownik();
                    PracownikData temp_pracownik;

                    pracownicy_gabinetu.getAllData();

                    while (!pracownicy_gabinetu.emptyPracownik()) {
                        temp_pracownik = pracownicy_gabinetu.popPracownik();
                        out.print("<option value=\"" + temp_pracownik.getId() + "\">" + temp_pracownik.getNazwisko() + " " + temp_pracownik.getImie() + "</option>");
                    }
                %>
            </select>
        </div>
    </div>
    <div class="form-group"><label style="font-size:17px;margin-left:101px;">Wybierz usługę</label>
        <div class="form-group" style="margin-bottom:-2px;">
            <select class="form-control" name="usluga_nadaj" value="usluga_nadaj" required="" id="usluga_nadaj" style="margin:0;margin-left:145px;width:500px;margin-bottom:16px;">
                <%
                    /* Pobieramy listę pracowników gabinetu - pracujących */
                    Usluga uslugi = new Usluga();
                    UslugaData usluga_temp;

                    try {
                        uslugi.getEverything();
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }

                    while (!uslugi.uslugiEmpty()) {
                        usluga_temp = uslugi.uslugaPop();
                        out.print("<option value=\"" + usluga_temp.getId_uslugi() + "\">" + usluga_temp.getTyp_uslugi() + " - " + usluga_temp.getNazwa() + "</option>");
                    }
                %>
            </select>
        </div>
    </div>

    <div class="form-group"><button class="btn btn-primary" type="submit" style="margin:0;width:265px;margin-left: 267px;margin-top: 26px;">Nadaj uprawnienie</button></div>
</form>
</section>
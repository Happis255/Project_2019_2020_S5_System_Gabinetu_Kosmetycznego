<%@ page import="myPage.basicObjects.Pracownik" %>
<%@ page import="myPage.data.dataBase.PracownikData" %>
<%@ page import="myPage.basicObjects.Usluga" %>
<%@ page import="java.sql.SQLException" %>
<%@ page import="myPage.data.dataBase.UslugaData" %>
<%@ page import="myPage.basicObjects.Klient" %>
<%@ page import="myPage.data.dataBase.KlientData" %>
<section id="meeting_creator_work" class="bg-light-gray" style="margin:0;background-color:rgba(0,0,0,0.11);color:#ffffff;padding-bottom:20px;padding-top:20px;max-width:800px;margin-right:auto;margin-left:auto;border-radius:20px;margin-bottom: 58px;margin-top: 43px;">
    <form method="post" action="${pageContext.request.contextPath}/ControllerCreateWizytaPracownik">
    <h2 class="text-center" style="height:79px;">Utwórz wizytę klientowi</h2>
    <h5 class="text-center" style="height:21px;margin-right:50px;margin-left:50px;">Aby utworzyć wizytę, wybierz usługę oraz pracownika.<br><br></h5>
    <h6 class="text-center" id="RODO" style="height:44px;margin-right:50px;margin-left:50px;">Następnie ustal datę oraz godzinę i zatwierdź operację.</h6><br>
    <div class="form-group"><label style="font-size:17px;margin-left:101px;">Wybierz pracownika</label>
        <div class="form-group" style="margin-bottom:-2px;">
            <select class="form-control" name="pracownik_id" value="pracownik_id" required="" id="pracownik_id" style="margin:0;margin-left:145px;width:500px;margin-bottom:16px;">
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
            <select class="form-control" name="usluga_id" value="usluga_id" required="" id="usluga_id" style="margin:0;margin-left:145px;width:500px;margin-bottom:16px;">
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
        <div class="form-group"><label style="font-size:17px;margin-left:101px;">Wybierz klienta</label>
            <div class="form-group" style="margin-bottom:-2px;">
                <select class="form-control" name="klient_id" value="klient_id" required="" id="klient_id" style="margin:0;margin-left:145px;width:500px;margin-bottom:16px;">
                    <%
                        /* Pobieramy listę pracowników gabinetu - pracujących */
                        Klient klienci = new Klient();
                        KlientData klient_data;

                        try {
                            klienci.getAllData();
                        } catch (SQLException e) {
                            e.printStackTrace();
                        }

                        while (!klienci.KlientEmpty()) {
                            klient_data = klienci.KlientPop();
                            out.print("<option value=\"" + klient_data.getId() + "\">" + klient_data.getNazwisko() + " - " + klient_data.getImie() + "</option>");
                        }
                    %>
                </select>
            </div>
        </div>
        <label style="font-size:17px;margin-left:101px;">Termin wizyty</label>
        <input class="form-control" type="date" name="data" required="" style="margin:0;width:500px;margin-left:145px;">
        <div class="form-group"><label style="font-size:17px;margin-left:101px;margin-top: 14px;">Wybierz godzinę</label>
            <div class="form-group" style="margin-bottom:-2px;">
                <select class="form-control" name="godzina" value="godzina" required="" id="godzina" style="margin:0;margin-left:145px;width:500px;margin-bottom:16px;">
                    <%
                        /* Pobieramy listę pracowników gabinetu - pracujących */
                        String godzina_minuty;
                        int godzina = 10;

                        while (godzina < 18){
                                godzina_minuty = godzina + ":00";
                                out.print("<option value=\"" + godzina_minuty + "\">" + godzina_minuty + "</option>");
                                godzina_minuty = godzina + ":30";
                                out.print("<option value=\"" + godzina_minuty + "\">" + godzina_minuty + "</option>");
                                godzina++;
                        }
                    %>
                </select>
            </div>
        </div>
    <div class="form-group"><button class="btn btn-primary" type="submit" style="margin:0;width:265px;margin-left: 267px;margin-top: 26px;">Utwórz wizytę</button></div>
</form>
</section>
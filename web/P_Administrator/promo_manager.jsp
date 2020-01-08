<%@ page import="myPage.data.dataBase.StatusData" %>
<%@ page import="myPage.basicObjects.Status_Klienta" %>
<%@ page import="java.sql.SQLException" %>
<%@ page import="myPage.basicObjects.Promocje" %>
<%@ page import="myPage.data.dataBase.PromocjaData" %>
<%@ page import="myPage.data.dataBase.PracownikData" %>
<%@ page import="myPage.basicObjects.Pracownik" %>
<%@ page import="java.text.SimpleDateFormat" %>
<%@ page import="java.text.DateFormat" %>
<!DOCTYPE html>
<html>

<section id="opis" class="bg-light-gray" style="margin:0;background-color:rgba(0,0,0,0.11);color:#ffffff;padding-bottom:20px;padding-top:20px;max-width:1140px;margin-right:auto;margin-left:auto;border-radius:20px;margin-bottom:30px;">
    <h2 class="text-center" style="height:79px;">Status klienta i promocje</h2>
    <h5 class="text-center" style="height:21px;margin-right:50px;margin-left:50px;">W poniższej sekcji znajdują się statusy oraz promocje wykorzystywane w systemie.</h5>
    <h6 class="text-center" id="informacja" style="height:10px;margin-right:50px;margin-left:50px;font-weight: 100;">Wybierz odpowiednią opcję, aby dodać nowy status klienta bądź przejść do edycji statusu. Pamiętaj - statusów nie da się usunąć.</h6>
    <h6 class="text-center" id="informacja2" style="height:44px;margin-right:50px;margin-left:50px;font-weight: 100;">Zaznacz wybraną promocję, aby ją usunąć. Wybierz również odpowiednią opcję, aby ją dodać bądź edytować.</h6>
</section>

    <section id="promo_status_setter" class="bg-light-gray" style="margin:0;background-color:rgba(0,0,0,0.11);color:#ffffff;padding-bottom:20px;padding-top:20px;max-width:1140px;margin-right:auto;margin-left:auto;border-radius:20px;margin-bottom:30px;">
        <h2 class="text-center" style="height:79px;">Statusy klientów</h2>
        <table id="tablica_status" class="table" cellspacing="0" width="100%" style="text-align: center;margin-bottom: 0;border: 3px solid #FFFFFF;width: 98%;max-width: 98%;margin-left: 12px;background-color: transparent;border-collapse: collapse;">
            <thead>
            <tr>
                <th>ID</th>
                <th>NAZWA STATUSU</th>
                <th>PUNKTY OD</th>
                <th>PUNKTY DO</th>
                <th>ZNIŻKA PROCEN.</th>
                <th>ZNIŻKA KWOTOWA</th>
                <th>LICZBA KLIENTÓW</th>
            </tr>
            </thead>
            <tbody>

            <%
                Status_Klienta statusy = new Status_Klienta();
                StatusData temp;
                try {
                    statusy.getStatusy();
                } catch (Exception e) {
                    e.printStackTrace();
                }

                int liczba = 0;
                while (!statusy.statusyEmpty()){
                    temp = statusy.statusyPop();

                    try {
                        liczba =  statusy.countStatus(temp.getId_statusu());
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }

                    out.println("<tr>" +
                                "<td>" + temp.getId_statusu() + "</td>" +
                                "<td>" + temp.getNazwa() + "</td>" +
                                "<td>" + temp.getPunkty_od() + "</td>" +
                                "<td>" + temp.getPunkty_do() + "</td>" +
                                "<td>" + temp.getZnizka_proc() + " %</td>" +
                                "<td>" + temp.getZnizka_kwot() + " PLN</td>" +
                                "<td>" + liczba + "</td>" +
                                "</tr>");
                }
            %>

            </tbody>
            </table>
            <a href="../ControllerAccount?page=dodaj_status"><button class="btn btn-primary float-none align-self-center" type="button" style="width:265px;position:static;text-align:center!important;margin-left:437px;margin-top:20px;">Dodaj status</button></a>
            <a href="../ControllerAccount?page=edytuj_status"><button class="btn btn-primary float-none align-self-center" type="button" style="width:265px;position:static;text-align:center!important;margin-left:437px;margin-top:20px;">Edytuj status</button></a>
        </section>

<section id="promo_manager" class="bg-light-gray" style="margin:0;background-color:rgba(0,0,0,0.11);color:#ffffff;padding-bottom:20px;padding-top:20px;max-width:1140px;margin-right:auto;margin-left:auto;border-radius:20px;margin-bottom:30px;">
    <form action="${pageContext.request.contextPath}/ControllerPromoRemover" method="post">
        <h2 class="text-center" style="height:79px;">Promocje</h2>
        <table id="tabela promocji" class="table" cellspacing="0" width="100%" style="text-align: center;margin-bottom: 0;border: 3px solid #FFFFFF;width: 98%;max-width: 98%;margin-left: 12px;background-color: transparent;border-collapse: collapse;">
            <thead>
            <tr>
                <th></th>
                <th>ID</th>
                <th>NAZWA</th>
                <th>OPIS</th>
                <th>ZNIZKA PROC.</th>
                <th>ZNIZKA KWOT.</th>
                <th>DATA OD</th>
                <th>DATA DO</th>
                <th>PRACOWNIK</th>
            </tr>
            </thead>
            <tbody>

            <%
                Promocje promocje_gabinetu = new Promocje();
                PromocjaData odczytany;
                PracownikData odpowiedzialny_pracownik;

                try {
                    promocje_gabinetu.get_all_promo();

                } catch (SQLException e) {
                    e.printStackTrace();
                }

                DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

                while (!promocje_gabinetu.promoListaEmpty()) {
                    odczytany = promocje_gabinetu.promoListaPop();
                    /* odczytujemy odpowieidzialnego pracownika */
                    try {
                        Pracownik pracownik = new Pracownik(odczytany.getId_pracownika());
                        odpowiedzialny_pracownik = pracownik.getData();
                        out.println("<tr>" +
                                "<td> <input type=\"Checkbox\" Name=\"do_usuniecia\" Value =\"" + odczytany.getId_promocji() + "\"></td>" +
                                "<td>" + odczytany.getId_promocji() + "</td>" +
                                "<td>" + odczytany.getNazwa() + "</td>" +
                                "<td>" + odczytany.getOpis() + "</td>" +
                                "<td>" + odczytany.getZnizka_proc() + " %</td>" +
                                "<td>" + odczytany.getZnizka_kwotowa() + " PLN</td>" +
                                "<td>" + dateFormat.format(odczytany.getData_od()) + "</td>" +
                                "<td>" + dateFormat.format(odczytany.getData_do()) + "</td>" +
                                "<td>" + odpowiedzialny_pracownik.getImie() + " " + odpowiedzialny_pracownik.getNazwisko() + "</td></tr>");
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                }
            %>

            </tbody>
        </table>
        <div class="row">
            <a href="../ControllerAccount?page=add_promo"><button type="button" class="btn btn-primary float-none align-self-center" style="width:265px;margin-left: 170px;margin-top:20px;">Dodaj nową promocję</button></a>
            <a><button type="submit" name="button1" value="Button 1" class="btn btn-primary float-none align-self-center" style="width:265px;margin-left: 17px;margin-top:20px;">Usuń promocję</button></a>
            <a href="../ControllerAccount?page=edit_promo"><button type="button" class="btn btn-primary float-none align-self-center" style="width:265px;margin-left: 17px;margin-top:20px;">Edytuj promocję</button></a>
        </div>
    </form>
</section>


</html>
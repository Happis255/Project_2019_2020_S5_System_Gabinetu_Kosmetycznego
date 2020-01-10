<%@ page import="java.text.SimpleDateFormat" %>
<%@ page import="java.time.format.DateTimeFormatter" %>
<%@ page import="java.sql.SQLException" %>
<%@ page import="myPage.data.dataBase.*" %>
<%@ page import="myPage.basicObjects.*" %>
<%@ page import="myPage.data.others.StatusWizyty" %>
<!DOCTYPE html>
<html>
    <section id="wizyty_spis_admin" class="bg-light-gray" style="margin:0;background-color:rgba(0,0,0,0.11);color:#ffffff;padding-bottom:20px;padding-top:20px;max-width:1350px;margin-right:auto;margin-left:auto;border-radius:20px;margin-bottom:30px;">
        <form action="${pageContext.request.contextPath}/ControllerWizytaModificator" method="post">
            <h2 class="text-center" style="height:79px;">Wizyty</h2>
            <h5 class="text-center" style="height:21px;margin-right:50px;margin-left:50px;">W poniższej sekcji znajdując się wszystkie wizyty znajdujące się w bazie systemu na aktualny miesiąc.</h5>
            <h6 class="text-center" id="informacja" style="margin-right:50px;margin-left:50px;font-weight: 100;    margin-bottom: 0;">Zaznacz wybraną wizytę, by ją odrzucić, potwierdzić, zatwierdzić płatność bądź usunąć.</h6>
            <h6 class="text-center" style="height:44px;margin-right:50px;margin-left:50px;font-weight: 100;">W celu dodania nowej wizyty wybierz odpowiednią opcję oraz wprowadź dane klienta.</h6>
            <table id="tablica_wizyt_caly_dzien" class="table" cellspacing="0" width="100%" style="text-align: center;margin-bottom: 0;border: 3px solid #FFFFFF;width: 98%;max-width: 98%;margin-left: 12px;background-color: transparent;border-collapse: collapse;">
            <thead>
            <tr>
                <th></th>
                <th>ID</th>
                <th style="max-width: 110px;">IMIĘ KLIENTA</th>
                <th style="max-width: 110px;">NAZWISKO KLIENTA</th>
                <th style="max-width: 132px;">IMIĘ PRACOWNIKA</th>
                <th style="max-width: 125px;">NAZWISKO PRACOWNIKA</th>
                <th>DATA</th>
                <th>GODZINA</th>
                <th>NAZWA USLUGI</th>
                <th>CENA</th>
                <th>CZAS</th>
                <th>STATUS</th>
            </tr>
            </thead>
            <tbody>

            <%

                Wizyta wizyty_w_gabinecie = new Wizyta();
                WizytaData odczytana_wizyta;
                KlientData odczytany_klient = null;
                PracownikData odczytany_pracownik = null;
                UslugaData odczytana_usluga = null;

                try {
                    /* Załadowanie wizyt znajdujących się w bazie gabinetu na aktualny miesiąc */
                    wizyty_w_gabinecie.getTodayWizyty();
                } catch (Exception e) {
                    e.printStackTrace();
                }

                while (!wizyty_w_gabinecie.isEmpty()){
                    odczytana_wizyta = wizyty_w_gabinecie.pop();

                    /* Wygeneruj datę */
                    SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
                    String data_wizyty = formatter.format(odczytana_wizyta.getData_wizyty());

                    /* Wygeneruj godzinę */
                    String godzina_wizyty = odczytana_wizyta.getGodzina().format(DateTimeFormatter.ofPattern("HH:mm"));

                    /* Pobierz dane klienta */
                    try {
                        Klient klient = new Klient(odczytana_wizyta.getId_klienta());
                        odczytany_klient = klient.getData();
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }

                    /* Pobierz dane usługi */
                    try {
                        Usluga uslugi = new Usluga();
                        odczytana_usluga = uslugi.getUsluga_ID(odczytana_wizyta.getId_uslugi());
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }

                    /* Pobierz dane pracownika */
                    try {
                        Pracownik pracownik = new Pracownik(odczytana_wizyta.getId_pracownika());
                        odczytany_pracownik = pracownik.getData();
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }

                    assert odczytana_usluga != null;
                    assert odczytany_pracownik != null;
                    assert odczytany_klient != null;
                    out.println("<tr><td> <input type=\"Checkbox\" Name=\"do_zmiany\" Value =\"" + odczytana_wizyta.getId_wizyty() + "\"></td>" +
                                "<td>" + odczytana_wizyta.getId_wizyty() + "</td>" +
                                "<td>" + odczytany_klient.getImie() + "</td>" +
                                "<td>" + odczytany_klient.getNazwisko() + "</td>" +
                                "<td>" + odczytany_pracownik.getImie() + "</td>" +
                                "<td>" + odczytany_pracownik.getNazwisko() + "</td>" +
                                "<td>" + data_wizyty + "</td>" +
                                "<td>" + godzina_wizyty + "</td>" +
                                "<td>" + odczytana_usluga.getNazwa() + "</td>" +
                                "<td>" + odczytana_usluga.getCena() + "</td>" +
                                "<td>" + odczytana_usluga.getCzas_trwania() + "</td>" +
                                "<td>" + StatusWizyty.getName(odczytana_wizyta.getStatus()) + "</td>" +
                                "</tr>");
                }
            %>

            </tbody>
            </table>
            <div class="row">
                <a><button class="btn btn-primary float-none align-self-center" type="submit" name="opcja" value="potwierdz" style="width:265px;margin-left: 272px;margin-top:20px;">Potwierdź wizytę</button></a>
                <a><button class="btn btn-primary float-none align-self-center" type="submit" name="opcja" value="odrzuc" style="width:265px;margin-left: 17px;margin-top:20px;">Odrzuć wizytę</button></a>
                <a><button class="btn btn-primary float-none align-self-center" type="submit" name="opcja" value="zatwierdz" style="width:265px;margin-left: 17px;margin-top:20px;">Zatwierdź płatność</button></a>
            </div>
            <div class="row">
                <a><button class="btn btn-primary float-none align-self-center" type="submit" name="opcja" value="usun" style="width:265px;margin-left: 272px;margin-top:20px;">Usuń wizytę</button></a>
                <a href="../ControllerAccount?page=dodaj_wizyte"><button class="btn btn-primary float-none align-self-center" type="button" style="width:265px;margin-left: 17px;margin-top:20px;">Dodaj wizytę</button></a>
                <a href="../ControllerAccount?page=wizyty"><button class="btn btn-primary float-none align-self-center" type="button" style="width:265px;margin-left: 17px;margin-top:20px;">Wyświetl aktualny miesiąc</button></a>
            </div>
            <div class="row">
                <a href="../ControllerAccount?page=wyswietl_okres_wizyte"><button class="btn btn-primary float-none align-self-center" type="button" style="width:265px;margin-left: 272px;margin-top:20px;">Wyświetl wybrany okres</button></a>
                <a href="../ControllerAccount?page=wyswietl_pracownik_wizyte"><button class="btn btn-primary float-none align-self-center" type="button" style="width:265px;margin-left: 17px;margin-top:20px;">Wyświetl wybranego pracownika</button></a>
                <a href="../ControllerAccount?page=wyswietl_klient_wizyte"><button class="btn btn-primary float-none align-self-center" type="button" style="width:265px;margin-left: 17px;margin-top:20px;">Wyświetl wybranego klienta</button></a>
            </div>
        </form>
        </section>
</html>
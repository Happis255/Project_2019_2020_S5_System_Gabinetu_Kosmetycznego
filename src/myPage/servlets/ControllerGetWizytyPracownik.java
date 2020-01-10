package myPage.servlets;

import myPage.basicObjects.Klient;
import myPage.basicObjects.Pracownik;
import myPage.basicObjects.Usluga;
import myPage.basicObjects.Wizyta;
import myPage.data.dataBase.KlientData;
import myPage.data.dataBase.PracownikData;
import myPage.data.dataBase.UslugaData;
import myPage.data.dataBase.WizytaData;
import myPage.data.others.SessionData;
import myPage.data.others.StatusWizyty;
import myPage.data.others.TypKonta;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;

@WebServlet("/ControllerGetWizytyPracownik")

public class ControllerGetWizytyPracownik extends HttpServlet {

    private HttpSession session;
    private String resultMessage = "";

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String data_od = request.getParameter("data_od");
        String data_do = request.getParameter("data_do");
        String id_pracownika = request.getParameter("pracownik_id");

        Wizyta wizyty_w_gabinecie = new Wizyta();
        WizytaData odczytana_wizyta;
        KlientData odczytany_klient = null;
        PracownikData odczytany_pracownik = null;
        UslugaData odczytana_usluga = null;

        session = request.getSession();
        SessionData sessionData = (SessionData) session.getAttribute("userData");

        try {
            wizyty_w_gabinecie.loadWizytyFromToWithWorker(data_od, data_do, id_pracownika);

            /* Pobierz dane pracownika */
            try {
                Pracownik pracownik = new Pracownik(Integer.parseInt(id_pracownika));
                odczytany_pracownik = pracownik.getData();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            resultMessage = "<section id=\"wizyty_spis_admin\" class=\"bg-light-gray\" style=\"margin:0;background-color:rgba(0,0,0,0.11);color:#ffffff;padding-bottom:20px;padding-top:20px;max-width:1350px;margin-right:auto;margin-left:auto;border-radius:20px;margin-bottom:30px;\">\n" +
                    "        <form action=\"ControllerWizytaModificator\" method=\"post\">\n" +
                    "            <h2 class=\"text-center\" style=\"height:79px;\">Wizyty pracownika: " + odczytany_pracownik.getNazwisko() + " " + odczytany_pracownik.getImie() + "</h2>\n" +
                    "            <h5 class=\"text-center\" style=\"height:21px;margin-right:50px;margin-left:50px;\">W poniższej sekcji znajdując się wszystkie wizyty znajdujące się w bazie systemu od " + data_od + " do " + data_do + " wybranego pracownika.</h5>\n" +
                    "            <h6 class=\"text-center\" id=\"informacja\" style=\"margin-right:50px;margin-left:50px;font-weight: 100;    margin-bottom: 0;\">Zaznacz wybraną wizytę, by ją odrzucić, potwierdzić, zatwierdzić płatność bądź usunąć.</h6>\n" +
                    "            <h6 class=\"text-center\" style=\"height:44px;margin-right:50px;margin-left:50px;font-weight: 100;\">W celu dodania nowej wizyty wybierz odpowiednią opcję oraz wprowadź dane klienta.</h6>\n" +
                    "            <table id=\"tablica_wizyt_caly_dzien\" class=\"table\" cellspacing=\"0\" width=\"100%\" style=\"text-align: center;margin-bottom: 0;border: 3px solid #FFFFFF;width: 98%;max-width: 98%;margin-left: 12px;background-color: transparent;border-collapse: collapse;\">\n" +
                    "            <thead>\n" +
                    "            <tr>\n" +
                    "                <th></th>\n" +
                    "                <th>ID</th>\n" +
                    "                <th style=\"max-width: 110px;\">IMIĘ KLIENTA</th>\n" +
                    "                <th style=\"max-width: 110px;\">NAZWISKO KLIENTA</th>\n" +
                    "                <th style=\"max-width: 132px;\">IMIĘ PRACOWNIKA</th>\n" +
                    "                <th style=\"max-width: 125px;\">NAZWISKO PRACOWNIKA</th>\n" +
                    "                <th>DATA</th>\n" +
                    "                <th>GODZINA</th>\n" +
                    "                <th>NAZWA USLUGI</th>\n" +
                    "                <th>CENA</th>\n" +
                    "                <th>CZAS</th>\n" +
                    "                <th>STATUS</th>\n" +
                    "            </tr>\n" +
                    "            </thead>\n" +
                    "            <tbody>";

            while (!wizyty_w_gabinecie.isEmpty()) {
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


                resultMessage += "<tr><td>";
                if (!StatusWizyty.getName(odczytana_wizyta.getStatus()).equals("Opłacona"))
                    resultMessage += "<input type=\"Checkbox\" Name=\"do_zmiany\" Value =\"" + odczytana_wizyta.getId_wizyty() + "\">";
                resultMessage += "</td>" +
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
                        "</tr>";
            }
            resultMessage += "</tbody>\n" +
                    "            </table>\n" +
                    "            <div class=\"row\">\n" +
                    "                <a><button class=\"btn btn-primary float-none align-self-center\" type=\"submit\" name=\"opcja\" value=\"potwierdz\" style=\"width:265px;margin-left: 272px;margin-top:20px;\">Potwierdź wizytę</button></a>\n" +
                    "                <a><button class=\"btn btn-primary float-none align-self-center\" type=\"submit\" name=\"opcja\" value=\"odrzuc\" style=\"width:265px;margin-left: 17px;margin-top:20px;\">Odrzuć wizytę</button></a>\n" +
                    "                <a><button class=\"btn btn-primary float-none align-self-center\" type=\"submit\" name=\"opcja\" value=\"zatwierdz\" style=\"width:265px;margin-left: 17px;margin-top:20px;\">Zatwierdź płatność</button></a>\n" +
                    "            </div>\n" +
                    "            <div class=\"row\">\n" +
                    "                <a><button class=\"btn btn-primary float-none align-self-center\" type=\"submit\" name=\"opcja\" value=\"usun\" style=\"width:265px;margin-left: 272px;margin-top:20px;\">Usuń wizytę</button></a>\n" +
                    "                <a href=\"ControllerAccount?page=dodaj_wizyte\"><button class=\"btn btn-primary float-none align-self-center\" type=\"button\" style=\"width:265px;margin-left: 17px;margin-top:20px;\">Dodaj wizytę</button></a>\n" +
                    "                <a href=\"ControllerAccount?page=wizyty\"><button class=\"btn btn-primary float-none align-self-center\" type=\"button\" style=\"width:265px;margin-left: 17px;margin-top:20px;\">Wyświetl aktualny miesiąc</button></a>\n" +
                    "            </div>\n" +
                    "            <div class=\"row\">\n" +
                    "                <a href=\"ControllerAccount?page=wyswietl_okres_wizyte\"><button class=\"btn btn-primary float-none align-self-center\" type=\"button\" style=\"width:265px;margin-left: 272px;margin-top:20px;\">Wyświetl wybrany okres</button></a>\n" +
                    "                <a href=\"ControllerAccount?page=wyswietl_pracownik_wizyte\"><button class=\"btn btn-primary float-none align-self-center\" type=\"button\" style=\"width:265px;margin-left: 17px;margin-top:20px;\">Wyświetl wybranego pracownika</button></a>\n" +
                    "                <a href=\"ControllerAccount?page=wyswietl_klient_wizyte\"><button class=\"btn btn-primary float-none align-self-center\" type=\"button\" style=\"width:265px;margin-left: 17px;margin-top:20px;\">Wyświetl wybranego klienta</button></a>\n" +
                    "            </div>\n" +
                    "        </form>\n" +
                    "        </section>";

            request.setAttribute("message", resultMessage);
            if (TypKonta.getStringVal(sessionData.getAccoutType()).equals("ADMINISTRATOR"))
                getServletContext().getRequestDispatcher("/index_table_result.jsp").forward(request, response);
            else if (TypKonta.getStringVal(sessionData.getAccoutType()).equals("PRACOWNIK"))
                getServletContext().getRequestDispatcher("/index_table_result_worker.jsp").forward(request, response);
            else throw new Exception("BLAD TYPU KONTA");
        } catch (Exception e) {
            e.printStackTrace();
            resultMessage = "<h2 class=\"text-center\" style=\"height:53px;\">Wystąpił błąd podczas ładowania danych.</h2><h5 class=\"text-center\" style=\"height:99px;margin-right:50px;margin-left:50px;\"><br>Prosimy o sprawdzenie, czy wybrane zostały prawidłowe dane<br>bądź czy serwer bazy danych jest uruchomiony.<br></h5> <div class=\"form-group\"><a href=\"ControllerAccount?page=wizyty\"><button class=\"btn btn-primary\" type=\"submit\" style=\"margin:0;width:265px;margin-left:267px;\">Powrót do bazy klientów</button></a></div>";
            request.setAttribute("message", resultMessage);
            getServletContext().getRequestDispatcher("/index_result.jsp").forward(request, response);
        }
    }
}
package myPage.servlets;
import myPage.basicObjects.Klient;
import myPage.basicObjects.Pracownik;
import myPage.basicObjects.Usluga;
import myPage.basicObjects.Wizyta;
import myPage.data.dataBase.KlientData;
import myPage.data.dataBase.PracownikData;
import myPage.data.dataBase.UslugaData;
import myPage.data.dataBase.WizytaData;

import javax.mail.MessagingException;
import javax.servlet.ServletContext;
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

@WebServlet("/ControllerWizytaModificator")
public class ControllerWizytaModificator extends HttpServlet {

    private String host;
    private String port;
    private String user;
    private String pass;
    private HttpSession session;
    private String resultMessage = "";

    public void init() {
        ServletContext context = getServletContext();
        host = context.getInitParameter("host");
        port = context.getInitParameter("port");
        user = context.getInitParameter("user");
        pass = context.getInitParameter("pass");
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        request.setCharacterEncoding("UTF-8");
        session = request.getSession();

        Wizyta wizyty = new Wizyta();
        WizytaData wizyta_info;

        String[] spis_id = request.getParameterValues("do_zmiany");
        int a = 0;

        try {
            for (String s : spis_id) {
                a = Integer.parseInt(s);

                switch(request.getParameter("opcja")){
                    case "potwierdz":

                        /* Potwierdz wizyte mailem */
                        wizyta_info = wizyty.getWizytaID_DB(a);
                        potwierdz_mail(wizyta_info);

                        wizyty.potwierdz_wizyteID(a);
                        break;

                    case "odrzuc":

                        /* Odrzuc wizyte mailem */
                        wizyta_info = wizyty.getWizytaID_DB(a);
                        odrzuc_mail(wizyta_info);

                        wizyty.odrzuc_wizyteID(a);
                        break;

                    case "zatwierdz": wizyty.zatwierdz_platnosc_wizytyID(a);
                        wizyta_info = wizyty.getWizytaID_DB(a);
                        wizyty.zaktualizuj_punkty(wizyta_info);
                        break;

                    case "usun":

                        /* Usun wizyte mailem */
                        wizyta_info = wizyty.getWizytaID_DB(a);
                        usun_mail(wizyta_info);

                        wizyty.usun_wizyteID(a);
                        break;
                }
            }
            resultMessage = "<h2 class=\"text-center\" style=\"height:53px;\">Wizyta została zmodyfikowana w bazie danych</h2><h5 class=\"text-center\" style=\"height:99px;margin-right:50px;margin-left:50px;\"><br>Zmiany powinny być już widoczne.<br></h5> <div class=\"form-group\"><a href=\"ControllerAccount?page=wizyty\"><button class=\"btn btn-primary\" type=\"submit\" style=\"margin:0;width:265px;margin-left:267px;\">Powrót do wizyt</button></a></div>";
            request.setAttribute("message", resultMessage);
            getServletContext().getRequestDispatcher("/index_result.jsp").forward(request, response);

        } catch (Exception e) {
            e.printStackTrace();
            resultMessage = "<h2 class=\"text-center\" style=\"height:53px;\">Wystąpił błąd podczas modyfikacji wizyty.</h2><h5 class=\"text-center\" style=\"height:99px;margin-right:50px;margin-left:50px;\"><br>Prosimy o skontaktowanie się z administratorem systemu<br>bądź prosimy o zgłoszenie błędu.<br></h5> <div class=\"form-group\"><a href=\"ControllerAccount?page=wizyty\"><button class=\"btn btn-primary\" type=\"submit\" style=\"margin:0;width:265px;margin-left:267px;\">Powrót do wizyt</button></a></div>";
            request.setAttribute("message", resultMessage);
            getServletContext().getRequestDispatcher("/index_result.jsp").forward(request, response);
        }
    }

    private void potwierdz_mail(WizytaData dane_wizyty){
        /* Pobieranie danych pracownika, klienta oraz uslugi */
        KlientData odczytany_klient = null;
        UslugaData odczytana_usluga = null;

        try {
            /* Pobierz dane klienta */
            Klient klient = new Klient(dane_wizyty.getId_klienta());
            odczytany_klient = klient.getData();

            /* Pobierz dane usługi */
            Usluga uslugi = new Usluga();
            odczytana_usluga = uslugi.getUsluga_ID(dane_wizyty.getId_uslugi());

            /* Formatowanie danych na temat wizyty */
            SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
            String data_wizyty = formatter.format(dane_wizyty.getData_wizyty());
            String godzina_wizyty = dane_wizyty.getGodzina().format(DateTimeFormatter.ofPattern("HH:mm"));

            /* Generowanie wiadomości */
            String recipient = odczytany_klient.getE_mail();
            String subject = "[Gabinet GRACJA] " + odczytany_klient.getNazwisko() + " " + odczytany_klient.getImie() + ", Twoja wizyta została potwierdzona.";
            String content = "<head>" +
                    "  <meta charset=\"UTF-8\">" +
                    "</head>" +  "Informujemy, iż wizyta dotycząca usługi " + odczytana_usluga.getNazwa() + " z dnia " + data_wizyty + " o godzinie " + godzina_wizyty + " została potwierdzona.<br/>";

            /* Sprawdzanie, czy jest wskazówka */
            try {
                if (odczytana_usluga.getWskazowki() != null)
                content = content + "<br>Wskazówki dotyczące wizyty: " + odczytana_usluga.getWskazowki();
            } catch (NullPointerException e) {
                System.out.println("Brak wskazówki do wysłania");
            }

            content = content + "<br><br>" + "Gabinet Kosmetyczny Gracja \"Anna Wąsik\"";

            EmailUtility_N.sendEmailWithNoAttachment(host, port, user, pass, recipient, subject, content);

        } catch (SQLException | MessagingException e) {
            e.printStackTrace();
        }
    }

    private void odrzuc_mail(WizytaData dane_wizyty){
        /* Pobieranie danych pracownika, klienta oraz uslugi */
        KlientData odczytany_klient = null;
        UslugaData odczytana_usluga = null;

        try {
            /* Pobierz dane klienta */
            Klient klient = new Klient(dane_wizyty.getId_klienta());
            odczytany_klient = klient.getData();

            /* Pobierz dane usługi */
            Usluga uslugi = new Usluga();
            odczytana_usluga = uslugi.getUsluga_ID(dane_wizyty.getId_uslugi());

            /* Formatowanie danych na temat wizyty */
            SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
            String data_wizyty = formatter.format(dane_wizyty.getData_wizyty());
            String godzina_wizyty = dane_wizyty.getGodzina().format(DateTimeFormatter.ofPattern("HH:mm"));

            /* Generowanie wiadomości */
            String recipient = odczytany_klient.getE_mail();
            String subject = "[Gabinet GRACJA] " + odczytany_klient.getNazwisko() + " " + odczytany_klient.getImie() + ", Twoja wizyta została odrzucona.";
            String content = "<head>" +
                    "  <meta charset=\"UTF-8\">" +
                    "</head>" +  "Informujemy, iż wizyta dotycząca usługi " + odczytana_usluga.getNazwa() + " z dnia " + data_wizyty + " o godzinie " + godzina_wizyty + " została odrzucona.<br/><br/>" +
                    "Niestety, wizyta nie może się odbyć. Prosimy o zalogowanie się do naszego systemu i wybranie innego terminu wizyty.<br>" +
                    "Za utrudnienia przepraszamy,<br><br>" + "Gabinet Kosmetyczny Gracja \"Anna Wąsik\"";

            EmailUtility_N.sendEmailWithNoAttachment(host, port, user, pass, recipient, subject, content);

        } catch (SQLException | MessagingException e) {
            e.printStackTrace();
        }
    }

    private void usun_mail(WizytaData dane_wizyty){

        /* Pobieranie danych pracownika, klienta oraz uslugi */
        KlientData odczytany_klient = null;
        UslugaData odczytana_usluga = null;

        try {
            /* Pobierz dane klienta */
            Klient klient = new Klient(dane_wizyty.getId_klienta());
            odczytany_klient = klient.getData();

            /* Pobierz dane usługi */
            Usluga uslugi = new Usluga();
            odczytana_usluga = uslugi.getUsluga_ID(dane_wizyty.getId_uslugi());

            /* Formatowanie danych na temat wizyty */
            SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
            String data_wizyty = formatter.format(dane_wizyty.getData_wizyty());
            String godzina_wizyty = dane_wizyty.getGodzina().format(DateTimeFormatter.ofPattern("HH:mm"));

            /* Generowanie wiadomości */
            String recipient = odczytany_klient.getE_mail();
            String subject = "[Gabinet GRACJA] " + odczytany_klient.getNazwisko() + " " + odczytany_klient.getImie() + ", Twoja wizyta została usunięta.";
            String content = "<head>" +
                    "  <meta charset=\"UTF-8\">" +
                    "</head>" +  "Informujemy, iż wizyta dotycząca usługi " + odczytana_usluga.getNazwa() + " z dnia " + data_wizyty + " o godzinie " + godzina_wizyty + " została usunięta.<br/><br/>" +
                    "Prosimy o skontaktowanie się z obsługą gabinetu poprzez adres info@gabinetgracja.com.pl bądź przez kontakt telefoniczny: 609 155 827.<br>" +
                    "Za utrudnienia przepraszamy,<br><br>" + "Gabinet Kosmetyczny Gracja \"Anna Wąsik\"";

            EmailUtility_N.sendEmailWithNoAttachment(host, port, user, pass, recipient, subject, content);

        } catch (SQLException | MessagingException e) {
            e.printStackTrace();
        }
    }
}


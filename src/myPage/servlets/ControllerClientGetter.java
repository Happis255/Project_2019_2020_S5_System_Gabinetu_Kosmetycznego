package myPage.servlets;

import myPage.basicObjects.KartaKlienta;
import myPage.basicObjects.Klient;
import myPage.data.dataBase.KartaKlientaData;
import myPage.data.dataBase.KlientData;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

@WebServlet("/ControllerClientGetter")

public class ControllerClientGetter extends HttpServlet {

    private HttpSession session;
    private String resultMessage = "";

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {


        request.setCharacterEncoding("UTF-8");
        Klient getKlient = new Klient(Integer.parseInt(request.getParameter("klient_id")));
        KlientData klient;

        KartaKlienta karta = new KartaKlienta();
        KartaKlientaData klient_kartaKlienta;
        String status = null;

        try {
            klient = getKlient.getData();
            status = getKlient.getAccountStatusName(klient.getId_statusu());

            /* Odczytujemy karte klienta zalogowanego w systemie */
            DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

            klient_kartaKlienta = karta.KartaKlientaData_get(klient.getId());
            resultMessage =
                    "    <section id=\"pracownik\" class=\"bg-light-gray\" style=\"margin:0;background-color:rgba(0,0,0,0.11);color:#ffffff;padding-bottom:20px;padding-top:20px;max-width:1140px;margin-right:auto;margin-left:auto;border-radius:20px; margin-bottom: 8px;\n" +
                            "\">\n" +
                            "        <h2 class=\"text-center\" style=\"    margin-bottom: 24px;\n" +
                            "    margin-top: 10px;\">Dane klienta: " + klient.getImie() + " " + klient.getNazwisko() + "</h2>\n" +
                            "        <br>\n" +
                            "        <table class=\"table\" cellspacing=\"0\" width=\"100%\" style=\"margin-top: -18px;text-align: center;margin-bottom: 0;width: 55%;margin-left: 12%;background-color: transparent;border-collapse: collapse;min-width: 760px;\"\">\n" +
                            "            <tr>\n" +
                            "                <td style=\" min-width: 430px;\"><h5 class=\"text-center\" style=\"margin-bottom: 1px;\">Dane osobowe</h5></td>\n" +
                            "                <td style=\" min-width: 430px;\"><h5 class=\"text-center\" style=\"margin-bottom: 1px;\">Dane karty klienta</h5></td>\n" +
                            "            </tr>\n" +
                            "            <tr>\n" +
                            "                <td style=\" text-align: center; vertical-align: middle;\">\n" +
                            "                    <h6 class=\"text-center\" style=\"margin-right:50px;margin-left:50px;font-weight: 100;\">Ulica: " + klient.getUlica() + "</h6>\n" +
                            "                    <h6 class=\"text-center\" style=\"margin-right:50px;margin-left:50px;font-weight: 100;\">Kod pocztowy: " + klient.getKod_pocztowy() + "</h6>\n" +
                            "                    <h6 class=\"text-center\" style=\"margin-right:50px;margin-left:50px;font-weight: 100;\">Miejscowość: " + klient.getMiejscowosc() + "</h6>\n" +
                            "                    <h6 class=\"text-center\" style=\"margin-right:50px;margin-left:50px;font-weight: 100;\">Data urodzenia: "  + dateFormat.format(klient.getData_urodzenia()) + "</h6>\n" +
                            "                    <h6 class=\"text-center\" style=\"margin-right:50px;margin-left:50px;font-weight: 100;\">Telefon: "  + klient.getTelefon() + "</h6>\n" +
                            "                    <h6 class=\"text-center\" style=\"margin-right:50px;margin-left:50px;font-weight: 100;\">E-Mail: "  + klient.getE_mail() + "</h6>\n" +
                            "                    <h6 class=\"text-center\" style=\"margin-right:50px;margin-left:50px;font-weight: 100;\">Status konta: "  + status + "</h6>\n" +
                            "                    <h6 class=\"text-center\" style=\"margin-right:50px;margin-left:50px;font-weight: 100;\">Punkty: "  + klient.getIlosc_punktow() + "pkt.</h6>\n" +
                            "                </td>\n" +
                            "                <td style=\" text-align: center; vertical-align: middle;\">\n" +
                            "                    <h6 class=\"text-center\" style=\"margin-right:50px;margin-left:50px;font-weight: 100;\">Rozrusznik serca: " + klient_kartaKlienta.toStringp1() +"</h6>\n" +
                            "                    <h6 class=\"text-center\" style=\"margin-right:50px;margin-left:50px;font-weight: 100;\">Hermofilia: "+ klient_kartaKlienta.toStringp2() +"</h6>\n" +
                            "                    <h6 class=\"text-center\" style=\"margin-right:50px;margin-left:50px;font-weight: 100;\">Łuszczyca: "+ klient_kartaKlienta.toStringp3() +"</h6>\n" +
                            "                    <h6 class=\"text-center\" style=\"margin-right:50px;margin-left:50px;font-weight: 100;\">Alergie: "+ klient_kartaKlienta.toStringp4() +"</h6>\n" +
                            "                    <h6 class=\"text-center\" style=\"margin-right:50px;margin-left:50px;font-weight: 100;\">Przebarwienia: "+ klient_kartaKlienta.toStringp5() +"</h6>\n" +
                            "                    <h6 class=\"text-center\" style=\"margin-right:50px;margin-left:50px;font-weight: 100;\">Choroby zakaźne: "+ klient_kartaKlienta.toStringp6() +"</h6>\n" +
                            "                    <h6 class=\"text-center\" style=\"margin-right:50px;margin-left:50px;font-weight: 100;\">Zaburzenia ukrwienia: "+ klient_kartaKlienta.toStringp7() +"</h6>\n" +
                            "                    <h6 class=\"text-center\" style=\"margin-right:50px;margin-left:50px;font-weight: 100;\">Opryszczka, osłabuienia, opryszczka: "+ klient_kartaKlienta.toStringp8() +"</h6>\n" +
                            "                    <h6 class=\"text-center\" style=\"margin-right:50px;margin-left:50px;font-weight: 100;\">Ciąża: "+ klient_kartaKlienta.toStringp9() +"</h6>\n" +

                            "                    <h6 class=\"text-center\" style=\"margin-right:50px;margin-left:50px;font-weight: 100;\">Ocena skóry: "+ klient_kartaKlienta.getOcena_skory() +"</h6>\n" +
                            "                    <h6 class=\"text-center\" style=\"margin-right:50px;margin-left:50px;font-weight: 100;\">Typ skóry: "+ klient_kartaKlienta.getRodzaj_jakos() +"</h6>\n" +
                            "                    <h6 class=\"text-center\" style=\"margin-right:50px;margin-left:50px;font-weight: 100;\">Wrażliwości skóry: "+ klient_kartaKlienta.getWrazliwosc() +"</h6>\n" +
                            "                    <h6 class=\"text-center\" style=\"margin-right:50px;margin-left:50px;font-weight: 100;\">Uwagi dotyczące skóry: "+ klient_kartaKlienta.getInne_uwagi() +"</h6>\n" +
                            "                </td>\n" +
                            "            </tr>\n" +
                            "        </table>\n" +
                            "<form>\n" +
                            "  <button type=\"button\" value=\"Go back!\" onclick=\"history.back()\"class=\"btn btn-primary float-none align-self-center\" style=\"width:265px;margin-left: 437px;margin-top:20px;\">Powrót do bazy klientów</button>\n" +
                            "</form>" +
                            "</section>";
            request.setAttribute("message", resultMessage);
            getServletContext().getRequestDispatcher("/konto_result.jsp").forward(request, response);

        } catch (Exception e) {
            e.printStackTrace();
            resultMessage = "<h2 class=\"text-center\" style=\"height:53px;\">Wystąpił błąd podczas ładowania danych.</h2><h5 class=\"text-center\" style=\"height:99px;margin-right:50px;margin-left:50px;\"><br>Prosimy o sprawdzenie, czy wybrane zostały prawidłowe dane<br>bądź czy serwer bazy danych jest uruchomiony.<br></h5> <div class=\"form-group\"><a href=\"ControllerAccount?page=klienci\"><button class=\"btn btn-primary\" type=\"submit\" style=\"margin:0;width:265px;margin-left:267px;\">Powrót do bazy klientów</button></a></div>";
            request.setAttribute("message", resultMessage);
            getServletContext().getRequestDispatcher("/index_result.jsp").forward(request, response);
        }
    }
}

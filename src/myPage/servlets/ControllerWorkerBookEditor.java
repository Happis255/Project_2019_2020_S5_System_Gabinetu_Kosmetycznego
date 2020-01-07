package myPage.servlets;

import myPage.basicObjects.Pracownik;
import myPage.exceptions.DBReadWriteException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.HashMap;

@WebServlet("/ControllerWorkerBookEditor")

public class ControllerWorkerBookEditor extends HttpServlet {

    private HttpSession session;
    private String resultMessage = "";

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        request.setCharacterEncoding("UTF-8");

        Pracownik pracownik = new Pracownik();
        HashMap<String, String> parameters = new HashMap<>();

        parameters.put("p_id_ksiazeczki", request.getParameter("ksiazeczka_id"));
        parameters.put("p_rozrusznik_serca", request.getParameter("czy_rozrusznik"));
        parameters.put("p_hermofilia", request.getParameter("czy_hermofilia"));
        parameters.put("p_luszczyca", request.getParameter("czy_luszczyca"));
        parameters.put("p_alergia", request.getParameter("alergia"));
        parameters.put("p_przebarwienie", request.getParameter("przebarwienia"));
        parameters.put("p_choroba_zakazna", request.getParameter("choroby"));
        parameters.put("p_zaburzenia_ukrwienia", request.getParameter("ukrwienie"));
        parameters.put("p_opryszczka", request.getParameter("opryszczka"));
        parameters.put("p_goraczka", request.getParameter("goraczka"));
        parameters.put("p_oslabienie", request.getParameter("oslabienie"));
        parameters.put("p_ciaza", request.getParameter("czy_ciaza"));

        try {
            pracownik.editKsiazeczkaPracownika(parameters);
            resultMessage = "<h2 class=\"text-center\" style=\"height:53px;\">Dane książeczki zostały zmienione!</h2><h5 class=\"text-center\" style=\"height:99px;margin-right:50px;margin-left:50px;\"><br>Zmiany powinny być już widoczne.<br></h5> <div class=\"form-group\"><a href=\"ControllerAccount?page=pracownicy\"><button class=\"btn btn-primary\" type=\"submit\" style=\"margin:0;width:265px;margin-left:267px;\">Powrót do pracowników</button></a></div>";
            request.setAttribute("message", resultMessage);
            getServletContext().getRequestDispatcher("/index_result.jsp").forward(request, response);

        } catch (SQLException | ParseException | DBReadWriteException  | NullPointerException e) {
            e.printStackTrace();
            resultMessage = "<h2 class=\"text-center\" style=\"height:53px;\">Wystąpił błąd podczas edycji książeczki.</h2><h5 class=\"text-center\" style=\"height:99px;margin-right:50px;margin-left:50px;\"><br>Prosimy o sprawdzenie, czy wprowadzone zostały prawidłowe dane<br>bądź czy serwer bazy danych jest uruchomiony.<br></h5> <div class=\"form-group\"><a href=\"ControllerAccount?page=pracownicy\"><button class=\"btn btn-primary\" type=\"submit\" style=\"margin:0;width:265px;margin-left:267px;\">Powrót do pracowników</button></a></div>";
            request.setAttribute("message", resultMessage);
            getServletContext().getRequestDispatcher("/index_result.jsp").forward(request, response);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

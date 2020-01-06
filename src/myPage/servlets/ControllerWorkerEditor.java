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

@WebServlet("/ControllerWorkerEditor")

public class ControllerWorkerEditor extends HttpServlet {

    private HttpSession session;
    private String resultMessage = "";

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        request.setCharacterEncoding("UTF-8");

        Pracownik pracownik = new Pracownik();
        HashMap<String, String> parameters = new HashMap<>();

        parameters.put("p_id_pracownika", request.getParameter("pracownik_id"));
        parameters.put("p_nazwisko", request.getParameter("nazwisko"));
        parameters.put("p_ulica", request.getParameter("ulica"));
        parameters.put("p_kod_pocztowy", request.getParameter("kod_pocztowy"));
        parameters.put("p_miejscowowsc", request.getParameter("miejscowosc"));
        parameters.put("p_data_urodzenia", request.getParameter("data-urodzenia"));
        parameters.put("p_telefon", request.getParameter("telefon"));
        parameters.put("p_e_mail", request.getParameter("e_mail"));
        parameters.put("p_certyfikaty", request.getParameter("certyfikaty"));
        parameters.put("p_haslo", request.getParameter("haslo"));

        try {
            pracownik.editPracownik(parameters);
            resultMessage = "<h2 class=\"text-center\" style=\"height:53px;\">Dane pracownika zostały zmienione!</h2><h5 class=\"text-center\" style=\"height:99px;margin-right:50px;margin-left:50px;\"><br>Zmiany powinny być już widoczne.<br></h5> <div class=\"form-group\"><a href=\"ControllerAccount?page=pracownicy\"><button class=\"btn btn-primary\" type=\"submit\" style=\"margin:0;width:265px;margin-left:267px;\">Powrót do pracowników</button></a></div>";
            request.setAttribute("message", resultMessage);
            getServletContext().getRequestDispatcher("/index_result.jsp").forward(request, response);

        } catch (SQLException | ParseException | DBReadWriteException e) {
            e.printStackTrace();
            resultMessage = "<h2 class=\"text-center\" style=\"height:53px;\">Wystąpił błąd podczas edycji pracownika.</h2><h5 class=\"text-center\" style=\"height:99px;margin-right:50px;margin-left:50px;\"><br>Prosimy o sprawdzenie, czy wprowadzone zostały prawidłowe dane<br>bądź czy podany adres e-mail nie jest zajęty.<br></h5> <div class=\"form-group\"><a href=\"ControllerAccount?page=pracownicy\"><button class=\"btn btn-primary\" type=\"submit\" style=\"margin:0;width:265px;margin-left:267px;\">Powrót do pracowników</button></a></div>";
            request.setAttribute("message", resultMessage);
            getServletContext().getRequestDispatcher("/index_result.jsp").forward(request, response);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

package myPage.servlets;

import myPage.basicObjects.Wydarzenie;
import myPage.data.others.SessionData;
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

@WebServlet(name="kontroler sluzacy do dodawania  wydarzenia", urlPatterns = { "/ControllerEventCreator" })

public class ControllerEventsCreator extends HttpServlet {

    private HttpSession session;
    private String resultMessage = "";

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        session = request.getSession();
        SessionData sessionData = (SessionData) session.getAttribute("userData");
        request.setCharacterEncoding("UTF-8");

        Wydarzenie event = new Wydarzenie();
        HashMap<String, String> parameters = new HashMap<>();

        parameters.put("typWydarzenia", request.getParameter("typ"));
        parameters.put("nazwa", request.getParameter("nazwa"));
        parameters.put("opis", request.getParameter("opis"));
        parameters.put("miejscowosc", request.getParameter("miejscowosc"));
        parameters.put("kod_pocztowy", request.getParameter("kod pocztowy"));
        parameters.put("ulica", request.getParameter("ulica"));
        parameters.put("data_od", request.getParameter("dataOd"));
        parameters.put("data_do", request.getParameter("dataDo"));
        parameters.put("kosz", request.getParameter("koszt"));
        parameters.put("id_pracownika", Integer.toString(sessionData.getId()));

        System.out.println("ID: " + session.getId());

        try {
            event.addWydarzenie(parameters);
            resultMessage = "<h2 class=\"text-center\" style=\"height:53px;\">Dodano poprawnie wydarzenie!</h2><h5 class=\"text-center\" style=\"height:99px;margin-right:50px;margin-left:50px;\"><br>Zmiany powinny być już widoczne.<br></h5> <div class=\"form-group\"><a href=\"ControllerAccount?page=wydarzenia\"><button class=\"btn btn-primary\" type=\"submit\" style=\"margin:0;width:265px;margin-left:267px;\">Powrót</button></a></div>";
            request.setAttribute("message", resultMessage);
            getServletContext().getRequestDispatcher("/index_result.jsp").forward(request, response);

        } catch (SQLException | ParseException | DBReadWriteException  | NullPointerException e) {
            e.printStackTrace();
            resultMessage = "<h2 class=\"text-center\" style=\"height:53px;\">Wystąpił błąd podczas dodawania wydarzenia.</h2><h5 class=\"text-center\" style=\"height:99px;margin-right:50px;margin-left:50px;\"><br>Prosimy o sprawdzenie, czy wprowadzone zostały prawidłowe dane<br>bądź czy podany adres e-mail nie jest zajęty.<br></h5> <div class=\"form-group\"><a href=\"ControllerAccount?page=wydarzenia\"><button class=\"btn btn-primary\" type=\"submit\" style=\"margin:0;width:265px;margin-left:267px;\">Powrót</button></a></div>";
            request.setAttribute("message", resultMessage);
            getServletContext().getRequestDispatcher("/index_result.jsp").forward(request, response);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

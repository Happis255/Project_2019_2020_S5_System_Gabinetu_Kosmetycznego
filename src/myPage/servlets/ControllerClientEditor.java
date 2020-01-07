package myPage.servlets;

import myPage.basicObjects.Klient;
import myPage.basicObjects.Pracownik;
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

@WebServlet("/ControllerClientEditor")

public class ControllerClientEditor extends HttpServlet {

    private HttpSession session;
    private String resultMessage = "";

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        request.setCharacterEncoding("UTF-8");

        session = request.getSession();
        SessionData sessionData = (SessionData) session.getAttribute("userData");
        Klient klient = new Klient(sessionData.getId());

        HashMap<String, String> parameters = new HashMap<>();

        parameters.put("p_id_klienta", Integer.toString(sessionData.getId()));
        parameters.put("p_nazwisko", request.getParameter("nazwisko"));
        parameters.put("p_ulica", request.getParameter("ulica"));
        parameters.put("p_kod_pocztowy", request.getParameter("kod_pocztowy"));
        parameters.put("p_miejscowowsc", request.getParameter("miejscowosc"));
        parameters.put("p_telefon", request.getParameter("telefon"));
        parameters.put("p_e_mail", request.getParameter("e_mail"));
        parameters.put("p_haslo", request.getParameter("haslo"));

        try {
            klient.editClient(parameters);
            resultMessage = "<h2 class=\"text-center\" style=\"height:53px;\">Twoje dane osobowy zostały zmienione!</h2><h5 class=\"text-center\" style=\"height:99px;margin-right:50px;margin-left:50px;\"><br>Zmiany powinny być już widoczne.<br></h5> <div class=\"form-group\"><a href=\"ControllerAccount?page=konto\"><button class=\"btn btn-primary\" type=\"submit\" style=\"margin:0;width:265px;margin-left:267px;\">Powrót do konta</button></a></div>";
            request.setAttribute("message", resultMessage);
            getServletContext().getRequestDispatcher("/index_result.jsp").forward(request, response);

        } catch (SQLException | ParseException | DBReadWriteException  | NullPointerException e) {
            e.printStackTrace();
            resultMessage = "<h2 class=\"text-center\" style=\"height:53px;\">Wystąpił błąd podczas edycji danych.</h2><h5 class=\"text-center\" style=\"height:99px;margin-right:50px;margin-left:50px;\"><br>Prosimy o sprawdzenie, czy wprowadzone zostały prawidłowe dane<br>bądź czy podany adres e-mail nie jest zajęty.<br></h5> <div class=\"form-group\"><a href=\"ControllerAccount?page=konto\"><button class=\"btn btn-primary\" type=\"submit\" style=\"margin:0;width:265px;margin-left:267px;\">Powrót do konta</button></a></div>";
            request.setAttribute("message", resultMessage);
            getServletContext().getRequestDispatcher("/index_result.jsp").forward(request, response);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

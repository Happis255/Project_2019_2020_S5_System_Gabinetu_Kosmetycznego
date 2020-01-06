package myPage.servlets;

import myPage.basicObjects.Klient;
import myPage.data.others.SessionData;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet("/ControllerClientRemover")
public class ControllerClientRemover extends HttpServlet {

    private HttpSession session;
    private String resultMessage = "";

    protected void doPost (HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        request.setCharacterEncoding("UTF-8");
        session = request.getSession();
        SessionData sessionData = (SessionData) session.getAttribute("userData");
        Klient klient = new Klient(sessionData.getId());

        try {
            klient.removeClient(sessionData.getId());
            resultMessage = "<h2 class=\"text-center\" style=\"height:53px;\">Twoje konto zostało usunięte z naszego systemu</h2><h5 class=\"text-center\" style=\"height:99px;margin-right:50px;margin-left:50px;\"><br>Jest nam niezmiernie przykro z tego powodu.<br>Prosimy o powiadomienie nas, dlaczego zaszła taka decyzja drogą mailową.</h5> <div class=\"form-group\"><a href=\"UserLogin\"><button class=\"btn btn-primary\" type=\"submit\" style=\"margin:0;width:265px;margin-left:267px;\">Powrót do strony głównej</button></a></div>";
            request.setAttribute("message", resultMessage);
            getServletContext().getRequestDispatcher("/index_result.jsp").forward(request, response);

        } catch (SQLException e) {
            e.printStackTrace();
            resultMessage = "<h2 class=\"text-center\" style=\"height:53px;\">Wystąpił błąd podczas próby usunięcia twojego konta.</h2><h5 class=\"text-center\" style=\"height:99px;margin-right:50px;margin-left:50px;\"><brProsimy o skontaktowanie się z administratorem systemu<br>bądź zgłoszenie błędu.<br></h5> <div class=\"form-group\"><a href=\"UserLogin\"><button class=\"btn btn-primary\" type=\"submit\" style=\"margin:0;width:265px;margin-left:267px;\"Powrót do strony głównej</button></a></div>";
            request.setAttribute("message", resultMessage);
            getServletContext().getRequestDispatcher("/index_result.jsp").forward(request, response);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

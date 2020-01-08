package myPage.servlets;

import myPage.basicObjects.Pracownik;
import myPage.basicObjects.Wydarzenie;
import myPage.data.others.ErrorMessage;
import myPage.data.others.SessionData;
import myPage.data.others.TypKonta;
import myPage.exceptions.ErrorException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet(name =  "kontroler służący do zapisywania sie na wydarzenia", urlPatterns = { "/SingInEvents" })
public class ControllerEventsSingIn extends HttpServlet {
    private HttpSession session;
    private String resultMessage = "";

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        request.setCharacterEncoding("UTF-8");

        session = request.getSession();
        SessionData sessionData = (SessionData) session.getAttribute("userData");
        Pracownik pracownik = null;

        if (sessionData.getAccoutType() != TypKonta.KLIENT) {
            pracownik = new Pracownik(sessionData.getId());
        } else {
            ErrorMessage errorMessage = new ErrorMessage(new ErrorException("brak dostepu do tej operacji"));
            session.setAttribute("errorMessage", errorMessage);
            response.sendRedirect("errorPage.jsp");
            return;
        }

        Wydarzenie event = new Wydarzenie();
        String[] spis_id = request.getParameterValues("do_zapisania");
        int idEventu = 0;
        int idPracownika = sessionData.getId();
        try {
            for (int i = 0; i < spis_id.length; i++){
                idEventu = Integer.parseInt(spis_id[i]);
                event.singInForEvent(idPracownika, idEventu);
            }
            resultMessage = "<h2 class=\"text-center\" style=\"height:53px;\">Zostałeś zapisany na wybrane wydarzenia!</h2> <div class=\"form-group\"><a href=\"ControllerAccount?page=wydarzenia\"><button class=\"btn btn-primary\" type=\"submit\" style=\"margin:0;width:265px;margin-left:267px;\">Powrót do wydarzeń</button></a></div>";
            request.setAttribute("message", resultMessage);
            getServletContext().getRequestDispatcher("/index_result.jsp").forward(request, response);

        } catch (NullPointerException e) {
            e.printStackTrace();
            resultMessage = "<h2 class=\"text-center\" style=\"height:53px;\">Wystąpił błąd.</h2><h5 class=\"text-center\" style=\"height:99px;margin-right:50px;margin-left:50px;\"><br>Prosimy o skontaktowanie się z administratorem systemu<br>bądź prosimy o zgłoszenie błędu.<br></h5> <div class=\"form-group\"><a href=\"ControllerAccount?page=wydarzenia\"><button class=\"btn btn-primary\" type=\"submit\" style=\"margin:0;width:265px;margin-left:267px;\">Powrót</button></a></div>";
            request.setAttribute("message", resultMessage);
            getServletContext().getRequestDispatcher("/index_result.jsp").forward(request, response);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}


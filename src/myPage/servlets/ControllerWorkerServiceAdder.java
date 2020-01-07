package myPage.servlets;

import myPage.basicObjects.Pracownik;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet(name =  "kontroler służący do nadawania możliwości wykonywania usługi wybranemu pracownika", urlPatterns = { "/ControllerWorkerServiceAdder" })
public class ControllerWorkerServiceAdder extends HttpServlet {

    private HttpSession session;
    private String resultMessage = "";

    protected void doPost (HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        request.setCharacterEncoding("UTF-8");
        session = request.getSession();
        Pracownik pracownik = new Pracownik();

        try {
            pracownik.addServiceWorker(Integer.parseInt(request.getParameter("pracownik_nadaj")), Integer.parseInt(request.getParameter("usluga_nadaj")));
            resultMessage = "<h2 class=\"text-center\" style=\"height:53px;\">Pracownik może wykonywać usługę.</h2><h5 class=\"text-center\" style=\"height:99px;margin-right:50px;margin-left:50px;\"><br>Zmiany powinny być już widoczne.<br></h5> <div class=\"form-group\"><a href=\"ControllerAccount?page=pracownicy\"><button class=\"btn btn-primary\" type=\"submit\" style=\"margin:0;width:265px;margin-left:267px;\">Powrót do pracowników</button></a></div>";
            request.setAttribute("message", resultMessage);
            getServletContext().getRequestDispatcher("/index_result.jsp").forward(request, response);

        } catch (Exception e ) {
            e.printStackTrace();
            resultMessage = "<h2 class=\"text-center\" style=\"height:53px;\">Wystąpił błąd podczas nadawania uprawnienia pracownikowi.</h2><h5 class=\"text-center\" style=\"height:99px;margin-right:50px;margin-left:50px;\"><br>Sprawdź, czy pracownik już posiada to urpawnienie<br>bądź zgłoś błąd administratorowi systemu.<br></h5> <div class=\"form-group\"><a href=\"ControllerAccount?page=pracownicy\"><button class=\"btn btn-primary\" type=\"submit\" style=\"margin:0;width:265px;margin-left:267px;\">Powrót do pracowników</button></a></div>";
            request.setAttribute("message", resultMessage);
            getServletContext().getRequestDispatcher("/index_result.jsp").forward(request, response);
        }
    }
}

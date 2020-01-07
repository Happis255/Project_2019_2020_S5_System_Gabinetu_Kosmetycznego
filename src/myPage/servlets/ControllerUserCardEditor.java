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

@WebServlet("/ControllerUserCardEditor")

public class ControllerUserCardEditor extends HttpServlet {

    private HttpSession session;
    private String resultMessage = "";

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        request.setCharacterEncoding("UTF-8");

        session = request.getSession();
        SessionData sessionData = (SessionData) session.getAttribute("userData");
        Klient klient = new Klient(sessionData.getId());

        HashMap<String, String> parameters = new HashMap<>();

        parameters.put("p_id_klienta", Integer.toString(sessionData.getId()));
        parameters.put("p1", request.getParameter("p1"));
        parameters.put("p2", request.getParameter("p2"));
        parameters.put("p3", request.getParameter("p3"));
        parameters.put("p4", request.getParameter("p4"));
        parameters.put("p5", request.getParameter("p5"));
        parameters.put("p6", request.getParameter("p6"));
        parameters.put("p7", request.getParameter("p7"));
        parameters.put("p8", request.getParameter("p8"));
        parameters.put("p9", request.getParameter("p9"));
        parameters.put("p10", request.getParameter("ocena-skory"));
        parameters.put("p11", request.getParameter("typ-skory"));
        parameters.put("p12", request.getParameter("wrazliwosc"));
        parameters.put("p13", request.getParameter("inne"));

        try {
            klient.editClientBook(parameters);
            resultMessage = "<h2 class=\"text-center\" style=\"height:53px;\">Dane karty klienta zostały zmienione!</h2><h5 class=\"text-center\" style=\"height:99px;margin-right:50px;margin-left:50px;\"><br>Zmiany powinny być już widoczne.<br></h5> <div class=\"form-group\"><a href=\"ControllerAccount?page=konto\"><button class=\"btn btn-primary\" type=\"submit\" style=\"margin:0;width:265px;margin-left:267px;\">Powrót do konta</button></a></div>";
            request.setAttribute("message", resultMessage);
            getServletContext().getRequestDispatcher("/index_result.jsp").forward(request, response);

        } catch (SQLException  | NullPointerException e) {
            e.printStackTrace();
            resultMessage = "<h2 class=\"text-center\" style=\"height:53px;\">Wystąpił błąd podczas edycji karty klienta.</h2><h5 class=\"text-center\" style=\"height:99px;margin-right:50px;margin-left:50px;\"><br>Prosimy o sprawdzenie, czy wprowadzone zostały prawidłowe dane.<br>W przypadku dalszych problemów prosimy o kontakt mailowy.<br></h5> <div class=\"form-group\"><a href=\"ControllerAccount?page=konto\"><button class=\"btn btn-primary\" type=\"submit\" style=\"margin:0;width:265px;margin-left:267px;\">Powrót do konta</button></a></div>";
            request.setAttribute("message", resultMessage);
            getServletContext().getRequestDispatcher("/index_result.jsp").forward(request, response);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

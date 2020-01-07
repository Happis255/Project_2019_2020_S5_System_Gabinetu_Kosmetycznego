package myPage.servlets;

import myPage.basicObjects.Status_Klienta;
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

@WebServlet("/ControllerStatusEditor")

public class ControllerStatusEditor extends HttpServlet {

    private HttpSession session;
    private String resultMessage = "";

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        request.setCharacterEncoding("UTF-8");

        Status_Klienta status = new Status_Klienta();
        HashMap<String, String> parameters = new HashMap<>();

        parameters.put("p_id", request.getParameter("status_id"));
        parameters.put("p_punkty_od", request.getParameter("punkty_od"));
        parameters.put("p_punkty_do", request.getParameter("punkty_do"));
        parameters.put("p_znizka_proc", request.getParameter("znizka_proc"));
        parameters.put("p_znizka_kwot", request.getParameter("znizka_kwot"));
        parameters.put("p_nazwa", request.getParameter("nazwa"));

        try {
            status.editStatus(parameters);
            resultMessage = "<h2 class=\"text-center\" style=\"height:53px;\">Dane statusu zostały zmienione!</h2><h5 class=\"text-center\" style=\"height:99px;margin-right:50px;margin-left:50px;\"><br>Zmiany powinny być już widoczne.<br></h5> <div class=\"form-group\"><a href=\"ControllerAccount?page=promocje\"><button class=\"btn btn-primary\" type=\"submit\" style=\"margin:0;width:265px;margin-left:267px;\">Powrót do promocji</button></a></div>";
            request.setAttribute("message", resultMessage);
            getServletContext().getRequestDispatcher("/index_result.jsp").forward(request, response);

        } catch (SQLException | NullPointerException e) {
            e.printStackTrace();
            resultMessage = "<h2 class=\"text-center\" style=\"height:53px;\">Wystąpił błąd podczas edycji statusu klienta.</h2><h5 class=\"text-center\" style=\"height:99px;margin-right:50px;margin-left:50px;\"><br>Prosimy o sprawdzenie, czy wprowadzone zostały prawidłowe dane<br>bądź serwer bazy danych jest uruchomiony.<br></h5> <div class=\"form-group\"><a href=\"ControllerAccount?page=promocje\"><button class=\"btn btn-primary\" type=\"submit\" style=\"margin:0;width:265px;margin-left:267px;\">Powrót do promocji</button></a></div>";
            request.setAttribute("message", resultMessage);
            getServletContext().getRequestDispatcher("/index_result.jsp").forward(request, response);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

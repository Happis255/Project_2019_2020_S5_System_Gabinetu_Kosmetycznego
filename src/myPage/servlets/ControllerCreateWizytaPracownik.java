package myPage.servlets;

import myPage.basicObjects.Wizyta;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;
import java.util.HashMap;

@WebServlet("/ControllerCreateWizytaPracownik" )
public class ControllerCreateWizytaPracownik extends HttpServlet {

    private HttpSession session;
    private String resultMessage = "";

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        request.setCharacterEncoding("UTF-8");

        HashMap<String, String> parameters = new HashMap<>();
        parameters.put("P_ID_PRACOWNIKA", request.getParameter("pracownik_id"));
        parameters.put("P_ID_USLUGI", request.getParameter("usluga_id"));
        parameters.put("P_ID_KLIENTA", request.getParameter("klient_id"));
        parameters.put("P_STATUS", "DO_ZATWIERDZENIA");
        parameters.put("P_DATA", request.getParameter("data"));
        parameters.put("P_GODZINA", request.getParameter("godzina"));

        Wizyta wizyta = new Wizyta();

        try {
            wizyta.createByWorker(parameters);
            resultMessage = "<h2 class=\"text-center\" style=\"height:53px;\">Wizyta została utworzona.</h2><h5 class=\"text-center\" style=\"height:99px;margin-right:50px;margin-left:50px;\"><br>Należy ją teraz jedynie potwierdzić,<br>bądź odrzucić w przypadku gdyby została utwrzona błędna wizyta.</h5> <div class=\"form-group\"><a href=\"ControllerAccount?page=wizyty\"><button class=\"btn btn-primary\" type=\"submit\" style=\"margin:0;width:265px;margin-left:267px;\">Powrót do wizyt</button></a></div>";
            request.setAttribute("message", resultMessage);
            getServletContext().getRequestDispatcher("/index_result.jsp").forward(request, response);
        } catch (SQLException e) {
            e.printStackTrace();
            resultMessage = "<script>\n" +
                    "function goBack() {\n" +
                    "  window.history.back();\n" +
                    "}\n" +
                    "</script><h2 class=\"text-center\" style=\"height:53px;\">Wybrany pracownik nie może wykonać tej usługi.</h2><h5 class=\"text-center\" style=\"height:99px;margin-right:50px;margin-left:50px;\"><br>Zmień pracownika wykonującego zabieg<br>bądź zmień wykonywaną usługę.<br></h5> <div class=\"form-group\"><a><button class=\"btn btn-primary\" onclick=\"goBack()\" style=\"margin:0;width:265px;margin-left:267px;\">Powrót do wizyt</button></a></div>";
            request.setAttribute("message", resultMessage);
            getServletContext().getRequestDispatcher("/index_result.jsp").forward(request, response);
        } catch (Exception e) {
            e.printStackTrace();
            resultMessage = "<h2 class=\"text-center\" style=\"height:53px;\">Wystąpił błąd podczas tworzenia wizyty.</h2><h5 class=\"text-center\" style=\"height:99px;margin-right:50px;margin-left:50px;\"><br>Prosimy o sprawdzenie, czy wprowadzone zostały prawidłowe dane<br>bądź czy nie wybrano błędnej daty.<br></h5> <div class=\"form-group\"><a href=\"ControllerAccount?page=wizyty\"><button class=\"btn btn-primary\" type=\"submit\" style=\"margin:0;width:265px;margin-left:267px;\">Powrót do wizyt</button></a></div>";
            request.setAttribute("message", resultMessage);
            getServletContext().getRequestDispatcher("/index_result.jsp").forward(request, response);
        }
    }
}

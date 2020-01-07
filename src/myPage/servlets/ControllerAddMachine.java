package myPage.servlets;

import myPage.basicObjects.Sprzet;
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

@WebServlet("/ControllerAddMachine")
public class ControllerAddMachine extends HttpServlet {

    private HttpSession session;
    private String resultMessage = "";

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        request.setCharacterEncoding("UTF-8");
        Sprzet sprzet = new Sprzet();

        HashMap<String, String> parameters = new HashMap<>();

        parameters.put("nazwa", request.getParameter("nazwa"));
        parameters.put("opis", request.getParameter("opis"));
        parameters.put("data_zakupu", request.getParameter("data_buy"));
        parameters.put("gwarancja", request.getParameter("gwarancja"));
        parameters.put("uwagi", request.getParameter("uwagi"));

        try {
            sprzet.addMachine(parameters);
            resultMessage = "<h2 class=\"text-center\" style=\"height:53px;\">Sprzęt została dodany do systemu.</h2><h5 class=\"text-center\" style=\"height:99px;margin-right:50px;margin-left:50px;\"><br>Zmiany powinny być już widoczne.<br></h5> <div class=\"form-group\"><a href=\"ControllerAccount?page=sprzet\"><button class=\"btn btn-primary\" type=\"submit\" style=\"margin:0;width:265px;margin-left:267px;\">Powrót do sprzętu</button></a></div>";
            request.setAttribute("message", resultMessage);
            getServletContext().getRequestDispatcher("/index_result.jsp").forward(request, response);

        } catch (SQLException | ParseException | NullPointerException e) {
            e.printStackTrace();
            resultMessage = "<h2 class=\"text-center\" style=\"height:53px;\">Wystąpił błąd podczas dodawania sprzetu.</h2><h5 class=\"text-center\" style=\"height:99px;margin-right:50px;margin-left:50px;\"><br>Prosimy o sprawdzenie, czy wprowadzone zostały prawidłowe dane<br>bądź czy nie wybrano błędnej daty.<br></h5> <div class=\"form-group\"><a href=\"ControllerAccount?page=sprzet\"><button class=\"btn btn-primary\" type=\"submit\" style=\"margin:0;width:265px;margin-left:267px;\">Powrót do sprzętu</button></a></div>";
            request.setAttribute("message", resultMessage);
            getServletContext().getRequestDispatcher("/index_result.jsp").forward(request, response);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

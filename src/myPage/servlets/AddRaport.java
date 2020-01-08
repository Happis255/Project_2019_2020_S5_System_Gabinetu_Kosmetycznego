package myPage.servlets;

import myPage.basicObjects.Nieobecnosc;
import myPage.basicObjects.Pracownik;
import myPage.basicObjects.Raport;
import myPage.data.others.ErrorMessage;
import myPage.data.others.SessionData;
import myPage.data.others.TypKonta;
import myPage.exceptions.DBReadWriteException;
import myPage.exceptions.ErrorException;

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

@WebServlet("/AddRaport")
public class AddRaport extends HttpServlet {
    private HttpSession session;
    private String resultMessage = "";

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        request.setCharacterEncoding("UTF-8");

        session = request.getSession();
        SessionData sessionData = (SessionData) session.getAttribute("userData");

        Raport raport = new Raport();
        HashMap<String, String> parameters = new HashMap<>();
        System.out.println("Pracownik o id: " + sessionData.getId() + " dodaje raport.");
        parameters.put("tytul", request.getParameter("tytul"));
        parameters.put("typ", request.getParameter("typ"));
        parameters.put("data", request.getParameter("data"));
        parameters.put("tresc", request.getParameter("tresc"));
        parameters.put("id_pracownika", Integer.toString(sessionData.getId()));

        try {
            raport.register(parameters);
            resultMessage = "<h2 class=\"text-center\" style=\"height:53px;\">Raport został dodany.</h2><h5 class=\"text-center\" style=\"height:99px;margin-right:50px;margin-left:50px;\"><br>Zmiany powinny być już widoczne.<br></h5> <div class=\"form-group\"><a href=\"ControllerAccount?page=raporty\"><button class=\"btn btn-primary\" type=\"submit\" style=\"margin:0;width:265px;margin-left:267px;\">Powrót do raportów</button></a></div>";
            request.setAttribute("message", resultMessage);
            getServletContext().getRequestDispatcher("/index_result.jsp").forward(request, response);

        } catch (SQLException | ParseException | DBReadWriteException | NullPointerException e) {
            e.printStackTrace();
            resultMessage = "<h2 class=\"text-center\" style=\"height:53px;\">Wystąpił błąd podczas dodawania raportu.</h2><h5 class=\"text-center\" style=\"height:99px;margin-right:50px;margin-left:50px;\"><br>Prosimy o sprawdzenie, czy wprowadzone zostały prawidłowe dane<br>bądź czy nie wybrano błędnej daty.<br></h5> <div class=\"form-group\"><a href=\"ControllerAccount?page=raporty\"><button class=\"btn btn-primary\" type=\"submit\" style=\"margin:0;width:265px;margin-left:267px;\">Powrót do raportów</button></a></div>";
            request.setAttribute("message", resultMessage);
            getServletContext().getRequestDispatcher("/index_result.jsp").forward(request, response);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

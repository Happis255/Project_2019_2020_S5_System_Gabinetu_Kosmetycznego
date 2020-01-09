package myPage.servlets;

import myPage.basicObjects.Services;
import myPage.data.others.SessionData;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.HashMap;

@WebServlet("/AddPrzeglad")
public class AddPrzeglad extends HttpServlet {

    private HttpSession session;
    private String resultMessage = "";

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        request.setCharacterEncoding("UTF-8");

        session = request.getSession();
        SessionData sessionData = (SessionData) session.getAttribute("userData");

        Services przeglad = new Services();
        HashMap<String, String> parameters = new HashMap<>();

        parameters.put("tytul_przegladu", request.getParameter("tytul_przegladu"));
        parameters.put("opis_przegladu", request.getParameter("opis_przegladu"));
        parameters.put("id_pracownika", Integer.toString(sessionData.getId()));
        parameters.put("id_sprzetu", request.getParameter("do_przegladu"));

        try {
                przeglad.register(parameters);
                resultMessage = "<h2 class=\"text-center\" style=\"height:53px;\">Raport został dodany.</h2><h5 class=\"text-center\" style=\"height:99px;margin-right:50px;margin-left:50px;\"><br>Zmiany powinny być już widoczne.<br></h5> <div class=\"form-group\"><a href=\"ControllerAccount?page=sprzet\"><button class=\"btn btn-primary\" type=\"submit\" style=\"margin:0;width:265px;margin-left:267px;\">Powrót do raportów</button></a></div>";
                request.setAttribute("message", resultMessage);
                getServletContext().getRequestDispatcher("/index_result.jsp").forward(request, response);

        } catch (Exception e) {
                e.printStackTrace();
                resultMessage = "<h2 class=\"text-center\" style=\"height:53px;\">Wystąpił błąd podczas dodawania raportu.</h2><h5 class=\"text-center\" style=\"height:99px;margin-right:50px;margin-left:50px;\"><br>Prosimy o sprawdzenie, czy wprowadzone zostały prawidłowe dane<br>bądź czy serwer bazy danych jest uruchomiony.<br></h5> <div class=\"form-group\"><a href=\"ControllerAccount?page=sprzet\"><button class=\"btn btn-primary\" type=\"submit\" style=\"margin:0;width:265px;margin-left:267px;\">Powrót do raportów</button></a></div>";
                request.setAttribute("message", resultMessage);
                getServletContext().getRequestDispatcher("/index_result.jsp").forward(request, response);
        }
    }
}

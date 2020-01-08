package myPage.servlets;

import myPage.basicObjects.Promocje;
import myPage.data.others.SessionData;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.HashMap;

@WebServlet("/ControllerEditPromo")
public class ControllerEditPromo extends HttpServlet {

    private HttpSession session;
    private String resultMessage = "";

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

        request.setCharacterEncoding("UTF-8");

        session = request.getSession();
        SessionData sessionData = (SessionData) session.getAttribute("userData");

        Promocje promo = new Promocje();
        HashMap<String, String> parameters = new HashMap<>();

        parameters.put("P_ID_PROMOCJI", request.getParameter("id_promocji"));
        parameters.put("P_NAZWA", request.getParameter("nazwa"));
        parameters.put("P_OPIS", request.getParameter("opis"));
        parameters.put("P_ZNIZKA_PROC", request.getParameter("znizka_proc"));
        parameters.put("P_ZNIZKA_KWOT", request.getParameter("znizka_kwot"));
        parameters.put("P_DATA_OD", request.getParameter("data_od"));
        parameters.put("P_DATA_DO", request.getParameter("data_do"));
        parameters.put("P_ID_PRACOWNIKA", Integer.toString(sessionData.getId()));

        try {
            promo.edit_promo(parameters);
            resultMessage = "<h2 class=\"text-center\" style=\"height:53px;\">Promocja została dodana do bazy danych.</h2><h5 class=\"text-center\" style=\"height:99px;margin-right:50px;margin-left:50px;\"><br>Zmiany powinny być już widoczne.<br></h5> <div class=\"form-group\"><a href=\"ControllerAccount?page=promocje\"><button class=\"btn btn-primary\" type=\"submit\" style=\"margin:0;width:265px;margin-left:267px;\">Powrót do promocji</button></a></div>";
            request.setAttribute("message", resultMessage);
            getServletContext().getRequestDispatcher("/index_result.jsp").forward(request, response);

        } catch (Exception e) {
            e.printStackTrace();
            resultMessage = "<h2 class=\"text-center\" style=\"height:53px;\">Wystąpił błąd podczas dodawania promocji.</h2><h5 class=\"text-center\" style=\"height:99px;margin-right:50px;margin-left:50px;\"><br>Prosimy o sprawdzenie, czy wprowadzone zostały prawidłowe dane<br>bądź czy nie wybrano błędnej daty.<br></h5> <div class=\"form-group\"><a href=\"ControllerAccount?page=promocje\"><button class=\"btn btn-primary\" type=\"submit\" style=\"margin:0;width:265px;margin-left:267px;\">Powrót do promocji</button></a></div>";
            request.setAttribute("message", resultMessage);
            getServletContext().getRequestDispatcher("/index_result.jsp").forward(request, response);
        }
    }
}

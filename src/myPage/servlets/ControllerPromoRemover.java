package myPage.servlets;

import myPage.basicObjects.Promocje;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/ControllerPromoRemover")
public class ControllerPromoRemover extends HttpServlet {

    private HttpSession session;
    private String resultMessage = "";

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        request.setCharacterEncoding("UTF-8");

        session = request.getSession();

        Promocje promo = new Promocje();
        String[] spis_id = request.getParameterValues("do_usuniecia");
        int a = 0;

        try {

            for (String s : spis_id) {
                a = Integer.parseInt(s);
                promo.remove_promo_ID(a);
            }
            resultMessage = "<h2 class=\"text-center\" style=\"height:53px;\">Promocja została ususnięta z bazy danych.</h2><h5 class=\"text-center\" style=\"height:99px;margin-right:50px;margin-left:50px;\"><br>Zmiany powinny być już widoczne.<br></h5> <div class=\"form-group\"><a href=\"ControllerAccount?page=promocje\"><button class=\"btn btn-primary\" type=\"submit\" style=\"margin:0;width:265px;margin-left:267px;\">Powrót do promocji</button></a></div>";
            request.setAttribute("message", resultMessage);
            getServletContext().getRequestDispatcher("/index_result.jsp").forward(request, response);

        } catch (Exception e) {
            e.printStackTrace();
            resultMessage = "<h2 class=\"text-center\" style=\"height:53px;\">Wystąpił błąd podczas usuwania promocji.</h2><h5 class=\"text-center\" style=\"height:99px;margin-right:50px;margin-left:50px;\"><br>Prosimy o sprawdzenie, czy zostały zaznaczone odpowiednie dane<br>bądź o skontaktowanie się z administratorem systemu.<br></h5> <div class=\"form-group\"><a href=\"ControllerAccount?page=promocje\"><button class=\"btn btn-primary\" type=\"submit\" style=\"margin:0;width:265px;margin-left:267px;\">Powrót do promocji</button></a></div>";
            request.setAttribute("message", resultMessage);
            getServletContext().getRequestDispatcher("/index_result.jsp").forward(request, response);
        }
    }
}


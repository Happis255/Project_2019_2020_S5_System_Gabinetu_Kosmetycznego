package myPage.servlets;

import myPage.basicObjects.Usluga;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet(name =  "kontroler służący do usuwania zaznaczonych z listy uslug", urlPatterns = { "/RemoveService" })
public class ControllerServiceRemover extends HttpServlet {

    private HttpSession session;
    private String resultMessage = "";

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        request.setCharacterEncoding("UTF-8");

        session = request.getSession();

        Usluga service = new Usluga();
        String[] spis_id = request.getParameterValues("do_usuniecia");
        int a = 0;

        try {
            for (int i = 0; i < spis_id.length; i++){
                a = Integer.parseInt(spis_id[i]);
                service.removeId(a);
            }
            resultMessage = "<h2 class=\"text-center\" style=\"height:53px;\">Usługa została usunięta z bazy danych</h2><h5 class=\"text-center\" style=\"height:99px;margin-right:50px;margin-left:50px;\"><br>Zmiany powinny być już widoczne.<br></h5> <div class=\"form-group\"><a href=\"index.jsp\"><button class=\"btn btn-primary\" type=\"submit\" style=\"margin:0;width:265px;margin-left:267px;\">Powrót do strony głównej</button></a></div>";
            request.setAttribute("message", resultMessage);
            getServletContext().getRequestDispatcher("../ControllerAccount?page=uslugi").forward(request, response);

        } catch (SQLException e) {
            e.printStackTrace();
            resultMessage = "<h2 class=\"text-center\" style=\"height:53px;\">Wystąpił błąd podczas usuwania usługi.</h2><h5 class=\"text-center\" style=\"height:99px;margin-right:50px;margin-left:50px;\"><brProsimy o skontaktowanie się z administratorem systemu<br>bądź zgłoszenie błędu.<br></h5> <div class=\"form-group\"><a href=\"index.jsp\"><button class=\"btn btn-primary\" type=\"submit\" style=\"margin:0;width:265px;margin-left:267px;\">Powrót do strony głównej</button></a></div>";
            request.setAttribute("message", resultMessage);
            getServletContext().getRequestDispatcher("../ControllerAccount?page=uslugi").forward(request, response);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}


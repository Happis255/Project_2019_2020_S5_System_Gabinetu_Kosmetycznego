package myPage.servlets;

import myPage.basicObjects.Usluga;
import myPage.exceptions.DBReadWriteException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.HashMap;

@WebServlet(name =  "kontroler sluzacy do dodawania uslug w gabinecie", urlPatterns = { "/AddService" })

public class ControllerServiceUploader extends HttpServlet {

    private HttpSession session;
    private String resultMessage = "";

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        request.setCharacterEncoding("UTF-8");

        Usluga uslugi = new Usluga();

        HashMap<String, String> parameters = new HashMap<>();

        String content = request.getParameter("typ_uslugi");
        System.out.println(content);
        parameters.put("id_uprawnienia", request.getParameter("id_uprawnienia"));
        parameters.put("typ_uslugi", request.getParameter("typ_uslugi"));
        parameters.put("nazwa", request.getParameter("nazwa"));
        parameters.put("opis", request.getParameter("opis"));
        parameters.put("czy_karta", request.getParameter("czy_karta"));
        parameters.put("cena", request.getParameter("cena"));
        parameters.put("czas_trwania", request.getParameter("czas_trwania"));
        parameters.put("wskazowka", request.getParameter("wskazowka"));

        try {
            uslugi.register(parameters);
            resultMessage = "<h2 class=\"text-center\" style=\"height:53px;\">Usługa została dodana do bazy gabinetu.</h2><h5 class=\"text-center\" style=\"height:99px;margin-right:50px;margin-left:50px;\"><br>Zmiany powinny być już widoczne.<br></h5> <div class=\"form-group\"><a href=\"ControllerAccount?page=uslugi\"><button class=\"btn btn-primary\" type=\"submit\" style=\"margin:0;width:265px;margin-left:267px;\">Powrót do usług</button></a></div>";
            request.setAttribute("message", resultMessage);
            getServletContext().getRequestDispatcher("/index_result.jsp").forward(request, response);

        } catch (SQLException | ParseException | DBReadWriteException e) {
            e.printStackTrace();
            resultMessage = "<h2 class=\"text-center\" style=\"height:53px;\">Wystąpił błąd podczas dodawania usługi.</h2><h5 class=\"text-center\" style=\"height:99px;margin-right:50px;margin-left:50px;\"><br>Prosimy o sprawdzenie, czy wprowadzone zostały prawidłowe dane<br>bądź czy nie wybrano błędnego ID uprawnienia.<br></h5> <div class=\"form-group\"><a href=\"ControllerAccount?page=uslugi\"><button class=\"btn btn-primary\" type=\"submit\" style=\"margin:0;width:265px;margin-left:267px;\">Powrót do usług</button></a></div>";
            request.setAttribute("message", resultMessage);
            getServletContext().getRequestDispatcher("/index_result.jsp").forward(request, response);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

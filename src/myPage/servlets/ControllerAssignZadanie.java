package myPage.servlets;

import myPage.basicObjects.ZadanieGospodarcze;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/ControllerAssignZadanie")
public class ControllerAssignZadanie extends HttpServlet {

    private HttpSession session;
    private String resultMessage = "";

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

        request.setCharacterEncoding("UTF-8");
        session = request.getSession();
        ZadanieGospodarcze zadanie = new ZadanieGospodarcze();

        try {
            zadanie.assigneZadanie(Integer.parseInt(request.getParameter("id_zadania")), Integer.parseInt(request.getParameter("id_pracownika")));
            resultMessage = "<h2 class=\"text-center\" style=\"height:53px;\">Zadanie zostało przypisane do pracownika.</h2><h5 class=\"text-center\" style=\"height:99px;margin-right:50px;margin-left:50px;\"><br>Zmiany powinny być już widoczne.<br></h5> <div class=\"form-group\"><a href=\"ControllerAccount?page=zadania_gosp\"><button class=\"btn btn-primary\" type=\"submit\" style=\"margin:0;width:265px;margin-left:267px;\">Powrót do zadań</button></a></div>";
            request.setAttribute("message", resultMessage);
            getServletContext().getRequestDispatcher("/index_result.jsp").forward(request, response);

        } catch (Exception e) {
            e.printStackTrace();
            resultMessage = "<h2 class=\"text-center\" style=\"height:53px;\">Wystąpił błąd podczas przypisywania zadania.</h2><h5 class=\"text-center\" style=\"height:99px;margin-right:50px;margin-left:50px;\"><br>Prosimy o sprawdzenie, czy wybrany zostały prawidłowy pracownik<br>bądź czy nie wybrano błędnego zadania.<br></h5> <div class=\"form-group\"><a href=\"ControllerAccount?page=zadania_gosp\"><button class=\"btn btn-primary\" type=\"submit\" style=\"margin:0;width:265px;margin-left:267px;\">Powrót do zadań</button></a></div>";
            request.setAttribute("message", resultMessage);
            getServletContext().getRequestDispatcher("/index_result.jsp").forward(request, response);
        }
    }
}

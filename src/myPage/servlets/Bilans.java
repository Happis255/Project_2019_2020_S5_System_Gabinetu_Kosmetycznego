package myPage.servlets;

import myPage.basicObjects.Nieobecnosc;
import myPage.basicObjects.Pracownik;
import myPage.basicObjects.Raport;
import myPage.basicObjects.Waste;
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

@WebServlet("/Bilans")
public class Bilans extends HttpServlet {
    private HttpSession session;
    private String resultMessage = "";

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        request.setCharacterEncoding("UTF-8");

        //session = request.getSession();
        //SessionData sessionData = (SessionData) session.getAttribute("userData");
        int bilans;
        Waste odpady = new Waste();
        HashMap<String, String> parameters = new HashMap<>();
        //System.out.println("Pracownik o id: " + sessionData.getId() + " dodaje raport.");
        parameters.put("data_od", request.getParameter("data_od"));
        parameters.put("data_do", request.getParameter("data_do"));

        try {
            bilans = odpady.count(parameters);
            resultMessage = "<h2 class=\"text-center\" style=\"height:53px;\">Obliczono bilans utylizacji</h2><h5 class=\"text-center\" style=\"height:99px;margin-right:50px;margin-left:50px;\"><br>Bilans utylizacji za wskazany okres wynosi: " + bilans + "<br></h5> <div class=\"form-group\"><a href=\"ControllerAccount?page=odpady\"><button class=\"btn btn-primary\" type=\"submit\" style=\"margin:0;width:265px;margin-left:267px;\">Powrót do raportów</button></a></div>";
            request.setAttribute("message", resultMessage);
            getServletContext().getRequestDispatcher("/index_result.jsp").forward(request, response);

        } catch (SQLException | ParseException | DBReadWriteException | NullPointerException e) {
            e.printStackTrace();
            resultMessage = "<h2 class=\"text-center\" style=\"height:53px;\">Wystąpił błąd podczas obliczeń.</h2><h5 class=\"text-center\" style=\"height:99px;margin-right:50px;margin-left:50px;\"><br>Prosimy o sprawdzenie, czy serwer bazy danych jest uruchomiony.<br></h5> <div class=\"form-group\"><a href=\"ControllerAccount?page=odpady\"><button class=\"btn btn-primary\" type=\"submit\" style=\"margin:0;width:265px;margin-left:267px;\">Powrót do raportów</button></a></div>";
            request.setAttribute("message", resultMessage);
            getServletContext().getRequestDispatcher("/index_result.jsp").forward(request, response);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

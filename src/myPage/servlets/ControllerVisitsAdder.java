package myPage.servlets;

import myPage.basicObjects.Wizyta;
import myPage.data.others.SessionData;
import myPage.data.others.VisitPage;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalTime;

@WebServlet("/ControllerVisitsAdder")
public class ControllerVisitsAdder extends HttpServlet {
    private HttpSession session;
    private String url = "";
    String resultMessage;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        session = request.getSession();
        String czas = request.getParameter("wybrana_usluga");

        if(czas != null){
            System.out.println("WYBRANA GODZINA:" + czas);
            url = "/ControllerAccount?page=wizytyWorker";
            VisitPage visitPage = (VisitPage)session.getAttribute("VisitPage");
            SessionData sessionData = (SessionData)session.getAttribute("userData");
            Wizyta wizyty = new Wizyta();
            LocalTime time = LocalTime.parse(czas);

            try {
                wizyty.dodajWizyteDoZatwierdzenia(visitPage.getIdUslugi(), sessionData.getId(),
                        visitPage.getIdPracownika(), visitPage.getData(), time);
                resultMessage = "<h2 class=\"text-center\" style=\"height:53px;\">Zgłoszenie wizyty zostało wysłane!</h2><h5 class=\"text-center\" style=\"height:99px;margin-right:50px;margin-left:50px;\"><br>Zmiany powinny być już widoczne.<br></h5> <div class=\"form-group\"><a href=\"ControllerAccount?page=konto\"><button class=\"btn btn-primary\" type=\"submit\" style=\"margin:0;width:265px;margin-left:267px;\">Powrót do konta</button></a></div>";
            } catch (SQLException | NullPointerException e) {
                e.printStackTrace();
                resultMessage = "<h2 class=\"text-center\" style=\"height:53px;\">Wystąpił błąd podczas wysyłania zgłoszenia wizyty.</h2><h5 class=\"text-center\" style=\"height:99px;margin-right:50px;margin-left:50px;\"><br>Prosimy o sprawdzenie, czy wprowadzone zostały prawidłowe dane.</h5> <div class=\"form-group\"><a href=\"ControllerAccount?page=konto\"><button class=\"btn btn-primary\" type=\"submit\" style=\"margin:0;width:265px;margin-left:267px;\">Powrót do konta</button></a></div>";
            } catch (Exception e) {
                e.printStackTrace();
            }

            session.removeAttribute("VisitPage");
        }else{
            System.out.println("NIE WYBRANO USLUGI");
            url = "/ControllerAccount?page=wizytyWorker";
        }

        request.setAttribute("message", resultMessage);
        getServletContext().getRequestDispatcher("/index_result.jsp").forward(request, response);
    }
}

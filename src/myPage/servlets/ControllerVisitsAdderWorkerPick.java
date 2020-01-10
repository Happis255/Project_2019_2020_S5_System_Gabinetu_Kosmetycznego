package myPage.servlets;

import myPage.data.others.VisitPage;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@WebServlet("/ControllerVisitsAdderWorkerPick")
public class ControllerVisitsAdderWorkerPick extends HttpServlet {
    private HttpSession session;
    private String url = "";

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        session = request.getSession();
        String pracownik = request.getParameter("pracownik");
        String data = request.getParameter("data-wizyty");

        if(pracownik != null && data != null){
            System.out.println("NUMER WYBRANEGO PRACOWNIKA:" + pracownik + "  DATA:" + data);
            url = "/ControllerAccount?page=wizytyHour";
            int idPracownika = Integer.parseInt(pracownik);;
            Date date = null;
            try {
                date = new SimpleDateFormat("yyyy-MM-dd").parse(data);
            } catch (ParseException e) {
                e.printStackTrace();
            }
            VisitPage visitPage = (VisitPage)session.getAttribute("VisitPage");
            visitPage.setIdPracownika(idPracownika);
            visitPage.setData(date);
        }else{
            System.out.println("NIE WYBRANO PRACOWNIKA I/LUB DATY");
            url = "/ControllerAccount?page=wizytyWorker";
        }

        response.sendRedirect(request.getContextPath() + url);
    }
}

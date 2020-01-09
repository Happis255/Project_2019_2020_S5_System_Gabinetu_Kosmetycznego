package myPage.servlets;

import myPage.data.others.VisitPage;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Date;

@WebServlet("/ControllerVisitsAdder")
public class ControllerVisitsAdder extends HttpServlet {

    private HttpSession session;
    private String resultMessage = "";

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        session = request.getSession();
        String page = request.getParameter("page");
        VisitPage visitPage = (VisitPage)request.getSession().getAttribute("VisitPage");

        String val;

        val = request.getParameter("data-wizyty");
        if(val != null)
            visitPage.setData(new Date(val));

        doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        session = request.getSession();
        String redirect = "P_Klient/visits_manager.jsp";
        String page = request.getParameter("visitSubpage");
        String userID = request.getParameter("userID");
        String workerID = request.getParameter("workerID");
        VisitPage visitPage = (VisitPage)request.getSession().getAttribute("VisitPage");

        if(userID != null)
            visitPage.setIdUslugi(Integer.parseInt(userID));
        if(workerID != null)
            visitPage.setIdPracownika(Integer.parseInt(workerID));

        visitPage.clearContent();

        Date data = (Date)session.getAttribute("DATA");
        if(page == null){
            visitPage.addContent("../P_Klient/visits_servicePick.jsp");
            visitPage.addContent("../P_Klient/visits_workerPick.jsp");
            visitPage.addContent("../P_Klient/visits_timePick.jsp");
            visitPage.addContent("../P_Klient/visits_hoursPick.jsp");
        }else {
            switch (page) {
                case "visits_servicePick":
                    visitPage.addContent("../P_Klient/visits_servicePick.jsp");
                    break;
                case "visits_workerPick":
                    visitPage.addContent("../P_Klient/visits_servicePick.jsp");
                    visitPage.addContent("../P_Klient/visits_workerPick.jsp");
                    break;
                case "visits_timePick":
                    visitPage.addContent("../P_Klient/visits_servicePick.jsp");
                    visitPage.addContent("../P_Klient/visits_workerPick.jsp");
                    visitPage.addContent("../P_Klient/visits_timePick.jsp");
                    break;
                default:
                    visitPage.addContent("../P_Klient/visits_servicePick.jsp");
                    break;
            }
        }
        response.sendRedirect(redirect);
    }
}

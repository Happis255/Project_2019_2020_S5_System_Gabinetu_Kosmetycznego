package myPage.servlets;

import myPage.data.others.VisitPage;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/ControllerVisitsAdderServicePick")
public class ControllerVisitsAdderServicePick extends HttpServlet {

    private HttpSession session;
    private String url = "";

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        session = request.getSession();
        String usluga = request.getParameter("usluga");

        if(usluga != null){
            System.out.println("NUMER WYBRANEJ USLUGI:" + usluga);
            url = "/ControllerAccount?page=wizytyWorker";
            int idUslugi = Integer.parseInt(usluga);
            VisitPage visitPage = new VisitPage();
            visitPage.setIdUslugi(idUslugi);
            session.setAttribute("VisitPage", visitPage);
        }else{
            System.out.println("NIE WYBRANO USLUGI");
            url = "/ControllerAccount?page=servicePick";
        }

        response.sendRedirect(request.getContextPath() + url);
    }
}

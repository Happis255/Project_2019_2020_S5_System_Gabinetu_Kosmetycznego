package myPage.servlets;

import myPage.data.others.SessionData;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(description = "kontroler do obsługi kont klientow", urlPatterns = { "/AccountKlient" })
public class ControllerAccountKlient extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        SessionData sessionData = (SessionData)request.getSession().getAttribute("userData");
        sessionData.setPage(request.getParameter("page"));
        response.sendRedirect("P_User/account.jsp");
    }
}

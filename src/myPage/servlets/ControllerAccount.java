package myPage.servlets;

import myPage.data.others.SessionData;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(description = "kontroler do obsługi kont", urlPatterns = { "/AccountController" })
public class ControllerAccount extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        SessionData sessionData = (SessionData)request.getSession().getAttribute("userData");
        String redirect;
        sessionData.setPage(request.getParameter("page"));
        switch (sessionData.getAccoutType()){
            case KLIENT:
                redirect = "P_Klient/klient_result.jsp";
                break;
            case PRACOWNIK:
                redirect = "P_Pracownik/pracownik_result.jsp";
                break;
            case ADMINISTRATOR:
                redirect = "P_Administrator/administrator_result.jsp";
                break;
            default:
                redirect = "index.jsp";
                break;
        }
        response.sendRedirect(redirect);
    }
}

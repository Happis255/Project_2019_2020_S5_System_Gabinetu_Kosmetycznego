package myPage.servlets;

import myPage.basicObjects.User;
import myPage.data.others.ErrorMessage;
import myPage.others.Encrypter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet(description = "kontroler do obsługi logowania klientow", urlPatterns = { "/UserLogin" })
public class ControllerLogin extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private static final int maxLoginAttempts = 5;
    private HttpSession session;
    private String url;
    private int loginAttempts;

    public ControllerLogin() {
        super();
        loginAttempts = 0;
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        session = request.getSession();
        if(loginAttempts >= maxLoginAttempts){
            System.out.println("zbyt duzo logowan");
            response.sendRedirect("logowanie.jsp");
            return;
        }

        User user = new User();
        Encrypter pws = new Encrypter();

        try {
            boolean ret;
            ret = user.login(request, request.getParameter("username"), pws.encrypt(request.getParameter("password")));
            if(!ret){
                ++loginAttempts;
                System.out.println("błędny login i/lub hasło");
            }else{
                loginAttempts = 0;
                System.out.println("zalogowano");
            }
        } catch (SQLException e) {
            ErrorMessage errorMessage = new ErrorMessage(e);
            session.setAttribute("errorMessage", errorMessage);
            response.sendRedirect("errorPage.jsp");
            return;
        }

        response.sendRedirect("index.jsp");
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        User user = new User();
        user.logout(request.getSession());

        response.sendRedirect("index.jsp");
    }
}

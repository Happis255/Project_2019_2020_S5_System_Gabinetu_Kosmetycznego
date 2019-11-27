package myPage.servlets;

import myPage.exceptions.DBReadWriteException;
import myPage.others.DataSource;
import myPage.others.Encrypter;
import myPage.data.SessionData;
import myPage.data.User;

import javax.servlet.RequestDispatcher;
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
        }

        if(session.getAttribute("userData") != null){
            System.out.println("najpierw sie wyloguj");
            response.sendRedirect("logowanie.jsp");
            return;
        }

        String username = request.getParameter("username");
        String password = request.getParameter("password");

        Encrypter pws = new Encrypter();
        String encryptedPasswd = pws.encrypt(password);

        DataSource dataSource;
        User user = null;
        try {
            dataSource = new DataSource();
            user = dataSource.getUserDB(username);
        } catch (DBReadWriteException | SQLException e) {
            e.printStackTrace();
        }

        if(user != null){
            session = request.getSession();
            session.invalidate();
            session = request.getSession(true);
            SessionData sessionData = new SessionData(username);
            session.setAttribute("userData", sessionData);
            loginAttempts = 0;
            System.out.println("zalogowano");
            url = "index.jsp";
        } else{
            ++loginAttempts;
            System.out.println("errorLogowania");
            url = "index.jsp";
        }
        RequestDispatcher dispatcher = request.getRequestDispatcher(url);
        dispatcher.forward(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getSession().invalidate();
        response.sendRedirect("index.jsp");
    }

    /*
    public void logout() {
        session.invalidate();
    }
    */
}

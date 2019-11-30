package myPage.servlets;

import myPage.data.Client;
import myPage.data.ErrorMessage;
import myPage.data.SessionData;
import myPage.exceptions.DBReadWriteException;
import myPage.others.DataSource;
import myPage.others.Encrypter;

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
        Client client = null;
        try {
            dataSource = new DataSource();
            client = dataSource.getClientDateDB(username);
        } catch (DBReadWriteException e) {
            client = null;
        }catch (SQLException e) {
            ErrorMessage errorMessage = new ErrorMessage(e);
            session.setAttribute("errorMessage", errorMessage);
            response.sendRedirect("errorPage.jsp");
            return;
        }

        if(client != null){
            session = request.getSession();
            session.invalidate();
            session = request.getSession(true);
            SessionData sessionData = new SessionData(username, client.getTyp_konta());
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

package myPage;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet(description = "kontroler do obsługi logowania klientow", urlPatterns = { "/UserRegister" })
public class ControllerRegister extends HttpServlet {
    private HttpSession session;

    public ControllerRegister(){
        super();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        session = request.getSession();
        if(session.getAttribute("userData") != null){
            System.out.println("najpierw sie wyloguj");
            response.sendRedirect("logowanie.jsp");
            return;
        }

        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String repeatPassword = request.getParameter("repeatPassword");

        if(password.compareTo(repeatPassword) != 0){
            System.out.println("hasla nie sa takie same");
            response.sendRedirect("rejestracja.jsp");
            return;
        }

        DataSource dataSource = new DataSource();
        User user = dataSource.getUserDB(username);

        if(user != null){
            System.out.println("urzytkownik o podanym niku juz istnieje");
            response.sendRedirect("rejestracja.jsp");
            return;
        }

        Encrypter pws = new Encrypter();
        String encryptedPasswd = pws.encrypt(password);

        dataSource.createClientDB(new Client(username, encryptedPasswd));

        response.sendRedirect("logowanie.jsp");
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }


}

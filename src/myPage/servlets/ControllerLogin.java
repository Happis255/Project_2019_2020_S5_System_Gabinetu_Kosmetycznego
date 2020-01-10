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
            String resultMessage = "<h2 class=\"text-center\" style=\"height:53px;\">Logowanie nie powiodło się.</h2><h5 class=\"text-center\" style=\"height:99px;margin-right:50px;margin-left:50px;\"><br>Dokonano zbyt wielu prób niepoprawnego logowania.<br>Skontaktuj się z administratorem systemu.</h5> <div class=\"form-group\"><a href=\"index.jsp\"><button class=\"btn btn-primary\" type=\"submit\" style=\"margin:0;width:265px;margin-left:267px;\">Powrót do strony głównej</button></a></div>";
            request.setAttribute("message", resultMessage);
            getServletContext().getRequestDispatcher("/index_result.jsp").forward(request, response);
        }

        User user = new User();
        Encrypter pws = new Encrypter();

        try {
            boolean ret;
            ret = user.login(request, request.getParameter("username"), pws.encrypt(request.getParameter("password")));
            if(!ret){
                ++loginAttempts;
                String resultMessage = "<h2 class=\"text-center\" style=\"height:53px;\">Logowanie nie powiodło się.</h2><h5 class=\"text-center\" style=\"height:99px;margin-right:50px;margin-left:50px;\"><br>Upewnij się, czy wprowadzony został poprawny e-mail/hasło,<br>bądź czy niezmieniany był ostatnio e-mail bądź hasło.</h5> <div class=\"form-group\"><a href=\"index.jsp\"><button class=\"btn btn-primary\" type=\"submit\" style=\"margin:0;width:265px;margin-left:267px;\">Powrót do strony głównej</button></a></div>";
                request.setAttribute("message", resultMessage);
                getServletContext().getRequestDispatcher("/index_result.jsp").forward(request, response);
            }else{
                loginAttempts = 0;
                String resultMessage = "<h2 class=\"text-center\" style=\"height:53px;\">Witamy w systemie.</h2><h5 class=\"text-center\" style=\"height:99px;margin-right:50px;margin-left:50px;\"><br>Przejdź do swojego panelu za pomocą opcji z górnego menu.</h5> <div class=\"form-group\"><a href=\"index.jsp\"><button class=\"btn btn-primary\" type=\"submit\" style=\"margin:0;width:265px;margin-left:267px;\">Przejście do strony głównej</button></a></div>";
                request.setAttribute("message", resultMessage);
                getServletContext().getRequestDispatcher("/index_result.jsp").forward(request, response);
            }
        } catch (SQLException e) {
            String resultMessage = "<h2 class=\"text-center\" style=\"height:53px;\">Logowanie nie powiodło się.</h2><h5 class=\"text-center\" style=\"height:99px;margin-right:50px;margin-left:50px;\"><br>Upewnij się, czy wprowadzony został poprawny e-mail/hasło,<br>bądź czy niezmieniany był ostatnio e-mail bądź hasło.</h5> <div class=\"form-group\"><a href=\"index.jsp\"><button class=\"btn btn-primary\" type=\"submit\" style=\"margin:0;width:265px;margin-left:267px;\">Powrót do strony głównej</button></a></div>";
            request.setAttribute("message", resultMessage);
            getServletContext().getRequestDispatcher("/index_result.jsp").forward(request, response);
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

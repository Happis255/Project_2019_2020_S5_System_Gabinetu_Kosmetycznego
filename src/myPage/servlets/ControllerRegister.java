package myPage.servlets;

import myPage.basicObjects.User;
import myPage.data.others.ErrorMessage;
import myPage.exceptions.DBReadWriteException;
import myPage.others.Encrypter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.HashMap;

@WebServlet(description = "kontroler do obsługi logowania klientow", urlPatterns = { "/UserRegister" })
public class ControllerRegister extends HttpServlet {
    private HttpSession session;

    public ControllerRegister(){
        super();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        session = request.getSession();
        if(session.getAttribute("userData") != null){
            System.out.println("juz jestes zalogowany");
            response.sendRedirect("logowanie.jsp");
            return;
        }

        Encrypter pws = new Encrypter();
        User user = new User();
        boolean ret;
        HashMap<String, String> parameters = new HashMap<>();

        parameters.put("e-mail", request.getParameter("e-mail"));
        parameters.put("haslo", pws.encrypt(request.getParameter("haslo")));
        parameters.put("imie", request.getParameter("imie"));
        parameters.put("nazwisko", request.getParameter("nazwisko"));
        parameters.put("data-urodzenia", request.getParameter("data-urodzenia"));
        parameters.put("telefon", request.getParameter("telefon"));
        parameters.put("ulica", request.getParameter("ulica"));
        parameters.put("kod", request.getParameter("kod"));
        parameters.put("miejscowosc", request.getParameter("miejscowosc"));

        try {
            ret = user.register(parameters);
        } catch (SQLException | ParseException | DBReadWriteException e) {
            ErrorMessage errorMessage = new ErrorMessage(e);
            session.setAttribute("errorMessage", errorMessage);
            response.sendRedirect("errorPage.jsp");
            return;
        }

        if(!ret)
            System.out.println("urzytkownik o podanym nicku juz istnieje");

        response.sendRedirect("logowanie.jsp");
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}

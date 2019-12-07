package myPage.servlets;

import myPage.basicObjects.Pracownik;
import myPage.data.dataBase.AktualnoscData;
import myPage.data.others.ErrorMessage;
import myPage.data.others.SessionData;
import myPage.data.others.TypKonta;
import myPage.exceptions.DBReadWriteException;
import myPage.exceptions.ErrorException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@WebServlet(name =  "kontroler do obsługi uploadowania grafiki i dodawania aktualnosci do bazy danych", urlPatterns = { "/UploadNewsWithJPG" })
public class ControllerNewsUploader extends HttpServlet {

    private HttpSession session;
    private String resultMessage = "";
    private boolean isMultipart;
    private String filePath;
    private File file ;
    private int maxFileSize = 1024 * 1024 * 50;
    private int maxMemSize = 4 * 1024;

    public void init( ){
        filePath = getServletContext().getInitParameter("file-upload");
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        /* Tu musi byc sprawdzanie, czy zalogowana osoba ma uprawnienia do wrzucania news'a?
        * (Sprawdź, czy pracownik to pracownik, jak nie, przekieruj na stronę wynikową*/

        session = request.getSession();
        SessionData sessionData = (SessionData)session.getAttribute("userData");
        Pracownik pracownik = null;

        if(sessionData.getAccoutType() != TypKonta.KLIENT){
            pracownik = new Pracownik(sessionData.getId());
        }else{
            ErrorMessage errorMessage = new ErrorMessage(new ErrorException("brak dostepu do tej operacji"));
            session.setAttribute("errorMessage", errorMessage);
            response.sendRedirect("errorPage.jsp");
            return;
        }

        Date date_od;
        Date date_do;
        try {
            DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            date_od = dateFormat.parse(request.getParameter("data-od"));
            date_do = dateFormat.parse(request.getParameter("data-do"));
        } catch (ParseException e) {
            ErrorMessage errorMessage = new ErrorMessage(e);
            session.setAttribute("errorMessage", errorMessage);
            response.sendRedirect("errorPage.jsp");
            return;
        }

        AktualnoscData aktualnosc = new AktualnoscData(
                0,
                request.getParameter("tytul"),
                request.getParameter("opis"),
                date_od,
                date_do,
                sessionData.getId()
        );





        try {
            int ID_Newsa = pracownik.addAktualnosci(aktualnosc);

            /* Pobieramy informacje o tym, jakie ID ma ta aktualnosc - potrzebne do ustawienia nazwy pliku.
            *  W bazie danych będzie to poprostu max ID */

            /* Uploadujemy grafike dla news'a */
            //DiskFileItemFactory factory = new DiskFileItemFactory();

            // maximum size that will be stored in memory
            //factory.setSizeThreshold(maxMemSize);

            // Location to save data that is larger than maxMemSize.
            //factory.setRepository(new File("c:\\temp"));

            // Create a new file upload handler
            ///ServletFileUpload upload = new ServletFileUpload(factory);

            // maximum file size to be uploaded.
            //upload.setSizeMax( maxFileSize );


            resultMessage = "<h2 class=\"text-center\" style=\"height:53px;\">Aktualność została dodana do bazy danych</h2><h5 class=\"text-center\" style=\"height:99px;margin-right:50px;margin-left:50px;\"><br>Zmiany powinny być już widoczne.<br></h5> <div class=\"form-group\"><a href=\"index.jsp\"><button class=\"btn btn-primary\" type=\"submit\" style=\"margin:0;width:265px;margin-left:267px;\">Powrót do strony głównej</button></a></div>";
        } catch (DBReadWriteException | SQLException e) {
            ErrorMessage errorMessage = new ErrorMessage(e);
            session.setAttribute("errorMessage", errorMessage);
            response.sendRedirect("errorPage.jsp");
            resultMessage = "<h2 class=\"text-center\" style=\"height:53px;\">Wystąpił błąd przy podczas dodawania aktualności.</h2><h5 class=\"text-center\" style=\"height:99px;margin-right:50px;margin-left:50px;\"><br>Prosimy o sprawdzenie, czy wybrane zostały prawidłowe daty<br>bądź czy zawarta grafika nie przekracza 50MB i jest w formacie .jpg.<br></h5> <div class=\"form-group\"><a href=\"wyslij-cv.jsp\"><button class=\"btn btn-primary\" type=\"submit\" style=\"margin:0;width:265px;margin-left:267px;\">Powrót do strony głównej</button></a></div>";
        }
        finally {
            request.setAttribute("message", resultMessage);
            getServletContext().getRequestDispatcher("/wynik-wiadomosc.jsp").forward(request, response);
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}

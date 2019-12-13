package myPage.servlets;

import myPage.basicObjects.Aktualnosci;
import myPage.basicObjects.Pracownik;
import myPage.data.others.ErrorMessage;
import myPage.data.others.SessionData;
import myPage.data.others.TypKonta;
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

@WebServlet(name =  "kontroler służący do usuwania zaznaczonych z listy aktualności", urlPatterns = { "/RemoveNewsWithJPG" })
public class ControllerNewsRemover extends HttpServlet {

    private HttpSession session;
    private String resultMessage = "";

    String file_directory = "C:\\Users\\Huber\\Desktop\\Project_2019_2020_S5\\out\\artifacts\\projectWebbAppTest2_war_exploded\\assets\\aktualnosci_grafika\\";

    private String generateFileName(int a) {
        String rozszerzenie = ".jpg";
        int id = a;
        String name = "aktualnosc_" + id + rozszerzenie;
        return name;
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        request.setCharacterEncoding("UTF-8");

        session = request.getSession();
        SessionData sessionData = (SessionData) session.getAttribute("userData");
        Pracownik pracownik = null;

        if (sessionData.getAccoutType() != TypKonta.KLIENT) {
            pracownik = new Pracownik(sessionData.getId());
        } else {
            ErrorMessage errorMessage = new ErrorMessage(new ErrorException("brak dostepu do tej operacji"));
            session.setAttribute("errorMessage", errorMessage);
            response.sendRedirect("errorPage.jsp");
            return;
        }

        Aktualnosci news = new Aktualnosci();
        String[] spis_id = request.getParameterValues("do_usuniecia");
        int a = 0;
        String file_name;
        File deleteFile;

        try {
            for (int i = 0; i < spis_id.length; i++){
                a = Integer.parseInt(spis_id[i]);
                news.removeId(a);
                file_name = generateFileName(a);
                deleteFile = new File(file_directory+file_name);
                deleteFile.delete();
            }
            resultMessage = "<h2 class=\"text-center\" style=\"height:53px;\">Aktualność została usunięta z bazy danych</h2><h5 class=\"text-center\" style=\"height:99px;margin-right:50px;margin-left:50px;\"><br>Zmiany powinny być już widoczne.<br></h5> <div class=\"form-group\"><a href=\"index.jsp\"><button class=\"btn btn-primary\" type=\"submit\" style=\"margin:0;width:265px;margin-left:267px;\">Powrót do strony głównej</button></a></div>";
            request.setAttribute("message", resultMessage);
            getServletContext().getRequestDispatcher("/P_Pracownik/pracownik_result.jsp").forward(request, response);

        } catch (SQLException e) {
            e.printStackTrace();
            resultMessage = "<h2 class=\"text-center\" style=\"height:53px;\">Wystąpił błąd podczas usuwania aktualności.</h2><h5 class=\"text-center\" style=\"height:99px;margin-right:50px;margin-left:50px;\"><brProsimy o skontaktowanie się z administratorem systemu<br>bądź zgłoszenie błędu.<br></h5> <div class=\"form-group\"><a href=\"index.jsp\"><button class=\"btn btn-primary\" type=\"submit\" style=\"margin:0;width:265px;margin-left:267px;\">Powrót do strony głównej</button></a></div>";
            request.setAttribute("message", resultMessage);
            getServletContext().getRequestDispatcher("/P_Pracownik/pracownik_result.jsp").forward(request, response);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}


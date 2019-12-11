package myPage.servlets;

import myPage.basicObjects.Pracownik;
import myPage.basicObjects.Aktualnosci;
import myPage.data.others.ErrorMessage;
import myPage.data.others.SessionData;
import myPage.data.others.TypKonta;
import myPage.exceptions.DBReadWriteException;
import myPage.exceptions.ErrorException;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.Collection;
import java.util.HashMap;

@MultipartConfig(   fileSizeThreshold = 1024 * 1024 * 2,
        maxFileSize = 1024 * 1024 * 50,         // 50MB
        maxRequestSize = 1024 * 1024 * 50)      // 50MB
@WebServlet(name =  "kontroler do obsługi uploadowania grafiki i dodawania aktualnosci do bazy danych", urlPatterns = { "/UploadNewsWithJPG" })

public class ControllerNewsUploader extends HttpServlet {

    String file_directory = "C:\\Users\\Huber\\Desktop\\Project_2019_2020_S5\\out\\artifacts\\projectWebbAppTest2_war_exploded\\assets\\aktualnosci_grafika\\";

    private HttpSession session;
    private String resultMessage = "";

    private String generateFileName() throws SQLException, DBReadWriteException {
        Aktualnosci news = new Aktualnosci();
        String rozszerzenie = ".jpg";
        int id = news.get_MaxId();
        String name = "aktualnosc_" + id + rozszerzenie;
        return name;
    }

    private void saveUploadedFiles(HttpServletRequest request) throws IllegalStateException, IOException, ServletException, SQLException, DBReadWriteException {

        byte[] buffer = new byte[4096];
        int bytesRead = -1;
        Collection<Part> multiparts = request.getParts();
        if (multiparts.size() > 0) {
            for (Part part : request.getParts()) {

                String fileName = generateFileName();

                if (fileName == null || fileName.equals("")) {
                    continue;
                }
                File saveFile = new File(fileName);


                FileOutputStream outputStream = new FileOutputStream(file_directory + saveFile);
                InputStream inputStream = part.getInputStream();
                while ((bytesRead = inputStream.read(buffer)) != -1) {
                    outputStream.write(buffer, 0, bytesRead);
                }
                outputStream.close();
                inputStream.close();
            }
        }
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

        HashMap<String, String> parameters = new HashMap<>();
        String content = request.getParameter("tytul");
        System.out.println(content);
        parameters.put("tytul", request.getParameter("tytul"));
        parameters.put("opis", request.getParameter("opis"));
        parameters.put("data-od", request.getParameter("date-od"));
        parameters.put("data-do", request.getParameter("date-do"));
        parameters.put("id-pracownika", Integer.toString(sessionData.getId()));

        try {
            news.register(parameters);
            saveUploadedFiles(request);
            resultMessage = "<h2 class=\"text-center\" style=\"height:53px;\">Aktualność została dodana do bazy danych</h2><h5 class=\"text-center\" style=\"height:99px;margin-right:50px;margin-left:50px;\"><br>Zmiany powinny być już widoczne.<br></h5> <div class=\"form-group\"><a href=\"index.jsp\"><button class=\"btn btn-primary\" type=\"submit\" style=\"margin:0;width:265px;margin-left:267px;\">Powrót do strony głównej</button></a></div>";
            request.setAttribute("message", resultMessage);
            getServletContext().getRequestDispatcher("/wynik-wiadomosc.jsp").forward(request, response);

        } catch (SQLException | ParseException | DBReadWriteException e) {
            e.printStackTrace();
            resultMessage = "<h2 class=\"text-center\" style=\"height:53px;\">Wystąpił błąd przy podczas dodawania aktualności.</h2><h5 class=\"text-center\" style=\"height:99px;margin-right:50px;margin-left:50px;\"><br>Prosimy o sprawdzenie, czy wybrane zostały prawidłowe daty<br>bądź czy zawarta grafika nie przekracza 50MB i jest w formacie .jpg.<br></h5> <div class=\"form-group\"><a href=\"wyslij-cv.jsp\"><button class=\"btn btn-primary\" type=\"submit\" style=\"margin:0;width:265px;margin-left:267px;\">Powrót do strony głównej</button></a></div>";
            request.setAttribute("message", resultMessage);
            getServletContext().getRequestDispatcher("/wynik-wiadomosc.jsp").forward(request, response);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

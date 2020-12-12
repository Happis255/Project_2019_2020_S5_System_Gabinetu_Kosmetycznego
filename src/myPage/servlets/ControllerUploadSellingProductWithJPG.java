package myPage.servlets;

import myPage.basicObjects.Produkt;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.SQLException;
import java.util.Collection;
import java.util.HashMap;

@MultipartConfig(   fileSizeThreshold = 1024 * 1024 * 2,
        maxFileSize = 1024 * 1024 * 50,         // 50MB
        maxRequestSize = 1024 * 1024 * 50)      // 50MB
@WebServlet("/ControllerUploadSellingProductWithJPG")

public class ControllerUploadSellingProductWithJPG extends HttpServlet {

    String file_directory = "C:\\Sieci\\2020\\out\\artifacts\\projectWebbAppTest2_war_exploded\\assets\\produkty_grafika\\";

    private HttpSession session;
    private String resultMessage = "";

    private String generateFileName() throws SQLException {
        Produkt produkty = new Produkt();
        String rozszerzenie = ".jpg";
        int id = produkty.get_MaxId_produkt_sprzedazowy();
        String name = "produkt_" + id + rozszerzenie;
        return name;
    }

    private void saveUploadedFiles(HttpServletRequest request) throws IllegalStateException, IOException, ServletException, SQLException {

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
        Produkt produkty = new Produkt();

        HashMap<String, String> parameters = new HashMap<>();
        parameters.put("P_NAZWA", request.getParameter("nazwa"));
        parameters.put("P_OPIS", request.getParameter("opis"));
        parameters.put("P_CENA", request.getParameter("cena"));
        parameters.put("P_ILOSC", request.getParameter("ilosc"));

        try {
            produkty.add_sellingProduct(parameters);
            saveUploadedFiles(request);
            resultMessage = "<h2 class=\"text-center\" style=\"height:53px;\">Produkt został dodany do bazy danych.</h2><h5 class=\"text-center\" style=\"height:99px;margin-right:50px;margin-left:50px;\"><br>Zmiany powinny być już widoczne.<br></h5> <div class=\"form-group\"><a href=\"ControllerAccount?page=produkty\"><button class=\"btn btn-primary\" type=\"submit\" style=\"margin:0;width:265px;margin-left:267px;\">Powrót do produktów</button></a></div>";
            request.setAttribute("message", resultMessage);
            getServletContext().getRequestDispatcher("/index_result.jsp").forward(request, response);

        } catch (Exception e) {
            e.printStackTrace();
            resultMessage = "<h2 class=\"text-center\" style=\"height:53px;\">Wystąpił błąd podczas dodawania produktu.</h2><h5 class=\"text-center\" style=\"height:99px;margin-right:50px;margin-left:50px;\"><br>Prosimy o upewnienie się, czy zostały wprowadzone odpowiednie dane<br>bądź czy wybrana grafika nie przekracza rozmiaru 50MB.<br></h5> <div class=\"form-group\"><a href=\"ControllerAccount?page=produkty\"><button class=\"btn btn-primary\" type=\"submit\" style=\"margin:0;width:265px;margin-left:267px;\">Powrót do produktów</button></a></div>";
            request.setAttribute("message", resultMessage);
            getServletContext().getRequestDispatcher("/index_result.jsp").forward(request, response);
        }
    }
}

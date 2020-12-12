package myPage.servlets;

import myPage.basicObjects.Produkt;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;

@WebServlet("/ControllerProductSellingRemover")
public class ControllerProductSellingRemover extends HttpServlet {

    private HttpSession session;
    private String resultMessage = "";

    String file_directory = "C:\\Sieci\\2020\\out\\artifacts\\projectWebbAppTest2_war_exploded\\assets\\produkty_grafika\\";
 

    private String generateFileName(int a) {
        String rozszerzenie = ".jpg";
        int id = a;
        String name = "produkt_" + id + rozszerzenie;
        return name;
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        request.setCharacterEncoding("UTF-8");

        session = request.getSession();

        Produkt sprzet = new Produkt();
        String[] spis_id = request.getParameterValues("do_usuniecia");
        int a = 0;
        File deleteFile;
        String file_name;
        try {
            for (int i = 0; i < spis_id.length; i++){
                a = Integer.parseInt(spis_id[i]);
                sprzet.removeSellingProductID(a);
                file_name = generateFileName(a);
                deleteFile = new File(file_directory+file_name);
                deleteFile.delete();
            }
            resultMessage = "<h2 class=\"text-center\" style=\"height:53px;\">Produkt został usunięty z bazy danych.</h2><h5 class=\"text-center\" style=\"height:99px;margin-right:50px;margin-left:50px;\"><br>Zmiany powinny być już widoczne.<br></h5> <div class=\"form-group\"><a href=\"ControllerAccount?page=produkty\"><button class=\"btn btn-primary\" type=\"submit\" style=\"margin:0;width:265px;margin-left:267px;\">Powrót do produktów</button></a></div>";
            request.setAttribute("message", resultMessage);
            getServletContext().getRequestDispatcher("/index_result.jsp").forward(request, response);

        } catch (Exception e) {
            e.printStackTrace();
            resultMessage = "<h2 class=\"text-center\" style=\"height:53px;\">Wystąpił błąd podczas usuwania produktu.</h2><h5 class=\"text-center\" style=\"height:99px;margin-right:50px;margin-left:50px;\"><br>Prosimy o skontaktowanie się z administratorem systemu<br>bądź zgłoszenie błędu.<br></h5> <div class=\"form-group\"><a href=\"ControllerAccount?page=produkty\"><button class=\"btn btn-primary\" type=\"submit\" style=\"margin:0;width:265px;margin-left:267px;\">Powrót do produktów</button></a></div>";
            request.setAttribute("message", resultMessage);
            getServletContext().getRequestDispatcher("/index_result.jsp").forward(request, response);
        }
    }
}


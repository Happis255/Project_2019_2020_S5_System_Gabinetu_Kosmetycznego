package myPage.servlets;

import myPage.basicObjects.Produkt;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.util.HashMap;

@WebServlet("/ControllerUploadUsageProduct")

public class ControllerUploadUsageProduct extends HttpServlet {


    private HttpSession session;
    private String resultMessage = "";

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        request.setCharacterEncoding("UTF-8");
        Produkt produkty = new Produkt();

        HashMap<String, String> parameters = new HashMap<>();
        parameters.put("P_NAZWA", request.getParameter("nazwa"));
        parameters.put("P_OPIS", request.getParameter("opis"));
        parameters.put("P_CENA", request.getParameter("cena"));
        parameters.put("P_ILOSC", request.getParameter("ilosc"));
        parameters.put("P_KOLOR", request.getParameter("kolor"));

        try {
            produkty.add_usedProduct(parameters);
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

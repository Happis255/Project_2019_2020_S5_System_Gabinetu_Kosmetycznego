package myPage.servlets;

import myPage.data.others.AccountPage;
import myPage.data.others.SessionData;
import myPage.data.others.TypKonta;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(description = "kontroler do obsługi kont", urlPatterns = { "/ControllerAccount" })
public class ControllerAccount extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        SessionData sessionData = (SessionData)request.getSession().getAttribute("userData");
        String redirect = "P_User/account.jsp";
        String page = request.getParameter("page");
        AccountPage accPage = sessionData.getAccountPage();

        accPage.setNavbarFooter(sessionData.getAccoutType());
        accPage.clearContent();



        if(sessionData.getAccoutType() == TypKonta.ADMINISTRATOR) {
            switch (page) {
                case "powiadomienia": break;
                case "konto": accPage.addContent("../P_Administrator/accountData_administrator.jsp"); break;
                case "pracownicy": break;
                case "wydarzenia": break;
                case "wizyty": break;
                case "promocje": break;
                case "aktualnosci": accPage.addContent("news_manager.jsp"); break;
                case "aktuanosci_upload": accPage.addContent("upload_news.jsp");
                case "uslugi": break;
                case "produkty": break;
                case "raporty": break;
                case "sprzet": break;
                case "odpady": break;
                case "sklep_online": break;
                default: break;
            }

        }else if(sessionData.getAccoutType() == TypKonta.PRACOWNIK) {
            switch(page){
                case "powiadomienia": break;
                case "konto": accPage.addContent("../P_Pracownik/accountData_pracownik.jsp"); break;
                case "wydarzenia": break;
                case "aktualnosci": accPage.addContent("news_manager.jsp"); break;
                case "aktuanosci_upload": accPage.addContent("upload_news.jsp"); break;
                case "wizyty": break;
                case "produkty": break;
                case "raporty": break;
                case "sklep_online": break;
                default: break;
            }

        } else if(sessionData.getAccoutType() == TypKonta.KLIENT){
            switch(page){
                case "powiadomienia": break;
                case "wizty": break;
                case "sklep": break;
                case "twojebonusy": break;
                case "konto": accPage.addContent("../P_Klient/accountData_klient.jsp"); break;
                default: break;
            }
        }

        response.sendRedirect(redirect);
    }
}

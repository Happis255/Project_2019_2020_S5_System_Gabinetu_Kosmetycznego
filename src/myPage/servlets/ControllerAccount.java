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

        System.out.println(sessionData.getAccoutType());

        if(sessionData.getAccoutType() == TypKonta.ADMINISTRATOR) {
            switch (page) {
                case "powiadomienia": break;
                case "konto": accPage.addContent("../P_Administrator/accountData_administrator.jsp"); break;
                case "pracownicy": accPage.addContent("../P_Administrator/workers_manager.jsp"); break;
                case "wydarzenia": accPage.addContent("../P_Administrator/event_manager.jsp"); break;
                case "dodaj_wydarzenie": accPage.addContent("../P_Administrator/event_adder.jsp"); break;
                case "wizyty": break;
                case "promocje": accPage.addContent("../P_Administrator/promo_manager.jsp"); break;
                case "edytuj_status": accPage.addContent("../P_Administrator/status_editor.jsp"); break;
                case "dodaj_status": accPage.addContent("../P_Administrator/status_adder.jsp"); break;
                case "add_promo": accPage.addContent("../P_Administrator/add_promo.jsp"); break;
                case "edit_promo": accPage.addContent("../P_Administrator/edit_promo.jsp"); break;
                case "aktualnosci": accPage.addContent("../P_Pracownik/news_manager.jsp"); break;
                case "aktualnosci_upload": accPage.addContent("../P_Pracownik/upload_news.jsp"); break;
                case "uslugi": accPage.addContent("../P_Administrator/service_manager.jsp"); break;
                case "uslugi_upload": accPage.addContent("../P_Administrator/add_service.jsp"); break;
                case "nieobecnosci": accPage.addContent("../P_Administrator/admin_absences_manager.jsp"); break;
                case "nieobecnosc_upload": accPage.addContent("../P_Pracownik/add_absence.jsp"); break;
                case "add_pracownik": accPage.addContent("../P_Administrator/worker_adder.jsp");break;
                case "remove_pracownik": accPage.addContent("../P_Administrator/worker_remover.jsp"); break;
                case "add_service_pracownik": accPage.addContent("../P_Administrator/worker_service_adder.jsp"); break;
                case "edit_worker": accPage.addContent("../P_Administrator/worker_editor.jsp"); break;
                case "edit_book": accPage.addContent("../P_Administrator/workerBook_editor.jsp"); break;
                case "produkty": accPage.addContent("../P_Administrator/products_manager.jsp"); break;
                case "add_selling_product": accPage.addContent("../P_Administrator/add_selling_product.jsp"); break;
                case "edit_selling_product": accPage.addContent("../P_Administrator/edit_product_sell.jsp"); break;
                case "add_usage_product": accPage.addContent("../P_Administrator/add_usage_product.jsp"); break;
                case "edit_usage_product": accPage.addContent("../P_Administrator/edit_product_use.jsp"); break;
                case "raporty": accPage.addContent("../P_Administrator/raports_manager.jsp"); break;
                case "raport_upload": accPage.addContent("../P_Administrator/add_raports.jsp"); break;
                case "sprzet": accPage.addContent("../P_Administrator/machine_manager.jsp"); break;
                case "dodaj_sprzet": accPage.addContent("../P_Administrator/add_machine.jsp"); break;
                case "odpady": break;
                case "sklep_online": break;
                default: break;
            }

        }else if(sessionData.getAccoutType() == TypKonta.PRACOWNIK) {
            switch(page){
                case "powiadomienia": break;
                case "konto": accPage.addContent("../P_Pracownik/accountData_pracownik.jsp"); break;
                case "wydarzenia": break;
                case "aktualnosci": accPage.addContent("../P_Pracownik/news_manager.jsp"); break;
                case "aktualnosci_upload": accPage.addContent("../P_Pracownik/upload_news.jsp"); break;
                case "nieobecnosci": accPage.addContent("../P_Pracownik/absences_manager.jsp"); break;
                case "nieobecnosc_upload": accPage.addContent("../P_Pracownik/add_absence.jsp"); break;
                case "wizyty": break;
                case "produkty": accPage.addContent("../P_Pracownik/products_manager.jsp"); break;
                case "edit_selling_product": accPage.addContent("../P_Pracownik/edit_product_sell.jsp"); break;
                case "edit_usage_product": accPage.addContent("../P_Pracownik/edit_product_use.jsp"); break;
                case "raporty": accPage.addContent("../P_Pracownik/raports_manager.jsp"); break;
                case "sklep_online": break;
                case "raport_upload": accPage.addContent("../P_Pracownik/add_raports.jsp"); break;
                default: break;
            }

        } else if(sessionData.getAccoutType() == TypKonta.KLIENT){
            switch(page){
                case "powiadomienia": break;
                case "wizty": break;
                case "twojebonusy": accPage.addContent("../P_Klient/bonusy.jsp"); break;
                case "konto": accPage.addContent("../P_Klient/accountData_klient.jsp"); break;
                case "konto_edit": accPage.addContent("../P_Klient/client_editor.jsp"); break;
                case "card_edit": accPage.addContent("../P_Klient/clientBook_editor.jsp"); break;
                default: break;
            }
        }
        response.sendRedirect(redirect);
    }
}

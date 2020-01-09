package myPage.data.others;

import java.util.Date;
import java.util.LinkedList;

public class VisitPage {
    String navBar;
    LinkedList<String> content;
    String footer;

    Integer idUslugi;
    Integer idPracownika;
    Date data;

    public VisitPage(){
        this.navBar = "../P_Klient/navbar_klient.jsp";
        this.footer = "../P_Klient/footer_klient.jsp";
        this.content = new LinkedList<>();
        this.content.add("../P_Klient_accountData_klient.jsp");

        this.idUslugi = null;
        this.idPracownika = null;
        this.data = null;
    }

    public void clearContent(){
        this.content.clear();
    }

    public void addContent(String content){
        this.content.add(content);
    }

    public String getNavBar() {
        return navBar;
    }

    public String getFooter() {
        return footer;
    }

    public void setIdUslugi(Integer idUslugi) {
        this.idUslugi = idUslugi;
    }

    public void setIdPracownika(Integer idPracownika) {
        this.idPracownika = idPracownika;
    }

    public void setData(Date data) {
        this.data = data;
    }

    public Integer getIdUslugi() {
        return idUslugi;
    }

    public Integer getIdPracownika() {
        return idPracownika;
    }

    public Date getData() {
        return data;
    }

    public Object[] getContent(){
        return content.toArray();
    }
}

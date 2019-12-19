package myPage.data.others;

import java.util.LinkedList;

public class AccountPage {
    String navBar;
    LinkedList<String> content;
    String footer;

    public AccountPage(){
        this.navBar = "../P_Klient/navbar_klient.jsp";
        this.footer = "../P_Klient/footer_klient.jsp";
        this.content = new LinkedList<>();
        this.content.add("../P_Klient_accountData_klient.jsp");
    }

    public void setNavbarFooter(TypKonta accountType){
        switch (accountType){
            default:
            case KLIENT:
                this.navBar = "../P_Klient/navbar_klient.jsp";
                this.footer = "../P_Klient/footer_klient.jsp";
                break;
            case PRACOWNIK:
                this.navBar = "../P_Pracownik/navbar_pracownik.jsp";
                this.footer = "../P_Pracownik/footer_pracownik.jsp";
                break;
            case ADMINISTRATOR:
                this.navBar = "../P_Administrator/navbar_administrator.jsp";
                this.footer = "../P_Administrator/footer_administrator.jsp";
                break;
        }
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

    public Object[] getContent(){
        return content.toArray();
    }
}

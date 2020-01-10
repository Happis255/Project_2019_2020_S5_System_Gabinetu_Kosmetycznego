package myPage.data.others;

import java.util.Date;

public class VisitPage {
    Integer idUslugi;
    Integer idPracownika;
    Date data;

    public VisitPage(){
        this.idUslugi = null;
        this.idPracownika = null;
        this.data = null;
    }

    public Integer getIdUslugi() {
        return idUslugi;
    }

    public void setIdUslugi(Integer idUslugi) {
        this.idUslugi = idUslugi;
    }

    public Integer getIdPracownika() {
        return idPracownika;
    }

    public void setIdPracownika(Integer idPracownika) {
        this.idPracownika = idPracownika;
    }

    public Date getData() {
        return data;
    }

    public void setData(Date data) {
        this.data = data;
    }
}

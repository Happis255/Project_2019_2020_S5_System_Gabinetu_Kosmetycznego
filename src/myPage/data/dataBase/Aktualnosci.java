package myPage.data.dataBase;

import java.io.File;
import java.util.Date;
import java.util.Stack;

public class Aktualnosci {

    public String getFileName(){
        int id_aktualnosci = this.getID();
        String nazwa_pliku = "aktualnosc_" + id_aktualnosci + ".jpg";
        return nazwa_pliku;
    }

    public int getID() {
        int a = (int) this.stos_ID.pop();
        return a;
    }

    public void setID(int id) {
        this.ID = id;
        this.stos_ID.push(id);
    }

    public String getTresc() {
        String a = (String) this.stos_tresci.pop();
        return a;
    }

    public void setTresc(String tresc) {
        this.tresc = tresc;
        this.stos_tresci.push(tresc);
    }

    public String getTytul() {
        String a = (String) this.stos_tytulow.pop();
        return a;
    }

    public void setTytul(String tytul) {
        this.tytul = tytul;
        this.stos_tytulow.push(tytul);
    }

    public Stack getStos_tytulow() {
        return stos_tytulow;
    }

    public void setStos_tytulow(Stack stos_tytulow) {
        this.stos_tytulow = stos_tytulow;
    }

    public Stack getStos_tresci() {
        return stos_tresci;
    }

    public void setStos_tresci(Stack stos_tresci) {
        this.stos_tresci = stos_tresci;
    }

    private String tresc;
    private Stack stos_tresci;

    private String tytul;
    private Stack stos_tytulow;

    private int ID;
    private Stack stos_ID;

    public int getID_Pracownika() {
        return ID_Pracownika;
    }

    public void setID_Pracownika(int ID_Pracownika) {
        this.ID_Pracownika = ID_Pracownika;
    }

    private int ID_Pracownika;

    public Date getData_od() {
        return data_od;
    }

    public void setData_od(Date data_od) {
        this.data_od = data_od;
    }

    public Date getData_do() {
        return data_do;
    }

    public void setData_do(Date data_do) {
        this.data_do = data_do;
    }

    private Date data_od;
    private Date data_do;

    public Aktualnosci() {
        this.tresc = "";
        this.tytul = "";
        this.stos_tresci = new Stack <String>();
        this.stos_tytulow = new Stack <String>();
        this.ID = 0;
        this.stos_ID = new Stack <Integer>();
    }
}

package myPage.data;

import java.io.File;
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

    public Aktualnosci() {
        this.tresc = "";
        this.tytul = "";
        this.stos_tresci = new Stack <String>();
        this.stos_tytulow = new Stack <String>();
        this.ID = 0;
        this.stos_ID = new Stack <Integer>();
    }
}

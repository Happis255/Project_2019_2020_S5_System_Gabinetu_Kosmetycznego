package myPage.data.dataBase;

import java.util.Date;

public class ZadanieGospodarczeData {

    int id_zadania;
    String tytul;
    String tresc;
    Date data_od;
    Date data_do;
    int id_pracownika;

    public ZadanieGospodarczeData(int id_zadania, String tytul, String tresc, Date data_od, Date data_do, int id_pracownika) {
        this.id_zadania = id_zadania;
        this.tytul = tytul;
        this.tresc = tresc;
        this.data_od = data_od;
        this.data_do = data_do;
        this.id_pracownika = id_pracownika;
    }

    public int getId_zadania() {
        return id_zadania;
    }

    public void setId_zadania(int id_zadania) {
        this.id_zadania = id_zadania;
    }

    public String getTytul() {
        return tytul;
    }

    public void setTytul(String tytul) {
        this.tytul = tytul;
    }

    public String getTresc() {
        return tresc;
    }

    public void setTresc(String tresc) {
        this.tresc = tresc;
    }

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

    public int getId_pracownika() {
        return id_pracownika;
    }

    public void setId_pracownika(int id_pracownika) {
        this.id_pracownika = id_pracownika;
    }
}

package myPage.data.dataBase;

import java.util.Date;

public class AktualnoscData{

    private int id_aktualnosci;
    private String tytul;
    private String tresc;
    private Date data_od;
    private Date data_do;
    private int id_pracownika;

    public AktualnoscData(
            int id_aktualnosci,
            String tytul,
            String tresc,
            Date data_od,
            Date data_do,
            int id_pracownika
    ){
        this.id_aktualnosci = id_aktualnosci;
        this.tytul = tytul;
        this.tresc = tresc;
        this.data_od = data_od;
        this.data_do = data_do;
        this.id_pracownika = id_pracownika;
    }

    public int getId_aktualnosci() {
        return id_aktualnosci;
    }

    public String getTytul() {
        return tytul;
    }

    public String getTresc() {
        return tresc;
    }

    public Date getData_od() {
        return data_od;
    }

    public Date getData_do() {
        return data_do;
    }

    public int getId_pracownika() {
        return id_pracownika;
    }

    public String getFileName() {
        return "aktualnosc_" + this.id_aktualnosci + ".jpg";
    }
}

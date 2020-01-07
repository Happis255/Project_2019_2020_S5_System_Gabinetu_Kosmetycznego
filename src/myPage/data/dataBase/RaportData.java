package myPage.data.dataBase;

import java.util.Date;

public class RaportData {

    private int id_sprawozdania;
    private Date data;
    private String tytul;
    private String typ;
    private String tresc;
    private int id_pracownika;

    public RaportData(int id_sprawozdania, Date data, String tytul, String typ, String tresc ,int id_pracownika){
        this.id_sprawozdania = id_sprawozdania;
        this.data = data;
        this.tytul = tytul;
        this.tresc = tresc;
        this.typ = typ;
        this.id_pracownika = id_pracownika;
    }


    public Date getData() {
        return data;
    }

    public int getId_sprawozdania() {
        return  id_sprawozdania;
    }

    public int getId_pracownika(){
        return id_pracownika;
    }

    public String getTytul() {
        return tytul;
    }

    public String getTyp() {
        return typ;
    }

    public String getTresc() {
        return tresc;
    }
}

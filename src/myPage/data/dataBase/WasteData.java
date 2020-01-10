package myPage.data.dataBase;

import myPage.basicObjects.Waste;

import java.util.Date;

public class WasteData {

    private int id_raportu;
    private String tytul_raportu;
    private String typ_odpadow;
    private int ilos;
    private int koszt;
    private int id_pracownika;
    private Date data;

    public WasteData(int id_raportu, Date data, String tytul_raportu, String typ_odpadow, int ilos , int koszt, int id_pracownika){
        this.id_raportu = id_raportu;
        this.data = data;
        this.tytul_raportu = tytul_raportu;
        this.typ_odpadow = typ_odpadow;
        this.ilos = ilos;
        this.koszt = koszt;
        this.id_pracownika = id_pracownika;
    }


    public Date getData() {
        return data;
    }

    public int getId_raportu() {
        return id_raportu;
    }

    public String getTytul_raportu() {
        return tytul_raportu;
    }

    public String getTyp_odpadow() {
        return typ_odpadow;
    }

    public int getIlos() {
        return ilos;
    }

    public int getKoszt() {
        return koszt;
    }
}

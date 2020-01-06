package myPage.data.dataBase;

import java.util.Date;

public class RaportData {

    private int id_raportu;
    private Date data;
    private String tytul_raportu;
    private String typ_odpadow;
    private int ilosc;
    private int koszt;
    private int id_pracownika;

    public RaportData(int id_raportu, Date data, String tytul_raportu, String typ_odpadow, int ilosc, int koszt, int id_pracownika){
        this.id_raportu = id_raportu;
        this.data = data;
        this.tytul_raportu = tytul_raportu;
        this.typ_odpadow = typ_odpadow;
        this.ilosc = ilosc;
        this.koszt = koszt;
        this.id_pracownika = id_pracownika;
    }


    public Date getData() {
        return data;
    }

    public int getId_raportu() {
        return  id_raportu;
    }

    public int getId_pracownika(){
        return id_pracownika;
    }

    public String getTytul_raportu() {
        return tytul_raportu;
    }

    public String getTyp_odpadow() {
        return typ_odpadow;
    }

    public int getIlosc() {
        return ilosc;
    }

    public int getKoszt() {
        return koszt;
    }
}

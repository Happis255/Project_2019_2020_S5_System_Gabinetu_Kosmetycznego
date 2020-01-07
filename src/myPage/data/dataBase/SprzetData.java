package myPage.data.dataBase;

import java.util.Date;

public class SprzetData {

   int id_sprzetu;
   String nazwa_sprzetu;
   String opis_sprzetu;
   Date data_zakupu;
   Date data_gwarancji;
   String uwagi;

    public SprzetData(int id_sprzetu, String nazwa_sprzetu, String opis_sprzetu, Date data_zakupu, Date data_gwarancji, String uwagi) {
        this.id_sprzetu = id_sprzetu;
        this.nazwa_sprzetu = nazwa_sprzetu;
        this.opis_sprzetu = opis_sprzetu;
        this.data_zakupu = data_zakupu;
        this.data_gwarancji = data_gwarancji;
        this.uwagi = uwagi;
    }

    public int getId_sprzetu() {
        return id_sprzetu;
    }

    public void setId_sprzetu(int id_sprzetu) {
        this.id_sprzetu = id_sprzetu;
    }

    public String getNazwa_sprzetu() {
        return nazwa_sprzetu;
    }

    public void setNazwa_sprzetu(String nazwa_sprzetu) {
        this.nazwa_sprzetu = nazwa_sprzetu;
    }

    public String getOpis_sprzetu() {
        return opis_sprzetu;
    }

    public void setOpis_sprzetu(String opis_sprzetu) {
        this.opis_sprzetu = opis_sprzetu;
    }

    public Date getData_zakupu() {
        return data_zakupu;
    }

    public void setData_zakupu(Date data_zakupu) {
        this.data_zakupu = data_zakupu;
    }

    public Date getData_gwarancji() {
        return data_gwarancji;
    }

    public void setData_gwarancji(Date data_gwarancji) {
        this.data_gwarancji = data_gwarancji;
    }

    public String getUwagi() {
        return uwagi;
    }

    public void setUwagi(String uwagi) {
        this.uwagi = uwagi;
    }
}

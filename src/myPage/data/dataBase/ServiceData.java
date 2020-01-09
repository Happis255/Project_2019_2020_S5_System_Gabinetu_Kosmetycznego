package myPage.data.dataBase;

import java.util.Date;

public class ServiceData {

    private int id_przegladu;
    private String tytul_przegladu;
    private String opis_przegladu;
    private Date data;
    private int id_pracownika;

    public ServiceData(int id_przegladu, String tytul_przegladu, String opis_przegladu, Date data, int id_pracownika){
        this.id_przegladu = id_przegladu;
        this.data = data;
        this.tytul_przegladu = tytul_przegladu;
        this.opis_przegladu = opis_przegladu;
        this.id_pracownika = id_pracownika;
    }


    public Date getData() {
        return data;
    }

    public int getId_przegladu() {
        return  id_przegladu;
    }

    public int getId_pracownika(){
        return id_pracownika;
    }

    public String getTytul_przegladu() {
        return tytul_przegladu;
    }

    public String getOpis_przegladu() {
        return opis_przegladu;
    }
}

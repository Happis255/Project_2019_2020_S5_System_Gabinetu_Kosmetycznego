package myPage.data.dataBase;

import java.util.Date;

public class PromocjaData {

    int id_promocji;
    String nazwa;
    String opis;
    int znizka_proc;
    int znizka_kwotowa;
    Date data_od;
    Date data_do;
    int id_pracownika;

    public PromocjaData(int id_promocji, String nazwa, String opis, int znizka_proc, int znizka_kwotowa, Date data_od, Date data_do, int id_pracownika) {
        this.id_promocji = id_promocji;
        this.nazwa = nazwa;
        this.opis = opis;
        this.znizka_proc = znizka_proc;
        this.znizka_kwotowa = znizka_kwotowa;
        this.data_od = data_od;
        this.data_do = data_do;
        this.id_pracownika = id_pracownika;
    }

    public int getId_promocji() {
        return id_promocji;
    }

    public void setId_promocji(int id_promocji) {
        this.id_promocji = id_promocji;
    }

    public String getNazwa() {
        return nazwa;
    }

    public void setNazwa(String nazwa) {
        this.nazwa = nazwa;
    }

    public String getOpis() {
        return opis;
    }

    public void setOpis(String opis) {
        this.opis = opis;
    }

    public int getZnizka_proc() {
        return znizka_proc;
    }

    public void setZnizka_proc(int znizka_proc) {
        this.znizka_proc = znizka_proc;
    }

    public int getZnizka_kwotowa() {
        return znizka_kwotowa;
    }

    public void setZnizka_kwotowa(int znizka_kwotowa) {
        this.znizka_kwotowa = znizka_kwotowa;
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

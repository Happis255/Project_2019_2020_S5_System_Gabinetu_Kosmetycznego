package myPage.data.dataBase;

public class StatusData {

int id_statusu;
int punkty_od;
int punkty_do;
int znizka_proc;
int znizka_kwot;
String nazwa;

    public StatusData(int id_statusu, int punkty_od, int punkty_do, int znizka_proc, int znizka_kwot, String nazwa) {
        this.id_statusu = id_statusu;
        this.punkty_od = punkty_od;
        this.punkty_do = punkty_do;
        this.znizka_proc = znizka_proc;
        this.znizka_kwot = znizka_kwot;
        this.nazwa = nazwa;
    }

    public int getId_statusu() {
        return id_statusu;
    }

    public void setId_statusu(int id_statusu) {
        this.id_statusu = id_statusu;
    }

    public int getPunkty_od() {
        return punkty_od;
    }

    public void setPunkty_od(int punkty_od) {
        this.punkty_od = punkty_od;
    }

    public int getPunkty_do() {
        return punkty_do;
    }

    public void setPunkty_do(int punkty_do) {
        this.punkty_do = punkty_do;
    }

    public int getZnizka_proc() {
        return znizka_proc;
    }

    public void setZnizka_proc(int znizka_proc) {
        this.znizka_proc = znizka_proc;
    }

    public int getZnizka_kwot() {
        return znizka_kwot;
    }

    public void setZnizka_kwot(int znizka_kwot) {
        this.znizka_kwot = znizka_kwot;
    }

    public String getNazwa() {
        return nazwa;
    }

    public void setNazwa(String nazwa) {
        this.nazwa = nazwa;
    }
}

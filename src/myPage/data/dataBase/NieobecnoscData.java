package myPage.data.dataBase;

import java.util.Date;

public class NieobecnoscData {

    private int id_nieobecnosci;
    private Date data_od;
    private Date data_do;
    private String powod;
    private int id_pracownika;
    private Status status;

    private String imie_pracownika;
    private String nazwisko_pracownika;

    public enum Status{
        NIEPOTWIERDZONE(false),
        POTWIERDZONE(true);

        boolean czy_potwierdzone;
        Status(Boolean a){
            czy_potwierdzone = a;
        }

        @Override
        public String toString() {
            if (czy_potwierdzone) {
                return "Potwierdzone";
            } else {
                return "Niepotwierdzone";
            }
        }
    }

    /* Z id - id są ustawiane przez bazę danych - z imieniem i nazwiskiem */
    public NieobecnoscData(int id_nieobecnosci, Date data_od, Date data_do, String powod, int id_pracownika, Status status, String imie_pracownika, String nazwisko_pracownika) {
        this.id_nieobecnosci = id_nieobecnosci;
        this.data_od = data_od;
        this.data_do = data_do;
        this.powod = powod;
        this.id_pracownika = id_pracownika;
        this.status = status;
        this.imie_pracownika = imie_pracownika;
        this.nazwisko_pracownika = nazwisko_pracownika;
    }

    public int getId_nieobecnosci() {
        return id_nieobecnosci;
    }

    public void setId_nieobecnosci(int id_nieobecnosci) {
        this.id_nieobecnosci = id_nieobecnosci;
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

    public String getPowod() {
        return powod;
    }

    public void setPowod(String powod) {
        this.powod = powod;
    }

    public int getId_pracownika() {
        return id_pracownika;
    }

    public void setId_pracownika(int id_pracownika) {
        this.id_pracownika = id_pracownika;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public String getImie_pracownika() {
        return imie_pracownika;
    }

    public void setImie_pracownika(String imie_pracownika) {
        this.imie_pracownika = imie_pracownika;
    }

    public String getNazwisko_pracownika() {
        return nazwisko_pracownika;
    }

    public void setNazwisko_pracownika(String nazwisko_pracownika) {
        this.nazwisko_pracownika = nazwisko_pracownika;
    }
}

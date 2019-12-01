package myPage.data;

import java.util.Date;

public class User {
    private String imie;
    private String nazwisko;
    private String ulica;
    private String kod_pocztowy;
    private String miejscowosc;
    private Date data_urodzenia;
    private int telefon;
    private String e_mail;
    private String haslo;
    private TypKonta typ_konta;

    public User(){
        this.imie = "";
        this.nazwisko = "";
        this.ulica = "";
        this.kod_pocztowy = "";
        this.miejscowosc = "";
        this.data_urodzenia = new Date();
        this.telefon = 0;
        this.e_mail = "";
        this.haslo = "";
    }

    public String getImie() {
        return imie;
    }

    public void setImie(String imie) {
        this.imie = imie;
    }

    public String getNazwisko() {
        return nazwisko;
    }

    public void setNazwisko(String nazwisko) {
        this.nazwisko = nazwisko;
    }

    public String getUlica() {
        return ulica;
    }

    public void setUlica(String ulica) {
        this.ulica = ulica;
    }

    public String getKod_pocztowy() {
        return kod_pocztowy;
    }

    public void setKod_pocztowy(String kod_pocztowy) {
        this.kod_pocztowy = kod_pocztowy;
    }

    public String getMiejscowosc() {
        return miejscowosc;
    }

    public void setMiejscowosc(String miejscowosc) {
        this.miejscowosc = miejscowosc;
    }

    public Date getData_urodzenia() {
        return data_urodzenia;
    }

    public void setData_urodzenia(Date data_urodzenia) {
        this.data_urodzenia = data_urodzenia;
    }

    public int getTelefon() {
        return telefon;
    }

    public void setTelefon(int telefon) {
        this.telefon = telefon;
    }

    public String getE_mail() {
        return e_mail;
    }

    public void setE_mail(String e_mail) {
        this.e_mail = e_mail;
    }

    public String getHaslo() {
        return haslo;
    }

    public void setHaslo(String haslo) {
        this.haslo = haslo;
    }

    public TypKonta getTyp_konta() {
        return typ_konta;
    }

    public String getTyp_konta_String(){
        String string;
        switch (typ_konta){
            case KLIENT:
                string = "KLIENT";
                break;
            case PRACOWNIK:
                string = "PRACOWNIK";
                break;
            case ADMINISTRATOR:
                string = "ADMINISTRATOR";
                break;
            default:
                string = "nieznany";
                break;
        }
        return string;
    }

    public void setTyp_konta(String typKonta) {
        switch (typKonta){
            case "KLIENT":
                typ_konta = TypKonta.KLIENT;
                break;
            case "PRACOWNIK":
                typ_konta = TypKonta.PRACOWNIK;
                break;
            case "ADMINISTRATOR":
                typ_konta = TypKonta.ADMINISTRATOR;
                break;
            default:
                break;
        }
    }
}

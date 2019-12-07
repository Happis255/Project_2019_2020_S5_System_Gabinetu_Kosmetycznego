package myPage.data.dataBase;

import java.util.Date;

public class UserData{
    private int id;
    private String imie;
    private String nazwisko;
    private String ulica;
    private String kod_pocztowy;
    private String miejscowosc;
    private Date data_urodzenia;
    private int telefon;
    private String e_mail;
    private int id_konta;

    public UserData(
            int id,
            String imie,
            String nazwisko,
            String ulica,
            String kod_pocztowy,
            String miejscowosc,
            Date data_urodzenia,
            int telefon,
            String e_mail,
            int id_konta
    ){
        this.id = id;
        this.imie = imie;
        this.nazwisko = nazwisko;
        this.ulica = ulica;
        this.kod_pocztowy = kod_pocztowy;
        this.miejscowosc = miejscowosc;
        this.data_urodzenia = data_urodzenia;
        this.telefon = telefon;
        this.e_mail = e_mail;
        this.id_konta = id_konta;
    }

    public int getId() {
        return id;
    }

    public String getImie() {
        return imie;
    }

    public String getNazwisko() {
        return nazwisko;
    }

    public String getUlica() {
        return ulica;
    }

    public String getKod_pocztowy() {
        return kod_pocztowy;
    }

    public String getMiejscowosc() {
        return miejscowosc;
    }

    public Date getData_urodzenia() {
        return data_urodzenia;
    }

    public int getTelefon() {
        return telefon;
    }

    public String getE_mail() {
        return e_mail;
    }

    public int getId_konta() {
        return id_konta;
    }
}

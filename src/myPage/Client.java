package myPage;

import java.util.Date;

public class Client {
    private String imie;
    private String nazwisko;
    private String ulica;
    private int kod_pocztowy;
    private String miejscowosc;
    private Date data_urodzenia;
    private int telefon;
    private String e_mail;
    private int ilosc_punktow;
    private boolean p_p1;
    private boolean p_p2;
    private boolean p_p3;
    private boolean p_p4;
    private boolean p_p5;
    private boolean p_p6;
    private boolean p_p7;
    private boolean p_p8;
    private boolean p_p9;
    private String p_ocena_skory;
    private String p_rodzaj_jakosc;
    private String p_wrazliwosc;
    private String p_inne_uwagi;

    public Client(){
        this.imie = "";
        this.nazwisko = "";
        this.ulica = "";
        this.kod_pocztowy = 0;
        this.miejscowosc = "";
        this.data_urodzenia = new Date();
        this.telefon = 0;
        this.e_mail = "";
        this.ilosc_punktow = 0;
        this.p_p1 = true;
        this.p_p2 = true;
        this.p_p3 = true;
        this.p_p4 = true;
        this.p_p5 = true;
        this.p_p6 = true;
        this.p_p7 = true;
        this.p_p8 = true;
        this.p_p9 = true;
        this.p_ocena_skory = "";
        this.p_rodzaj_jakosc = "";
        this.p_wrazliwosc = "";
        this.p_inne_uwagi = "";
    }

    public Client(
            //String imie,
            //String nazwisko,
            //String ulica,
            //int kod_pocztowy,
            //String miejscowosc,
            //Date data_urodzenia,
            //int telefon,
            String e_mail
            //int ilosc_punktow,
            //boolean p_p1,
            //boolean p_p2,
            //boolean p_p3,
            //boolean p_p4,
            //boolean p_p5,
            //boolean p_p6,
            //boolean p_p7,
            //boolean p_p8,
            //boolean p_p9,
            //String p_ocena_skory,
            //String p_rodzaj_jakosc,
            //String p_wrazliwosc,
            //String p_inne_uwagi
    ){
        this();
        this.e_mail = e_mail;
    }

    String getImie() {
        return imie;
    }

    String getNazwisko() {
        return nazwisko;
    }

    String getUlica() {
        return ulica;
    }

    int getKod_pocztowy() {
        return kod_pocztowy;
    }

    String getMiejscowosc() {
        return miejscowosc;
    }

    Date getData_urodzenia() {
        return data_urodzenia;
    }

    int getTelefon() {
        return telefon;
    }

    String getE_mail() {
        return e_mail;
    }

    boolean getP_p1() {
        return p_p1;
    }

    boolean getP_p2() {
        return p_p2;
    }

    boolean getP_p3() {
        return p_p3;
    }

    boolean getP_p4() {
        return p_p4;
    }

    boolean getP_p5() {
        return p_p5;
    }

    boolean getP_p6() {
        return p_p6;
    }

    boolean getP_p7() {
        return p_p7;
    }

    boolean getP_p8() {
        return p_p8;
    }

    boolean getP_p9() {
        return p_p9;
    }

    String getP_ocena_skory() {
        return p_ocena_skory;
    }

    String getP_rodzaj_jakosc() {
        return p_rodzaj_jakosc;
    }

    String getP_wrazliwosc() {
        return p_wrazliwosc;
    }

    String getP_inne_uwagi() {
        return p_inne_uwagi;
    }
}

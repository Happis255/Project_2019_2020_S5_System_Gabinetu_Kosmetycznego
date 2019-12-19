package myPage.data.dataBase;

import myPage.data.others.TypWydarzenia;

import java.util.Date;

public class WydarzenieData {
    private int id;
    private TypWydarzenia typWydarzenia;
    private String nazwa;
    private String opis;
    private String ulica;
    private String kod_pocztowy;
    private String miejscowosc;
    private Date data_od;
    private Date data_do;
    private int kosz;

    public WydarzenieData(
            int id,
            TypWydarzenia typWydarzenia,
            String nazwa,
            String opis,
            String ulica,
            String kod_pocztowy,
            String miejscowosc,
            Date data_od,
            Date data_do,
            int kosz
    ){
        this.id = id;
        this.typWydarzenia = typWydarzenia;
        this.nazwa = nazwa;
        this.opis = opis;
        this.ulica = ulica;
        this.kod_pocztowy = kod_pocztowy;
        this.miejscowosc = miejscowosc;
        this.data_od = data_od;
        this.data_do = data_do;
        this.kosz = kosz;
    }

    public int getId() {
        return id;
    }

    public TypWydarzenia getTypWydarzenia() {
        return typWydarzenia;
    }

    public String getNazwa() {
        return nazwa;
    }

    public String getOpis() {
        return opis;
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

    public Date getData_od() {
        return data_od;
    }

    public Date getData_do() {
        return data_do;
    }

    public int getKosz() {
        return kosz;
    }
}

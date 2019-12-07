package myPage.data.dataBase;

import java.util.Date;

public class PracownikData extends UserData{
    private int id_pracownika;
    private int pesel;
    private Date data_zatrudnienia;
    private String certyfikaty;
    private int id_ksiazeczki;

    public PracownikData(
            int id,
            String imie,
            String nazwisko,
            String ulica,
            String kod_pocztowy,
            String miejscowosc,
            Date data_urodzenia,
            int telefon,
            String e_mail,
            int id_konta,

            int id_pracownika,
            int pesel,
            Date data_zatrudnienia,
            String certyfikaty,
            int id_ksiazeczki
    ){
        super(
                id,
                imie,
                nazwisko,
                ulica,
                kod_pocztowy,
                miejscowosc,
                data_urodzenia,
                telefon,
                e_mail,
                id_konta
        );
        this.id_pracownika = id_pracownika;
        this.pesel = pesel;
        this.data_zatrudnienia = data_zatrudnienia;
        this.certyfikaty = certyfikaty;
        this.id_ksiazeczki = id_ksiazeczki;
    }

    @Override
    public int getId() {
        return id_pracownika;
    }

    public int getPesel() {
        return pesel;
    }

    public Date getData_zatrudnienia() {
        return data_zatrudnienia;
    }

    public String getCertyfikaty() {
        return certyfikaty;
    }

    public int getId_ksiazeczki() {
        return id_ksiazeczki;
    }
}

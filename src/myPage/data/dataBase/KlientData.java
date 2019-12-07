package myPage.data.dataBase;

import java.util.Date;

public class KlientData extends UserData{
    private int id_klienta;
    private int ilosc_punktow;
    private int id_karty;
    private int id_statusu;

    public KlientData(
            int id_klienta,
            String imie,
            String nazwisko,
            String ulica,
            String kod_pocztowy,
            String miejscowosc,
            Date data_urodzenia,
            int telefon,
            String e_mail,
            int id_konta,

            int ilosc_punktow,
            int id_karty,
            int id_statusu
    ){
        super(
                id_klienta,
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
        this.ilosc_punktow = ilosc_punktow;
        this.id_karty = id_karty;
        this.id_statusu = id_statusu;
    }

    public int getIlosc_punktow() {
        return ilosc_punktow;
    }

    public int getId_karty() {
        return id_karty;
    }

    public int getId_statusu() {
        return id_statusu;
    }
}

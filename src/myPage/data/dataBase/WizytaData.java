package myPage.data.dataBase;

import myPage.data.others.StatusWizyty;

import java.time.LocalTime;
import java.util.Date;

public class WizytaData {
    private int id_wizyty;
    private Date data_wizyty;
    private LocalTime godzina;
    private StatusWizyty status;
    private int id_pracownika;
    private int id_klienta;
    private int id_uslugi;

    public WizytaData(
            int id_wizyty,
            Date data_wizyty,
            LocalTime godzina,
            StatusWizyty status,
            int id_pracownika,
            int id_klienta,
            int id_uslugi
    ) {
        this.id_wizyty = id_wizyty;
        this.data_wizyty = data_wizyty;
        this.godzina = godzina;
        this.status = status;
        this.id_pracownika = id_pracownika;
        this.id_klienta = id_klienta;
        this.id_uslugi = id_uslugi;
    }

    public int getId_wizyty() {
        return id_wizyty;
    }

    public Date getData_wizyty() {
        return data_wizyty;
    }

    public LocalTime getGodzina() {
        return godzina;
    }

    public StatusWizyty getStatus() {
        return status;
    }

    public int getId_pracownika() {
        return id_pracownika;
    }

    public int getId_klienta() {
        return id_klienta;
    }

    public int getId_uslugi() {
        return id_uslugi;
    }
}

package myPage.data.dataBase;

public class UslugaData {

    int id_uslugi;
    int id_uprawnienia_usl;
    String typ_uslugi;
    String nazwa;
    String opis;
    boolean czy_karta;
    int cena;
    int czas_trwania;
    String wskazowki;
    int id_promocji;

    public UslugaData(int id_uslugi, int id_uprawnienia_usl, String typ_uslugi, String nazwa, String opis, boolean czy_karta, int cena, int czas_trwania, String wskazowki, int id_promocji) {
        this.id_uslugi = id_uslugi;
        this.id_uprawnienia_usl = id_uprawnienia_usl;
        this.typ_uslugi = typ_uslugi;
        this.nazwa = nazwa;
        this.opis = opis;
        this.czy_karta = czy_karta;
        this.cena = cena;
        this.czas_trwania = czas_trwania;
        this.wskazowki = wskazowki;
        this.id_promocji = id_promocji;
    }

    public int getId_uslugi() {
        return id_uslugi;
    }

    public void setId_uslugi(int id_uslugi) {
        this.id_uslugi = id_uslugi;
    }

    public int getId_uprawnienia_usl() {
        return id_uprawnienia_usl;
    }

    public void setId_uprawnienia_usl(int id_uprawnienia_usl) {
        this.id_uprawnienia_usl = id_uprawnienia_usl;
    }

    public String getTyp_uslugi() {
        return typ_uslugi;
    }

    public void setTyp_uslugi(String typ_uslugi) {
        this.typ_uslugi = typ_uslugi;
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

    public boolean isCzy_karta() {
        return czy_karta;
    }

    public void setCzy_karta(boolean czy_karta) {
        this.czy_karta = czy_karta;
    }

    public int getCena() {
        return cena;
    }

    public void setCena(int cena) {
        this.cena = cena;
    }

    public int getCzas_trwania() {
        return czas_trwania;
    }

    public void setCzas_trwania(int czas_trwania) {
        this.czas_trwania = czas_trwania;
    }

    public String getWskazowki() {
        return wskazowki;
    }

    public void setWskazowki(String wskazowki) {
        this.wskazowki = wskazowki;
    }

    public int getId_promocji() {
        return id_promocji;
    }

    public void setId_promocji(int id_promocji) {
        this.id_promocji = id_promocji;
    }
}

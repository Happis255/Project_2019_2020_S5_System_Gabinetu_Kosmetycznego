package myPage.data.dataBase;

public class ProduktSprzedazowyData {

    int id_produktu_s;
    String nazwa;
    String opis;
    int cena;
    int ilosc;
    int id_promocji;

    /* Konstruktor bez ID promocji */
    public ProduktSprzedazowyData(int id_produktu, String nazwa, String opis, int cena, int ilosc) {
        this.id_produktu_s = id_produktu;
        this.nazwa = nazwa;
        this.opis = opis;
        this.cena = cena;
        this.ilosc = ilosc;
        this.id_promocji = 0; //jesli zero - produkt nie ma promocji
    }

    /* Konstruktor z ID promocji */
    public ProduktSprzedazowyData(int id_produktu, String nazwa, String opis, int cena, int ilosc, int id_promocji) {
        this.id_produktu_s = id_produktu;
        this.nazwa = nazwa;
        this.opis = opis;
        this.cena = cena;
        this.ilosc = ilosc;
        this.id_promocji = id_promocji;
    }

    public int getId_produktu() {
        return id_produktu_s;
    }

    public void setId_produktu(int id_produktu) {
        this.id_produktu_s = id_produktu;
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

    public int getCena() {
        return cena;
    }

    public void setCena(int cena) {
        this.cena = cena;
    }

    public int getIlosc() {
        return ilosc;
    }

    public void setIlosc(int ilosc) {
        this.ilosc = ilosc;
    }

    public int getId_promocji() {
        return id_promocji;
    }

    public boolean check_czyPromocja(){
        return this.id_promocji != 0;
    }

    public void setId_promocji(int id_promocji) {
        this.id_promocji = id_promocji;
    }

    public String getFileName() {
        return "produkt_" + this.id_produktu_s + ".jpg";
    }
}

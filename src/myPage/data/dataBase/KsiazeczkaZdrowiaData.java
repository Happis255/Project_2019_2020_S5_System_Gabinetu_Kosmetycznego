package myPage.data.dataBase;

public class KsiazeczkaZdrowiaData{
    private int id_ksiazeczki;
    private boolean rozrusznik_serca;
    private boolean hermofilia;
    private boolean luszczyca;
    private String alergia;
    private String przebarwienie;
    private String choroba_zakazna;
    private String zaburzenia_ukrwienia;
    private String goraczka;
    private String oslabienie;
    private boolean ciaza;

    public KsiazeczkaZdrowiaData(
            int id_ksiazeczki,
            boolean rozrusznik_serca,
            boolean hermofilia,
            boolean luszczyca,
            String alergia,
            String przebarwienie,
            String choroba_zakazna,
            String zaburzenia_ukrwienia,
            String goraczka,
            String oslabienie,
            boolean ciaza
    ){
        this.id_ksiazeczki = id_ksiazeczki;
        this.rozrusznik_serca = rozrusznik_serca;
        this.hermofilia = hermofilia;
        this.luszczyca = luszczyca;
        this.alergia = alergia;
        this.przebarwienie = przebarwienie;
        this.choroba_zakazna = choroba_zakazna;
        this.zaburzenia_ukrwienia = zaburzenia_ukrwienia;
        this.goraczka = goraczka;
        this.oslabienie = oslabienie;
        this.ciaza = ciaza;
    }

    public int getId_ksiazeczki() {
        return id_ksiazeczki;
    }

    public boolean isRozrusznik_serca() {
        return rozrusznik_serca;
    }

    public boolean isHermofilia() {
        return hermofilia;
    }

    public boolean isLuszczyca() {
        return luszczyca;
    }

    public String getAlergia() {
        return alergia;
    }

    public String getPrzebarwienie() {
        return przebarwienie;
    }

    public String getChoroba_zakazna() {
        return choroba_zakazna;
    }

    public String getZaburzenia_ukrwienia() {
        return zaburzenia_ukrwienia;
    }

    public String getGoraczka() {
        return goraczka;
    }

    public String getOslabienie() {
        return oslabienie;
    }

    public boolean isCiaza() {
        return ciaza;
    }
}

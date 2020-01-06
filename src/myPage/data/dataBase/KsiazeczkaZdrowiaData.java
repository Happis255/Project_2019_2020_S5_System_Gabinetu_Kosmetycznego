package myPage.data.dataBase;

public class KsiazeczkaZdrowiaData{
    private int id_ksiazeczki;
    private boolean rozrusznik_serca;
    private boolean hermofilia;
    private boolean luszczyca;
    private boolean ciaza;
    private String alergia;
    private String przebarwienie;
    private String choroba_zakazna;
    private String zaburzenia_ukrwienia;
    private String goraczka;
    private String oslabienie;

    private String opryszczka;

    public KsiazeczkaZdrowiaData(
            int id_ksiazeczki,
            boolean rozrusznik_serca,
            boolean hermofilia,
            boolean luszczyca,
            String alergia,
            String przebarwienie,
            String choroba_zakazna,
            String zaburzenia_ukrwienia,
            String opryszczka,
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
        this.opryszczka = opryszczka;
        this.goraczka = goraczka;
        this.oslabienie = oslabienie;
        this.ciaza = ciaza;
    }

    public int getId_ksiazeczki() {
        return id_ksiazeczki;
    }

    public void setId_ksiazeczki(int id_ksiazeczki) {
        this.id_ksiazeczki = id_ksiazeczki;
    }

    public boolean isRozrusznik_serca() {
        return rozrusznik_serca;
    }

    public void setRozrusznik_serca(boolean rozrusznik_serca) {
        this.rozrusznik_serca = rozrusznik_serca;
    }

    public boolean isHermofilia() {
        return hermofilia;
    }

    public void setHermofilia(boolean hermofilia) {
        this.hermofilia = hermofilia;
    }

    public boolean isLuszczyca() {
        return luszczyca;
    }

    public void setLuszczyca(boolean luszczyca) {
        this.luszczyca = luszczyca;
    }

    public String getAlergia() {
        return alergia;
    }

    public void setAlergia(String alergia) {
        this.alergia = alergia;
    }

    public String getPrzebarwienie() {
        return przebarwienie;
    }

    public void setPrzebarwienie(String przebarwienie) {
        this.przebarwienie = przebarwienie;
    }

    public String getChoroba_zakazna() {
        return choroba_zakazna;
    }

    public void setChoroba_zakazna(String choroba_zakazna) {
        this.choroba_zakazna = choroba_zakazna;
    }

    public String getZaburzenia_ukrwienia() {
        return zaburzenia_ukrwienia;
    }

    public void setZaburzenia_ukrwienia(String zaburzenia_ukrwienia) {
        this.zaburzenia_ukrwienia = zaburzenia_ukrwienia;
    }

    public String getGoraczka() {
        return goraczka;
    }

    public void setGoraczka(String goraczka) {
        this.goraczka = goraczka;
    }

    public String getOslabienie() {
        return oslabienie;
    }

    public void setOslabienie(String oslabienie) {
        this.oslabienie = oslabienie;
    }

    public boolean isCiaza() {
        return ciaza;
    }

    public void setCiaza(boolean ciaza) {
        this.ciaza = ciaza;
    }

    public String getOpryszczka() {
        return opryszczka;
    }

    public void setOpryszczka(String opryszczka) {
        this.opryszczka = opryszczka;
    }

    public String toStringrozrusznik_serca(){
        if (this.rozrusznik_serca) return "tak";
        else return "nie";
    }

    public String toStringHermofilia(){
        if (this.hermofilia) return "tak";
        else return "nie";
    }

    public String toStringLuszczyca(){
        if (this.luszczyca) return "tak";
        else return "nie";
    }

    public String toStringroCiaza(){
        if (this.ciaza) return "tak";
        else return "nie";
    }
}

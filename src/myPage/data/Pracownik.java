package myPage.data;

import java.util.Date;

public class Pracownik extends User {
    private int pesel;
    private Date data_zatrudnienia;
    private String certyfikaty;
    private boolean rozrusznik_serca;
    private boolean hermofilia;
    private boolean luszczyca;
    private String alergia;
    private String przebarwienie;
    private String choroba_zakazna;
    private String zaburzenia_ukrwienia;
    private String opryszczka;
    private String garaczka;
    private String oslabienie;
    private boolean ciaza;

    public Pracownik(){
        this.pesel = 0;
        this.data_zatrudnienia = new Date();
        this.certyfikaty = "";
        this.rozrusznik_serca = false;
        this.hermofilia = false;
        this.luszczyca = false;
        this.alergia = "";
        this.przebarwienie = "";
        this.choroba_zakazna = "";
        this.zaburzenia_ukrwienia = "";
        this.opryszczka = "";
        this.garaczka = "";
        this.oslabienie = "";
        this.ciaza = false;
    }

    public int getPesel() {
        return pesel;
    }

    public void setPesel(int pesel) {
        this.pesel = pesel;
    }

    public Date getData_zatrudnienia() {
        return data_zatrudnienia;
    }

    public void setData_zatrudnienia(Date data_zatrudnienia) {
        this.data_zatrudnienia = data_zatrudnienia;
    }

    public String getCertyfikaty() {
        return certyfikaty;
    }

    public void setCertyfikaty(String certyfikaty) {
        this.certyfikaty = certyfikaty;
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

    public String getOpryszczka() {
        return opryszczka;
    }

    public void setOpryszczka(String opryszczka) {
        this.opryszczka = opryszczka;
    }

    public String getGaraczka() {
        return garaczka;
    }

    public void setGaraczka(String garaczka) {
        this.garaczka = garaczka;
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
}

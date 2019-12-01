package myPage.data;

public class Client extends User {
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
        this.p_ocena_skory = "-";
        this.p_rodzaj_jakosc = "-";
        this.p_wrazliwosc = "-";
        this.p_inne_uwagi = "-";
    }

    public int getIlosc_punktow() {
        return ilosc_punktow;
    }

    public void setIlosc_punktow(int ilosc_punktow) {
        this.ilosc_punktow = ilosc_punktow;
    }

    public boolean isP_p1() {
        return p_p1;
    }

    public void setP_p1(boolean p_p1) {
        this.p_p1 = p_p1;
    }

    public boolean isP_p2() {
        return p_p2;
    }

    public void setP_p2(boolean p_p2) {
        this.p_p2 = p_p2;
    }

    public boolean isP_p3() {
        return p_p3;
    }

    public void setP_p3(boolean p_p3) {
        this.p_p3 = p_p3;
    }

    public boolean isP_p4() {
        return p_p4;
    }

    public void setP_p4(boolean p_p4) {
        this.p_p4 = p_p4;
    }

    public boolean isP_p5() {
        return p_p5;
    }

    public void setP_p5(boolean p_p5) {
        this.p_p5 = p_p5;
    }

    public boolean isP_p6() {
        return p_p6;
    }

    public void setP_p6(boolean p_p6) {
        this.p_p6 = p_p6;
    }

    public boolean isP_p7() {
        return p_p7;
    }

    public void setP_p7(boolean p_p7) {
        this.p_p7 = p_p7;
    }

    public boolean isP_p8() {
        return p_p8;
    }

    public void setP_p8(boolean p_p8) {
        this.p_p8 = p_p8;
    }

    public boolean isP_p9() {
        return p_p9;
    }

    public void setP_p9(boolean p_p9) {
        this.p_p9 = p_p9;
    }

    public String getP_ocena_skory() {
        return p_ocena_skory;
    }

    public void setP_ocena_skory(String p_ocena_skory) {
        this.p_ocena_skory = p_ocena_skory;
    }

    public String getP_rodzaj_jakosc() {
        return p_rodzaj_jakosc;
    }

    public void setP_rodzaj_jakosc(String p_rodzaj_jakosc) {
        this.p_rodzaj_jakosc = p_rodzaj_jakosc;
    }

    public String getP_wrazliwosc() {
        return p_wrazliwosc;
    }

    public void setP_wrazliwosc(String p_wrazliwosc) {
        this.p_wrazliwosc = p_wrazliwosc;
    }

    public String getP_inne_uwagi() {
        return p_inne_uwagi;
    }

    public void setP_inne_uwagi(String p_inne_uwagi) {
        this.p_inne_uwagi = p_inne_uwagi;
    }
}

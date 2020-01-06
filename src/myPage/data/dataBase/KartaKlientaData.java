package myPage.data.dataBase;

public class KartaKlientaData{
    private int id_karty;
    private boolean p1;
    private boolean p2;
    private boolean p3;
    private boolean p4;
    private boolean p5;
    private boolean p6;
    private boolean p7;
    private boolean p8;
    private boolean p9;
    private String ocena_skory;
    private String rodzaj_jakos;
    private String wrazliwosc;
    private String inne_uwagi;

    public KartaKlientaData(
            int id_karty,
            boolean p1,
            boolean p2,
            boolean p3,
            boolean p4,
            boolean p5,
            boolean p6,
            boolean p7,
            boolean p8,
            boolean p9,
            String ocena_skory,
            String rodzaj_jakos,
            String wrazliwosc,
            String inne_uwagi
    ){
        this.id_karty = id_karty;
        this.p1 = p1;
        this.p2 = p2;
        this.p3 = p3;
        this.p4 = p4;
        this.p5 = p5;
        this.p6 = p6;
        this.p7 = p7;
        this.p8 = p8;
        this.p9 = p9;
        this.ocena_skory = ocena_skory;
        this.rodzaj_jakos = rodzaj_jakos;
        this.wrazliwosc = wrazliwosc;
        this.inne_uwagi = inne_uwagi;
    }

    public int getId_karty() {
        return id_karty;
    }

    public boolean isP1() {
        return p1;
    }

    public boolean isP2() {
        return p2;
    }

    public boolean isP3() {
        return p3;
    }

    public boolean isP4() {
        return p4;
    }

    public boolean isP5() {
        return p5;
    }

    public boolean isP6() {
        return p6;
    }

    public boolean isP7() {
        return p7;
    }

    public boolean isP8() {
        return p8;
    }

    public boolean isP9() {
        return p9;
    }

    public String getOcena_skory() {
        return ocena_skory;
    }

    public String getRodzaj_jakos() {
        return rodzaj_jakos;
    }

    public String getWrazliwosc() {
        return wrazliwosc;
    }

    public String getInne_uwagi() {
        return inne_uwagi;
    }

    public String toStringp1(){
            if (p1) return "tak"; else return "nie";
    }

    public String toStringp2(){
        if (p2) return "tak"; else return "nie";
    }

    public String toStringp3(){
        if (p3) return "tak"; else return "nie";
    }

    public String toStringp4(){
        if (p4) return "tak"; else return "nie";
    }

    public String toStringp5(){
        if (p5) return "tak"; else return "nie";
    }

    public String toStringp6(){
        if (p6) return "tak"; else return "nie";
    }

    public String toStringp7(){
        if (p7) return "tak"; else return "nie";
    }

    public String toStringp8(){
        if (p8) return "tak"; else return "nie";
    }

    public String toStringp9(){
        if (p9) return "tak"; else return "nie";
    }
}

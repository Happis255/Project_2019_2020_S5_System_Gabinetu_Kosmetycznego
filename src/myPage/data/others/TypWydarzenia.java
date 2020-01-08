package myPage.data.others;

public enum TypWydarzenia {
    KONGRES(1),
    SZKOLENIE(2),
    TARGI_KOSMETYCZNE(3);

    private int val;

    TypWydarzenia(int val){
        this.val=val;
    }

    public static String getStringVal(TypWydarzenia eventType){
        String str;
        switch (eventType){
            case KONGRES:
                str = "Kongres";
                break;
            case SZKOLENIE:
                str = "Szkolenie";
                break;
            case TARGI_KOSMETYCZNE:
                str = "Targi kosmetyczne";
                break;
            default:
                str = null;
                break;
        }
        return str;
    }

    public static TypWydarzenia getTypWydarzenia(String eventType){
        TypWydarzenia typWydarzenia;
        switch (eventType){
            case "KONGRES":
                typWydarzenia = KONGRES;
                break;
            case "SZKOLENIE":
                typWydarzenia = SZKOLENIE;
                break;
            case "TARGI_KOSMETYCZNE":
                typWydarzenia = TARGI_KOSMETYCZNE;
                break;
            default:
                typWydarzenia = null;
                break;
        }
        return typWydarzenia;
    }
}

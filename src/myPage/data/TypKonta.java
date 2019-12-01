package myPage.data;

public enum TypKonta{
    PRACOWNIK(1),
    ADMINISTRATOR(2),
    KLIENT(3);

    private int val;

    TypKonta(int val){
        this.val=val;
    }

    public static String getStringVal(TypKonta accountType){
        String str;
        switch (accountType){
            case ADMINISTRATOR:
                str = "ADMINISTRATOR";
                break;
            case PRACOWNIK:
                str = "PRACOWNIK";
                break;
            case KLIENT:
                str = "KLIENT";
                break;
            default:
                str = null;
                break;
        }
        return str;
    }
}

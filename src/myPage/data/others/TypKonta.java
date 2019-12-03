package myPage.data.others;

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

    public static TypKonta getTypKonta(String accountType){
        TypKonta typKonta;
        switch (accountType){
            case "ADMINISTRATOR":
                typKonta = ADMINISTRATOR;
                break;
            case "PRACOWNIK":
                typKonta = PRACOWNIK;
                break;
            case "KLIENT":
                typKonta = KLIENT;
                break;
            default:
                typKonta = null;
                break;
        }
        return typKonta;
    }
}

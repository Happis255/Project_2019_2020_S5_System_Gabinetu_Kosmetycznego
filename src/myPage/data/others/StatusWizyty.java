package myPage.data.others;

public enum StatusWizyty {
    DO_ZATWIERDZENIA(1),
    ODRZUCONE(2),
    POTWIERDZONE(3),
    OPLACONE(4);

    private int val;

    StatusWizyty(int val){
        this.val=val;
    }

    public static String getStringVal(StatusWizyty status){
        String str;
        switch (status){
            case DO_ZATWIERDZENIA:
                str = "DO_ZATWIERDZENIA";
                break;
            case ODRZUCONE:
                str = "ODRZUCONE";
                break;
            case POTWIERDZONE:
                str = "POTWIERDZONE";
                break;
            case OPLACONE:
                str = "OPLACONE";
                break;
            default:
                str = null;
                break;
        }
        return str;
    }

    public static StatusWizyty getStatusWizyty(String accountType){
        StatusWizyty statusWizyty;
        switch (accountType){
            case "DO_ZATWIERDZENIA":
                statusWizyty = DO_ZATWIERDZENIA;
                break;
            case "ODRZUCONE":
                statusWizyty = ODRZUCONE;
                break;
            case "POTWIERDZONE":
                statusWizyty = POTWIERDZONE;
                break;
            case "OPLACONE":
                statusWizyty = OPLACONE;
                break;
            default:
                statusWizyty = null;
                break;
        }
        return statusWizyty;
    }
}

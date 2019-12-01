package myPage.data;

public class SessionData {
    private String nick;
    private TypKonta accoutType;
    private ErrorMessage errorMessage;

    public SessionData(String nick, TypKonta accountType){
        this.nick = nick;
        this.accoutType = accountType;
    }

    public String getNick() {
        return nick;
    }

    public void setNick(String nick) {
        this.nick = nick;
    }

    public TypKonta getAccoutType() {
        return accoutType;
    }

    public void setAccoutType(TypKonta accoutType) {
        this.accoutType = accoutType;
    }

    public ErrorMessage getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(ErrorMessage errorMessage) {
        this.errorMessage = errorMessage;
    }

    public String toString(){
        return "Nick:"+nick+" AccountType:"+ TypKonta.getStringVal(accoutType) +"\n";
    }
}

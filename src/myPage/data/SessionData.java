package myPage.data;

public class SessionData {
    private String nick;
    private Client.TypKonta accoutType;
    private ErrorMessage errorMessage;

    public SessionData(String nick, Client.TypKonta accountType){
        this.nick = nick;
        this.accoutType = accountType;
    }

    public String getNick() {
        return nick;
    }

    public void setNick(String nick) {
        this.nick = nick;
    }

    public Client.TypKonta getAccoutType() {
        return accoutType;
    }

    public void setAccoutType(Client.TypKonta accoutType) {
        this.accoutType = accoutType;
    }

    public ErrorMessage getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(ErrorMessage errorMessage) {
        this.errorMessage = errorMessage;
    }

    public String toString(){
        return "Nick:"+nick+" AccountType:"+ Client.TypKonta.getStringVal(accoutType) +"\n";
    }
}

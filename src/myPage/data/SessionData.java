package myPage.data;

public class SessionData {
    private String nick;
    private Client.TypKonta accoutType;

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

    public String toString(){
        return "Nick:"+nick+" AccountType:"+ Client.TypKonta.getStringVal(accoutType) +"\n";
    }
}

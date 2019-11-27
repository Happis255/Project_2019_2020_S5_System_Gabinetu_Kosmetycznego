package myPage.data;

public class SessionData {
    private String nick;

    public SessionData(String nick){
        this.nick = nick;
    }

    public String getNick() {
        return nick;
    }

    public void setNick(String nick) {
        this.nick = nick;
    }

    public String toString(){
        return "Nick:"+nick+"\n";
    }
}

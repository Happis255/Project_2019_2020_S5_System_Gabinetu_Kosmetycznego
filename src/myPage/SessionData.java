package myPage;

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
}

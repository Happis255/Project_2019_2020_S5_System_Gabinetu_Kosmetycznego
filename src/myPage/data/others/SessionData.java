package myPage.data.others;

public class SessionData {

    private String nick;
    private TypKonta accoutType;
    private int id;
    private ErrorMessage errorMessage;
    private AccountPage accountPage;


    public SessionData(String nick, TypKonta accountType, int id){
        this.nick = nick;
        this.accoutType = accountType;
        this.id = id;
        this.accountPage = new AccountPage();
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

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public ErrorMessage getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(ErrorMessage errorMessage) {
        this.errorMessage = errorMessage;
    }

    public AccountPage getAccountPage() {
        return accountPage;
    }

    public String toString(){
        return "Nick:"+nick+" AccountType:"+ TypKonta.getStringVal(accoutType) +"\n";
    }
}

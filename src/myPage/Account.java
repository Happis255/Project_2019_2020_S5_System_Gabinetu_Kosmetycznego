package myPage;


public class Account {
    enum TypKonta{
        PRACOWNIK(1),
        ADMINISTRATOR(2),
        KLIENT(3);

        private int val;

        TypKonta(int typKonta){
            this.val = typKonta;
        }
    }

    private String haslo;

    public Account(){
        this.haslo = "";
    }

    public Account(String haslo){
        this();
        this.haslo = haslo;
    }

    public String getHaslo() {
        return haslo;
    }
}

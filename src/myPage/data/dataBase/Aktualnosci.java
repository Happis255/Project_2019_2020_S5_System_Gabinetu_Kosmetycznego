package myPage.data.dataBase;

import java.util.LinkedList;

public class Aktualnosci {
    private LinkedList<AktualnoscData> aktualnosci;

    public Aktualnosci() {
        aktualnosci = new LinkedList<>();
    }

    public int getAktualnosciCount(){
        return aktualnosci.size();
    }

    public void addAktualnosc(AktualnoscData akt){
        aktualnosci.add(akt);
    }

    public AktualnoscData peekAktualnosc(int id){
        return aktualnosci.get(id);
    }

    public AktualnoscData popAktualnosc(int id) {
        AktualnoscData data = aktualnosci.get(id);
        aktualnosci.remove(id);
        return data;
    }
}

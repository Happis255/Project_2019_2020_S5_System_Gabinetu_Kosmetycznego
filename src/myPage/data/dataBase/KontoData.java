package myPage.data.dataBase;

import myPage.data.others.TypKonta;

public class KontoData{
    private int id_konta;
    private String haslo;
    private TypKonta typ_konta;

    public KontoData(
            int id_konta,
            String haslo,
            TypKonta typ_konta
    ){
        this.id_konta = id_konta;
        this.haslo = haslo;
        this.typ_konta = typ_konta;
    }

    public int getId_konta() {
        return id_konta;
    }

    public String getHaslo() {
        return haslo;
    }

    public TypKonta getTyp_konta() {
        return typ_konta;
    }
}

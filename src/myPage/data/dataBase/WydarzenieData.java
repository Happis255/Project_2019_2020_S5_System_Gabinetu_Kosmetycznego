package myPage.data.dataBase;

import myPage.data.others.TypWydarzenia;
import myPage.dataSourceDB.DataSource;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.util.LinkedList;

public class WydarzenieData {
    private int id;
    private TypWydarzenia typWydarzenia;
    private String nazwa;
    private String opis;
    private String ulica;
    private String kod_pocztowy;
    private String miejscowosc;
    private Date data_od;
    private Date data_do;
    private int kosz;
    private DataSource dataSource;

    public WydarzenieData(
            int id,
            TypWydarzenia typWydarzenia,
            String nazwa,
            String opis,
            String ulica,
            String kod_pocztowy,
            String miejscowosc,
            Date data_od,
            Date data_do,
            int kosz
    ){
        this.id = id;
        this.typWydarzenia = typWydarzenia;
        this.nazwa = nazwa;
        this.opis = opis;
        this.ulica = ulica;
        this.kod_pocztowy = kod_pocztowy;
        this.miejscowosc = miejscowosc;
        this.data_od = data_od;
        this.data_do = data_do;
        this.kosz = kosz;
        dataSource = new DataSource();
    }

    public int getId() {
        return id;
    }

    public TypWydarzenia getTypWydarzenia() {
        return typWydarzenia;
    }

    public String getOpisTypuWydarzenia() {
        return TypWydarzenia.getStringVal(this.typWydarzenia);
    }


    public String getNazwa() {
        return nazwa;
    }

    public String getOpis() {
        return opis;
    }

    public String getUlica() {
        return ulica;
    }

    public String getKod_pocztowy() {
        return kod_pocztowy;
    }

    public String getMiejscowosc() {
        return miejscowosc;
    }

    public Date getData_od() {
        return data_od;
    }

    public Date getData_do() {
        return data_do;
    }

    public int getKosz() {
        return kosz;
    }

    public LinkedList<String> getWorkerName(int id) throws SQLException {
        LinkedList<String> name = new LinkedList<>();
        ResultSet result;

        result = dataSource.getWorkerName(id);
        while(result.next()){
            name.add(String.valueOf(result.getInt("id_pracownika")));
            name.add(result.getString("imie"));
            name.add(result.getString("nazwisko"));
        }

        return name;
    }
}

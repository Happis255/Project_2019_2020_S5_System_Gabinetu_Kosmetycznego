package myPage.basicObjects;

import myPage.data.dataBase.WydarzenieData;
import myPage.data.others.TypWydarzenia;
import myPage.dataSourceDB.DataSource;
import myPage.others.DateTransformer;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;

public class Wydarzenie {
    private LinkedList<WydarzenieData> wydarzenia;
    private DataSource dataSource;

    public Wydarzenie() {
        dataSource = new DataSource();
        wydarzenia = new LinkedList<>();
    }

    public WydarzenieData pop(){
        return wydarzenia.pop();
    }

    public boolean isEmpty(){
        return wydarzenia.isEmpty();
    }

    public void getEverything() throws SQLException {
        ResultSet resultQuery = dataSource.getAllPracownicyDB();

        while (resultQuery.next()) {

            wydarzenia.add(new WydarzenieData(
                    resultQuery.getInt("id_wydarzenia"),
                    TypWydarzenia.getTypWydarzenia(resultQuery.getString("typ")),
                    resultQuery.getString("nazwa"),
                    resultQuery.getString("opis"),
                    resultQuery.getString("ulica"),
                    resultQuery.getString("kod_pocztowy"),
                    resultQuery.getString("miejscowosc"),
                    DateTransformer.getJavaDate(resultQuery.getDate("data_od")),
                    DateTransformer.getJavaDate(resultQuery.getDate("data_do")),
                    resultQuery.getInt("kosz")
            ));
        }
    }

    //podobnie jak przy Usluga
}

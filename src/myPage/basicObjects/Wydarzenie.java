package myPage.basicObjects;

import myPage.data.dataBase.WydarzenieData;
import myPage.data.others.TypWydarzenia;
import myPage.dataSourceDB.DataSource;
import myPage.exceptions.DBReadWriteException;
import myPage.others.DateTransformer;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.HashMap;
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

    public void addWydarzenie(HashMap<String, String> parameters) throws DBReadWriteException, SQLException, ParseException {
        dataSource.createEventDB(parameters);
    }

    public void getEverything() throws SQLException {
        ResultSet resultQuery = dataSource.getAllEventsDB();

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

    public void singInForEvent(int idPracownika, int idWydarzenia){
        dataSource.singInWorkerForEvent(idPracownika, idWydarzenia);
    }

    //podobnie jak przy Usluga
}

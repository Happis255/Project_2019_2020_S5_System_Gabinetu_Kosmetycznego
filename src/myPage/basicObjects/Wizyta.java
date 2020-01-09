package myPage.basicObjects;

import myPage.data.dataBase.WizytaData;
import myPage.data.others.StatusWizyty;
import myPage.dataSourceDB.DataSource;
import myPage.exceptions.DBReadWriteException;
import myPage.others.DateTransformer;
import myPage.others.TimeTransformer;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.util.LinkedList;

public class Wizyta {
    private LinkedList<WizytaData> wizyty;
    protected DataSource dataSource;

    public Wizyta() {
        dataSource = new DataSource();
        wizyty = new LinkedList<>();
    }

    public WizytaData pop(){
        return wizyty.pop();
    }

    public boolean isEmpty(){
        return wizyty.isEmpty();
    }

    public void getEverythingActual() throws SQLException, DBReadWriteException {
        ResultSet resultQuery = dataSource.getAllEventsDB();

        while (resultQuery.next()) {

            wizyty.add(new WizytaData(
                    resultQuery.getInt("id_wizyty"),
                    DateTransformer.getJavaDate(resultQuery.getDate("data")),
                    TimeTransformer.getJavaTime(resultQuery.getTime("godzina")),
                    StatusWizyty.getStatusWizyty(resultQuery.getString("status")),
                    resultQuery.getInt("id_pracownika"),
                    resultQuery.getInt("id_klienta"),
                    resultQuery.getInt("id_uslugi")
            ));
        }
    }

    public void getVisitsInDayWithWorker(Date data, int idPracownika) throws SQLException{
        ResultSet resultQuery = dataSource.getWizytyWDniuZPracownikiem(DateTransformer.getSqlDate(data), idPracownika);

        while (resultQuery.next()) {

            wizyty.add(new WizytaData(
                    resultQuery.getInt("id_wizyty"),
                    DateTransformer.getJavaDate(resultQuery.getDate("data")),
                    TimeTransformer.getJavaTime(resultQuery.getTime("godzina")),
                    StatusWizyty.getStatusWizyty(resultQuery.getString("status")),
                    resultQuery.getInt("id_pracownika"),
                    resultQuery.getInt("id_klienta"),
                    resultQuery.getInt("id_uslugi")
            ));
        }
    }

    public void getTimeWorkerFree(int idPracownika, Date dzien) throws SQLException, DBReadWriteException {
        ResultSet resultQuery = dataSource.getVisitsWorkerInDay(idPracownika, dzien);

        while (resultQuery.next()) {
            wizyty.add(new WizytaData(
                    resultQuery.getInt("id_wizyty"),
                    DateTransformer.getJavaDate(resultQuery.getDate("data")),
                    TimeTransformer.getJavaTime(resultQuery.getTime("godzina")),
                    StatusWizyty.getStatusWizyty(resultQuery.getString("status")),
                    resultQuery.getInt("id_pracownika"),
                    resultQuery.getInt("id_klienta"),
                    resultQuery.getInt("id_uslugi")
            ));
        }
    }
}

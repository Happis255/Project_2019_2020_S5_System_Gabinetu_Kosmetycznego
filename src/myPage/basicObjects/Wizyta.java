package myPage.basicObjects;

import myPage.data.dataBase.KlientData;
import myPage.data.dataBase.UslugaData;
import myPage.data.dataBase.WizytaData;
import myPage.data.others.StatusWizyty;
import myPage.dataSourceDB.DataSource;
import myPage.exceptions.DBReadWriteException;
import myPage.others.DateTransformer;
import myPage.others.TimeTransformer;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalTime;
import java.util.Date;
import java.util.HashMap;
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

    public int size(){
        return wizyty.size();
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

    public void dodajWizyteDoZatwierdzenia(int idUslugi, int idKlienta, int idPracownika, Date data, LocalTime godzina) throws SQLException {
        dataSource.createVisitToConfirm(idUslugi, idKlienta, idPracownika, data, godzina);
    }

    /* Ładuje wizyy na aktualny miesiąc */
    public void getMonthWizyty() throws SQLException {
        ResultSet resultQuery = dataSource.getMonthWizytyDB();

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

    public WizytaData getWizytaID_DB(int id) throws SQLException {
        ResultSet resultQuery = dataSource.getWizytaID_DB(id);
        WizytaData result = null;

        if (resultQuery.next())
        result = new WizytaData(
                    resultQuery.getInt("id_wizyty"),
                    DateTransformer.getJavaDate(resultQuery.getDate("data")),
                    TimeTransformer.getJavaTime(resultQuery.getTime("godzina")),
                    StatusWizyty.getStatusWizyty(resultQuery.getString("status")),
                    resultQuery.getInt("id_pracownika"),
                    resultQuery.getInt("id_klienta"),
                    resultQuery.getInt("id_uslugi")
            );
        return result;

    }


    public void potwierdz_wizyteID(int id) throws SQLException {
        dataSource.potwierdz_wizyteID_DB(id);
    }

    public void odrzuc_wizyteID(int id) throws SQLException {
        dataSource.odrzuc_wizyteID_DB(id);
    }

    public void zatwierdz_platnosc_wizytyID(int id) throws SQLException {
        dataSource.zatwierdz_platnosc_wizytyID_DB(id);
    }

    public void usun_wizyteID(int id) throws SQLException {
        dataSource.usun_wizyteID_DB(id);
    }

    public void createByWorker(HashMap<String, String> parameters) throws SQLException {
        dataSource.checkIfCanDoService(Integer.parseInt(parameters.get("P_ID_PRACOWNIKA")), Integer.parseInt(parameters.get("P_ID_USLUGI")));
        dataSource.createWizytaWorkerDB(parameters);
    }

    public void getTodayWizyty() throws SQLException {
        ResultSet resultQuery = dataSource.getTodayWizytyDB();

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

    public void loadWizytyFroMDatatoData(String data_od, String data_do) throws SQLException {
        ResultSet resultQuery = dataSource.loadWizytyFroMDatatoData_DB(data_od, data_do);
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

    public void loadWizytyFromToWithClient(String data_od, String data_do, String id_klienta) throws SQLException {
        ResultSet resultQuery = dataSource.loadWizytyFromToWithClient_DB(data_od, data_do, id_klienta);
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

    public void loadWizytyFromToWithWorker(String data_od, String data_do, String id_pracownika) throws SQLException {
        ResultSet resultQuery = dataSource.loadWizytyFromToWithWorker_DB(data_od, data_do, id_pracownika);
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

    public void getMonthWizytyForClient(int id) throws SQLException {
        ResultSet resultQuery = dataSource.getMonthWizytyForClient_DB(id);
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

    public void loadWizytyFromDataToDataClient(String data_od, String data_do, int id) throws SQLException {
        ResultSet resultQuery = dataSource.loadWizytyFromToWithClient_DB(data_od, data_do, Integer.toString(id));
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

    public void zaktualizuj_punkty(WizytaData wizyta_info) throws SQLException {
        Klient klient = new Klient(wizyta_info.getId_klienta());
        KlientData klientData = klient.getData();
        Usluga usluga = new Usluga();
        UslugaData uslugaData = usluga.getUsluga_ID(wizyta_info.getId_uslugi());
        dataSource.zaktualizujPunktyDB(klientData.getE_mail(), uslugaData.getCena());
    }

    public void loadWizytyPracownikaWDniu(Date data, int idPracownika) throws SQLException {
        ResultSet resultQuery = dataSource.pobierzWizytyPracnikaWDniu(data, idPracownika);
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

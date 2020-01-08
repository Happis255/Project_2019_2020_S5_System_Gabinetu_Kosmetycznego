package myPage.dataSourceDB;

import myPage.data.dataBase.*;
import myPage.exceptions.DBReadWriteException;
import myPage.exceptions.NoResultsException;
import myPage.others.DateTransformer;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedList;

public class DataSource {
    protected Connection connection;
    protected HashMap<String, PreparedStatement> statements;

    public DataSource(){
        connection = DataBaseManager.getConnection();
        statements = DataBaseManager.getStatements();
    }

    /* Metody wykorzystywane do komunikacji z bazą danych */

    //Karta klienta
    /* Metoda zwraca informacje o karcie klienta o podanym ID */
    public KartaKlientaData getKartaKlientaID(int id_klienta) throws SQLException {

        PreparedStatement exeStatement;

        exeStatement = statements.get("pobierz_karteKlientaID_P");
        exeStatement.setInt(1, id_klienta);
        ResultSet resultSet = exeStatement.executeQuery();

        KartaKlientaData wynik = null;

        if (resultSet.next())
        wynik = new KartaKlientaData(
                resultSet.getInt("id_karty"),
                resultSet.getBoolean("p1"),
                resultSet.getBoolean("p2"),
                resultSet.getBoolean("p3"),
                resultSet.getBoolean("p4"),
                resultSet.getBoolean("p5"),
                resultSet.getBoolean("p6"),
                resultSet.getBoolean("p7"),
                resultSet.getBoolean("p8"),
                resultSet.getBoolean("p9"),
                resultSet.getString("ocena_skory"),
                resultSet.getString("rodzaj_jakosc"),
                resultSet.getString("wrazliwosc"),
                resultSet.getString("inne_uwagi")
        );
        return wynik;
    }


    //Ksiazeczka zdrowia
    /* Metoda zwraca informacje o ksiazece zdrowia odczytanej z bazy danych */
    public KsiazeczkaZdrowiaData getKsiazeczkaZdrowiaID(int id) throws SQLException {

        PreparedStatement exeStatement;

        exeStatement = statements.get("pobierz_ksiazeczkeZdrowiaID_P");
        exeStatement.setInt(1, id);
        ResultSet resultSet = exeStatement.executeQuery();
        KsiazeczkaZdrowiaData odczytana = null;

        while(resultSet.next()){
             odczytana = new KsiazeczkaZdrowiaData(
                    resultSet.getInt("id_ksiazeczki"),
                    resultSet.getBoolean("rozrusznik_serca"),
                    resultSet.getBoolean("hermofilia"),
                    resultSet.getBoolean("luszczyca"),
                    resultSet.getString("alergia"),
                    resultSet.getString("przebarwienie"),
                    resultSet.getString("choroba_zakazna"),
                    resultSet.getString("zaburzenia_ukrwienia"),
                    resultSet.getString("opryszczka"),
                    resultSet.getString("goraczka"),
                    resultSet.getString("oslabienie"),
                    resultSet.getBoolean("ciaza")
            );
        }
        return odczytana;
    }

    //Nieobecnosc
    /* Metoda do zgłaszania nieobecności w bazie danych */
    public void createNieobecnoscDB(HashMap<String, String> parameters) throws DBReadWriteException, SQLException, ParseException {

        PreparedStatement exeStatement;
        int result;

        exeStatement = statements.get("zglos_nieobecnosc_P");

        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date date_od = dateFormat.parse(parameters.get("data_od"));
        Date date_do = dateFormat.parse(parameters.get("data_do"));
        exeStatement.setDate(1, DateTransformer.getSqlDate(date_od));
        exeStatement.setDate(2, DateTransformer.getSqlDate(date_do));
        exeStatement.setString(3, parameters.get("powod"));
        exeStatement.setInt(4, Integer.parseInt(parameters.get("id_pracownika")));
        exeStatement.setString(5, "NIEPOTWIERDZONE");

        result = exeStatement.executeUpdate();
        if(result != 1)
            throw new DBReadWriteException(result + " rows add with execute: zglos_nieobecnosc_P");
    }

    /* Metoda do pobierania nieobecności z bazy danych */
    public LinkedList<NieobecnoscData> getAbsencesAll() throws SQLException {

        LinkedList<NieobecnoscData> news_list = new LinkedList<>();

        PreparedStatement exeStatement;
        ResultSet resultSet;
        exeStatement = statements.get("pobierz_nieobecnosci_all_p");
        resultSet = exeStatement.executeQuery();

        NieobecnoscData.Status status = null;

        while(resultSet.next()){

            if (resultSet.getString("status").equals("NIEPOTWIERDZONE"))
                status = NieobecnoscData.Status.NIEPOTWIERDZONE;
            else
                status = NieobecnoscData.Status.POTWIERDZONE;

            news_list.push(new NieobecnoscData(
                    resultSet.getInt("id_nieobecnosci"),
                    DateTransformer.getJavaDate(resultSet.getDate("data_od")),
                    DateTransformer.getJavaDate(resultSet.getDate("data_do")),
                    resultSet.getString("powod"),
                    resultSet.getInt("id_pracownika"),
                    status,
                    resultSet.getString("imie"),
                    resultSet.getString("nazwisko")));
        }
        return news_list;
    }

    /* Metoda do usuwania pracownika z bazy */
    public void removePracownikID(int a) throws SQLException {
        PreparedStatement exeStatement;
        ResultSet resultSet;
        exeStatement = statements.get("usunPracownika_p");
        exeStatement.setInt(1, a);
        exeStatement.executeQuery();

        PreparedStatement exeStatement2;
        exeStatement2 = statements.get("usunaKartePracownika_p");
        exeStatement2.setInt(1, a);
        exeStatement2.executeQuery();

        PreparedStatement exeStatement3;
        exeStatement3 = statements.get("usunKonto_p");
        exeStatement3.setInt(1, a);
        exeStatement.executeQuery();
    }

    /* Zwracanie listy sprzętów używanych w gabinecie kosmetycznym */
    public LinkedList<SprzetData> getAllMachinesDB() throws SQLException {

        LinkedList<SprzetData> sprzety = new LinkedList<>();
        PreparedStatement exeStatement;
        ResultSet resultSet;
        exeStatement = statements.get("pobierz_sprzet_gabinet_p");
        resultSet = exeStatement.executeQuery();

        while(resultSet.next()){
            if (!resultSet.getString("uwagi").equals("Sprzęt nie jest już w użytku"))
                sprzety.push(new SprzetData(
                        resultSet.getInt("id_sprzetu"),
                        resultSet.getString("nazwa_sprzetu"),
                        resultSet.getString("opis_sprzetu"),
                        DateTransformer.getJavaDate(resultSet.getDate("data_zakupu")),
                        DateTransformer.getJavaDate(resultSet.getDate("data_gwarancji")),
                        resultSet.getString("uwagi")));
        }
        return sprzety;
    }

    /* Dodawanie sprzetu */
    public void addMachine (HashMap<String, String> parameters) throws SQLException, ParseException {

        PreparedStatement exeStatement;
        exeStatement = statements.get("dodaj_sprzet_p");

        exeStatement.setString(1, parameters.get("gwarancja"));
        exeStatement.setString(2, parameters.get("opis"));
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date data_zakupu = dateFormat.parse(parameters.get("data_zakupu"));
        Date data_gwarancji = dateFormat.parse(parameters.get("gwarancja"));
        exeStatement.setDate(3, DateTransformer.getSqlDate(data_zakupu));
        exeStatement.setDate(4, DateTransformer.getSqlDate(data_gwarancji));
        exeStatement.setString(5, parameters.get("uwagi"));

        exeStatement.executeUpdate();
    }

    /* Usuwanie sprzetu */
    public void removeMachine (int id) throws SQLException {
        PreparedStatement exeStatement;
        exeStatement = statements.get("usun_sprzet_id_p");
        exeStatement.setInt(1, id);
        exeStatement.executeUpdate();
    }

    /* Pobieranie statusów */
    public LinkedList<StatusData> getStatusy() throws SQLException {

        LinkedList<StatusData> status_list = new LinkedList<>();
        PreparedStatement exeStatement;
        ResultSet resultSet;

        exeStatement = statements.get("pobierz_statusy_DB");

        resultSet = exeStatement.executeQuery();

        while(resultSet.next()){
            status_list.push(new StatusData(
                resultSet.getInt("id_statusu"),
                resultSet.getInt("punkty_od"),
                resultSet.getInt("punkty_do"),
                resultSet.getInt("znizka_proc"),
                resultSet.getInt("znizka_kwot"),
                resultSet.getString("nazwa")
            ));
        }
        return status_list;
    }

    public StatusData getStatusyID(int ID) throws SQLException {

        StatusData wynik = null;
        PreparedStatement exeStatement;
        ResultSet resultSet;

        exeStatement = statements.get("pobierz_statusy_ID_DB");
        exeStatement.setInt(1, ID);

        resultSet = exeStatement.executeQuery();

        if(resultSet.next()){
            wynik = new StatusData(
                    resultSet.getInt("id_statusu"),
                    resultSet.getInt("punkty_od"),
                    resultSet.getInt("punkty_do"),
                    resultSet.getInt("znizka_proc"),
                    resultSet.getInt("znizka_kwot"),
                    resultSet.getString("nazwa")
            );
        }
        return wynik;
    }

    /* Dodanie statusu */
    public void addStatusDB(HashMap<String, String> parameters) throws SQLException {

        PreparedStatement exeStatement;
        exeStatement = statements.get("dodaj_status_DB");

        exeStatement.setInt(1, Integer.parseInt(parameters.get("p_punkty_od")));
        exeStatement.setInt(2, Integer.parseInt(parameters.get("p_punkty_do")));
        exeStatement.setInt(3, Integer.parseInt(parameters.get("p_znizka_proc")));
        exeStatement.setInt(4, Integer.parseInt(parameters.get("p_znizka_kwot")));
        exeStatement.setString(5, parameters.get("p_nazwa"));

        exeStatement.executeUpdate();
    }

    /* Pobieranie liczby użytkowników danego statusu*/
    public int countStatus(int id_statusu) throws SQLException {
        int count = 0;
        PreparedStatement exeStatement;
        exeStatement = statements.get("zlicz_status_ID_DB");
        exeStatement.setInt(1, id_statusu);
        ResultSet resultSet = exeStatement.executeQuery();

        if (resultSet.next())
            count = resultSet.getInt("liczba");

        return count;
    }

    /* Edycja statusu */
    public void editStatusDB(HashMap<String, String> parameters) throws SQLException {
        PreparedStatement exeStatement;

        exeStatement = statements.get("edytuj_status_DB");

        exeStatement.setInt(1, Integer.parseInt(parameters.get("p_id")));
        exeStatement.setInt(2, Integer.parseInt(parameters.get("p_punkty_od")));
        exeStatement.setInt(3, Integer.parseInt(parameters.get("p_punkty_do")));
        exeStatement.setInt(4, Integer.parseInt(parameters.get("p_znizka_proc")));
        exeStatement.setInt(5, Integer.parseInt(parameters.get("p_znizka_kwot")));
        exeStatement.setString(6, parameters.get("p_nazwa"));

        exeStatement.executeUpdate();
    }

    /* Metoda do pobierania nieobecności z bazy danych wybranego pracownika */
    public LinkedList<NieobecnoscData> getAbsenceWorker(int id) throws SQLException {

        LinkedList<NieobecnoscData> news_list = new LinkedList<>();

        PreparedStatement exeStatement;
        ResultSet resultSet;
        exeStatement = statements.get("pobierz_nieobecnosci_pracownika_p");
        exeStatement.setInt(1, id);
        resultSet = exeStatement.executeQuery();

        NieobecnoscData.Status status = null;

        while(resultSet.next()){

            if (resultSet.getString("status").equals("NIEPOTWIERDZONE"))
                status = NieobecnoscData.Status.NIEPOTWIERDZONE;
            else
                status = NieobecnoscData.Status.POTWIERDZONE;

            news_list.push(new NieobecnoscData(
                    resultSet.getInt("id_nieobecnosci"),
                    DateTransformer.getJavaDate(resultSet.getDate("data_od")),
                    DateTransformer.getJavaDate(resultSet.getDate("data_do")),
                    resultSet.getString("powod"),
                    resultSet.getInt("id_pracownika"),
                    status,
                    resultSet.getString("imie"),
                    resultSet.getString("nazwisko")));
        }
        return news_list;
    }
    /* Metody do ustawiania statusu nieobecności danego pracownika */
    public void declineAbsenceID(int id) throws SQLException {
        PreparedStatement exeStatement;
        exeStatement = statements.get("declineAbsenceID_P");
        exeStatement.setInt(1, id);
        exeStatement.executeUpdate();
    }

    public void approveAbsenceID(int id) throws SQLException {
        PreparedStatement exeStatement;
        exeStatement = statements.get("approveAbsenceID_P");
        exeStatement.setInt(1, id);
        exeStatement.executeUpdate();
    }

    /* Metoda do usuwania nieobecności z bazy danych przez wybranego pracownika */
    public void removeAbsenceID(int id) throws SQLException {
        PreparedStatement exeStatement;
        exeStatement = statements.get("removeAbsenceID_P");
        exeStatement.setInt(1, id);
        exeStatement.executeUpdate();
    }


    //Aktualnosci
    /* Metoda do usuwania aktualnosci o podanym id */
    public void removeNewsID(int id) throws SQLException {
        PreparedStatement exeStatement;
        exeStatement = statements.get("removeNewsID_P");
        exeStatement.setInt(1, id);
        exeStatement.executeUpdate();
    }

    /* Metoda do pobierania aktualnosci */
    public LinkedList<AktualnoscData> getAktualnosciDB() throws SQLException{

        LinkedList<AktualnoscData> news_list = new LinkedList<>();

        PreparedStatement exeStatement;
        ResultSet resultSet;
        exeStatement = statements.get("pobierz_dzisiaj_aktualnosci_P");
        resultSet = exeStatement.executeQuery();

        while(resultSet.next()){
            news_list.push(new AktualnoscData(
                    resultSet.getInt("id_aktualnosci"),
                    resultSet.getString("tytul"),
                    resultSet.getString("tresc"),
                    DateTransformer.getJavaDate(resultSet.getDate("data_od")),
                    DateTransformer.getJavaDate(resultSet.getDate("data_do")),
                    resultSet.getInt("id_pracownika")));
        }
        return news_list;
    }

    public LinkedList<AktualnoscData> getAllAtktualnosciDB()  throws SQLException {

        LinkedList<AktualnoscData> news_list = new LinkedList<>();

        PreparedStatement exeStatement;
        ResultSet resultSet;
        exeStatement = statements.get("pobierz_wszystkieAktualnosci_P");
        resultSet = exeStatement.executeQuery();

        while(resultSet.next()){
            news_list.push(new AktualnoscData(
                    resultSet.getInt("id_aktualnosci"),
                    resultSet.getString("tytul"),
                    resultSet.getString("tresc"),
                    DateTransformer.getJavaDate(resultSet.getDate("data_od")),
                    DateTransformer.getJavaDate(resultSet.getDate("data_do")),
                    resultSet.getString("nazwisko")));
        }
        return news_list;
    }

    public int getMaxIDAktualnosciDB() throws NoResultsException, SQLException{
        PreparedStatement exeStatement;
        ResultSet resultSet;
        exeStatement = statements.get("pobierz_max_id_aktualnosci_p");
        resultSet = exeStatement.executeQuery();
        if (resultSet.next()){
            int wynik = resultSet.getInt("id_aktualnosci");
            return wynik;
        } else
            throw new NoResultsException();
    }

    public void createAktualnoscDB(HashMap<String, String> parameters) throws DBReadWriteException, SQLException, ParseException {

        PreparedStatement exeStatement;
        int result;
        exeStatement = statements.get("createNews_P");
        exeStatement.setString(1, parameters.get("tytul"));
        exeStatement.setString(2, parameters.get("opis"));
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date date_od = dateFormat.parse(parameters.get("data-od"));
        Date date_do = dateFormat.parse(parameters.get("data-do"));
        exeStatement.setDate(3, DateTransformer.getSqlDate(date_od));
        exeStatement.setDate(4, DateTransformer.getSqlDate(date_do));
        exeStatement.setInt(5, Integer.parseInt(parameters.get("id-pracownika")));
        result = exeStatement.executeUpdate();
        if(result != 1)
            throw new DBReadWriteException(result + " rows add with execute: createNews_P");
    }

    /* Uslugi */
    // 1. Pobieranie wszystkich uslug gabinetu
    public LinkedList<UslugaData> getAllServicesDB() throws SQLException{

        LinkedList<UslugaData> news_list = new LinkedList<>();

        PreparedStatement exeStatement;
        ResultSet resultSet;
        exeStatement = statements.get("pobierz_UslugiGabinetu_p");
        resultSet = exeStatement.executeQuery();

        while(resultSet.next()){

            if (!resultSet.getString("opis").equals("usługa nie jest wykonywana"))
            news_list.push(new UslugaData(
                    resultSet.getInt("id_uslugi"),
                    resultSet.getString("typ_uslugi"),
                    resultSet.getString("nazwa"),
                    resultSet.getString("opis"),
                    resultSet.getBoolean("czy_karta"),
                    resultSet.getInt("cena"),
                    resultSet.getInt("czas_trwania"),
                    resultSet.getString("wskazowki"),
                    resultSet.getInt("id_promocji")));
        }
        return news_list;
    }

    // 1.1. Pobieranie uslugi wykonywanych przez pracownika o ID
    public LinkedList<UslugaData> getAllServicesForWorker(int id) throws SQLException {
        LinkedList<UslugaData> news_list = new LinkedList<>();

        PreparedStatement exeStatement;
        ResultSet resultSet;
        exeStatement = statements.get("pobierz_UslugiGabinetuDlaPracownika_p");
        exeStatement.setInt(1, id);
        resultSet = exeStatement.executeQuery();

        while(resultSet.next()){
            news_list.push(new UslugaData(
                    resultSet.getInt("id_uslugi"),
                    resultSet.getString("typ_uslugi"),
                    resultSet.getString("nazwa"),
                    resultSet.getString("opis"),
                    resultSet.getBoolean("czy_karta"),
                    resultSet.getInt("cena"),
                    resultSet.getInt("czas_trwania"),
                    resultSet.getString("wskazowki"),
                    resultSet.getInt("id_promocji")));
        }
        return news_list;
    }

    // 2. Pobieranie max id uslugi
    public int getMaxIDServiceDB() throws NoResultsException, SQLException{
        PreparedStatement exeStatement;
        ResultSet resultSet;
        exeStatement = statements.get("pobierz_max_id_uslugi_p");
        resultSet = exeStatement.executeQuery();
        if (resultSet.next()){
            int wynik = resultSet.getInt("id_uslugi");
            return wynik;
        } else
            throw new NoResultsException();
    }

    // 3. Usuwanie uslugi
    public void removeServiceID(int id) throws SQLException {
        PreparedStatement exeStatement;
        exeStatement = statements.get("removeServiceID_P");
        exeStatement.setInt(1, id);
        exeStatement.executeUpdate();
    }

    // 3.1 Usuwanie możliwości wykonywania uslugi
    public void removeAllowenceDB(int id_uslugi, int id_prac) throws SQLException {
        PreparedStatement exeStatement;
        exeStatement = statements.get("removeAllowenceWorker_p");
        exeStatement.setInt(1, id_prac);
        exeStatement.setInt(2, id_uslugi);
        exeStatement.executeUpdate();
    }

    // 4. Utworz usluge
    public void createServiceDB(HashMap<String, String> parameters) throws DBReadWriteException, SQLException {

        PreparedStatement exeStatement;
        int result;
        exeStatement = statements.get("createService_p");
        exeStatement.setString(1, parameters.get("typ_uslugi"));
        exeStatement.setString(2, parameters.get("nazwa"));
        exeStatement.setString(3, parameters.get("opis"));
        exeStatement.setBoolean(4, Boolean.parseBoolean(parameters.get("czy_karta")));
        exeStatement.setInt(5, Integer.parseInt(parameters.get("cena")));
        exeStatement.setInt(6, Integer.parseInt(parameters.get("czas_trwania")));
        exeStatement.setString(7, parameters.get("wskazowki"));
        result = exeStatement.executeUpdate();
        if(result != 1)
            throw new DBReadWriteException(result + " rows add with execute: createService_p");
    }

    /* Pracownik */
    //1. Utworz pracownika
    public void createWorkerDB(HashMap<String, String> parameters) throws DBReadWriteException, SQLException, ParseException {

        PreparedStatement exeStatement;
        exeStatement = statements.get("createWorker_p");

        Date date = new Date();
        java.sql.Date sqlDate = new java.sql.Date((date.getTime()));

        exeStatement.setString(1, parameters.get("p_haslo"));
        exeStatement.setString(2, parameters.get("p_imie"));
        exeStatement.setString(3, parameters.get("p_nazwisko"));
        exeStatement.setString(4, parameters.get("p_ulica"));
        exeStatement.setString(5, parameters.get("p_kod_pocztowy"));
        exeStatement.setString(6, parameters.get("p_miejscowowsc"));
        exeStatement.setDate(7, java.sql.Date.valueOf(parameters.get("p_data_urodzenia")));
        exeStatement.setInt(8, Integer.parseInt(parameters.get("p_telefon")));
        exeStatement.setString(9, parameters.get("p_e_mail"));
        exeStatement.setLong(10, Long.parseLong(parameters.get("p_pesel")));
        exeStatement.setDate(11, sqlDate);
        exeStatement.setString(12, parameters.get("p_certyfikaty"));
        exeStatement.setBoolean(13, Boolean.parseBoolean(parameters.get("p_rozrusznik_serca")));
        exeStatement.setBoolean(14, Boolean.parseBoolean(parameters.get("p_hermofilia")));
        exeStatement.setBoolean(15, Boolean.parseBoolean(parameters.get("p_luszczyca")));
        exeStatement.setString(16, parameters.get("p_alergia"));
        exeStatement.setString(17, parameters.get("p_przebarwienie"));
        exeStatement.setString(18, parameters.get("p_choroba_zakazna"));
        exeStatement.setString(19, parameters.get("p_zaburzenia_ukrwienia"));
        exeStatement.setString(20, parameters.get("p_opryszczka"));
        exeStatement.setString(21, parameters.get("p_goraczka"));
        exeStatement.setString(22, parameters.get("p_oslabienie"));
        exeStatement.setBoolean(23, Boolean.parseBoolean(parameters.get("p_ciaza")));

        exeStatement.executeQuery();
    }

    //2. Edytuj pracownika
    public void editWorkerDB(HashMap<String, String> parameters) throws DBReadWriteException, SQLException, ParseException {

        PreparedStatement exeStatement;
        exeStatement = statements.get("editWorker_P");

        System.out.println(parameters.get("p_id_pracownika"));

        exeStatement.setInt(1, Integer.parseInt(parameters.get("p_id_pracownika")));
        exeStatement.setString(2, parameters.get("p_nazwisko"));
        exeStatement.setString(3, parameters.get("p_ulica"));
        exeStatement.setString(4, parameters.get("p_kod_pocztowy"));
        exeStatement.setString(5, parameters.get("p_miejscowowsc"));
        exeStatement.setInt(6, Integer.parseInt(parameters.get("p_telefon")));
        exeStatement.setString(7, parameters.get("p_e_mail"));
        exeStatement.setString(8, parameters.get("p_certyfikaty"));
        exeStatement.setString(9, parameters.get("p_haslo"));
        exeStatement.executeQuery();
    }

    //2.1 Edytuj ksiazeczke zdrowia pracownika
    public void editWorkersBookDB(HashMap<String, String> parameters) throws DBReadWriteException, SQLException, ParseException {

        PreparedStatement exeStatement;
        exeStatement = statements.get("editWorkerBook_P");

        exeStatement.setInt(1, Integer.parseInt(parameters.get("p_id_ksiazeczki")));
        exeStatement.setBoolean(2, Boolean.parseBoolean(parameters.get("p_rozrusznik_serca")));
        exeStatement.setBoolean(3, Boolean.parseBoolean(parameters.get("p_hermofilia")));
        exeStatement.setBoolean(4, Boolean.parseBoolean(parameters.get("p_luszczyca")));
        exeStatement.setString(5, parameters.get("p_alergia"));
        exeStatement.setString(6, parameters.get("p_przebarwienie"));
        exeStatement.setString(7, parameters.get("p_choroba_zakazna"));
        exeStatement.setString(8, parameters.get("p_zaburzenia_ukrwienia"));
        exeStatement.setString(9, parameters.get("p_opryszczka"));
        exeStatement.setString(10, parameters.get("p_goraczka"));
        exeStatement.setString(11, parameters.get("p_oslabienie"));
        exeStatement.setBoolean(12, Boolean.parseBoolean(parameters.get("p_ciaza")));
        exeStatement.executeQuery();
    }

    //Edytuj dane klienta
    public void UpdateClientDB(HashMap<String, String> parameters) throws DBReadWriteException, SQLException, ParseException {

        PreparedStatement exeStatement;
        exeStatement = statements.get("editClient_p");

        exeStatement.setInt(1, Integer.parseInt(parameters.get("p_id_klienta")));
        exeStatement.setString(2, parameters.get("p_e_mail"));
        exeStatement.setString(3, parameters.get("p_nazwisko"));
        exeStatement.setString(4, parameters.get("p_ulica"));
        exeStatement.setString(5, parameters.get("p_kod_pocztowy"));
        exeStatement.setString(6, parameters.get("p_miejscowowsc"));
        exeStatement.setInt(7, Integer.parseInt(parameters.get("p_telefon")));
        exeStatement.setString(8, parameters.get("p_haslo"));
        exeStatement.executeQuery();
    }

    //Edytuj karte klienta
    public void editClientBookDB(HashMap<String, String> parameters) throws SQLException {

        PreparedStatement exeStatement;
        exeStatement = statements.get("editClientBook_p");

        exeStatement.setInt(1, Integer.parseInt(parameters.get("p_id_klienta")));
        exeStatement.setBoolean(2, Boolean.parseBoolean(parameters.get("p1")));
        exeStatement.setBoolean(3, Boolean.parseBoolean(parameters.get("p2")));
        exeStatement.setBoolean(4, Boolean.parseBoolean(parameters.get("p3")));
        exeStatement.setBoolean(5, Boolean.parseBoolean(parameters.get("p4")));
        exeStatement.setBoolean(6, Boolean.parseBoolean(parameters.get("p5")));
        exeStatement.setBoolean(7, Boolean.parseBoolean(parameters.get("p6")));
        exeStatement.setBoolean(8, Boolean.parseBoolean(parameters.get("p7")));
        exeStatement.setBoolean(9, Boolean.parseBoolean(parameters.get("p8")));
        exeStatement.setBoolean(10, Boolean.parseBoolean(parameters.get("p9")));
        exeStatement.setString(11, parameters.get("p10"));
        exeStatement.setString(12, parameters.get("p11"));
        exeStatement.setString(13, parameters.get("p12"));
        exeStatement.setString(14, parameters.get("p13"));

        exeStatement.executeQuery();
    }

    public void CheckUserDB(String e_mail) throws SQLException{
        PreparedStatement exeStatement;
        ResultSet resultSet;

        exeStatement = statements.get("getUser");
        exeStatement.setString(1, e_mail);
        resultSet = exeStatement.executeQuery();

        if(resultSet.next())
            throw new SQLException();
    }

    public ResultSet getUserDB(String e_mail) throws SQLException{
        PreparedStatement exeStatement;
        ResultSet resultSet;

        exeStatement = statements.get("getUser");
        exeStatement.setString(1, e_mail);
        resultSet = exeStatement.executeQuery();

        return resultSet;
    }

    public void createClientDB(HashMap<String, String> parameters) throws DBReadWriteException, SQLException, ParseException {

        PreparedStatement exeStatement1 = statements.get("createClientCard_P");
        exeStatement1.setBoolean(1, false);
        exeStatement1.setBoolean(2, false);
        exeStatement1.setBoolean(3, false);
        exeStatement1.setBoolean(4, false);
        exeStatement1.setBoolean(5, false);
        exeStatement1.setBoolean(6, false);
        exeStatement1.setBoolean(7, false);
        exeStatement1.setBoolean(8, false);
        exeStatement1.setBoolean(9, false);
        exeStatement1.setString(10, "-");
        exeStatement1.setString(11, "-");
        exeStatement1.setString(12, "-");
        exeStatement1.setString(13, "-");
        exeStatement1.executeUpdate();

        PreparedStatement exeStatement2 = statements.get("createClient_P");
        exeStatement2.setString(1, parameters.get("e-mail"));
        exeStatement2.setString(2, parameters.get("haslo"));
        exeStatement2.setString(3, parameters.get("imie"));
        exeStatement2.setString(4, parameters.get("nazwisko"));
        exeStatement2.setString(5, parameters.get("ulica"));
        exeStatement2.setString(6, parameters.get("kod"));
        exeStatement2.setString(7, parameters.get("miejscowosc"));

        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date date = dateFormat.parse(parameters.get("data-urodzenia"));

        exeStatement2.setDate(8, DateTransformer.getSqlDate(date));
        exeStatement2.setInt(9, Integer.parseInt(parameters.get("telefon")));
        exeStatement2.executeUpdate();

        PreparedStatement exeStatement3 = statements.get("assignClientCard_P");
        exeStatement3.setString(1, parameters.get("e-mail"));
        exeStatement3.executeUpdate();
    }

    public ResultSet getClientDB(int idKlienta) throws SQLException{
        PreparedStatement exeStatement;
        ResultSet resultSet;

        exeStatement = statements.get("getClient");
        exeStatement.setInt(1, idKlienta);
        resultSet = exeStatement.executeQuery();

        return resultSet;
    }

    public void removeClientDN (int id_kontaKlienta) throws SQLException {

        PreparedStatement exeStatement;
        exeStatement = statements.get("removeClientDN_P");

        exeStatement.setInt(1, id_kontaKlienta);
        exeStatement.executeQuery();
    }

    public String getClientStatusNameDB(int id_statusu) throws SQLException, NoResultsException {

        String wynik = "";
        PreparedStatement exeStatement;
        ResultSet resultSet;

        exeStatement = statements.get("getClientStatusNameDB_p");
        exeStatement.setInt(1, id_statusu);
        resultSet = exeStatement.executeQuery();

        if (resultSet.next()){
            wynik = resultSet.getString("nazwa");
            return wynik;
        } else
            throw new NoResultsException();
    }

    public ResultSet getClientAccountDB(int idKlienta) throws SQLException{
        PreparedStatement exeStatement;
        ResultSet resultSet;

        exeStatement = statements.get("getClientAccountData");
        exeStatement.setInt(1, idKlienta);
        resultSet = exeStatement.executeQuery();

        return resultSet;
    }

    public ResultSet getPracownikDB(int idPracownika) throws SQLException {
        PreparedStatement exeStatement;
        ResultSet resultSet;

        exeStatement = statements.get("getPracownik");
        exeStatement.setInt(1, idPracownika);
        resultSet = exeStatement.executeQuery();

        return resultSet;
    }

    public ResultSet getPracownikAccountDB(int idKlienta) throws SQLException{
        PreparedStatement exeStatement;
        ResultSet resultSet;

        exeStatement = statements.get("getClientAccountData");
        exeStatement.setInt(1, idKlienta);
        resultSet = exeStatement.executeQuery();

        return resultSet;
    }

    /* Zwraca zbiór pracowników bez danych ich konta */
    public ResultSet getAllPracownicyDB() throws SQLException {
        PreparedStatement exeStatement;
        ResultSet resultSet;
        exeStatement = statements.get("getAllWorkers");
        resultSet = exeStatement.executeQuery();
        return resultSet;
    }

    /* Nadaje możliwość wykonywania usługi pracownikowi */
    public void addServiceWorkerDB(int id_prac, int id_usl) throws SQLException {

        PreparedStatement exeStatement;
        exeStatement = statements.get("setServiceWorker");
        exeStatement.setInt(1,id_prac);
        exeStatement.setInt(2, id_usl);
        exeStatement.executeUpdate();
    }

    public void checkServiceWorkerDB(int id_prac, int id_usl) throws SQLException {

        PreparedStatement exeStatement;
        exeStatement = statements.get("checkServiceWorker");
        exeStatement.setInt(1,id_prac);
        exeStatement.setInt(2, id_usl);
        ResultSet resultSet = exeStatement.executeQuery();
        if(resultSet.next()) throw new SQLException("POWTORZONA TABELA");

    }

    //Raporty
    public LinkedList<RaportData> getRaportsWorker(int id) throws SQLException {

        LinkedList<RaportData> raport_list = new LinkedList<>();

        PreparedStatement exeStatement;
        ResultSet resultSet;
        exeStatement = statements.get("pobierz_raporty_pracownika_p");
        exeStatement.setInt(1, id);
        resultSet = exeStatement.executeQuery();

        while(resultSet.next()){

            raport_list.push(new RaportData(
                    resultSet.getInt("id_sprawozdania"),
                    DateTransformer.getJavaDate(resultSet.getDate("data")),
                    resultSet.getString("tytul"),
                    resultSet.getString("typ"),
                    resultSet.getString("tresc"),
                    resultSet.getInt("id_pracownika")));
        }

        return raport_list;
    }

    public LinkedList<RaportData> getRaports() throws SQLException {

        LinkedList<RaportData> raport_list = new LinkedList<>();

        PreparedStatement exeStatement;
        ResultSet resultSet;
        exeStatement = statements.get("pobierz_raporty");
        resultSet = exeStatement.executeQuery();

        while(resultSet.next()){

            raport_list.push(new RaportData(
                    resultSet.getInt("id_sprawozdania"),
                    DateTransformer.getJavaDate(resultSet.getDate("data")),
                    resultSet.getString("tytul"),
                    resultSet.getString("typ"),
                    resultSet.getString("tresc"),
                    resultSet.getInt("id_pracownika")));
        }

        return raport_list;

    }

    public void removeRaportID(int id_sprawozdania) throws SQLException {
        PreparedStatement exeStatement;
        exeStatement = statements.get("removeRaport");

        exeStatement.setInt(1, id_sprawozdania);
        exeStatement.executeQuery();
    }

    public void createRaportDB(HashMap<String, String> parameters) throws DBReadWriteException, SQLException, ParseException {
        PreparedStatement exeStatement;
        int result;

        exeStatement = statements.get("dodaj_raport");

        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date data = dateFormat.parse(parameters.get("data"));
        exeStatement.setString(1, parameters.get("typ"));
        exeStatement.setString(2, parameters.get("tytul"));
        exeStatement.setString(3, parameters.get("tresc"));
        exeStatement.setDate(4, DateTransformer.getSqlDate(data));
        exeStatement.setInt(5, Integer.parseInt(parameters.get("id_pracownika")));


        result = exeStatement.executeUpdate();
        if(result != 1)
            throw new DBReadWriteException(result + " rows add with execute: dodaj_raport");
    }

    /* Produkty znajdujące się w gabinecie kosmetycznym */

    //Pobieranie max id produktu - potrzebne dla ustawiania nazwy grafiki produktu
    public int get_MaxId_produkt_sprzedazowyDB() throws SQLException {
        PreparedStatement exeStatement;
        exeStatement = statements.get("get_MaxId_produkt_sprzedazowyDB_P");

        int wynik = 0;
        ResultSet resultSet = exeStatement.executeQuery();

        if(resultSet.next()){
            wynik = resultSet.getInt("max_id");
        }
        return wynik;
    }

    //1.0 Tworzenie produktu na sprzedaż
    public void createProductSell(HashMap<String, String> parameters) throws SQLException {

        PreparedStatement exeStatement;
        exeStatement = statements.get("createProductSell_P");

        exeStatement.setString(1, parameters.get("P_NAZWA"));
        exeStatement.setString(2, parameters.get("P_OPIS"));
        exeStatement.setInt(3, Integer.parseInt(parameters.get("P_CENA")));
        exeStatement.setInt( 4, Integer.parseInt(parameters.get("P_ILOSC")));
        exeStatement.executeQuery();
    }

    //2.0 Tworzenie produktu uslugowego
    public void createProductUse(HashMap<String, String> parameters) throws SQLException {

        PreparedStatement exeStatement;
        exeStatement = statements.get("createProductUse_P");

        exeStatement.setString(1, parameters.get("P_NAZWA"));
        exeStatement.setString(2, parameters.get("P_OPIS"));
        exeStatement.setInt(3, Integer.parseInt(parameters.get("P_CENA")));
        exeStatement.setInt( 4, Integer.parseInt(parameters.get("P_ILOSC")));
        exeStatement.setString(5, parameters.get("P_KOLOR"));
        exeStatement.executeUpdate();
    }

    //3.0 Zaladowanie produktow sprzedazowych
    public LinkedList<ProduktSprzedazowyData> getAllSelling() throws SQLException {

        LinkedList<ProduktSprzedazowyData> lista = new LinkedList<>();

        PreparedStatement exeStatement;
        exeStatement = statements.get("getAllSelling_P");

        ResultSet resultSet = exeStatement.executeQuery();
        int id = 0;

        while(resultSet.next()){
            id = resultSet.getInt("id_promocji");
            if (id == 0){
                /* Produkt posiada promocje */
                if ( !resultSet.getString("opis").equals("produkt usuniety") )
                lista.push(new ProduktSprzedazowyData(
                        resultSet.getInt("id_produktu_s"),
                        resultSet.getString("nazwa"),
                        resultSet.getString("opis"),
                        resultSet.getInt("cena"),
                        resultSet.getInt("ilosc")
            ));
            } else {
                /* Produkt nie posiada promocje */
                if ( !resultSet.getString("opis").equals("produkt usuniety") )
                lista.push(new ProduktSprzedazowyData(
                        resultSet.getInt("id_produktu_s"),
                        resultSet.getString("nazwa"),
                        resultSet.getString("opis"),
                        resultSet.getInt("cena"),
                        resultSet.getInt("ilosc"),
                        id
                ));
            }
        }
        return lista;
    }

    //4.0 Zaladowanie produktow uzytkowych
    public LinkedList<ProduktUzytkowyData> getAllUsing() throws SQLException {

        LinkedList<ProduktUzytkowyData> lista = new LinkedList<>();

        PreparedStatement exeStatement;
        exeStatement = statements.get("getAllUsing_P");

        ResultSet resultSet = exeStatement.executeQuery();
        while (resultSet.next()) {
            /* Produkt posiada promocje */
            if ( !resultSet.getString("opis").equals("produkt usuniety") )
            lista.push(new ProduktUzytkowyData(
                    resultSet.getInt("id_produktu"),
                    resultSet.getString("nazwa"),
                    resultSet.getString("opis"),
                    resultSet.getInt("cena"),
                    resultSet.getInt("ilosc"),
                    resultSet.getString("kolor")
            ));
        }
        return lista;
    }

    //5.0 Edytowanie ilosci produktow na sprzedaz badz przypisywanie nowej promocji
    public void edit_sellingProductsDB(HashMap<String, String> parameters) throws SQLException {

        /* Sprawdzamy, czy trzba zmienić ilosc produktow */
        if (Integer.parseInt(parameters.get("p_ilosc")) > -1){
            /* Zmieniana bedzie ilość */
            PreparedStatement exeStatement;
            exeStatement = statements.get("edit_sellingProductsDB_P");
            exeStatement.setInt(1, Integer.parseInt(parameters.get("P_ID_PRODUKTU_S")));
            exeStatement.setInt(2, Integer.parseInt(parameters.get("p_ilosc")));
            exeStatement.executeUpdate();
        }

        /* Została wybrana promocja - nalezy ją uwzględnić */
        PreparedStatement exeStatement2;

        if (parameters.get("P_ID_PROMOCJI").equals("null")){
            exeStatement2 = statements.get("setNoPromoProduct_P");
            exeStatement2.setInt(1, Integer.parseInt(parameters.get("P_ID_PRODUKTU_S")));
        }
        else{
            exeStatement2 = statements.get("setPromoProductSell_P");
            exeStatement2.setInt(1, Integer.parseInt(parameters.get("P_ID_PROMOCJI")));
            exeStatement2.setInt(2, Integer.parseInt(parameters.get("P_ID_PRODUKTU_S")));
        }
        exeStatement2.executeUpdate();
    }

    //6.0 Zmiana ilosci produktow uzytkowych
    public void edit_usingProductsDB(HashMap<String, String> parameters) throws SQLException {

        if (Integer.parseInt(parameters.get("p_ilosc")) > -1){
            PreparedStatement exeStatement;
            exeStatement = statements.get("edit_usingProductsDB_P");
            exeStatement.setInt(1, Integer.parseInt(parameters.get("P_ID_PRODUKTU_S")));
            exeStatement.setInt(2, Integer.parseInt(parameters.get("p_ilosc")));
            exeStatement.executeUpdate();
        }
    }

    //7.0 Usuwanie produktu uzytkowego
    public void removeUsingProductIDDB(int a) throws SQLException {

        PreparedStatement exeStatement;
        exeStatement = statements.get("removeUsingProductIDDB_P");
        exeStatement.setInt(1, a);
        exeStatement.executeUpdate();
    }

    //8.0 Usuwanie produktu sprzedazowego
    public void removeSellingProductIDDB(int a) throws SQLException {

        PreparedStatement exeStatement;
        exeStatement = statements.get("removeSellingProductIDDB_P");
        exeStatement.setInt(1, a);
        exeStatement.executeUpdate();
    }

    /* Promocje uslugowe i zabiegowe */

    //1 Dodawanie promocji
    public void add_promoDB(HashMap<String, String> parameters) throws SQLException, ParseException {
        PreparedStatement exeStatement;
        exeStatement = statements.get("add_promoDB_P");

        exeStatement.setString(1, parameters.get("P_NAZWA"));
        exeStatement.setString(2, parameters.get("P_OPIS"));
        exeStatement.setInt(3, Integer.parseInt(parameters.get("P_ZNIZKA_PROC")));
        exeStatement.setInt( 4, Integer.parseInt(parameters.get("P_ZNIZKA_KWOT")));

        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date date_od = dateFormat.parse(parameters.get("P_DATA_OD"));
        Date date_do = dateFormat.parse(parameters.get("P_DATA_DO"));

        exeStatement.setDate(5, DateTransformer.getSqlDate(date_od));
        exeStatement.setDate(6, DateTransformer.getSqlDate(date_do));
        exeStatement.setInt(7, Integer.parseInt(parameters.get("P_ID_PRACOWNIKA")));
        exeStatement.executeUpdate();
    }

    //2 Wczytywanie promocji
    public LinkedList<PromocjaData> get_all_promoDB() throws SQLException {
        LinkedList<PromocjaData> lista = new LinkedList<>();

        PreparedStatement exeStatement;
        exeStatement = statements.get("get_all_promoDB_P");

        ResultSet resultSet = exeStatement.executeQuery();

        while (resultSet.next()) {
            if (!resultSet.getString("opis").equals("promocja nieaktualna"))
            lista.push(new PromocjaData(
                    resultSet.getInt("id_promocji"),
                    resultSet.getString("nazwa"),
                    resultSet.getString("opis"),
                    resultSet.getInt("znizka_proc"),
                    resultSet.getInt("znizka_kwot"),
                    DateTransformer.getJavaDate(resultSet.getDate("data_od")),
                    DateTransformer.getJavaDate(resultSet.getDate("data_do")),
                    resultSet.getInt("id_pracownika")
            ));
        }
        return lista;
    }

    //3 Usuwanie promocji
    public void remove_promo_IDDB(int a) throws SQLException {
        PreparedStatement exeStatement;
        exeStatement = statements.get("remove_promo_IDDB_P");
        exeStatement.setInt(1, a);
        exeStatement.executeUpdate();
    }

    //4 Pobieranie wybranej promocji
    public PromocjaData get_promo_IDDB(int a) throws SQLException {

        PreparedStatement exeStatement;
        exeStatement = statements.get("get_promo_IDDB_P");
        exeStatement.setInt(1, a);

        ResultSet resultSet = exeStatement.executeQuery();
        PromocjaData wczytana = null;

        if (resultSet.next()) {
            wczytana = new PromocjaData(
                    resultSet.getInt("id_promocji"),
                    resultSet.getString("nazwa"),
                    resultSet.getString("opis"),
                    resultSet.getInt("znizka_proc"),
                    resultSet.getInt("znizka_kwot"),
                    DateTransformer.getJavaDate(resultSet.getDate("data_od")),
                    DateTransformer.getJavaDate(resultSet.getDate("data_do")),
                    resultSet.getInt("id_pracownika")
            );
        }
        return wczytana;
    }

    //5 Pobieranie promocji dodanych przez pracownika
    public LinkedList<PromocjaData> get_all_promo_workerIDDB(int id_pracownika) throws SQLException {
        LinkedList<PromocjaData> lista = new LinkedList<>();
        PreparedStatement exeStatement;
        exeStatement = statements.get("get_all_promo_workerIDDB_P");
        exeStatement.setInt(1, id_pracownika);

        ResultSet resultSet = exeStatement.executeQuery();

        while (resultSet.next()) {

            lista.push(new PromocjaData(
                    resultSet.getInt("id_promocji"),
                    resultSet.getString("nazwa"),
                    resultSet.getString("opis"),
                    resultSet.getInt("znizka_proc"),
                    resultSet.getInt("znizka_kwot"),
                    DateTransformer.getJavaDate(resultSet.getDate("data_od")),
                    DateTransformer.getJavaDate(resultSet.getDate("data_do")),
                    resultSet.getInt("id_pracownika")
            ));
        }
        return lista;
    }

    //Edycja promocji
    public void edit_promo(HashMap<String, String> parameters) throws SQLException, ParseException {
        PreparedStatement exeStatement;
        exeStatement = statements.get("edit_promo_P");

        exeStatement.setString(1, parameters.get("P_NAZWA"));
        exeStatement.setString(2, parameters.get("P_OPIS"));
        exeStatement.setInt(3, Integer.parseInt(parameters.get("P_ZNIZKA_PROC")));
        exeStatement.setInt( 4, Integer.parseInt(parameters.get("P_ZNIZKA_KWOT")));

        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date date_od = dateFormat.parse(parameters.get("P_DATA_OD"));
        Date date_do = dateFormat.parse(parameters.get("P_DATA_DO"));

        exeStatement.setDate(5, DateTransformer.getSqlDate(date_od));
        exeStatement.setDate(6, DateTransformer.getSqlDate(date_do));
        exeStatement.setInt(7, Integer.parseInt(parameters.get("P_ID_PRACOWNIKA")));
        exeStatement.setInt(8, Integer.parseInt(parameters.get("P_ID_PROMOCJI")));
        exeStatement.executeUpdate();
    }

    public boolean checkPromoToday_DB(int id_promocji) throws SQLException {
        PreparedStatement exeStatement;
        exeStatement = statements.get("check_promo_today_P");
        exeStatement.setInt(1, id_promocji);
        ResultSet resultSet = exeStatement.executeQuery();
        return resultSet.next();
    }

    //wydarzenia
    public void createEventDB(HashMap<String, String> parameters) throws DBReadWriteException, SQLException, ParseException {
        PreparedStatement exeStatement;
        int result;
        DateFormat dateFormat;
        Date date;

        exeStatement = statements.get("createEvent_P");
        exeStatement.setString(1, parameters.get("typWydarzenia"));
        exeStatement.setString(2, parameters.get("nazwa"));
        exeStatement.setString(3, parameters.get("opis"));
        exeStatement.setString(4, parameters.get("miejscowosc"));
        exeStatement.setString(5, parameters.get("kod_pocztowy"));
        exeStatement.setString(6, parameters.get("ulica"));

        dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        date = dateFormat.parse(parameters.get("data_od"));
        exeStatement.setDate(7, DateTransformer.getSqlDate(date));
        date = dateFormat.parse(parameters.get("data_do"));
        exeStatement.setDate(8, DateTransformer.getSqlDate(date));

        exeStatement.setInt(9, Integer.parseInt(parameters.get("kosz")));
        exeStatement.setInt(10, Integer.parseInt(parameters.get("id_pracownika")));

        result = exeStatement.executeUpdate();
        if(result != 1)
            throw new DBReadWriteException(result + " rows add with execute: createClientCard_P");
    }

    public ResultSet getAllEventsDB() throws SQLException {
        PreparedStatement exeStatement;
        ResultSet resultSet;
        exeStatement = statements.get("getAllEvents");
        resultSet = exeStatement.executeQuery();
        return resultSet;
    }

    public void removeEventDB(HashMap<String, String> parameters) throws DBReadWriteException, SQLException, ParseException {
        PreparedStatement exeStatement;
        int result;
        DateFormat dateFormat;
        Date date;

        exeStatement = statements.get("createEvent_P");
        exeStatement.setString(1, parameters.get("typWydarzenia"));
        exeStatement.setString(2, parameters.get("nazwa"));
        exeStatement.setString(3, parameters.get("opis"));
        exeStatement.setString(4, parameters.get("miejscowosc"));
        exeStatement.setString(5, parameters.get("kod_pocztowy"));
        exeStatement.setString(6, parameters.get("ulica"));

        dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        date = dateFormat.parse(parameters.get("data_od"));
        exeStatement.setDate(7, DateTransformer.getSqlDate(date));
        date = dateFormat.parse(parameters.get("data_do"));
        exeStatement.setDate(8, DateTransformer.getSqlDate(date));

        exeStatement.setInt(9, Integer.parseInt(parameters.get("kosz")));
        exeStatement.setInt(10, Integer.parseInt(parameters.get("id_pracownika")));

        result = exeStatement.executeUpdate();
        if(result != 1)
            throw new DBReadWriteException(result + " rows add with execute: createClientCard_P");
    }
}
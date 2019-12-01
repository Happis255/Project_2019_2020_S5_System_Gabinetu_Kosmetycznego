package myPage.others;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.HashMap;

public class DataBaseManager {
    private final static String DB_URL = "jdbc:mysql://127.0.0.1:3306/projektgabinetgracjadb";
    private final static String USER_NAME = "root";
    private final static String USER_PASSWD = "";
    private final static String DB_DRIVER = "com.mysql.jdbc.Driver";

    private static Connection connection;
    private static HashMap<String, PreparedStatement> statements;

    static{
        try {
            Class.forName(DB_DRIVER);
            statements = new HashMap<>();
            connection = DriverManager.getConnection(DB_URL, USER_NAME, USER_PASSWD);
            statements.put("getClient", connection.prepareStatement("select * from konto join klient on konto.id_konta=klient.id_konta where klient.e_mail=?"));
            statements.put("createClient_P", connection.prepareStatement("{call utworz_klienta(?, ?, ?, ?, ?, ?, ?, ?, ?)}"));
            statements.put("getWorker", connection.prepareStatement("select * from konto join pracownik on konto.id_konta=pracownik.id_konta where pracownik.e_mail=?"));
            statements.put("getUser", connection.prepareStatement("SELECT t.e_mail, t.typ_konta, t.haslo " +
                    "FROM (SELECT kt.id_konta, IF(kt.typ_konta LIKE 'KLIENT' , kl.e_mail , pr.e_mail) AS e_mail, kt.typ_konta, kt.haslo " +
                          "FROM konto kt LEFT JOIN klient kl ON kt.id_konta = kl.id_konta LEFT JOIN pracownik pr ON kt.id_konta = pr.id_konta) as t " +
                          "WHERE e_mail =? "));
            statements.put("createClientCard_P", connection.prepareStatement("{call utworz_karte_klienta(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)}"));
            statements.put("assignClientCard_P", connection.prepareStatement("{call przypisz_karte_klienta(?)}"));
            statements.put("getAccountData", connection.prepareStatement("select kl.imie, kl.nazwisko, kl.ulica, kl.kod_pocztowy, kl.miejscowosc, kl.data_urodzenia, " +
                    "kl.telefon, kl.e_mail, kl.ilosc_punktow, kt.typ_konta from konto kt join klient kl on kt.id_konta=kl.id_konta where kl.e_mail = ?"));
            statements.put("pobierz_dzisiaj_aktualnosci_P", connection.prepareStatement("{call pobierz_dzisiaj_aktualnosci()}"));
            statements.put("getAllAccountsBasicDataWithTag", connection.prepareStatement("{call pobierz_dane_uzytkownikow_typu(?)}"));
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static HashMap<String, PreparedStatement> getStatements(){
        return statements;
    }

    public static Connection getConnection(){
        return connection;
    }
}

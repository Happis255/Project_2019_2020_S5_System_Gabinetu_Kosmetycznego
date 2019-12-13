package myPage.dataSourceDB;

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
            statements.put("getClient", connection.prepareStatement("select * from klient where id_klienta=?"));
            statements.put("createClientCard_P", connection.prepareStatement("{call utworz_karte_klienta(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)}"));
            statements.put("createClient_P", connection.prepareStatement("{call utworz_klienta(?, ?, ?, ?, ?, ?, ?, ?, ?)}"));
            statements.put("assignClientCard_P", connection.prepareStatement("{call przypisz_karte_klienta(?)}"));
            statements.put("getUser", connection.prepareStatement("SELECT t.e_mail, t.typ_konta, t.haslo, t.id " +
                    "FROM (SELECT kt.id_konta, IF(kt.typ_konta LIKE 'KLIENT' , kl.e_mail , pr.e_mail) AS e_mail, kt.typ_konta, kt.haslo, IF(kt.typ_konta LIKE 'KLIENT' , kl.id_klienta , pr.id_pracownika) AS id " +
                    "FROM konto kt LEFT JOIN klient kl ON kt.id_konta = kl.id_konta LEFT JOIN pracownik pr ON kt.id_konta = pr.id_konta) as t " +
                    "WHERE e_mail =? "));
            statements.put("getClientAccountData", connection.prepareStatement("select kt.* " +
                    "from konto kt join klient kl on kt.id_konta=kl.id_konta where kl.id_klienta = ?"));
            statements.put("getPracownikAccountData", connection.prepareStatement("select kt.* " +
                    "from konto kt join pracownik pr on kt.id_konta=pr.id_konta where pr.id_pracownika = ?"));
            statements.put("getPracownik", connection.prepareStatement("select * from pracownik where id_pracownika=?"));

            statements.put("pobierz_dzisiaj_aktualnosci_P", connection.prepareStatement("{call pobierz_dzisiaj_aktualnosci()}"));
            statements.put("getAllAccountsBasicDataWithTag", connection.prepareStatement("{call pobierz_dane_uzytkownikow_typu(?)}"));
            statements.put("createNews_P", connection.prepareStatement("{call dodaj_aktualnosc(?, ?, ?, ?, ?)}"));
            statements.put("pobierz_max_id_aktualnosci_p", connection.prepareStatement("select MAX(id_aktualnosci) as 'id_aktualnosci' from aktualnosc"));
            statements.put("pobierz_wszystkieAktualnosci_P", connection.prepareStatement("{call pokaz_aktualnosci_pracownik()}"));
            statements.put("removeNewsID_P", connection.prepareStatement("{call usun_aktualnosc(?)}"));
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

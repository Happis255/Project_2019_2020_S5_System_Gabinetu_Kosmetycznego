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
            statements.put("createWorker_p", connection.prepareStatement("{call utworz_pracownika(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)}"));
            statements.put("getPracownik", connection.prepareStatement("select * from pracownik where id_pracownika=?"));
            statements.put("getAllWorkers", connection.prepareStatement("select * from pracownik where pracownik.e_mail != '-'"));
            statements.put("usunPracownika_p", connection.prepareStatement("{call usun_pracownik(?)}"));
            statements.put("usunaKartePracownika_p", connection.prepareStatement("{call usun_ksiazeczka_zdrowia(?)}"));
            statements.put("usunKonto_p", connection.prepareStatement("{call usun_konto(?)}"));
            statements.put("setServiceWorker", connection.prepareStatement("{call nadaj_uprawnienie_uslugowe(?, ?)}"));
            statements.put("checkServiceWorker", connection.prepareStatement("select * from pracownik_usluga where pracownik_usluga.id_pracownika =? AND pracownik_usluga.id_uslugi =?"));
            statements.put("getAllAccountsBasicDataWithTag", connection.prepareStatement("{call pobierz_dane_uzytkownikow_typu(?)}"));

            /* Zarzadzanie ksiazeczka zdrowia pracownika */
            statements.put("pobierz_ksiazeczkeZdrowiaID_P", connection.prepareStatement("{call pobierz_ksiazeczke(?)}"));

            /* Zarządzanie aktualnosciami */
            statements.put("pobierz_dzisiaj_aktualnosci_P", connection.prepareStatement("{call pobierz_dzisiaj_aktualnosci()}"));
            statements.put("createNews_P", connection.prepareStatement("{call dodaj_aktualnosc(?, ?, ?, ?, ?)}"));
            statements.put("pobierz_max_id_aktualnosci_p", connection.prepareStatement("select MAX(id_aktualnosci) as 'id_aktualnosci' from aktualnosc"));
            statements.put("pobierz_wszystkieAktualnosci_P", connection.prepareStatement("{call pokaz_aktualnosci_pracownik()}"));
            statements.put("removeNewsID_P", connection.prepareStatement("{call usun_aktualnosc(?)}"));

            //statements.put("getAllEvents_P", connection.prepareStatement("{call usun_aktualnosc(?)}"));
            statements.put("createEvent_P", connection.prepareStatement("{call dodaj_wydarzenie(?, ?, ?, ?, ?, ?, ?, ?, ?, ?)}"));

            /* Zarządzanie usługą gabinetu */
            statements.put("pobierz_UslugiGabinetu_p", connection.prepareStatement("SELECT * FROM usluga WHERE usluga.opis != 'usługa nie jest wykonywana' ORDER BY usluga.typ_uslugi DESC"));
            statements.put("pobierz_max_id_uslugi_p", connection.prepareStatement("select MAX(id_uslugi) as 'id_uslugi' from usluga"));
            statements.put("removeServiceID_P", connection.prepareStatement("{call usun_usluge(?)}"));
            statements.put("createService_p", connection.prepareStatement("{call dodaj_usluge(?,?,?,?,?,?,?)}"));
            statements.put("pobierz_UslugiGabinetuDlaPracownika_p", connection.prepareStatement("SELECT usluga.* FROM usluga JOIN pracownik_usluga ON usluga.id_uslugi = pracownik_usluga.id_uslugi WHERE pracownik_usluga.id_pracownika = ? AND usluga.opis != 'usługa nie jest wykonywana'"));
            statements.put("removeAllowenceWorker_p", connection.prepareStatement("DELETE FROM pracownik_usluga WHERE id_pracownika =? and id_uslugi =?;"));

            /* Zarządzanie nieobecnosciami */
            statements.put("zglos_nieobecnosc_P", connection.prepareStatement("{call zglos_nieobecnosc(?,?,?,?,?)}"));
            statements.put("pobierz_nieobecnosci_pracownika_p", connection.prepareStatement("{call nieob_pracownika(?)}"));
            statements.put("pobierz_nieobecnosci_all_p", connection.prepareStatement("SELECT nieobecnosc.*, pracownik.* FROM nieobecnosc JOIN pracownik ON nieobecnosc.id_pracownika = pracownik.id_pracownika"));
            statements.put("removeAbsenceID_P", connection.prepareStatement("DELETE FROM nieobecnosc WHERE nieobecnosc.id_nieobecnosci = ?"));
            statements.put("declineAbsenceID_P", connection.prepareStatement("{call odrzuc_nieob(?)}"));
            statements.put("approveAbsenceID_P", connection.prepareStatement("{call potwr_nieob(?)}"));

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

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

            statements.put("getPracownikAccountData", connection.prepareStatement("select kt.* " +
                    "from konto kt join pracownik pr on kt.id_konta=pr.id_konta where pr.id_pracownika = ?"));
            statements.put("createWorker_p", connection.prepareStatement("{call utworz_pracownika(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)}"));
            statements.put("getPracownik", connection.prepareStatement("select * from pracownik where id_pracownika=?"));
            statements.put("getAllWorkers", connection.prepareStatement("select * from pracownik where pracownik.e_mail != '-'"));
            statements.put("usunPracownika_p", connection.prepareStatement("{call usun_pracownik(?)}"));
            statements.put("usunaKartePracownika_p", connection.prepareStatement("{call usun_ksiazeczka_zdrowia(?)}"));
            statements.put("usunKonto_p", connection.prepareStatement("{call usun_konto(?)}"));
            statements.put("editWorker_P", connection.prepareStatement("{call edytuj_pracownik(?,?,?,?,?,?,?,?,?)}"));
            statements.put("editWorkerBook_P", connection.prepareStatement("{call edytuj_ksiazeczke(?,?,?,?,?,?,?,?,?,?,?,?)}"));
            statements.put("setServiceWorker", connection.prepareStatement("{call nadaj_uprawnienie_uslugowe(?, ?)}"));
            statements.put("checkServiceWorker", connection.prepareStatement("select * from pracownik_usluga where pracownik_usluga.id_pracownika =? AND pracownik_usluga.id_uslugi =?"));
            statements.put("getAllAccountsBasicDataWithTag", connection.prepareStatement("{call pobierz_dane_uzytkownikow_typu(?)}"));

            /* Statusy */
            statements.put("pobierz_statusy_DB", connection.prepareStatement("SELECT * FROM status_klienta"));
            statements.put("dodaj_status_DB", connection.prepareStatement("{call utworz_status_klienta(?,?,?,?,?)}"));
            statements.put("zlicz_status_ID_DB", connection.prepareStatement("SELECT COUNT(*) as liczba FROM KLIENT WHERE id_statusu =?"));
            statements.put("edytuj_status_DB", connection.prepareStatement("{call edytuj_status(?,?,?,?,?,?)}"));
            statements.put("pobierz_statusy_ID_DB", connection.prepareStatement("SELECT * FROM status_klienta WHERE status_klienta.id_statusu = ?"));

            /* Sprzet */
            statements.put("pobierz_sprzet_gabinet_p", connection.prepareStatement("SELECT * FROM sprzet"));
            statements.put("usun_sprzet_id_p", connection.prepareStatement("{call usun_sprzet(?)}"));
            statements.put("dodaj_sprzet_p", connection.prepareStatement("{call dodaj_sprzet(?,?,?,?,?)}"));

            /* Klient */
            statements.put("getClientAccountData", connection.prepareStatement("select kt.* " +
                    "from konto kt join klient kl on kt.id_konta=kl.id_konta where kl.id_klienta = ?"));
            statements.put("pobierz_karteKlientaID_P", connection.prepareStatement("{call pobierz_karte_klienta(?)}"));
            statements.put("getClientStatusNameDB_p", connection.prepareStatement("SELECT nazwa FROM status_klienta WHERE status_klienta.id_statusu =? "));
            statements.put("removeClientDN_P", connection.prepareStatement("{call usun_klient(?)}"));
            statements.put("editClient_p", connection.prepareStatement("{call edytuj_klienta(?,?,?,?,?,?,?,?)}"));
            statements.put("editClientBook_p", connection.prepareStatement("{call edytuj_karte_klienta(?,?,?,?,?,?,?,?,?,?,?,?,?,?)}"));

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

            /*Raporty, wszystkie*/
            statements.put("pobierz_raporty_pracownika_p", connection.prepareStatement("{call pobierz_raporty_pracownika(?)}"));
            statements.put("removeRaport", connection.prepareStatement("{call usun_sprawozdanie(?)}"));
            statements.put("dodaj_raport", connection.prepareStatement("{call sprawozdanie(?, ?, ?, ?, ?)}"));
            statements.put("pobierz_raporty", connection.prepareStatement("SELECT * FROM sprawozdanie"));
            statements.put("pobierz_przeglady_pracownika_p", connection.prepareStatement("{call pobierz_przeglady_pracownika(?)}"));
            statements.put("pobierz_przeglady", connection.prepareStatement("SELECT * FROM przeglad"));
            statements.put("removeService", connection.prepareStatement("{call usun_przeglad(?)}"));
            statements.put("dodaj_przeglad", connection.prepareStatement("{call przeglad(?, ?, ?, ?, ?)}"));

            /* Zadania gospodarcze */
            statements.put("get_all_zadania_P", connection.prepareStatement("select * from zadanie_gospodarcze"));
            statements.put("get_all_zadania_pracownik_P", connection.prepareStatement("select * from zadanie_gospodarcze where zadanie_gospodarcze.id_pracownika = ?"));
            statements.put("add_zadanieGospodarcze_P", connection.prepareStatement("{call zadanie_gosp(?, ?, ?, ?, ?)}"));
            statements.put("assigneZadanie_DB_P", connection.prepareStatement("UPDATE zadanie_gospodarcze SET zadanie_gospodarcze.id_pracownika = ? WHERE zadanie_gospodarcze.id_zadania = ?"));
            statements.put("removeZadanie_P", connection.prepareStatement("DELETE FROM zadanie_gospodarcze WHERE zadanie_gospodarcze.id_zadania = ?"));

            /* Produkty */
            statements.put("get_MaxId_produkt_sprzedazowyDB_P", connection.prepareStatement("SELECT MAX(produkt_sprzedaz.id_produktu_s) as max_id FROM produkt_sprzedaz"));
            statements.put("createProductSell_P", connection.prepareStatement("{call dodaj_produkt_sprzedaz(?,?,?,?)}"));
            statements.put("setNoPromoProduct_P", connection.prepareStatement("UPDATE `produkt_sprzedaz` SET `id_promocji` = NULL WHERE `produkt_sprzedaz`.`id_produktu_s` = ?"));
            statements.put("setPromoProductSell_P", connection.prepareStatement("{call promocja_produkt(?,?)}"));
            statements.put("createProductUse_P", connection.prepareStatement("{call dodaj_produkt_uzytkowy(?,?,?,?,?)}"));
            statements.put("getAllSelling_P", connection.prepareStatement("SELECT * FROM produkt_sprzedaz ORDER BY produkt_sprzedaz.nazwa"));
            statements.put("getAllUsing_P", connection.prepareStatement("SELECT * FROM produkt_uzytkowy ORDER BY produkt_uzytkowy.nazwa"));
            statements.put("edit_sellingProductsDB_P", connection.prepareStatement("{call ile_produkt_sprzedaz(?, ?)}"));
            statements.put("edit_usingProductsDB_P", connection.prepareStatement("{call ile_produkt_uzytkowy(?, ?)}"));
            statements.put("removeUsingProductIDDB_P", connection.prepareStatement("UPDATE produkt_uzytkowy SET produkt_uzytkowy.opis = \"produkt usuniety\" WHERE produkt_uzytkowy.id_produktu = ?"));
            statements.put("removeSellingProductIDDB_P", connection.prepareStatement("UPDATE produkt_sprzedaz SET produkt_sprzedaz.opis = \"produkt usuniety\" WHERE produkt_sprzedaz.id_produktu_s = ?"));

            /* Promocje */
            statements.put("add_promoDB_P", connection.prepareStatement("{call dodaj_promocje(?,?,?,?,?,?,?)}"));
            statements.put("get_all_promoDB_P", connection.prepareStatement("SELECT * FROM promocja ORDER BY promocja.nazwa"));
            statements.put("remove_promo_IDDB_P", connection.prepareStatement("{call usun_promocje(?)}"));
            statements.put("get_promo_IDDB_P", connection.prepareStatement("SELECT * FROM promocja WHERE promocja.id_promocji = ?"));
            statements.put("get_all_promo_workerIDDB_P", connection.prepareStatement("SELECT * FROM promocja WHERE promocja.id_pracownika = ? ORDER BY promocja.nazwa"));
            statements.put("edit_promo_P", connection.prepareStatement("{call edytuj_promocje(?,?,?,?,?,?,?,?)}"));
            statements.put("check_promo_today_P", connection.prepareStatement("SELECT * FROM promocja WHERE CURRENT_DATE > promocja.data_od AND CURRENT_DATE < promocja.data_do AND promocja.id_promocji = ?"));

            /*zarzadzanie wydarzeniami*/
            statements.put("getAllEvents", connection.prepareStatement("select * from wydarzenie"));
            statements.put("zapiszPracownikaNaWydarzenie", connection.prepareStatement("INSERT INTO wydarzenie_pracownik(id_wydarzenia, id_pracownika) VALUES (?, ?); "));
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

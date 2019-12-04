package myPage.others;

import myPage.data.Aktualnosci;
import myPage.data.TypKonta;
import myPage.data.User;
import myPage.exceptions.NoResultsException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;

public class DataSource {
    protected Connection connection;
    protected HashMap<String, PreparedStatement> statements;

    public DataSource(){
        connection = DataBaseManager.getConnection();
        statements = DataBaseManager.getStatements();
    }

    public static DataSource getDataSource(TypKonta accountType){
        switch (accountType){
            case KLIENT:
                return new DataSourceClient();
            case PRACOWNIK:
                return new DataSourcePracownik();
            case ADMINISTRATOR:
                return new DataSourceAdmin();
            default:
                return new DataSource();
        }
    }

    /* Metody wykorzystywane do komunikacji z bazą danych */
    public Aktualnosci getAktualnosci() throws NoResultsException, SQLException{
        PreparedStatement exeStatement;
        ResultSet resultSet;
        Aktualnosci aktualnosc = new Aktualnosci();
        exeStatement = statements.get("pobierz_dzisiaj_aktualnosci_P");

        /* Pobieramy wynik zapytania */
        resultSet = exeStatement.executeQuery();

        /* Uzupełniamy klasę wynikami
        *  Każdy wiersz z treścią i każdy wiersz z tytulem trzeba umiescic w stosie w klasie Aktualnosc */
        resultSet.last();
        if(resultSet.getRow() == 0)
            throw new NoResultsException();
        resultSet.beforeFirst();

        while (resultSet.next()){
            aktualnosc.setID(resultSet.getInt("id_aktualnosci"));
            aktualnosc.setTytul(resultSet.getString("tytul"));
            aktualnosc.setTresc(resultSet.getString("tresc"));
        }
        return aktualnosc;
    }

    /* Metody wykorzystywane do komunikacji z bazą danych */
    public int getMaxIDAktualnosci() throws NoResultsException, SQLException{

        PreparedStatement exeStatement;
        ResultSet resultSet;
        exeStatement = statements.get("pobierz_max_id_aktualnosci_p");

        /* Pobieramy wynik zapytania */
        resultSet = exeStatement.executeQuery();

        /* Uzupełniamy klasę wynikami
         *  Każdy wiersz z treścią i każdy wiersz z tytulem trzeba umiescic w stosie w klasie Aktualnosc */
        if (resultSet.next()){
            int wynik = resultSet.getInt("id_aktualnosci");
            return wynik;
        }else{
            throw new NoResultsException();
        }
    }

    public User[] getAllAccountsBasicDataWithTagDB(TypKonta accountType)throws SQLException{
        User[] users;
        PreparedStatement exeStatement;
        ResultSet resultSet;
        String str = TypKonta.getStringVal(accountType);

        exeStatement = statements.get("getAllAccountsBasicDataWithTag");
        exeStatement.setString(1, str);
        resultSet = exeStatement.executeQuery();

        resultSet.last();
        int numOfRows = resultSet.getRow();
        users = new User[numOfRows];
        resultSet.beforeFirst();

        int i=0;
        while (resultSet.next()){
            users[i] = new User();
            users[i].setE_mail(resultSet.getString("e_mail"));
            users[i].setImie(resultSet.getString("imie"));
            users[i].setNazwisko(resultSet.getString("nazwisko"));
            users[i].setTyp_konta(resultSet.getString("typ_konta"));
            ++i;
        }

        return users;
    }

    public User getUserDB(String e_mail) throws NoResultsException, SQLException{
        PreparedStatement exeStatement;
        ResultSet resultSet;
        User user;

        exeStatement = statements.get("getUser");
        exeStatement.setString(1, e_mail);
        resultSet = exeStatement.executeQuery();

        if (resultSet.next()){
            user = new User();
            user.setE_mail(resultSet.getString("e_mail"));
            user.setTyp_konta(resultSet.getString("typ_konta"));
            user.setHaslo(resultSet.getString("haslo"));
        }else{
            throw new NoResultsException();
        }

        return user;
    }

    protected void getUser(User user, ResultSet resultSet) throws SQLException {
        user.setImie(resultSet.getString("imie"));
        user.setNazwisko(resultSet.getString("nazwisko"));
        user.setUlica(resultSet.getString("ulica"));
        user.setKod_pocztowy(resultSet.getString("kod_pocztowy"));
        user.setMiejscowosc(resultSet.getString("miejscowosc"));
        user.setData_urodzenia(DateTransformer.getJavaDate(resultSet.getDate("data_urodzenia")));
        user.setTelefon(resultSet.getInt("telefon"));
        user.setE_mail(resultSet.getString("e_mail"));
        user.setTyp_konta(resultSet.getString("typ_konta"));
    }
}

package myPage.basicObjects;
import myPage.data.dataBase.ZadanieGospodarczeData;
import myPage.dataSourceDB.DataSource;

import java.sql.SQLException;
import java.text.ParseException;
import java.util.HashMap;
import java.util.LinkedList;

public class ZadanieGospodarcze {

    private LinkedList<ZadanieGospodarczeData> zadaniaGospodarcze;
    private DataSource dataSource;

    public ZadanieGospodarcze() {
        dataSource = new DataSource();
        zadaniaGospodarcze = new LinkedList<>();
    }

    public boolean listaZadanEmpty(){
        return zadaniaGospodarcze.isEmpty();
    }

    public ZadanieGospodarczeData listaPop(){
        return zadaniaGospodarcze.pop();
    }

    /* Pobieranie zadan gospodarczych wszystkich pracownikow */
    public void getAllZadania() throws SQLException {
        this.zadaniaGospodarcze = dataSource.getAllZadania_DB();
    }

    /* Pobieranie zadan gospodarczych pracownika X */
    public void getAllZadania(int id_pracownika) throws SQLException {
        this.zadaniaGospodarcze = dataSource.getAllZadaniaID_DB(id_pracownika);
    }

    /* Dodawanie zadania gospodarczego do systemu */
    public void addNewZadanie(HashMap<String, String> parameters) throws ParseException, SQLException {
        dataSource.addNewZadanie_DB(parameters);
    }

    /* Przypisywanie zadania do pracownika o podanym ID */
    public void assigneZadanie(int id_zadania, int id_pracownika) throws SQLException {
        dataSource.assigneZadanie_DB(id_zadania, id_pracownika);
    }

    /* Usuwanie z systemu zadania gospodarczego */
    public void dropZadanie(int id_zadania) throws SQLException {
        dataSource.dropZadanie_DB(id_zadania);
    }
}

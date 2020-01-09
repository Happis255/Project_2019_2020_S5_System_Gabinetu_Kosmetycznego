package myPage.basicObjects;

import myPage.data.dataBase.SprzetData;
import myPage.dataSourceDB.DataSource;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.HashMap;
import java.util.LinkedList;

public class Sprzet {

    private LinkedList<SprzetData> sprzety;
    private DataSource dataSource;

    public Sprzet() {
        dataSource = new DataSource();
        sprzety = new LinkedList<>();
    }

    public SprzetData sprzetPop(){
        return sprzety.pop();
    }

    public boolean sprzetyListaEmpty(){
        return sprzety.isEmpty();
    }

    public void getEverything() throws SQLException {
        sprzety = dataSource.getAllMachinesDB();
    }

    public void removeId(int a) throws SQLException {
        dataSource.removeMachine(a);
    }

    public void addMachine(HashMap<String, String> parameters) throws SQLException, ParseException {
        dataSource.addMachine(parameters);
    }
}

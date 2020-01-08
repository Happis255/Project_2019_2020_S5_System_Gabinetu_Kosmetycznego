package myPage.basicObjects;

import myPage.data.dataBase.PracownikData;
import myPage.data.dataBase.PromocjaData;
import myPage.dataSourceDB.DataSource;

import java.sql.SQLException;
import java.text.ParseException;
import java.util.HashMap;
import java.util.LinkedList;

public class Promocje {

    private LinkedList<PromocjaData> promoList;
    private DataSource dataSource;

    public Promocje() {
        dataSource = new DataSource();
        promoList = new LinkedList<>();
    }

    public PromocjaData promoListaPop(){
        return promoList.pop();
    }

    public boolean promoListaEmpty(){
        return promoList.isEmpty();
    }

    public void add_promo(HashMap<String, String> parameters) throws SQLException, ParseException {
        dataSource.add_promoDB(parameters);
    }

    public void get_all_promo() throws SQLException {
        promoList = dataSource.get_all_promoDB();
    }

    public void remove_promo_ID(int a) throws SQLException {
        dataSource.remove_promo_IDDB(a);
    }

    public PromocjaData get_promo_ID(int a) throws SQLException {
        return dataSource.get_promo_IDDB(a);
    }

    public void get_all_promo_workerID(int id) throws SQLException {
        promoList = dataSource.get_all_promo_workerIDDB(id);
    }

    public void edit_promo(HashMap<String, String> parameters) throws SQLException, ParseException {
        dataSource.edit_promo(parameters);
    }

    public boolean checkPromoToday(int id) throws SQLException {
        return dataSource.checkPromoToday_DB(id);
    }
}

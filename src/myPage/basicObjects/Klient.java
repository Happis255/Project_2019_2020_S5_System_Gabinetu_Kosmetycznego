package myPage.basicObjects;

import myPage.data.dataBase.KlientData;
import myPage.data.dataBase.KontoData;

import java.sql.ResultSet;
import java.sql.SQLException;

public class Klient extends User{

    public Klient(int idKlienta){
        super(idKlienta);
    }

    @Override
    public KlientData getData() throws SQLException {
        ResultSet resultQuery = dataSource.getClientDB(id);
        return new KlientData(resultQuery);
    }

    @Override
    public KontoData getAccount() throws SQLException {
        ResultSet resultQuery = dataSource.getClientAccountDB(id);
        return new KontoData(resultQuery);
    }
}

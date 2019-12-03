package myPage.basicObjects;

import myPage.data.dataBase.KontoData;
import myPage.data.dataBase.PracownikData;

import java.sql.ResultSet;
import java.sql.SQLException;

public class Pracownik extends User{

    public Pracownik(int idPracownika){
        super(idPracownika);
    }

    @Override
    public PracownikData getData() throws SQLException {
        ResultSet resultQuery = dataSource.getPracownikDB(id);
        return new PracownikData(resultQuery);
    }

    @Override
    public KontoData getAccount() throws SQLException {
        ResultSet resultQuery = dataSource.getPracownikAccountDB(id);
        return new KontoData(resultQuery);
    }
}

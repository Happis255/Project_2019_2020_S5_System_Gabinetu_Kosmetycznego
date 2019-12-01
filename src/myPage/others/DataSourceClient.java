package myPage.others;

import myPage.data.Client;
import myPage.exceptions.DBReadWriteException;
import myPage.exceptions.NoResultsException;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DataSourceClient extends DataSource{

    public DataSourceClient(){
        super();
    }

    public void createClientDB(Client client) throws DBReadWriteException, SQLException {
        PreparedStatement exeStatement;
        int result;

        exeStatement = statements.get("createClientCard_P");
        exeStatement.setBoolean(1, client.isP_p1());
        exeStatement.setBoolean(2, client.isP_p2());
        exeStatement.setBoolean(3, client.isP_p3());
        exeStatement.setBoolean(4, client.isP_p4());
        exeStatement.setBoolean(5, client.isP_p5());
        exeStatement.setBoolean(6, client.isP_p6());
        exeStatement.setBoolean(7, client.isP_p7());
        exeStatement.setBoolean(8, client.isP_p8());
        exeStatement.setBoolean(9, client.isP_p9());
        exeStatement.setString(10, client.getP_ocena_skory());
        exeStatement.setString(11, client.getP_rodzaj_jakosc());
        exeStatement.setString(12, client.getP_wrazliwosc());
        exeStatement.setString(13, client.getP_inne_uwagi());
        result = exeStatement.executeUpdate();
        if(result != 1)
            throw new DBReadWriteException(result + " rows add with execute: createClientCard_P");

        exeStatement = statements.get("createClient_P");
        exeStatement.setString(1, client.getE_mail());
        exeStatement.setString(2, client.getHaslo());
        exeStatement.setString(3, client.getImie());
        exeStatement.setString(4, client.getNazwisko());
        exeStatement.setString(5, client.getUlica());
        exeStatement.setString(6, client.getKod_pocztowy());
        exeStatement.setString(7, client.getMiejscowosc());
        exeStatement.setDate(8, DateTransformer.getSqlDate(client.getData_urodzenia()));
        exeStatement.setInt(9, client.getTelefon());
        result = exeStatement.executeUpdate();
        if(result != 1)
            throw new DBReadWriteException(result + " rows add with execute: createClient_P");

        exeStatement = statements.get("assignClientCard_P");
        exeStatement.setString(1, client.getE_mail());
        result = exeStatement.executeUpdate();
        if(result != 1)
            throw new DBReadWriteException(result + " rows add with execute: assignClientCard_P");
    }

    public Client getClientDB(String e_mail) throws NoResultsException, SQLException{
        PreparedStatement exeStatement;
        ResultSet resultSet;
        Client client;

        exeStatement = statements.get("getAccountData");
        exeStatement.setString(1, e_mail);
        resultSet = exeStatement.executeQuery();

        if (resultSet.next()){
            client = new Client();
            super.getUser(client, resultSet);
            client.setIlosc_punktow(resultSet.getInt("ilosc_punktow"));
        }else{
            throw new NoResultsException();
        }

        return client;
    }
}

package myPage.data.dataBase;

import java.sql.ResultSet;
import java.sql.SQLException;

public abstract class Data {
    protected ResultSet resultQuery;

    Data(ResultSet resultQuery){
        this.resultQuery = resultQuery;
    }

    public int getResultLen() throws SQLException {
        int pos = resultQuery.getRow();
        resultQuery.last();
        int lenght = resultQuery.getRow();
        resultQuery.absolute(pos);
        return lenght;
    }

    public void afterLast() throws SQLException {
        resultQuery.afterLast();
    }

    public void beforeFirst() throws SQLException {
        resultQuery.beforeFirst();
    }

    public void first() throws SQLException {
        resultQuery.first();
    }

    public void last() throws SQLException {
        resultQuery.last();
    }

    public boolean next() throws SQLException {
        return resultQuery.next();
    }

    public boolean previous() throws SQLException {
        return resultQuery.previous();
    }

    public int getRow() throws SQLException {
        return resultQuery.getRow();
    }
}

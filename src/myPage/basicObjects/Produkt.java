package myPage.basicObjects;

import myPage.data.dataBase.ProduktSprzedazowyData;
import myPage.data.dataBase.ProduktUzytkowyData;
import myPage.dataSourceDB.DataSource;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.LinkedList;

public class Produkt {

    private LinkedList<ProduktSprzedazowyData> produktySprzedazoweList;
    private LinkedList<ProduktUzytkowyData> produktyUzytkoweList;
    private DataSource dataSource;

    public Produkt() {
        dataSource = new DataSource();
        produktySprzedazoweList = new LinkedList<>();
        produktyUzytkoweList = new LinkedList<>();
    }


    public ProduktSprzedazowyData produkt_sprzedazowyListaPop(){
        return produktySprzedazoweList.pop();
    }

    public boolean produkt_sprzedazowyListaEmpty(){
        return produktySprzedazoweList.isEmpty();
    }

    public ProduktUzytkowyData produkt_uzytkowyListaPop(){
        return produktyUzytkoweList.pop();
    }

    public boolean produkt_uzytkowyListaEmpty(){
        return produktyUzytkoweList.isEmpty();
    }

    public int get_MaxId_produkt_sprzedazowy() throws SQLException {
        return dataSource.get_MaxId_produkt_sprzedazowyDB();
    }

    public void add_sellingProduct(HashMap<String, String> parameters) throws SQLException {
        dataSource.createProductSell(parameters);
    }

    public void add_usedProduct(HashMap<String, String> parameters) throws SQLException {
        dataSource.createProductUse(parameters);
    }

    public void get_all_sellingProducts() throws SQLException {
        produktySprzedazoweList = dataSource.getAllSelling();
    }

    public void get_all_usingProducts() throws SQLException {
        produktyUzytkoweList = dataSource.getAllUsing();
    }

    public void edit_sellingProducts(HashMap<String, String> parameters) throws SQLException {
        dataSource.edit_sellingProductsDB(parameters);
    }

    public void edit_usingProducts(HashMap<String, String> parameters) throws SQLException {
        dataSource.edit_usingProductsDB(parameters);
    }

    public void removeUsingProductID(int a) throws SQLException {
        dataSource.removeUsingProductIDDB(a);
    }

    public void removeSellingProductID(int a) throws SQLException {
        dataSource.removeSellingProductIDDB(a);
    }
}

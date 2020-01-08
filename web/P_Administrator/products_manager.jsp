<%@ page import="myPage.basicObjects.Produkt" %>
<%@ page import="myPage.data.dataBase.ProduktSprzedazowyData" %>
<%@ page import="myPage.data.dataBase.ProduktUzytkowyData" %>
<%@ page import="java.sql.SQLException" %>
<%@ page import="myPage.basicObjects.Promocje" %>
<%@ page import="myPage.data.dataBase.PromocjaData" %>
<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>

<section id="opis" class="bg-light-gray" style="margin:0;background-color:rgba(0,0,0,0.11);color:#ffffff;padding-bottom:20px;padding-top:20px;max-width:1140px;margin-right:auto;margin-left:auto;border-radius:20px;margin-bottom:30px;">
    <h2 class="text-center" style="height:79px;">Zarządzanie produktami gabinetu</h2>
    <h5 class="text-center" style="height:21px;margin-right:50px;margin-left:50px;">Poniżej zamieszczona jest lista wszystkich produktów znajdujących się w bazie systemu.</h5>
    <h6 class="text-center" id="informacja" style="height:44px;margin-right:50px;margin-left:50px;font-weight: 100;">Zaznacz dany produkt, by go usunąć bądź wybierz daną opcję<br>w celu dodania produktu lub edycji jego ilości oraz związanej z nim promocji.</h6>
</section>

    <section id="baza_produktow" class="bg-light-gray" style="margin:0;background-color:rgba(0,0,0,0.11);color:#ffffff;padding-bottom:20px;padding-top:20px;max-width:1140px;margin-right:auto;margin-left:auto;border-radius:20px;margin-bottom:30px;">
        <form action="${pageContext.request.contextPath}/ControllerProductSellingRemover" method="post">
            <h2 class="text-center" style="height:79px;">Produkty sprzedażowe</h2>
            <table id="tablica_produktow_sprzedazowych" class="table" cellspacing="0" width="100%" style="text-align: center;margin-bottom: 0;border: 3px solid #FFFFFF;width: 98%;max-width: 98%;margin-left: 12px;background-color: transparent;border-collapse: collapse;">
            <thead>
            <tr>
                <th></th>
                <th>ID</th>
                <th>NAZWA</th>
                <th>OPIS</th>
                <th>CENA</th>
                <th>ILOŚĆ</th>
                <th>PROMOCJA</th>
            </tr>
            </thead>
            <tbody>

            <%
                Produkt produkt_sprzedazowy = new Produkt();
                ProduktSprzedazowyData odczytanyprodukt;

                Promocje promocje = new Promocje();
                PromocjaData tempPromocja = null;

                try {
                    produkt_sprzedazowy.get_all_sellingProducts();

                } catch (SQLException e) {
                    e.printStackTrace();
                }

                String promocja_nazwa = null;

                while (!produkt_sprzedazowy.produkt_sprzedazowyListaEmpty()){
                    odczytanyprodukt = produkt_sprzedazowy.produkt_sprzedazowyListaPop();

                    if (odczytanyprodukt.check_czyPromocja()){
                        /* Wyszukiwanie nazwy promocji */
                        try {
                            tempPromocja = promocje.get_promo_ID(odczytanyprodukt.getId_promocji());
                            promocja_nazwa = tempPromocja.getNazwa() + ", " + tempPromocja.getZnizka_kwotowa() + "%, " + tempPromocja.getZnizka_kwotowa() +" PLN";
                        } catch (SQLException e) {
                            e.printStackTrace();
                        }
                    } else
                        promocja_nazwa = "Produkt nie posiada promocji";

                    out.println("<tr>" +
                            "<td> <input type=\"Checkbox\" Name=\"do_usuniecia\" Value =\"" + odczytanyprodukt.getId_produktu() + "\"></td>" +
                            "<td>" + odczytanyprodukt.getId_produktu() + "</td>" +
                            "<td>" + odczytanyprodukt.getNazwa() + "</td>" +
                            "<td>" + odczytanyprodukt.getOpis() + "</td>" +
                            "<td>" + odczytanyprodukt.getCena() + " PLN</td>" +
                            "<td>" + odczytanyprodukt.getIlosc() + " szt.</td>" +
                            "<td>" + promocja_nazwa + "</td></tr>");
                }
            %>

            </tbody>
            </table>
            <div class="row">
                <a href="../ControllerAccount?page=add_selling_product"><button type="button" class="btn btn-primary float-none align-self-center" style="width:265px;margin-left: 170px;margin-top:20px;">Dodaj produkt sprzedażowy</button></a>
                <a><button type="submit" name="button1" value="Button 1" class="btn btn-primary float-none align-self-center" style="width:265px;margin-left: 17px;margin-top:20px;">Usuń produkt sprzedażowy</button></a>
                <a href="../ControllerAccount?page=edit_selling_product"><button type="button" class="btn btn-primary float-none align-self-center" style="width:265px;margin-left: 17px;margin-top:20px;">Edytuj ilość/promocję produktu</button></a>
            </div>
            </form>
    </section>

    <section id="baza_produktow_uzytkowych" class="bg-light-gray" style="margin:0;background-color:rgba(0,0,0,0.11);color:#ffffff;padding-bottom:20px;padding-top:20px;max-width:1140px;margin-right:auto;margin-left:auto;border-radius:20px;margin-bottom:30px;">
        <form action="${pageContext.request.contextPath}/ControllerProductUsingRemover" method="post">
            <h2 class="text-center" style="height:79px;">Produkty użytkowe</h2>
            <table id="tablica_uslugowych" class="table" cellspacing="0" width="100%" style="text-align: center;margin-bottom: 0;border: 3px solid #FFFFFF;width: 98%;max-width: 98%;margin-left: 12px;background-color: transparent;border-collapse: collapse;">
                <thead>
                <tr>
                    <th></th>
                    <th>ID</th>
                    <th>NAZWA</th>
                    <th>OPIS</th>
                    <th>CENA</th>
                    <th>ILOŚĆ</th>
                    <th>KOLOR</th>
                </tr>
                </thead>
                <tbody>

                <%
                    ProduktUzytkowyData odczytanyUzytkowy;
                    try {
                        produkt_sprzedazowy.get_all_usingProducts();
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }

                    while (!produkt_sprzedazowy.produkt_uzytkowyListaEmpty()){
                        odczytanyUzytkowy = produkt_sprzedazowy.produkt_uzytkowyListaPop();

                        out.println("<tr>" +
                                "<td> <input type=\"Checkbox\" Name=\"do_usuniecia\" Value =\"" + odczytanyUzytkowy.getId_produktu() + "\"></td>" +
                                "<td>" + odczytanyUzytkowy.getId_produktu() + "</td>" +
                                "<td>" + odczytanyUzytkowy.getNazwa() + "</td>" +
                                "<td>" + odczytanyUzytkowy.getOpis() + "</td>" +
                                "<td>" + odczytanyUzytkowy.getCena() + " PLN</td>" +
                                "<td>" + odczytanyUzytkowy.getIlosc() + " szt.</td>" +
                                "<td>" + odczytanyUzytkowy.getKolor() + "</td></tr>");
                    }
                %>
                </tbody>
            </table>
            <div class="row">
                <a href="../ControllerAccount?page=add_usage_product"><button type="button" class="btn btn-primary float-none align-self-center" style="width:265px;margin-left: 170px;margin-top:20px;">Dodaj produkt użytkowy</button></a>
                <a><button type="submit" name="button1" value="Button 1" class="btn btn-primary float-none align-self-center" style="width:265px;margin-left: 17px;margin-top:20px;">Usuń produkt użytkowy</button></a>
                <a href="../ControllerAccount?page=edit_usage_product"><button type="button" class="btn btn-primary float-none align-self-center" style="width:265px;margin-left: 17px;margin-top:20px;">Edytuj ilość produktu</button></a>
            </div>
        </form>
    </section>
</html>
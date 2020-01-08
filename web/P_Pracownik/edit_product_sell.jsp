<%@ page import="myPage.basicObjects.Produkt" %>
<%@ page import="myPage.data.dataBase.ProduktSprzedazowyData" %>
<%@ page import="myPage.basicObjects.Promocje" %>
<%@ page import="myPage.data.dataBase.PromocjaData" %>
<%@ page import="java.sql.SQLException" %>
<section id="edit_product_sell" class="bg-light-gray" style="margin:0;background-color:rgba(0,0,0,0.11);color:#ffffff;padding-bottom:20px;padding-top:20px;max-width:800px;margin-right:auto;margin-left:auto;border-radius:20px;margin-bottom: 24px;">
<form method="post" action="${pageContext.request.contextPath}/ControllerProductSellingEditor">
    <h2 class="text-center" style="height:79px;">Edycja produktu na sprzedaż</h2>
    <h5 class="text-center" style="height:21px;margin-right:50px;margin-left:50px;">Aby edytować wybrany produkt, wybierz go wpierw z listy.<br><br></h5>
    <h6 class="text-center" id="RODO" style="height:44px;margin-right:50px;margin-left:50px;">Następnie wprowadź odpowiednie dane i zatwierdź operację.<br> Jeśli nie chcesz zmieniać ilości produktów, wpisz ujemną liczbę</h6><br>
    <div class="form-group"><label style="font-size:17px;margin-left:101px;margin-top:19px">Wybierz produkt</label>
        <div class="form-group" style="margin-bottom:-2px;">
            <select class="form-control" name="produkt_wybor" value="produkt_wybor" required="" id="produkt_wybor" style="margin:0;margin-left:145px;width:500px;margin-bottom:16px;">
                <%
                    /* Pobieramy listę produktów gabinetu */
                    Produkt produkty_sell = new Produkt();
                    ProduktSprzedazowyData temp;

                    Promocje promocje = new Promocje();
                    PromocjaData temp2;

                    try {
                        produkty_sell.get_all_sellingProducts();
                        promocje.get_all_promo();
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }

                    while (!produkty_sell.produkt_sprzedazowyListaEmpty()) {
                        temp = produkty_sell.produkt_sprzedazowyListaPop();
                        if (temp.check_czyPromocja()){
                            try {
                                temp2 = promocje.get_promo_ID(temp.getId_promocji());
                                out.print("<option value=\"" + temp.getId_produktu() + "\">" + temp.getNazwa() + " : " + temp2.getNazwa() + "</option>");
                            } catch (SQLException e) {
                                e.printStackTrace();
                            }
                        }
                        else
                            out.print("<option value=\"" + temp.getId_produktu() + "\">" + temp.getNazwa() + " : brak promocj</option>");
                    }
                %>
            </select>
        </div>
    </div>

    <div class="form-group"><label style="font-size:17px;margin-left:101px;">Wybierz promocje</label>
        <div class="form-group" style="margin-bottom:-2px;">
            <select class="form-control" name="promocja_wybor" value="promocja_wybor" required="" id="promocja_wybor" style="margin:0;margin-left:145px;width:500px;margin-bottom:16px;">
                <%
                    out.print("<option value=\"null\">Brak promocji</option>");
                    while (!promocje.promoListaEmpty()) {
                        temp2 = promocje.promoListaPop();
                            out.print("<option value=\"" + temp2.getId_promocji() + "\">" + temp2.getNazwa() + ", " + temp2.getOpis() + "</option>");
                    }
                %>
            </select>
        </div>
    </div>
    <input class="form-control" type="text" name="ilosc" required="" placeholder="Ilość produktu na magazynie" maxlength="9" minlength="1" style="margin:0;width:500px;margin-left:145px;margin-top:15px;">
    <div class="form-group"><button class="btn btn-primary" type="submit" style="margin:0;width:265px;margin-left:267px;margin-top: 65px;">Zatwierdź zmianę</button></div>
</form>
</section>
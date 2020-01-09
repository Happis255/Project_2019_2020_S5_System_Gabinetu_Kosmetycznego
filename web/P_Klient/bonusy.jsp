<%@ page import="myPage.data.dataBase.StatusData" %>
<%@ page import="myPage.basicObjects.Status_Klienta" %>
<%@ page import="myPage.data.others.SessionData" %>
<%@ page import="myPage.basicObjects.Klient" %>
<%@ page import="myPage.data.dataBase.KlientData" %>
<!DOCTYPE html>
<html>
<% SessionData sessionData = (SessionData)session.getAttribute("userData"); %>

<section id="opis" class="bg-light-gray" style="margin:0;background-color:rgba(0,0,0,0.11);color:#ffffff;padding-bottom:20px;padding-top:20px;max-width:1140px;margin-right:auto;margin-left:auto;border-radius:20px;margin-bottom:30px;">
    <h2 class="text-center" style="height:79px;">Witaj w bonusach!</h2>
    <h5 class="text-center" style="height:21px;margin-right:50px;margin-left:50px;">W poniższej sekcji znajdują się wszystkie bonusy, które są dla Ciebie dostępne!.</h5>
    <h6 class="text-center" id="informacja" style="height:10px;margin-right:50px;margin-left:50px;font-weight: 100;">Pamiętaj, że niektóre bonusy mają termin ważności i wraz z upływem czasu mogą stać się nieaktywne.</h6>
    <h6 class="text-center" id="informacja2" style="height:44px;margin-right:50px;margin-left:50px;font-weight: 100;">Jeżeli również w chwili obecnej nie masz dostępnych bonusów - nie martw się! <br>
        Skontaktuj się z nami drogą mailową a my na pewno coś dla Ciebie przygotujemy</h6>
</section>

    <section id="promo_status_setter" class="bg-light-gray" style="margin:0;    margin-bottom: 115px;
background-color:rgba(0,0,0,0.11);color:#ffffff;padding-bottom:20px;padding-top:20px;max-width:1140px;margin-right:auto;margin-left:auto;border-radius:20px;">
        <h2 class="text-center" style="height:79px;">Twój obecny status klienta w naszym gabinecie</h2>
        <%
            Status_Klienta statusy = new Status_Klienta();
            StatusData status_zalogowanego;
            Klient zalogowany = new Klient(sessionData.getId());
            KlientData zalogowanego;

            int wynik;
                try {
                    zalogowanego = zalogowany.getData();
                    status_zalogowanego = statusy.getStatusyID(zalogowanego.getId_statusu());

                    wynik = status_zalogowanego.getPunkty_do() - zalogowanego.getIlosc_punktow() + 1;
                    out.println("<h6 class=\"text-center\" style=\"height:21px;margin-right:50px;margin-left:50px;font-weight: 100;\">" +
                            "W chwili obecnej przysługuje Ci zniżka procentowa wynosząca: " + status_zalogowanego.getZnizka_proc() +
                            " % oraz zniżka kwotowa wynosząca: " + status_zalogowanego.getZnizka_kwot() + " PLN.<br>" +
                            "Do uzyskania kolejnego statusu brakuje Ci: " + wynik + " pkt.!" +
                            "<br>" +
                            "Pamiętaj, otrzymujesz jeden punkt za każdą wydaną złotówkę w naszym gabinecie!" +
                            "<br>"
                            + "</h6><h5 class=\"text-center\" style=\"height:21px;margin-right:50px;margin-left:50px;margin-top: 84px;\">Liczba twoich punktów: " + zalogowanego.getIlosc_punktow() +
                            "</h5>");
                } catch (Exception e) {
                    e.printStackTrace();
                }
            %>
        </section>
</html>
<%@ page import="myPage.data.dataBase.KsiazeczkaZdrowiaData" %>
<%@ page import="myPage.basicObjects.Usluga" %>
<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>

    <section id="baza_pracownikow" class="bg-light-gray" style="margin:0;background-color:rgba(0,0,0,0.11);color:#ffffff;padding-bottom:20px;padding-top:20px;max-width:1140px;margin-right:auto;margin-left:auto;border-radius:20px;margin-bottom:30px;">
        <h2 class="text-center" style="height:79px;">Pracownicy</h2>
        <h5 class="text-center" style="height:21px;margin-right:50px;margin-left:50px;">Poniżej zamieszczona jest lista pracowników gabinetu oraz uprawnienia dot. wykonywanych usług.</h5>
        <h6 class="text-center" id="informacja" style="height:44px;margin-right:50px;margin-left:50px;font-weight: 100;">Zaznacz danego pracownika, by go zwolnić, bądź zaznacz dany wiersz tabeli,<br>by odebrać pracownikowi uprawnienie do wykonywania danej usługi.</h6>
    </section>

    <% /*
        Pracownik pracownicy_gabinetu = new Pracownik();
        PracownikData temp;

        KsiazeczkaZdrowia ksiazeczki_pracownikow = new KsiazeczkaZdrowiaData();
        KsiazeczkaZdrowiaData temp2;

        Usluga uslugi_Pracownikow = new Usluga();
        UslugaData temp3;

        try {
            pracownicy_gabinetu.getEverything();
        } catch (DBReadWriteException | SQLException e) {
            e.printStackTrace();
        }

        try {
            ksiazeczki_pracownikow.getEverything();
        } catch (DBReadWriteException | SQLException e) {
            e.printStackTrace();
        }

        try {
            uslugi_Pracownikow.getEverything();
        } catch (DBReadWriteException | SQLException e) {
            e.printStackTrace();
        }


        while (!pracownicy_gabinetu.uslugiEmpty()){
            temp = usluga.uslugaPop();

            out.println("<tr>" +
                    "<td> <input type=\"Checkbox\" Name=\"do_usuniecia\" Value =\"" + temp.getId_uslugi() + "\"></td>" +
                    "<td>" + temp.getId_uslugi() + "</td>" +
                    "<td>" + temp.getTyp_uslugi() + "</td>" +
                    "<td>" + temp.getNazwa() + "</td>" +
                    "<td>" + temp.getCena() + " PLN</td>" +
                    "<td>" + temp.getCzas_trwania() + " min.</td></tr>");
        } */
    %>

    <section id="pracownik" class="bg-light-gray" style="margin:0;background-color:rgba(0,0,0,0.11);color:#ffffff;padding-bottom:20px;padding-top:20px;max-width:1140px;margin-right:auto;margin-left:auto;border-radius:20px;margin-bottom:30px;">
        <h2 class="text-center">Imię i nazwisko pracownika</h2>
        <br>
        <table class="table" cellspacing="0" width="100%" style="margin-top: -18px;text-align: center;margin-bottom: 0;width: 55%;max-width: 55%;margin-left: 259px;background-color: transparent;border-collapse: collapse;">
            <tr>
                <td><h5 class="text-center" style="margin-bottom: 1px;">Dane osobowe</h5></td>
                <td><h5 class="text-center" style="margin-bottom: 1px;">Dane książeczki</h5></td>
            </tr>
            <tr>
                <td style=" text-align: center; vertical-align: middle;">
                    <h6 class="text-center" style="margin-right:50px;margin-left:50px;font-weight: 100;">ID Pracownika: </h6>
                    <h6 class="text-center" style="margin-right:50px;margin-left:50px;font-weight: 100;">Ulica: </h6>
                    <h6 class="text-center" style="margin-right:50px;margin-left:50px;font-weight: 100;">Miejscowość: </h6>
                    <h6 class="text-center" style="margin-right:50px;margin-left:50px;font-weight: 100;">Data urodzenia: </h6>
                    <h6 class="text-center" style="margin-right:50px;margin-left:50px;font-weight: 100;">Telefon: </h6>
                    <h6 class="text-center" style="margin-right:50px;margin-left:50px;font-weight: 100;">E-Mail: </h6>
                    <h6 class="text-center" style="margin-right:50px;margin-left:50px;font-weight: 100;">Pesel: </h6>
                    <h6 class="text-center" style="margin-right:50px;margin-left:50px;font-weight: 100;">Data zatrudnienia: </h6>
                    <h6 class="text-center" style="margin-right:50px;margin-left:50px;font-weight: 100;">Certyfikaty: </h6>
                </td>
                <td style=" text-align: center; vertical-align: middle;">
                    <h6 class="text-center" style="margin-right:50px;margin-left:50px;font-weight: 100;">Rozrusznik serca: </h6>
                    <h6 class="text-center" style="margin-right:50px;margin-left:50px;font-weight: 100;">Hermofilia: </h6>
                    <h6 class="text-center" style="margin-right:50px;margin-left:50px;font-weight: 100;">Łuszczyca: </h6>
                    <h6 class="text-center" style="margin-right:50px;margin-left:50px;font-weight: 100;">Alergie: </h6>
                    <h6 class="text-center" style="margin-right:50px;margin-left:50px;font-weight: 100;">Przebarwienia: </h6>
                    <h6 class="text-center" style="margin-right:50px;margin-left:50px;font-weight: 100;">Choroby zakaźne: </h6>
                    <h6 class="text-center" style="margin-right:50px;margin-left:50px;font-weight: 100;">Zaburzenia ukrwienia: </h6>
                    <h6 class="text-center" style="margin-right:50px;margin-left:50px;font-weight: 100;">Opryszczka: </h6>
                    <h6 class="text-center" style="margin-right:50px;margin-left:50px;font-weight: 100;">Gorączka: </h6>
                    <h6 class="text-center" style="margin-right:50px;margin-left:50px;font-weight: 100;">Osłabienie: </h6>
                    <h6 class="text-center" style="margin-right:50px;margin-left:50px;font-weight: 100;">Ciąża: </h6>
                </td>
            </tr>
        </table>
        <br>
        <h5 class="text-center" style="height:30px;margin-right:50px;margin-left:50px;">Uprawnienia do wykonywania usług</h5>
        <table class="table" cellspacing="0" width="100%" style="text-align: center;margin-bottom: 0;border: 3px solid #FFFFFF;width: 55%;max-width: 55%;margin-left: 259px;background-color: transparent;border-collapse: collapse;">
            <thead>
            <tr>
                <th></th>
                <th>TYP USLUGI</th>
                <th>NAZWA USLUGI</th>
            </tr>
            </thead>
            <tbody>
            <tr>
                <td> <input type="Checkbox" name="do_usuniecia" value="01"></td>
                <td>Kosmetyka twarzy</td>
                <td>Regulacja brwi</td>
            </tr>
            </tbody>
        </table>
    </section>

    <section id="przyciski" class="bg-light-gray" style="margin:0;background-color:rgba(0,0,0,0.11);color:#ffffff;padding-bottom:20px;max-width:1140px;margin-right:auto;margin-left:auto;border-radius:20px;margin-bottom:30px;">
        <div class="row">
            <a href="#"><button class="btn btn-primary float-none align-self-center" style="width:265px;margin-left: 160px;margin-top:20px;">Dodaj pracownika</button></a>
            <a href="#"><button class="btn btn-primary float-none align-self-center" style="width:265px;margin-left: 17px;margin-top:20px;">Usuń pracownika</button></a>
            <a href="#"><button class="btn btn-primary float-none align-self-center" style="width:265px;margin-left: 17px;margin-top:20px;">Nadaj uprawnienie</button></a>
        </div>
        <div class="row">
            <a><button class="btn btn-primary float-none align-self-center" style="width:265px;margin-left: 160px;margin-top:20px;">Odbierz uprawnienie</button></a>
            <a href="#"><button class="btn btn-primary float-none align-self-center" style="width:265px;margin-left: 17px;margin-top:20px;">Edytuj pracownika</button></a>
            <a href="#"><button class="btn btn-primary float-none align-self-center" style="width:265px;margin-left: 17px;margin-top:20px;">Edytuj książeczkę zdrowia</button></a>
        </div>
    </section>
</html>
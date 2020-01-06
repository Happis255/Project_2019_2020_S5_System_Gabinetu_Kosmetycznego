<%@ page import="myPage.data.dataBase.PracownikData" %>
<%@ page import="myPage.data.others.SessionData" %>
<%@ page import="java.sql.SQLException" %>
<%@ page import="myPage.basicObjects.Pracownik" %>
<%@ page import="myPage.basicObjects.KsiazeczkaZdrowia" %>
<%@ page import="myPage.data.dataBase.KsiazeczkaZdrowiaData" %>
<%@ page import="myPage.basicObjects.Usluga" %>
<%@ page import="myPage.data.dataBase.UslugaData" %>
<%@ page import="java.text.DateFormat" %>
<%@ page import="java.text.SimpleDateFormat" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<% SessionData sessionData = (SessionData)session.getAttribute("userData"); %>

<section id="baza_pracownikow" class="bg-light-gray" style="margin:0;background-color:rgba(0,0,0,0.11);color:#ffffff;padding-bottom:20px;padding-top:20px;max-width:1140px;margin-right:auto;margin-left:auto;border-radius:20px;margin-bottom:30px;">
    <h2 class="text-center" style="height:79px;">Witaj w panelu administratora</h2>
    <h5 class="text-center" style="height:21px;margin-right:50px;margin-left:50px;">Poniżej zamieszczone są informacje dotycząca twoich danych konta.</h5>
    <h6 class="text-center" id="informacja" style="height:44px;margin-right:50px;margin-left:50px;font-weight: 100;">W celu przejścia do danej funkcji,<br>wybierz odpowiednią opcję z listy powyżej.</h6>
</section>
        <%
        /* Pobieramy listę pracowników gabinetu - pracujących */
        Pracownik pracownicy_gabinetu = new Pracownik(sessionData.getId());
        PracownikData temp_pracownik = null;

        KsiazeczkaZdrowia ksiazeczka = new KsiazeczkaZdrowia();
        KsiazeczkaZdrowiaData temp_ksiazeczka;

        Usluga uslugi = new Usluga();
        UslugaData temp_usluga;

        try {
                temp_pracownik = pracownicy_gabinetu.getData();

                /* Odczytujemy ksiazeczke zdrowia pracownika oraz jego możliwe do wykonywania usługi */
                DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

                temp_ksiazeczka = ksiazeczka.getKsiazeczkaID(temp_pracownik.getId_ksiazeczki());
                /* Pracownik może wykonywać wiele usług, dlatego należy pobrać zbiór usług możliwych do wykonywania przez pracownika */
                uslugi.getUslugiPracownikaID(temp_pracownik.getId());

                /* Generujemy stronę pracownikowi */
                out.print("    <section id=\"pracownik\" class=\"bg-light-gray\" style=\"margin:0;background-color:rgba(0,0,0,0.11);color:#ffffff;padding-bottom:20px;padding-top:20px;max-width:1140px;margin-right:auto;margin-left:auto;border-radius:20px;margin-bottom:30px;\">\n" +
                        "        <h2 class=\"text-center\">" + temp_pracownik.getImie() + " " + temp_pracownik.getNazwisko() + "</h2>\n" +
                        "        <br>\n" +
                        "        <table class=\"table\" cellspacing=\"0\" width=\"100%\" style=\"margin-top: -18px;text-align: center;margin-bottom: 0;width: 55%;/* max-width: 100%; */margin-left: 16%;background-color: transparent;border-collapse: collapse;min-width: 760px;\"\">\n" +
                        "            <tr>\n" +
                        "                <td><h5 class=\"text-center\" style=\"margin-bottom: 1px;\">Dane osobowe</h5></td>\n" +
                        "                <td><h5 class=\"text-center\" style=\"margin-bottom: 1px;\">Dane książeczki</h5></td>\n" +
                        "            </tr>\n" +
                        "            <tr>\n" +
                        "                <td style=\" text-align: center; vertical-align: middle;\">\n" +
                        "                    <h6 class=\"text-center\" style=\"margin-right:50px;margin-left:50px;font-weight: 100;\">ID Pracownika: " + temp_pracownik.getId() + "</h6>\n" +
                        "                    <h6 class=\"text-center\" style=\"margin-right:50px;margin-left:50px;font-weight: 100;\">Ulica: " + temp_pracownik.getUlica() + "</h6>\n" +
                        "                    <h6 class=\"text-center\" style=\"margin-right:50px;margin-left:50px;font-weight: 100;\">Miejscowość: " + temp_pracownik.getMiejscowosc() + "</h6>\n" +
                        "                    <h6 class=\"text-center\" style=\"margin-right:50px;margin-left:50px;font-weight: 100;\">Data urodzenia: "  + dateFormat.format(temp_pracownik.getData_urodzenia()) + "</h6>\n" +
                        "                    <h6 class=\"text-center\" style=\"margin-right:50px;margin-left:50px;font-weight: 100;\">Telefon: "  + temp_pracownik.getTelefon() + "</h6>\n" +
                        "                    <h6 class=\"text-center\" style=\"margin-right:50px;margin-left:50px;font-weight: 100;\">E-Mail: "  + temp_pracownik.getE_mail() + "</h6>\n" +
                        "                    <h6 class=\"text-center\" style=\"margin-right:50px;margin-left:50px;font-weight: 100;\">Pesel: " + temp_pracownik.getPesel() + "</h6>\n" +
                        "                    <h6 class=\"text-center\" style=\"margin-right:50px;margin-left:50px;font-weight: 100;\">Data zatrudnienia: "  + dateFormat.format(temp_pracownik.getData_zatrudnienia()) + "</h6>\n" +
                        "                    <h6 class=\"text-center\" style=\"margin-right:50px;margin-left:50px;font-weight: 100;\">Certyfikaty: " + temp_pracownik.getCertyfikaty() + "</h6>\n" +
                        "                </td>\n" +
                        "                <td style=\" text-align: center; vertical-align: middle;\">\n" +
                        "                    <h6 class=\"text-center\" style=\"margin-right:50px;margin-left:50px;font-weight: 100;\">Rozrusznik serca: " + temp_ksiazeczka.toStringrozrusznik_serca() +"</h6>\n" +
                        "                    <h6 class=\"text-center\" style=\"margin-right:50px;margin-left:50px;font-weight: 100;\">Hermofilia: "+ temp_ksiazeczka.toStringHermofilia() +"</h6>\n" +
                        "                    <h6 class=\"text-center\" style=\"margin-right:50px;margin-left:50px;font-weight: 100;\">Łuszczyca: "+ temp_ksiazeczka.toStringLuszczyca() +"</h6>\n" +
                        "                    <h6 class=\"text-center\" style=\"margin-right:50px;margin-left:50px;font-weight: 100;\">Alergie: "+ temp_ksiazeczka.getAlergia() +"</h6>\n" +
                        "                    <h6 class=\"text-center\" style=\"margin-right:50px;margin-left:50px;font-weight: 100;\">Przebarwienia: "+ temp_ksiazeczka.getPrzebarwienie() +"</h6>\n" +
                        "                    <h6 class=\"text-center\" style=\"margin-right:50px;margin-left:50px;font-weight: 100;\">Choroby zakaźne: "+ temp_ksiazeczka.getChoroba_zakazna() +"</h6>\n" +
                        "                    <h6 class=\"text-center\" style=\"margin-right:50px;margin-left:50px;font-weight: 100;\">Zaburzenia ukrwienia: "+ temp_ksiazeczka.getZaburzenia_ukrwienia() +"</h6>\n" +
                        "                    <h6 class=\"text-center\" style=\"margin-right:50px;margin-left:50px;font-weight: 100;\">Opryszczka: "+ temp_ksiazeczka.getOpryszczka() +"</h6>\n" +
                        "                    <h6 class=\"text-center\" style=\"margin-right:50px;margin-left:50px;font-weight: 100;\">Gorączka: "+ temp_ksiazeczka.getGoraczka() +"</h6>\n" +
                        "                    <h6 class=\"text-center\" style=\"margin-right:50px;margin-left:50px;font-weight: 100;\">Osłabienie: "+ temp_ksiazeczka.getOslabienie() +"</h6>\n" +
                        "                    <h6 class=\"text-center\" style=\"margin-right:50px;margin-left:50px;font-weight: 100;\">Ciąża: "+ temp_ksiazeczka.toStringroCiaza() +"</h6>\n" +
                        "                </td>\n" +
                        "            </tr>\n" +
                        "        </table>\n" +
                        "        <br>\n" +
                        "        <h5 class=\"text-center\" style=\"height:30px;margin-right:50px;margin-left:50px;\">Uprawnienia do wykonywania usług</h5>\n" +
                        "        <table class=\"table\" cellspacing=\"0\" width=\"100%\" style=\"text-align: center;margin-bottom: 0;border: 3px solid #FFFFFF;width: 55%;max-width: 55%;margin-left: 259px;background-color: transparent;border-collapse: collapse;\">\n" +
                        "            <thead>\n" +
                        "            <tr>\n" +
                        "                <th></th>\n" +
                        "                <th style=\"width: 50%\">TYP USLUGI</th>\n" +
                        "                <th style=\"width: 50%\">NAZWA USLUGI</th>\n" +
                        "            </tr>\n" +
                        "            </thead>\n" +
                        "            <tbody>\n");
                         while (!uslugi.uslugiEmpty()){
                             temp_usluga = uslugi.uslugaPop();
                             out.print(
                                     "            <tr>\n" +
                                     "                <td> <input type=\"Checkbox\" name=\"do_usuniecia\" value=\"" + temp_usluga.getId_uslugi() + " " + temp_pracownik.getId() + "\"></td>\n" +
                                     "                <td>" + temp_usluga.getTyp_uslugi() +"</td>\n" +
                                     "                <td>"  + temp_usluga.getNazwa() +"</td>\n" +
                                     "            </tr>\n");
                         }
                         out.print(
                                 "            </tbody>\n" +
                                         "        </table>\n" +
                                         "    </section>");

            } catch (SQLException e) {
                e.printStackTrace();
            }
    %>
</html>

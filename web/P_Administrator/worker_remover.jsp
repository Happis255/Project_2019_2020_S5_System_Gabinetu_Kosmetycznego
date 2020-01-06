<%@ page import="myPage.basicObjects.Pracownik" %>
<%@ page import="myPage.data.dataBase.PracownikData" %>
<section id="usuwanie_pracownika" class="bg-light-gray" style="margin:0;background-color:rgba(0,0,0,0.11);color:#ffffff;padding-bottom:20px;padding-top:20px;max-width:800px;margin-right:auto;margin-left:auto;border-radius:20px;margin-top: 88px;margin-bottom:123px;">
<form method="post" action="${pageContext.request.contextPath}/ControllerWorkerRemover">
    <h2 class="text-center" style="height:79px;">Zwalnianie pracownika</h2>
    <h5 class="text-center" style="height:21px;margin-right:50px;margin-left:50px;">Aby usunąć pracownika z systemu, wybierz odpowiedniego pracownika.<br><br></h5>
    <h6 class="text-center" id="RODO" style="height:44px;margin-right:50px;margin-left:50px;">Następnie zatwierdź usunięcie pracownika.<br>Pamiętaj - nie będzie można przywrócić pracownika.</h6><br>
    <div class="form-group"><label style="font-size:17px;margin-left:101px;">Wybierz pracownika</label>

        <div class="form-group" style="margin-bottom:-2px;">
            <select class="form-control" name="pracownik_usun" value="pracownik_usun" required="" id="pracownik_usun" style="margin:0;margin-left:145px;width:500px;margin-bottom:16px;">
                <%
                    /* Pobieramy listę pracowników gabinetu - pracujących */
                    Pracownik pracownicy_gabinetu = new Pracownik();
                    PracownikData temp_pracownik;

                    pracownicy_gabinetu.getAllData();

                    while (!pracownicy_gabinetu.emptyPracownik()) {
                        temp_pracownik = pracownicy_gabinetu.popPracownik();
                        out.print("<option value=\"" + temp_pracownik.getId() + "\">" + temp_pracownik.getNazwisko() + " " + temp_pracownik.getImie() + "</option>");
                    }
                %>
            </select>
        </div>
    </div>

    <div class="form-group"><button class="btn btn-primary" type="submit" style="margin:0;width:265px;margin-left:267px;margin-top: 26px;">Zwolnij pracownika</button></div>
</form>
</section>
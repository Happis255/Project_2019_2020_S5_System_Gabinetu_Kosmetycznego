<%@ page import="myPage.data.dataBase.PracownikData" %>
<%@ page import="myPage.basicObjects.Pracownik" %>

<section id="edycja_pracownika" class="bg-light-gray" style="margin:0;background-color:rgba(0,0,0,0.11);color:#ffffff;padding-bottom:20px;padding-top:20px;max-width:800px;margin-right:auto;margin-left:auto;border-radius:20px;margin-bottom:30px;">
<form method="post" action="${pageContext.request.contextPath}/ControllerWorkerBookEditor">
    <h2 class="text-center" style="height:79px;">Edycja danych książeczki zdrowia pracownika</h2>
    <h5 class="text-center" style="height:21px;margin-right:50px;margin-left:50px;">Aby edytować dane książeczki, wypełnij poniższe pola.<br><br></h5>
    <h6 class="text-center" id="RODO" style="height:44px;margin-right:50px;margin-left:50px;">Zapoznaj się również z &nbsp;podstawowymi informacjami o przetwarzaniu&nbsp;<br>danych osobowych -&nbsp;<a href="../P_User/rodo_info.jsp">(przeczytaj)</a><br></h6>
    <div class="form-group"><label style="font-size:17px;margin-left:101px;">Wybierz pracownika</label>
        <div class="form-group" style="margin-bottom:-2px;">
            <select class="form-control" name="ksiazeczka_id" required="" id="ksiazeczka_id" style="margin:0;margin-left:145px;width:500px;margin-bottom:16px;">
                <%
                    /* Pobieramy listę pracowników gabinetu - pracujących */
                    Pracownik pracownicy_gabinetu = new Pracownik();
                    PracownikData temp_pracownik;

                    pracownicy_gabinetu.getAllData();

                    while (!pracownicy_gabinetu.emptyPracownik()) {
                        temp_pracownik = pracownicy_gabinetu.popPracownik();
                        out.print("<option value=\"" + temp_pracownik.getId_ksiazeczki() + "\">" + temp_pracownik.getNazwisko() + " " + temp_pracownik.getImie() + "</option>");
                    }
                %>
            </select>
        </div>
    </div>
    <div class="form-group">
        <h5 class="text-center" style="height:21px;margin-right:50px;margin-left:50px;">Dane książeczki zdrowia</h5>
        <div class="form-group" style="margin-bottom:-2px;"><label style="margin-top: 16px;font-size:17px;margin-left:101px;">Czy posiadasz rozrusznik serca?</label>
            <select class="form-control" name="czy_rozrusznik" value="czy_rozrusznik" required="" id="czy_rozrusznik" style="margin:0;margin-left:145px;width:500px;margin-bottom:16px;">
                <option value="false">Nie</option>
                <option value="true">Tak</option>
            </select>
        </div>
        <div class="form-group" style="margin-bottom:-2px;"><label style="margin-top: 16px;font-size:17px;margin-left:101px;">Czy posiadasz hermofilię?</label>
            <select class="form-control" name="czy_hermofilia" value="czy_hermofilia" required="" id="czy_hermofilia" style="margin:0;margin-left:145px;width:500px;margin-bottom:16px;">
                <option value="false">Nie</option>
                <option value="true">Tak</option>
            </select>
        </div>
        <div class="form-group" style="margin-bottom:-2px;"><label style="margin-top: 16px;font-size:17px;margin-left:101px;">Czy posiadasz łuszczycę?</label>
            <select class="form-control" name="czy_luszczyca" value="czy_luszczyca" required="" id="czy_luszczyca" style="margin:0;margin-left:145px;width:500px;margin-bottom:16px;">
                <option value="false">Nie</option>
                <option value="true">Tak</option>
            </select>
        </div>
        <div class="form-group">
            <label style="margin-top: 16px;font-size:17px;margin-left:101px;">Opisz: </label>
            <input class="form-control" type="text" name="alergia" placeholder="Czy posiadasz alergie?" maxlength="255" minlength="2" style="width:500px;margin:0;margin-left:145px;"></div>
        <div class="form-group">
            <input class="form-control" type="text" name="przebarwienia" placeholder="Czy posiadasz przebarwienia skóry?" maxlength="255" minlength="2" style="width:500px;margin:0;margin-left:145px;"></div>
        <div class="form-group">
            <input class="form-control" type="text" name="choroby" placeholder="Czy chorujesz na choroby zakaźne?" maxlength="255" minlength="2" style="width:500px;margin:0;margin-left:145px;"></div>
        <div class="form-group">
            <input class="form-control" type="text" name="ukrwienie" placeholder="Czy posiadasz problemy z ukrwieniem?" maxlength="255" minlength="2" style="width:500px;margin:0;margin-left:145px;"></div>
        <div class="form-group">
            <input class="form-control" type="text" name="opryszczka" placeholder="Czy masz opryszczkę?" maxlength="255" minlength="2" style="width:500px;margin:0;margin-left:145px;"></div>
        <div class="form-group">
            <input class="form-control" type="text" name="goraczka" placeholder="Czy posiadasz częste napady gorączki?" maxlength="255" minlength="2" style="width:500px;margin:0;margin-left:145px;"></div>
        <div class="form-group">
            <input class="form-control" type="text" name="oslabienie" placeholder="Czy posiadasz osłabienia?" maxlength="255" minlength="2" style="width:500px;margin:0;margin-left:145px;"></div>
        <div class="form-group" style="margin-bottom:-2px;"><label style="margin-top: 16px;font-size:17px;margin-left:101px;">Czy jesteś w ciąży?</label>
            <select class="form-control" name="czy_ciaza" value="czy_ciaza" required="" id="czy_ciaza" style="margin:0;margin-left:145px;width:500px;margin-bottom:16px;">
                <option value="false">Nie</option>
                <option value="true">Tak</option>
            </select>
        </div>
    </div>
    <div class="form-group"><button class="btn btn-primary" type="submit" style="margin:0;width:265px;margin-left:267px;">Zatwierdź zmiany</button></div>
</form>
</section>
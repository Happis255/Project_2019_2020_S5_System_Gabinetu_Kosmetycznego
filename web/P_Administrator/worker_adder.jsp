
<script language='javascript' type='text/javascript'>
    function checkPasswordMatch() {
        var a = $("#haslo").val();
        var b = $("#powtorz-haslo").val();

        if (a === b)
            $("#divCheckPasswordMatch").html("Wpisane hasła są poprawne.");
        else
            $("#divCheckPasswordMatch").html("Wpisane hasła się nie zgadzają!");
    }

    $(document).ready(function () {
        $("#haslo, #powtorz-haslo").keyup(checkPasswordMatch);
    });

</script>

<section id="rejestracja" class="bg-light-gray" style="margin:0;background-color:rgba(0,0,0,0.11);color:#ffffff;padding-bottom:20px;padding-top:20px;max-width:800px;margin-right:auto;margin-left:auto;border-radius:20px;margin-bottom:30px;">
<form method="post" action="${pageContext.request.contextPath}/ControllerWorkerCreator">
    <h2 class="text-center" style="height:79px;">Rejestracja pracownika</h2>
    <h5 class="text-center" style="height:21px;margin-right:50px;margin-left:50px;">Aby założyć konto, wypełnij poniższe pola.<br><br></h5>
    <h6 class="text-center" id="RODO" style="height:44px;margin-right:50px;margin-left:50px;">Zapoznaj się również z &nbsp;podstawowymi informacjami o przetwarzaniu&nbsp;<br>Twoich danych osobowych -&nbsp;<a href="../P_User/rodo_info.jsp">(przeczytaj)</a><br></h6>
    <div class="form-group"><label style="font-size:17px;margin-left:101px;">Twoje dane</label>
        <input class="form-control" type="text" name="imie" required="" placeholder="Imię" maxlength="255" minlength="2" style="margin:0;width:500px;margin-left:145px;">
        <input class="form-control" type="text" name="nazwisko" required="" placeholder="Nazwisko" maxlength="255" minlength="2" style="margin:0;width:500px;margin-left:145px;margin-top:15px;">
        <input class="form-control" type="date" name="data-urodzenia" required="" style="margin:0;width:500px;margin-left:145px;margin-top:15px;" requiredplaceholder="Data urodzenia"></div>
        <input class="form-control" type="text" name="pesel" required="" placeholder="Pesel" maxlength="13" minlength="2" style="margin:0;width:500px;margin-left:145px;margin-top:15px;">
        <input class="form-control" type="text" name="certyfikaty" required="" placeholder="Opisz certyfikaty" maxlength="5000" minlength="2" style="margin:0;width:500px;margin-left:145px;margin-top:15px;">
    <div class="form-group"><label style="margin-top: 14px;font-size:17px;margin-left:101px;">Dane kontaktowe</label>
        <input class="form-control" type="text" name="e_mail" required="" placeholder="E-Mail" maxlength="255" style="margin:0;width:500px;margin-left:145px;"></div>
    <div class="form-group">
        <input class="form-control" type="tel" name="telefon" placeholder="Numer telefonu" maxlength="9" minlength="6" style="width:500px;margin:0;margin-left:145px;"></div>

    <div class="form-group"><label style="font-size:17px;margin-left:101px;">Adres zamieszkania</label>
        <input class="form-control" type="text" name="ulica" placeholder="Ulica Miesz/Nr.Ul" maxlength="255" minlength="2" style="width:500px;margin:0;margin-left:145px;"></div>
    <div class="form-group">
        <input class="form-control" type="text" name="kod_pocztowy" placeholder="Kod pocztowy" maxlength="10" minlength="2" style="width:500px;margin:0;margin-left:145px;"></div>
    <div class="form-group">
        <input class="form-control" type="text" name="miejscowosc" placeholder="Miejscowosc" maxlength="255" minlength="2" style="width:500px;margin:0;margin-left:145px;"></div>

    <div class="form-group"><label style="font-size:17px;margin-left:101px;">Hasło dla twojego konta</label>
        <input class="form-control" required="" type="password" name="haslo" placeholder="Hasło" style="width:500px;margin:0;margin-left:145px;margin-bottom:15px;">
        <input onChange="checkPasswordMatch(this);" required="" class="form-control" type="password" name="powtorz-haslo" placeholder="Powtórz hasło" style="width:500px;margin:0;margin-left:145px;margin-bottom:15px;">
        <div class="registrationFormAlert" id="divCheckPasswordMatch" style="height:82px;margin:0px;margin-right:50px;margin-left:50px;margin-bottom:-38px;font-weight: 300;text-align: center!important;">
            Oba hasła muszą być identyczne.
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
    <div class="form-group"><button class="btn btn-primary" type="submit" style="margin:0;width:265px;margin-left:267px;">Utwórz pracownika</button></div>
</form>
</section>
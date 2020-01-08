
<section id="dodawanie_wydarzenia" class="bg-light-gray" style="margin:0;background-color:rgba(0,0,0,0.11);color:#ffffff;padding-bottom:20px;padding-top:20px;max-width:800px;margin-right:auto;margin-left:auto;border-radius:20px;margin-bottom:30px;">
<form method="post" action="${pageContext.request.contextPath}/ControllerEventCreator">
    <h2 class="text-center" style="height:79px;">Dodawanie wydarzenia</h2>
    <h5 class="text-center" style="height:21px;margin-right:50px;margin-left:50px;">Aby dodać wydarzenie, wypełnij poniższe pola.<br><br></h5>
    <div class="form-group"><label style="font-size:17px;margin-left:101px;margin-top: 20px;">Dane wydarzenia</label>
        <select class="form-control" name="typ" value="typ" required="" id="typ" style="margin:0;margin-left:145px;width:500px;margin-bottom:16px;">
            <option value="KONGRES">Kongres</option>
            <option value="SZKOLENIE">Szkolenie</option>
            <option value="TARGI_KOSMETYCZNE">Targi kosmetyczne</option>
        </select>
        <input class="form-control" type="text" name="nazwa" required="" placeholder="Nazwa wydarzenia" style="margin:0;width:500px;margin-left:145px;margin-top:15px;">
        <input class="form-control" type="text" name="opis" required="" placeholder="Opis wydarzenia" style="margin:0;width:500px;margin-left:145px;margin-top:15px;">
        <input class="form-control" type="text" name="miejscowosc" required="" placeholder="Miejscowość" style="margin:0;width:500px;margin-left:145px;margin-top:15px;">
        <input class="form-control" type="text" name="kod pocztowy" required="" placeholder="Kod pocztowy" style="margin:0;width:500px;margin-left:145px;margin-top:15px;">
        <input class="form-control" type="text" name="ulica" required="" placeholder="Ulica" style="margin:0;width:500px;margin-left:145px;margin-top:15px;margin-bottom: 16px;">
        <div class="form-group"><label style="font-size:17px;margin-left:101px;">Data rozpoczęcia wydarzenia</label>
        <input class="form-control" type="date" name="dataOd" required="" style="margin:0;width:500px;margin-left:145px;margin-bottom: 16px;">
            <div class="form-group"><label style="font-size:17px;margin-left:101px;">Data zakończenia wydarzenia</label>
        <input class="form-control" type="date" name="dataDo" required="" style="margin:0;width:500px;margin-left:145px;margin-bottom: 16px">
        <input class="form-control" type="text" name="koszt" required="" placeholder="Koszt wydarzenia" style="margin:0;width:500px;margin-left:145px;margin-top:15px;">
    </div>
    <div class="form-group"><button class="btn btn-primary" type="submit" style="margin:0;width:265px;margin-left:267px;">Utwórz wydarzenie</button></div>
</form>
</section>
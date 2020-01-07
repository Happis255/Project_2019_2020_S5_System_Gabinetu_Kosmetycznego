
<section id="status_edder" class="bg-light-gray" style="margin:0;background-color:rgba(0,0,0,0.11);color:#ffffff;padding-bottom:20px;padding-top:20px;max-width:800px;margin-right:auto;margin-left:auto;border-radius:20px;margin-bottom:30px;">
    <form method="post" action="${pageContext.request.contextPath}/ControllerStatusAdder">
        <h2 class="text-center" style="height:79px;">Dodawanie nowego statusu</h2>
        <h5 class="text-center" style="height:21px;margin-right:50px;margin-left:50px;margin-bottom: 43px;">Aby dodać nowe dane statusu, wypełnij poniższe pola.<br><br></h5>
        <div class="form-group"><label style="font-size:17px;margin-left:101px;">Dane statusu</label>
            <input class="form-control" type="text" name="nazwa" required="" placeholder="Nowa nazwa statusu" maxlength="255" minlength="1" style="margin:0;width:500px;margin-left:145px;">
            <input class="form-control" type="text" name="punkty_od" required="" placeholder="Punkty od" maxlength="9" minlength="1" style="margin:0;width:500px;margin-left:145px;margin-top:15px;">
            <input class="form-control" type="text" name="punkty_do" required="" placeholder="Punkty do" maxlength="9" minlength="1" style="margin:0;width:500px;margin-left:145px;margin-top:15px;">
            <input class="form-control" type="text" name="znizka_proc" required="" placeholder="Zniażka procentowa" maxlength="2" minlength="1" style="margin:0;width:500px;margin-left:145px;margin-top:15px;">
            <input class="form-control" type="text" name="znizka_kwot" required="" placeholder="Zniżka kwotowa" maxlength="9" minlength="1" style="margin:0;width:500px;margin-left:145px;margin-top:15px;">
            <div class="form-group"><button class="btn btn-primary" type="submit" style="margin:0;width:265px;margin-left:267px;margin-top: 20px;">Zatwierdź zmiany</button></div>
        </div>
    </form>
</section>
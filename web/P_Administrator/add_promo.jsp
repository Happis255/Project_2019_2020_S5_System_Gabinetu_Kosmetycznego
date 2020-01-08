<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
    <section id="add_absence" class="bg-light-gray" style="margin:0;background-color:rgba(0,0,0,0.11);color:#ffffff;padding-bottom:20px;padding-top:20px;max-width:800px;margin-right:auto;margin-left:auto;border-radius:20px;margin-bottom:30px;">
        <form action="${pageContext.request.contextPath}/ControllerAddPromo" method="post">
            <h2 class="text-center" style="height:79px;">Dodaj promocję</h2>
            <h5 class="text-center" style="height:21px;margin-right:50px;margin-left:50px;">Wprowadź dane dotyczące dodawanej promocji.</h5>
            <div class="form-group">
                <input class="form-control" type="text" name="nazwa" required="" placeholder="Nazwa promocji" maxlength="255" minlength="1" style="margin:0;width:500px;margin-left:145px;margin-top: 55px;">
                <input class="form-control" type="text" name="opis" required="" placeholder="Opis promocji" maxlength="255" minlength="1" style="margin:0;width:500px;margin-left:145px;margin-top: 15px;">
                <input class="form-control" type="text" name="znizka_proc" required="" placeholder="Wartość zniżki procentowej" maxlength="2" minlength="1" style="margin:0;width:500px;margin-left:145px;margin-top: 15px;">
                <input class="form-control" type="text" name="znizka_kwot" required="" placeholder="Wartość zniżki kwotowej" maxlength="9" minlength="1" style="margin:0;width:500px;margin-left:145px;margin-top: 15px;">
                <label style="font-size:17px;margin-left:101px;margin-top:17px;">Data rozpoczęcia promocji</label>
                <input class="form-control" type="date" name="data_od" required="" style="margin:0;width:500px;margin-left:145px;margin-top:-7px;">
                <label style="font-size:17px;margin-left:101px;margin-top:17px;">Data upływu promocji</label>
                <input class="form-control" type="date" name="data_do" required="" style="margin:0;width:500px;margin-left:145px;margin-top:-7px;">
                <div class="form-group"><button class="btn btn-primary" type="submit" style="margin:0;width:265px;margin-left:267px;margin-top: 59px;">Dodaj promocję</button></div>
            </div>
        </form>
    </section>
</html>
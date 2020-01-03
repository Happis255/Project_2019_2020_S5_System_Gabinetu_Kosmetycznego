<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
    <section id="add_service" class="bg-light-gray" style="margin:0;background-color:rgba(0,0,0,0.11);color:#ffffff;padding-bottom:20px;padding-top:20px;max-width:800px;margin-right:auto;margin-left:auto;border-radius:20px;margin-bottom:30px;">
        <form action="${pageContext.request.contextPath}/AddService" method="post">
            <h2 class="text-center" style="height:79px;">Dodaj usługę</h2>
            <h5 class="text-center" style="height:21px;margin-right:50px;margin-left:50px;">Wprowadź dane usługi a następnie zatwierdź dane.</h5>
            <h6 class="text-center" id="Informacja_Upload" style="font-weight:300;height:44px;margin-right:50px;margin-left:50px;">Pamiętaj, by wypełnić dane prawidłowo.  W przypadku chęci zmiany danych<br>
               zatwierdzonej usługi należy ją usunąć, a następnie dodać ponownie.</h6>
            <div class="form-group">
                <input class="form-control" type="text" name="typ_uslugi" required="" placeholder="Typ usługi" maxlength="255" minlength="10" style="margin:0;width:500px;margin-left:145px;">
                <input class="form-control" type="text" name="nazwa" required="" placeholder="Nazwa usługi" maxlength="255" minlength="10" style="margin:0;width:500px;margin-left:145px;margin-top:15px;">
                <input class="form-control" type="text" name="opis" required="" placeholder="Opis usługi" maxlength="5000" minlength="1" style="margin:0;width:500px;margin-left:145px;margin-top:15px;">
                <input class="form-control" type="text" name="cena" required="" placeholder="Cena usługi" maxlength="10" minlength="1" style="margin:0;width:500px;margin-left:145px;margin-top:15px;">
                <input class="form-control" type="text" name="czas_trwania" required="" placeholder="Czas trwania usługi" maxlength="10" minlength="1" style="margin:0;width:500px;margin-left:145px;margin-top:15px;">
                <div class="form-group" style="margin-bottom:-2px;"><label style="margin-top: 16px;font-size:17px;margin-left:101px;">Czy usługa wymaga karty klienta?</label><select class="form-control" name="czy_karta" value="czy_karta" required="" id="czy_karta" style="margin:0;margin-left:145px;width:500px;margin-bottom:16px;"><option value="false">Nie</option><option value="true">Tak</option></select></div>
                <input class="form-control" type="text" name="wskazowka" required="" placeholder="Wskazówka dotycząca usługi" maxlength="5000" style="margin:0;width:500px;margin-left:145px;margin-top:15px;">
                <input class="form-control" type="text" name="id_uprawnienia" required="" placeholder="ID uprawnienia do wykonania usługi" maxlength="255" style="margin:0;width:500px;margin-left:145px;margin-top:15px;">
                <div class="form-group"><button class="btn btn-primary" type="submit" style="margin:0;margin-top: 16px;width:265px;margin-left:267px;">Dodaj</button></div>
            </div>
        </form>
    </section>
</html>
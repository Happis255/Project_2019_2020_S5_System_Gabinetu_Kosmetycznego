<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<section id="add_waste" class="bg-light-gray" style="margin:0;background-color:rgba(0,0,0,0.11);color:#ffffff;padding-bottom:20px;padding-top:20px;max-width:800px;margin-right:auto;margin-left:auto;border-radius:20px;margin-bottom:30px;">
    <form action="${pageContext.request.contextPath}/AddWaste" method="post">
        <h2 class="text-center" style="height:79px;">Dodaj Raport Odpadów</h2>
        <h5 class="text-center" style="height:21px;margin-right:50px;margin-left:50px;">Wprowadź dane dotyczące raportu.</h5>
        <h6 class="text-center" id="Informacja_Upload" style="font-weight:300;height:44px;margin-right:50px;margin-left:50px;">Pamiętaj, by sprawdzić poprawność wprowadzonych danych!</h6>
        <div class="form-group">
            <label style="font-size:17px;margin-left:101px;margin-top:17px;">Tytuł</label>
            <input class="form-control" type="text" name="tytul_raportu" required="" placeholder="Tytuł raportu" maxlength="255" minlength="5" style="margin:0;width:500px;margin-left:145px;margin-top: 0px;">
            <div class="form-group" style="margin-bottom:-2px;"><label style="margin-top: 16px;font-size:17px;margin-left:101px;">Typ odpadów</label>
                <select class="form-control" name="typ_odpadow" value="typ_odpadow" required="" id="typ_odpadow" style="margin:0;margin-left:145px;width:500px;margin-bottom:16px;">
                    <option value="150110">150110</option>
                    <option value="150107">150107</option>
                    <option value="150102">150102</option>
                    <option value="160214">160214</option>
                    <option value="180103">180103</option>
                </select>
            </div>
            <input class="form-control" type="text" name="ilos" required="" placeholder="Ilość" maxlength="255" minlength="1" style="margin:0;width:500px;margin-left:145px;margin-top: 15px;">
            <input class="form-control" type="text" name="koszt" required="" placeholder="Koszt" maxlength="255" minlength="1" style="margin:0;width:500px;margin-left:145px;margin-top: 15px;">
            <div class="form-group"><button class="btn btn-primary" type="submit" style="margin:0;width:265px;margin-left:267px;margin-top: 59px;">Zgłoś</button></div>
        </div>
    </form>
</section>
</html>
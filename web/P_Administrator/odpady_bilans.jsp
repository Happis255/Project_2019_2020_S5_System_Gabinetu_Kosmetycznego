<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<section id="bilans" class="bg-light-gray" style="margin:0;background-color:rgba(0,0,0,0.11);color:#ffffff;padding-bottom:20px;padding-top:20px;max-width:800px;margin-right:auto;margin-left:auto;border-radius:20px;margin-bottom:30px;">
    <form action="${pageContext.request.contextPath}/Bilans" method="post">
        <h2 class="text-center" style="height:79px;">Oblicz bilans utylizacji</h2>
        <h5 class="text-center" style="height:21px;margin-right:50px;margin-left:50px;">Podaj przedział czasowy, dla którego chcesz policzyć bilans.</h5>
        <h6 class="text-center" id="Informacja_Upload" style="font-weight:300;height:44px;margin-right:50px;margin-left:50px;">Pamiętaj aby sprawdzić poprawność wprowadzonych danych!</h6>
        <div class="form-group">
            <label style="font-size:17px;margin-left:101px;margin-top:17px;">Data od</label>
            <input class="form-control" type="date" name="data_od" required="" style="margin:0;width:500px;margin-left:145px;margin-top:-7px;">
            <label style="font-size:17px;margin-left:101px;margin-top:17px;">Data od</label>
            <input class="form-control" type="date" name="data_do" required="" style="margin:0;width:500px;margin-left:145px;margin-top:-7px;">
            <div class="form-group"><button class="btn btn-primary" type="submit" style="margin:0;width:265px;margin-left:267px;margin-top: 59px;">Zgłoś</button></div>
        </div>
    </form>
</section>
</html>
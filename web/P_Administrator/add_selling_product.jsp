<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
    <section id="upload_selling_product" class="bg-light-gray" style="margin:0;background-color:rgba(0,0,0,0.11);color:#ffffff;padding-bottom:20px;padding-top:20px;max-width:800px;margin-right:auto;margin-left:auto;border-radius:20px;margin-bottom:30px;">
        <form action="${pageContext.request.contextPath}/ControllerUploadSellingProductWithJPG" method="post" enctype="multipart/form-data">
            <h2 class="text-center" style="height:79px;">Dodaj produkt na sprzedaż</h2>
            <h5 class="text-center" style="height:21px;margin-right:50px;margin-left:50px;">Wprowadź dane dotyczące produktu oraz dodaj grafikę.</h5>
            <h6 class="text-center" id="Informacja_Upload" style="font-weight:300;height:44px;margin-right:50px;margin-left:50px;">Uploadowana grafika nie powinna przekraczać rozmiaru 50 MB<br>
                oraz posiadać format JPEG, JPG bądź JPE.</h6>
            <div class="form-group">
                <input class="form-control" type="text" name="nazwa" required="" placeholder="Nazwa produktu" maxlength="255" minlength="1" style="margin:0;width:500px;margin-left:145px;margin-top:55px;">
                <input class="form-control" type="text" name="opis" required="" placeholder="Opis produktu" maxlength="5000" minlength="1" style="margin:0;width:500px;margin-left:145px;margin-top:15px;">
                <input class="form-control" type="text" name="cena" required="" placeholder="Cena produktu" maxlength="255" minlength="1" style="margin:0;width:500px;margin-left:145px;margin-top: 15px;">
                <input class="form-control" type="text" name="ilosc" required="" placeholder="Ilość produktu na magazynie" maxlength="255" minlength="1" style="margin:0;width:500px;margin-left:145px;margin-top: 15px;">
                <div class="form-group"><input type="file" name="file" id="fileToUpload" required="" style="margin-top:12px;margin:0px;margin-left:146px;margin-top: 15px;" accept="image/jpeg"></div>
                <div class="form-group"><button class="btn btn-primary" type="submit" style="margin:0;width:265px;margin-left:267px;">Dodaj produkt</button></div>
            </div>
        </form>
    </section>
</html>
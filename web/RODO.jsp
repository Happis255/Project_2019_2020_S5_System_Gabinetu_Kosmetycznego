<%--
  Created by IntelliJ IDEA.
  User: ppisk
  Date: 24.11.2019
  Time: 14:11
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<!DOCTYPE html>
<html>

<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Gabinet Gracja</title>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/4.0.0/css/bootstrap.min.css">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/ionicons/2.0.1/css/ionicons.min.css">
    <link rel="stylesheet" href="assets/css/styles.min.css?v=1.1">
    <script src="https://code.jquery.com/jquery-1.10.2.js"></script>
</head>

<body>

<!--Nabar bar-->
<div id="navbar-placeholder">

</div>

<script>
    $(function(){
        $("#navbar-placeholder").load("navbar.jsp");
    });
</script>
<!--end of navbar bar-->

<script>
    function goBack() {
        window.history.back();
    }
</script>

    <section id="RODO" class="bg-light-gray" style="margin:0;background-color:rgba(0,0,0,0.11);color:#ffffff;padding-bottom:27px;padding-top:-73px;max-width:800px;margin-right:auto;margin-left:auto;border-radius:20px;margin-bottom:56px;">
        <div>
            <h3 class="text-center" style="height:38px;margin-bottom:63px;"><br>Klauzula zgody na przetwarzanie<br>danych osobowych</strong><br><br></h3>
            <h6 class="text-center" style="font-weight: 100;height:auto;margin-right:50px;margin-left:50px;"><br><br>Od&nbsp;25.05.2018r.&nbsp;obowiązuje europejskie rozporządzenie o ochronie&nbsp;<br>danych osobowych zwane&nbsp;RODO. W związku z powyższym zgodnie&nbsp;<br>z art. 13 ust. 1 i ust. 2 ogólnego rozporządzenia o ochronie danych&nbsp;<br>osobowych
                z dnia 27 kwietnia 2016 r. informuję, iż:<br><br>- Administratorem danych osobowych jest&nbsp;<br>Gabinet Kosmetyczny "Gracja" Anna Wąsik&nbsp;<br>z siedzibą w Stalowej Woli przy Al. Jana Pawła II 13a/2.<br><br>- Dane osobowe przetwarzane
                będą w celu wyznaczenia terminu&nbsp;<br>wizyty oraz realizacji usługi kosmetycznej*.<br><br>&nbsp; &nbsp; - Dane osobowe nie będą przekazywane do państwa trzeciego/organizacji.<br><br></h6>
            <h6 class="text-center" style="font-weight: 100;height:auto;margin-right:50px;margin-left:50px;margin-bottom:35px;">Ponadto informuję że ma Pan/Pani prawo do:<br><br>- Dostępu do treści swoich danych oraz prawo ich sprostowania,&nbsp;<br>usunięcia, ograniczenia przetwarzania, prawo do przenoszenia danych,&nbsp;<br>prawo wniesienia sprzeciwu, prawo do cofnięcia
                zgody<br>w dowolnym momencie bez wpływu na zgodność z prawem&nbsp;<br>przetwarzania, którego dokonano na podstawie&nbsp;zgody przed jej cofnięciem<br><br>- Wniesienia skargi do organu nadzorczego gdy przetwarzanie Pani/Pana&nbsp;<br>danych
                osobowych narusza przepisy prawne. Podanie przez Pana/Panią&nbsp;<br>danych osobowych jest warunkiem&nbsp;wyznaczenia terminu wizyty&nbsp;<br>oraz&nbsp; bezpiecznego wykonania zabiegu kosmetycznej.<br></h6>
            <div class="form-group"><button class="btn btn-primary" onclick="goBack()" style="margin:0;width:265px;margin-left:267px;">Powrót do poprzedniej strony</button></div>
            <h6 class="text-uppercase text-center" id="spis" style="height:auto;margin-right:50px;margin-left:50px;margin-bottom:8px;margin-top:35px; font-size: 10px;">Podstawa prawna - Rozporządzenie Parlamentu Europejskiego i Rady (UE) 2016/679<br>z 27 kwietnia 2016 r. w sprawie ochrony osób fizycznych w związku z przetwarzaniem danych osobowych i w sprawie swobodnego przepływu takich danych oraz uchylenia
                dyrektywy 95/46/WE&nbsp; (ogólne rozporządzenie o ochronie danych).<br></h6>
        </div>
    </section>

<!--Footer bar-->
<div id="footer-placeholder">

</div>

<script>
    $(function(){
        $("#footer-placeholder").load("footer.jsp");
    });
</script>
<!--end of Footer bar-->

<script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/4.0.0/js/bootstrap.bundle.min.js"></script>

    <script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/4.0.0/js/bootstrap.bundle.min.js"></script>
</body>

</html>
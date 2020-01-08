<%@ page import="myPage.basicObjects.Raport" %>
<%@ page import="myPage.data.dataBase.RaportData" %>
<%@ page import="java.text.SimpleDateFormat" %>
<%@ page import="myPage.data.others.SessionData" %>
<!DOCTYPE html>
<html>
<section id="raporty_data" class="bg-light-gray" style="margin:0;background-color:rgba(0,0,0,0.11);color:#ffffff;padding-bottom:20px;padding-top:20px;max-width:1140px;margin-right:auto;margin-left:auto;border-radius:20px;margin-bottom:30px;">
    <form action="${pageContext.request.contextPath}/RemoveRaport" method="post">
        <h2 class="text-center" style="height:79px;">Raporty</h2>
        <h5 class="text-center" style="height:21px;margin-right:50px;margin-left:50px;">W poniższej sekcji znajdują się dodane przez Ciebie raporty.</h5>
        <h6 class="text-center" id="informacja" style="height:44px;margin-right:50px;margin-left:50px;font-weight: 100;">Jeśli w raporcie został popełniony błąd, zaznacz go aby go usunąć lub dodaj nowy raport.</h6>

            <%
                SessionData sessionData = (SessionData)session.getAttribute("userData");

                Raport raport = new Raport();
                RaportData temp;
                try {
                    raport.getRaporty();
                } catch (Exception e) {
                    e.printStackTrace();
                }

                while (!raport.raportyEmpty()){
                    temp = raport.raportyPop();

                    SimpleDateFormat formatter = new SimpleDateFormat("MM/dd/yyyy");
                    String date = formatter.format(temp.getData());

                    out.println("    <section id=\"pracownik\" class=\"bg-light-gray\" style=\"margin:0;background-color:rgba(0,0,0,0.11);color:#ffffff;padding-bottom:20px;padding-top:20px;max-width:1140px;margin-right:auto;margin-left:auto;border-radius:20px;margin-bottom:30px;\">\n" +
                            "        <br>\n" +
                            "        <table class=\"table\" cellspacing=\"0\" width=\"100%\" style=\"margin-top: -18px;text-align: center;margin-bottom: 0;width: 55%;/* max-width: 100%; */margin-left: 16%;background-color: transparent;border-collapse: collapse;min-width: 760px;\"\">\n" +
                            "            <tr>\n" +
                            "                <td><h5 class=\"text-center\" style=\"margin-bottom: 1px;\">Treść</h5></td>\n" +
                            "            </tr>\n" +
                            "            <tr>\n" +
                            "                <td style=\" text-align: center; vertical-align: middle;\">\n" +
                            "                    <h6 class=\"text-center\" style=\"margin-right:50px;margin-left:50px;font-weight: 100;\">" + temp.getTresc() + "</h6>\n" +
                            "                </td>\n" +
                            "            </tr>\n" +
                            "        </table>\n" +
                            "        <br>\n" +
                            "        <br>\n" +
                            "        <h5 class=\"text-center\" style=\"height:30px;margin-right:50px;margin-left:50px;\">Dane raportu</h5>\n" +
                            "        <table class=\"table\" cellspacing=\"0\" width=\"100%\" style=\"text-align: center;margin-bottom: 0;border: 3px solid #FFFFFF;width: 55%;max-width: 55%;margin-left: 259px;background-color: transparent;border-collapse: collapse;\">\n" +
                            "            <thead>\n" +
                            "            <tr>\n" +
                            "                <th></th>\n" +
                            "                <th style=\"width: 50%\">ID</th>\n" +
                            "                <th style=\"width: 50%\">TYTUŁ RAPORTU</th>\n" +
                            "                <th style=\"width: 50%\">TYP RAPORTU</th>\n" +
                            "                <th style=\"width: 50%\">DATA</th>\n" +
                            "            </tr>\n" +
                            "            </thead>\n" +
                            "            <tbody>\n" +
                            "            <tr>\n" +
                            "                <td> <input type=\"Checkbox\" name=\"do_usuniecia\" value=\"" + temp.getId_sprawozdania() + "\"></td>\n" +
                            "                <td>" + temp.getId_sprawozdania()  +"</td>\n" +
                            "                <td>"  + temp.getTytul() +"</td>\n" +
                            "                <td>"  + temp.getTyp() +"</td>\n" +
                            "                <td>"  + date +"</td>\n" +
                            "            </tr>\n" +
                            "            </tbody>\n" +
                            "        </table>\n" +
                            "    </section>");
                }
            %>
        <button class="btn btn-primary float-none align-self-center" type="submit" style="width:265px;position:static;text-align:center!important;margin-left:437px;margin-top:20px;">Usuń Raport</button>
    </form>
    <a href="../ControllerAccount?page=raport_upload"><button type="button" class="btn btn-primary float-none align-self-center" style="width:265px;position:static;text-align:center!important;margin-left:437px;margin-top:20px;">Dodaj Raport</button></a>
</section>
</html>
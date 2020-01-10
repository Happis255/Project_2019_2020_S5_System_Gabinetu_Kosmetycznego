<%@ page import="java.sql.SQLException" %>
<%@ page import="myPage.basicObjects.Wydarzenie" %>
<%@ page import="myPage.data.dataBase.WydarzenieData" %>
<%@ page import="myPage.data.others.SessionData" %>
<%@ page import="java.text.DateFormat" %>
<%@ page import="java.text.SimpleDateFormat" %>
<%@ page import="java.util.LinkedList" %>
<%@ page import="myPage.data.others.TypKonta" %>
<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
    <%
        session = request.getSession();
        SessionData sessionData = (SessionData) session.getAttribute("userData");
    %>
    <form action="${pageContext.request.contextPath}/SingInEvents" method="post">
    <section id="baza_uslug" class="bg-light-gray" style="margin:0;background-color:rgba(0,0,0,0.11);color:#ffffff;padding-bottom:20px;padding-top:20px;max-width:1140px;margin-right:auto;margin-left:auto;border-radius:20px;margin-bottom:30px;">

            <h2 class="text-center" style="height:79px;">Wydarzenia</h2>
            <h5 class="text-center" style="height:21px;margin-right:50px;margin-left:50px;">Poniżesz zamieszczona jest lista wydarzeń znajdująca się w systemie</h5>
            <h6 class="text-center" id="informacja" style="height:44px;margin-right:50px;margin-left:50px;font-weight: 100;">Zaznacz dane wydarzenia, by je edytować bądź wziąć w nim udział.</h6>
    </section>
            <%
                DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
                Wydarzenie event = new Wydarzenie();
                WydarzenieData temp;
                LinkedList<String> names = new LinkedList<>();

                try {
                    event.getEverything();
                } catch (SQLException e) {
                    e.printStackTrace();
                }

                while (!event.isEmpty()){
                    temp = event.pop();

                    out.println("" +
                            "    <section id=\"baza_uslug\" class=\"bg-light-gray\" style=\"margin:0;background-color:rgba(0,0,0,0.11);color:#ffffff;padding-bottom:20px;padding-top:20px;max-width:1140px;margin-right:auto;margin-left:auto;border-radius:20px;margin-bottom:30px;\"><table id=\"wydarzenia\" class=\"table\" cellspacing=\"0\" width=\"100%\" style=\"text-align: center;margin-bottom: 0;margin-top: 25px;border: 3px solid #FFFFFF;width: 98%;max-width: 98%;margin-left: 12px;background-color: transparent;border-collapse: collapse;\">\n" +
                            "            <thead>\n" +
                            "            <tr>\n" +
                            "                <th></th>\n" +
                            "                <th>TYP</th>\n" +
                            "                <th>NAZWA</th>\n" +
                            "                <th>OPIS</th>\n" +
                            "                <th>ULICA</th>\n" +
                            "                <th>KOD POCZTOWY</th>\n" +
                            "                <th>MIEJSCOWOSC</th>\n" +
                            "                <th>DATA OD</th>\n" +
                            "                <th>DATA DO</th>\n" +
                            "                <th>KOSZT</th>\n" +
                            "            </tr>\n" +
                            "            </thead>\n" +
                            "            <tbody>" + "<tr>" +
                            "<td> <input type=\"Checkbox\" Name=\"do_zapisania\" Value =\"" + temp.getId() + "\"></td>" +
                            "<td>" + temp.getOpisTypuWydarzenia() + "</td>" +
                            "<td>" + temp.getNazwa() + "</td>" +
                            "<td>" + temp.getOpis() + "</td>" +
                            "<td>" + temp.getUlica() + "</td>" +
                            "<td>" + temp.getKod_pocztowy() + "</td>" +
                            "<td>" + temp.getMiejscowosc() + "</td>" +
                            "<td>" + dateFormat.format(temp.getData_od()) + "</td>" +
                            "<td>" + dateFormat.format(temp.getData_do()) + "</td>" +
                            "<td>" + temp.getKosz() + " PLN</td></tr>" +
                            "</tbody> </table>");

                    try {
                        names = temp.getWorkerName(temp.getId());
                    }
                    catch (SQLException e){
                        e.printStackTrace();
                    }

                    out.println("<table id=\"nazwiska\" class=\"table\" cellspacing=\"0\" width=\"100%\" style=\"text-align: center;margin-bottom: 0;margin-top: 25px;border: 3px solid #FFFFFF;width: 60%;\n" +
                            "    max-width: 60%;\n" +
                            "    margin-left: 224px;background-color: transparent;border-collapse: collapse;\">\n" +
                            "            <thead>\n" +
                            "            <tr>\n" +
                            "                <th></th>\n" +
                            "                <th>ID</th>\n" +
                            "                <th>IMIĘ</th>\n" +
                            "                <th>NAZWISKO</th>\n" +
                            "            </tr>\n" +
                            "            </thead>\n" +
                            "            <tbody>");

                    String temporary;
                    while (!names.isEmpty()){
                        temporary = names.pop();
                        out.println("<tr><td> <input type=\"Checkbox\" Name=\"do_wypisania\" Value =\"" + temp.getId() + " " + temporary + "\"></td>" +
                                "<td>" + temporary + "</td>" +
                                "<td>" + names.pop() + "</td>" +
                                "<td>" + names.pop() + "</td></tr>");
                    }
                    out.println("</tbody> </table>    </section>");
                }
            %>
        <section id="baza_uslug" class="bg-light-gray" style="margin:0;background-color:rgba(0,0,0,0.11);color:#ffffff;padding-bottom:20px;padding-top:20px;max-width:1140px;margin-right:auto;margin-left:auto;border-radius:20px;margin-bottom:30px;">
            <div class="row">
                <a href="../ControllerAccount?page=dodaj_wydarzenie"><button type="button" class="btn btn-primary float-none align-self-center" style="width:265px;margin-left: 170px;">Utwórz wydarzenie</button></a>
                <a><button type="submit" name="opcja" value="zapisz" class="btn btn-primary float-none align-self-center" style="width:265px;margin-left: 17px;">Zapisz się na wydarzenie</button></a>
                <a><button type="submit" name="opcja" value="wypisz" class="btn btn-primary float-none align-self-center" style="width:265px;margin-left: 17px;">Wypisz pracownika</button></a>
            </div>
            <% if (TypKonta.getStringVal(sessionData.getAccoutType()).equals("ADMINISTRATOR"))
            out.print("<a><button type=\"submit\" name=\"opcja\" value=\"usun_wydarzenie\" class=\"btn btn-primary float-none align-self-center\" style=\"width:265px;    margin-top: 15px;margin-left: 437px;\">Usuń wydarzenie</button></a>");
            %>
    </section>
    </form>
</html>
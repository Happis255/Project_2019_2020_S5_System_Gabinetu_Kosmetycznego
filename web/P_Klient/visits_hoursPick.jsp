<%@ page import="myPage.basicObjects.Wizyta" %>
<%@ page import="myPage.data.dataBase.WizytaData" %>
<%@ page import="myPage.data.others.VisitPage" %>
<%@ page import="java.util.Date" %>
<%@ page import="java.util.HashMap" %>
<%@ page import="myPage.basicObjects.Usluga" %>
<%@ page import="myPage.data.dataBase.UslugaData" %>
<%@ page import="java.sql.SQLException" %>
<!DOCTYPE html>
<html>
<section id="wizyty_czas" class="bg-light-gray" style="margin:0;background-color:rgba(0,0,0,0.11);color:#ffffff;padding-bottom:20px;padding-top:20px;max-width:1000px;margin-right:auto;margin-left:auto;border-radius:20px;margin-top: 120px;margin-bottom: 152px;">
        <h2 class="text-center" style="height:79px;">Tworzenie wizyty</h2>
        <h5 class="text-center" style="height:21px;margin-right:50px;margin-left:50px;">Wybierz teraz jedynie interesującą Cię godzinę.</h5>
        <h6 class="text-center" id="RODO" style="height:44px;margin-right:50px;margin-left:50px;">Następnie zatwierdź operację.
                                                    <br>Jeśli wizyta zostanie pomyślnie utworzona, oczekuj na potwierdzenie e-mail.</h6>
            <form id="datePicker" action="${pageContext.request.contextPath}/ControllerVisitsAdder" method="post">

            <%  String row ="<select class=\"form-control\" name=\"wybrana_usluga\" value=\"\" required=\"\" style=\"    width: 265px;\n" +
                        "    position: static;\n" +
                        "    margin-left: 367px;margin-top: 36px;\">";
                out.println(row);

                VisitPage visitPage = (VisitPage)request.getSession().getAttribute("VisitPage");
                Wizyta wizyty = new Wizyta();
                Usluga uslugi = new Usluga();
                WizytaData tempW;
                UslugaData tempU;
                Date data = visitPage.getData();
                Integer idPracownika = visitPage.getIdPracownika();
                try {
                    wizyty.getVisitsInDayWithWorker(data, idPracownika);
                } catch (Exception e) {
                    e.printStackTrace();
                }

                HashMap<String, WizytaData> wizytyGodzinami = new HashMap<>();
                String czas;
                for(int h = 10; h<=16 ;h++){
                    for(int m = 0; m <=30; m+=30){
                        if(m == 0)
                            czas = h + ":00";
                        else
                            czas = h + ":30";
                        wizytyGodzinami.put(czas, null);
                    }
                }
                wizytyGodzinami.put("17:00", null);

                while (!wizyty.isEmpty()){
                    try {
                        tempW = wizyty.pop();
                        uslugi.getCzasUslugi(tempW.getId_uslugi());
                        tempU = uslugi.uslugaPop();
                        int timePeroids = tempU.getCzas_trwania() / 30;
                        if(tempU.getCzas_trwania() % 30 != 0)
                            timePeroids += 1;

                        for(int h = tempW.getGodzina().getHour() ; h <= 16 && timePeroids > 0;h++){
                            for(int m = tempW.getGodzina().getMinute(); m <=30 && timePeroids > 0; m+=30, timePeroids--){

                                if(m == 0)
                                    czas = h + ":00";
                                else
                                    czas = h + ":30";

                                wizytyGodzinami.replace(czas, new WizytaData(tempW));
                            }
                        }

                        czas = tempW.getGodzina().getHour() + ":" + tempW.getGodzina().getMinute();

                        wizytyGodzinami.replace(czas, tempW);
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                }

                for(int h = 10; h<=16 ;h++){
                    for(int m = 0; m <=30; m+=30){
                        if(m == 0)
                            czas = h + ":00";
                        else
                            czas = h + ":30";
                        tempW = wizytyGodzinami.get(czas);
                        if(tempW != null){
                            row = "<option value=\"\" disabled>" + czas + " - termin zajęty</option>\n";
                            out.println(row);
                        }else{
                            row = "<option value=\"" + czas +"\">" + czas + " - termin wolny</option>\n";
                            out.println(row);
                        }
                    }
                }
                czas = "17:00";
                tempW = wizytyGodzinami.get(czas);
                if(tempW != null){
                    row = "<option value=\"\" disabled>" + czas + " - termin zajęty</option>\n";
                    out.println(row);
                }else{
                    row = "<option value=\"" + czas +"\">" + czas + " - termin wolny</option>\n";
                    out.println(row);
                }
            %>
            </select>
        <button class="btn btn-primary" type="submit" style="width:265px;position:static;text-align:center!important;margin-left:367px;margin-top:20px;">Zatwierdź i wyślij prośbę</button>
        <a href="../ControllerAccount?page=wizytyWorker"><button class="btn btn-primary float-none align-self-center" type="button" style="width:265px;position:static;text-align:center!important;margin-left:367px;margin-top:20px;">Wybierz ponownie dane</button></a>
    </form>
</section>
</html>
<%@ page import="myPage.basicObjects.Wizyta" %>
<%@ page import="myPage.data.dataBase.WizytaData" %>
<%@ page import="myPage.data.others.VisitPage" %>
<%@ page import="java.util.Date" %>
<%@ page import="java.util.HashMap" %>
<!DOCTYPE html>
<html>
<section id="wizyty_czas" class="bg-light-gray"
         style="margin:0;background-color:rgba(0,0,0,0.11);color:#ffffff;padding-bottom:20px;padding-top:20px;max-width:1140px;margin-right:auto;margin-left:auto;border-radius:20px;margin-bottom:30px;">
    <table id="tablica_uslug" class="table" cellspacing="0" width="100%"
           style="text-align: center;margin-bottom: 0;border: 3px solid #FFFFFF;width: 50%;max-width: 50%;margin-left: 12px;background-color: transparent;border-collapse: collapse;">
        <thead>
        <tr>
            <th></th>
            <th>GODZINA</th>
            <th>STATUS</th>
        </tr>
        </thead>
        <tbody>
        <%
            VisitPage visitPage = (VisitPage)request.getSession().getAttribute("VisitPage");
            Wizyta wizyty = new Wizyta();
            WizytaData temp;
            Date data = visitPage.getData();
            Integer idPracownika = visitPage.getIdPracownika();
            try {
                wizyty.getVisitsInDayWithWorker(data, idPracownika);
            } catch (Exception e) {
                e.printStackTrace();
            }

            HashMap<String, WizytaData> wizytyGodzinami = new HashMap<>();
            String czas;
            for(int h = 10; h<16 ;h++){
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
                temp = wizyty.pop();
                czas = temp.getGodzina().getHour() + ":" + temp.getGodzina().getMinute();

                wizytyGodzinami.replace(czas, temp);
            }

        %>

        <%
            for(int h = 10; h<16 ;h++){
                for(int m = 0; m <=30; m+=30){
                    if(m == 0)
                        czas = h + ":00";
                    else
                        czas = h + ":30";
                    temp = wizytyGodzinami.get("czas");
                    if(temp != null){
                        out.println("<tr><td></td>" +
                                "<td>" + czas + "</td>" +
                                "<td>" + "NIEDOSTEPNY" + "</td>" +
                                "</tr>");
                    }else{
                        out.println("<tr><td> <input type=\"radio\" Name=\"wybrana_usluga\" Value =\"" + czas + "\"></td>" +
                                "<td>" + czas + "</td>" +
                                "<td>" + "DOSTEPNY" + "</td>" +
                                "</tr>");
                    }
                }
            }
            czas = "17:00";
            temp = wizytyGodzinami.get(czas);
            if(temp != null){
                out.println("<tr><td></td>" +
                        "<td>" + czas + "</td>" +
                        "<td>" + "NIEDOSTEPNY" + "</td>" +
                        "</tr>");
            }else{
                out.println("<tr><td> <input type=\"radio\" Name=\"wybrana_usluga\" Value =\"" + czas + "\"></td>" +
                        "<td>" + czas + "</td>" +
                        "<td>" + "DOSTEPNY" + "</td>" +
                        "</tr>");
            }
        %>
        </tbody>
    </table>

</section>

<a href="../ControllerVisitsSend"><button class="btn btn-primary float-none align-self-center" type="button" style="width:265px;position:static;text-align:center!important;margin-left:437px;margin-top:20px;">zatweirdz i wyślij</button></a>

</html>
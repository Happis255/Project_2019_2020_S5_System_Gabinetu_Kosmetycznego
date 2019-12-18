<%@ page import="myPage.data.others.SessionData" %>
<%@ page import="myPage.data.others.TypKonta" %>
<%@ page import="myPage.basicObjects.User" %>
<%@ page import="myPage.data.dataBase.UserData" %>
<%@ page import="myPage.exceptions.NoResultsException" %>
<%@ page import="java.sql.SQLException" %>
<%@ page import="myPage.data.others.ErrorMessage" %>
<%@ page import="myPage.exceptions.CannotInstanciateException" %>
<%@ page import="myPage.others.HTMLFilter" %>
<%@ page import="myPage.basicObjects.Klient" %>
<%@ page import="myPage.data.dataBase.KlientData" %><%--
  Created by IntelliJ IDEA.
  User: Huber
  Date: 25.11.2019
  Time: 15:35
  To change this template use File | Settings | File Templates.
--%>'
<%
    response.setCharacterEncoding("UTF-8");
    request.setCharacterEncoding("UTF-8");
%>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<!doctype html>
<head>
    <meta charset="utf-8">
</head>
<% SessionData sessionData = (SessionData)session.getAttribute("userData"); %>

<%
    switch(sessionData.getPage()){
        case "konto":
%>
<section style="margin-bottom:30px;">
    <div class="jumbotron" style="background-color:rgba(0,0,0,0.11);color:#ffffff;padding-bottom:20px;padding-top:20px;max-width:800px;margin-right:auto;margin-left:auto;">
        <br>DANE TWOJEGO KONTA:
        <%
            Klient user = null;
            KlientData data;
            try {
                user = new Klient(sessionData.getId());

                data = user.getData();
                if(data == null)
                    throw new NoResultsException();
            } catch (SQLException | NoResultsException e) {
                ErrorMessage errorMessage = new ErrorMessage(e);
                session.setAttribute("errorMessage", errorMessage);
                response.sendRedirect("errorPage.jsp");
                return;
            }

        %> <br><br> <%

        out.println("Imie:" + data.getImie() + "<br>");
        out.println("Nazwisko:" + data.getNazwisko() + "<br>");
        out.println("Ulica:" + data.getUlica() + "<br>");
        out.println("kod_pocztowy:" + data.getKod_pocztowy() + "<br>");
        out.println("miejscowosc:" + data.getMiejscowosc() + "<br>");
        out.println("data_urodzenia:" + data.getData_urodzenia() + "<br>");
        out.println("telefon:" + data.getTelefon() + "<br>");
        out.println("e_mail:" + data.getE_mail() + "<br>");
        out.println("id_klienta:" + data.getId() + "<br>");
        out.println("id_konta:" + data.getId_konta() + "<br><br>");
        out.println("ilosc_punktow:" + data.getIlosc_punktow() + "<br>");
        out.println("id_karty:" + data.getId_karty() + "<br>");
        out.println("id_statusu:" + data.getId_statusu() + "<br>");
/*
        if(data instanceof PracownikData){
            out.println("pesel:" + ((PracownikData)data).getPesel() + HTMLFilter.addBR());
            out.println("data_zatrudnienia:" + ((PracownikData)data).getData_zatrudnienia() + HTMLFilter.addBR());
            out.println("certyfikaty:" + ((PracownikData)data).getCertyfikaty() + HTMLFilter.addBR());
            out.println("id_ksiarzeczki:" + ((PracownikData)data).getId_ksiazeczki() + HTMLFilter.addBR());
        }

        if(data instanceof KlientData){
            out.println("ilosc_punktow:" + ((KlientData)data).getIlosc_punktow() + HTMLFilter.addBR());
            out.println("id_karty:" + ((KlientData)data).getId_karty() + HTMLFilter.addBR());
            out.println("id_statusu:" + ((KlientData)data).getId_statusu() + HTMLFilter.addBR());
        }
*/
    %>
    </div>
</section>

<%
        break;
    default:
%>

<%
            break;
    }
%>

<section style="margin-bottom:30px;">
    <div class="jumbotron" style="background-color:rgba(0,0,0,0.11);color:#ffffff;padding-bottom:20px;padding-top:20px;max-width:800px;margin-right:auto;margin-left:auto;">
            kontent!!!!!!!!
    </div>
</section>

</html>

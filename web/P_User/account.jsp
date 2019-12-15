<%--
  Created by IntelliJ IDEA.
  User: ppisk
  Date: 27.11.2019
  Time: 18:42
  To change this template use File | Settings | File Templates.
--%>

<%@ page import="myPage.basicObjects.User" %>
<%@ page import="myPage.data.dataBase.*" %>
<%@ page import="myPage.data.others.ErrorMessage" %>
<%@ page import="myPage.data.others.SessionData" %>
<%@ page import="myPage.data.others.TypKonta" %>
<%@ page import="myPage.exceptions.CannotInstanciateException" %>
<%@ page import="myPage.exceptions.NoResultsException" %>
<%@ page import="myPage.others.HTMLFilter" %>
<%@ page import="java.sql.SQLException" %>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.*" %>
<%
    response.setCharacterEncoding("UTF-8");
    request.setCharacterEncoding("UTF-8");
%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Konto</title>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/4.0.0/css/bootstrap.min.css">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/ionicons/2.0.1/css/ionicons.min.css">
    <link rel="stylesheet" href="../assets/css/styles.min.css?v=1.1">
    <script src="https://code.jquery.com/jquery-1.10.2.js"></script>
</head>
<body>
<% SessionData sessionData = (SessionData)session.getAttribute("userData"); %>

<!--Nav bar-->
<div id="navbar-placeholder"></div>

<script>
    function loadNavbar(navbarName){
        $("#navbar-placeholder").load(navbarName);
    };

    <% if(sessionData.getAccoutType() == TypKonta.KLIENT){ %>
        loadNavbar("../P_Klient/navbar_klient.jsp");
    <% }else if(sessionData.getAccoutType() == TypKonta.PRACOWNIK){ %>
        loadNavbar("../P_Pracownik/navbar_pracownik.jsp");
    <% }else if(sessionData.getAccoutType() == TypKonta.ADMINISTRATOR){ %>
        loadNavbar("../P_Administrator/navbar_administrator.jsp");
    <% } %>
</script>
<!--end of Nav bar-->

<% if(true) { %>
<section style="margin-bottom:30px;">
    <div class="jumbotron" style="background-color:rgba(0,0,0,0.11);color:#ffffff;padding-bottom:20px;padding-top:20px;max-width:800px;margin-right:auto;margin-left:auto;">

    </div>
</section>
<%}

if(true) { %>
<section style="margin-bottom:30px;">
    <div class="jumbotron" style="background-color:rgba(0,0,0,0.11);color:#ffffff;padding-bottom:20px;padding-top:20px;max-width:800px;margin-right:auto;margin-left:auto;">
        konto :) <br>
        <% out.println("TYP KONTA: " + TypKonta.getStringVal(sessionData.getAccoutType())); %>
        <br>DANE TWOJEGO KONTA:
        <%
            User user = null;
            UserData data;
            try {
                user = User.getInstance(sessionData.getId(), sessionData.getAccoutType());

                data = user.getData();
                if(data == null)
                    throw new NoResultsException();
            } catch (SQLException | NoResultsException | CannotInstanciateException e) {
                ErrorMessage errorMessage = new ErrorMessage(e);
                session.setAttribute("errorMessage", errorMessage);
                response.sendRedirect("errorPage.jsp");
                return;
            }

        %> <br><br> <%

            out.println("Imie:" + data.getImie() + HTMLFilter.addBR());
            out.println("Nazwisko:" + data.getNazwisko() + HTMLFilter.addBR());
            out.println("Ulica:" + data.getUlica() + HTMLFilter.addBR());
            out.println("kod_pocztowy:" + data.getKod_pocztowy() + HTMLFilter.addBR());
            out.println("miejscowosc:" + data.getMiejscowosc() + HTMLFilter.addBR());
            out.println("data_urodzenia:" + data.getData_urodzenia() + HTMLFilter.addBR());
            out.println("telefon:" + data.getTelefon() + HTMLFilter.addBR());
            out.println("e_mail:" + data.getE_mail() + HTMLFilter.addBR());
            out.println("id_klienta:" + data.getId() + HTMLFilter.addBR());
            out.println("id_konta:" + data.getId_konta() + HTMLFilter.addBR() + HTMLFilter.addBR());

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

        %>
        <br><br>

        <a href="../index.jsp">RETURN</a>

    </div>
</section>
<% } %>

<!--Footer bar-->
<div id="footer-placeholder"></div>

<script>
    function loadFooter(footerName){
        $("#footer-placeholder").load(footerName);
    };

    <% if(sessionData.getAccoutType() == TypKonta.KLIENT){ %>
        loadFooter("../P_Klient/footer_klient.jsp");
    <% }else if(sessionData.getAccoutType() == TypKonta.PRACOWNIK){ %>
        loadFooter("../P_Pracownik/footer_pracownik.jsp");
    <% }else if(sessionData.getAccoutType() == TypKonta.ADMINISTRATOR){ %>
        loadFooter("../P_Administrator/footer_administrator.jsp");
    <% } %>
</script>
<!--end of Footer bar-->

<script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/4.0.0/js/bootstrap.bundle.min.js"></script>

</body>
</html>

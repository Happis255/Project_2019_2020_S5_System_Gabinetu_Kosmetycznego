<%@ page import="myPage.basicObjects.User" %>
<%@ page import="myPage.data.dataBase.*" %>
<%@ page import="myPage.data.others.ErrorMessage" %>
<%@ page import="myPage.data.others.SessionData" %>
<%@ page import="myPage.data.others.TypKonta" %>
<%@ page import="myPage.exceptions.CannotInstanciateException" %>
<%@ page import="myPage.exceptions.DBReadWriteException" %>
<%@ page import="myPage.exceptions.NoResultsException" %>
<%@ page import="myPage.others.HTMLFilter" %>
<%@ page import="java.sql.SQLException" %>
<%@ page import="myPage.basicObjects.Pracownik" %>
<%@ page import="myPage.basicObjects.Klient" %>
<%@ page import="myPage.basicObjects.Admin" %>
<%--
  Created by IntelliJ IDEA.
  User: ppisk
  Date: 27.11.2019
  Time: 18:42
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<% SessionData sessionData = (SessionData)session.getAttribute("userData"); %>
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

%> <br><br> <%

%>

<a href="../index.jsp">RETURN</a>

</body>
</html>

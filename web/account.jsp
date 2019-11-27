<%@ page import="myPage.data.Client" %>
<%@ page import="myPage.others.DataSource" %>
<%@ page import="myPage.data.SessionData" %>
<%@ page import="myPage.exceptions.DBReadWriteException" %>
<%@ page import="java.sql.SQLException" %><%--
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
konto :)

DANE KTWOJEGO KONTA:
    <%
        Client client;
        DataSource dataSource = new DataSource();
        SessionData sessionData = (SessionData)session.getAttribute("userData");

        try {
            if(sessionData == null) throw new NullPointerException();
            client = dataSource.getClientDateDB(sessionData.getNick());
            if(client == null) throw  new NullPointerException();
        } catch (DBReadWriteException | SQLException e) {
            System.out.println("[ERR] DB Error");
            System.out.println(e);
            e.printStackTrace();
            session.invalidate();
            response.sendRedirect("index.jsp");
            return;
        } catch (NullPointerException e){
            System.out.println("[ERR] NullPointerException");
            System.out.println(e);
            e.printStackTrace();
            out.println("blad odczytu danych");
            response.sendRedirect("index.jsp");
            return;
        }

        %> <br><br> <%
        out.println("Imie:" + client.getImie()); %> <br> <%
        out.println("Nazwisko:" + client.getNazwisko()); %> <br> <%
        out.println("Ulica:" + client.getUlica()); %> <br> <%
        out.println("kod_pocztowy:" + client.getKod_pocztowy()); %> <br> <%
        out.println("miejscowosc:" + client.getMiejscowosc()); %> <br> <%
        out.println("data_urodzenia:" + client.getData_urodzenia()); %> <br> <%
        out.println("telefon:" + client.getTelefon()); %> <br> <%
        out.println("e_mail:" + client.getE_mail()); %> <br> <%
        out.println("ilosc_punktow:" + client.getIlosc_punktow()); %> <br> <%
        out.println("typ_konta:" + client.getTyp_konta_String()); %> <br><br> <%
    %>

<a href="index.jsp">RETURN</a>

</body>
</html>

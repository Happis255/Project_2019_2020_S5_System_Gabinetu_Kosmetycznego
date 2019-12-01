<%@ page import="myPage.data.*" %>
<%@ page import="myPage.exceptions.DBReadWriteException" %>
<%@ page import="myPage.exceptions.NoResultsException" %>
<%@ page import="java.sql.SQLException" %>
<%@ page import="myPage.others.*" %>
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
<br>DANE KTWOJEGO KONTA:
    <%
        User user = null;
        DataSourceClient dataSourceClient = new DataSourceClient();
        DataSourcePracownik dataSourceWorker = new DataSourcePracownik();
        DataSourceAdmin dataSourceAdmin = new DataSourceAdmin();

        try {
            if(sessionData.getAccoutType() == TypKonta.KLIENT){
                user = dataSourceClient.getClientDB(sessionData.getNick());
            }else{
                user = dataSourceWorker.getPracownikDB(sessionData.getNick());
            }
            if(user == null) throw  new NoResultsException();
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
        out.println("Imie:" + user.getImie()); %> <br> <%
        out.println("Nazwisko:" + user.getNazwisko()); %> <br> <%
        out.println("Ulica:" + user.getUlica()); %> <br> <%
        out.println("kod_pocztowy:" + user.getKod_pocztowy()); %> <br> <%
        out.println("miejscowosc:" + user.getMiejscowosc()); %> <br> <%
        out.println("data_urodzenia:" + user.getData_urodzenia()); %> <br> <%
        out.println("telefon:" + user.getTelefon()); %> <br> <%
        out.println("e_mail:" + user.getE_mail()); %> <br> <%
        out.println("typ_konta:" + user.getTyp_konta_String()); %> <br> <%
        if(user instanceof Client){
            out.println("ilosc_punktow:" + ((Client)user).getIlosc_punktow());
        }else{
            out.println("pesel:" + ((Pracownik)user).getPesel()); %> <br> <%
            out.println("data_zatrudnienia:" + ((Pracownik)user).getData_zatrudnienia()); %> <br> <%
            out.println("certyfikaty:" + ((Pracownik)user).getCertyfikaty());
        }
        %> <br><br> <%

        User[] clients = null;
        User[] workers = null; //na razie zakłądam ze pracwonicy różnią się tylko id_konta (póżniej sie to zmieni)
        User[] admins = null;  //na razie zakłądam ze admini różnią się tylko id_konta (póżniej sie to zmieni)
        if(sessionData.getAccoutType() == TypKonta.PRACOWNIK){
            try {
                clients = dataSourceClient.getAllAccountsBasicDataWithTagDB(TypKonta.KLIENT);
            } catch (SQLException e) {
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

            out.println("KLIENCI:" + HTMLFilter.addBR());
            for (User u : clients) {
                out.println(u.getE_mail() + "    " + u.getImie() + "    " + u.getNazwisko() + HTMLFilter.addBR());
            }
        }

        if(sessionData.getAccoutType() == TypKonta.ADMINISTRATOR){
            try {
                clients = dataSourceClient.getAllAccountsBasicDataWithTagDB(TypKonta.KLIENT);
                workers = dataSourceWorker.getAllAccountsBasicDataWithTagDB(TypKonta.PRACOWNIK);
                admins = dataSourceAdmin.getAllAccountsBasicDataWithTagDB(TypKonta.ADMINISTRATOR);
            } catch (SQLException e) {
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

            out.println(HTMLFilter.addBR() + "KLIENCI:" + HTMLFilter.addBR());
            for (User u : clients) {
                out.println(u.getE_mail() + "    " + u.getImie() + "    " + u.getNazwisko() + HTMLFilter.addBR());
            }
            out.println(HTMLFilter.addBR() + "PRACOWNICY:" + HTMLFilter.addBR());
            for (User u : workers) {
                out.println(u.getE_mail() + "    " + u.getImie() + "    " + u.getNazwisko() + HTMLFilter.addBR());
            }
            out.println(HTMLFilter.addBR() + "ADMINI:" + HTMLFilter.addBR());
            for (User u : admins) {
                out.println(u.getE_mail() + "    " + u.getImie() + "    " + u.getNazwisko() + HTMLFilter.addBR());
            }
        }

%>

<a href="index.jsp">RETURN</a>

</body>
</html>

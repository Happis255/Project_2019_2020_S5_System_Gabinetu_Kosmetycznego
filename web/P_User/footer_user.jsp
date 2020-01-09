<%--
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


<div class="footer-clean" style="color:rgb(0,0,0);">
    <footer>
        <div class="container">
            <div class="row justify-content-center">
                <div class="col-sm-4 col-md-3 item">
                    <h3>Usługi</h3>
                    <ul>
                        <% if(session.getAttribute("userData") == null){ %>
                            <li><a href="logowanie.jsp">Logowanie</a></li>
                        <% }else{ %>
                            <li><a href="account.jsp">Moje konto</a></li>
                        <% } %>
                        <li><a href="uslugi.jsp">Oferta usługowa</a></li>
                        <a class="nav-link" href="oferta_zakupowa.jsp" style="color:#ffffff;">Oferta sprzedażowa</a>
                    </ul>
                </div>
                <div class="col-sm-4 col-md-3 item">
                    <h3>O nas</h3>
                    <ul>
                        <li><a href="aktualnosci.jsp">Aktualności</a></li>
                        <li><a href="../index.jsp#kontakt">Skontaktuj się z nami</a></li>
                        <li><a href="wyslij-cv.jsp">Wyślij swoje CV</a></li>
                    </ul>
                </div>
                <div class="col-sm-4 col-md-3 item">
                    <h3>Gabinet Kosmetyczny "Gracja"</h3>
                    <ul>
                        <li>Al. Jana Pawla II 13a/2</li>
                        <li>37-450 Stalowa Wola</li>
                        <li>tel.: (15) 842 94 19</li>
                        <li>10.00-18.00 pn-pt<br></li>
                    </ul>
                </div>
                <div class="col-lg-3 item social"><a href="https://www.facebook.com/Gracja-Gabinet-Kosmetyczny-255745234516172/"><i class="icon ion-social-facebook"></i></a><a href="https://www.instagram.com/gabinet.gracja/"><i class="icon ion-social-instagram-outline"></i></a>
                    <a href="mailto:info@gabinetgracja.com.pl"><i class="icon ion-email"></i></a><a href="https://www.messenger.com/t/255745234516172"><i class="icon ion-android-mail"></i></a>
                    <p class="copyright" style="width:281px;">Gabinet Kosmetyczny "Gracja" Anna Wasik</p>
                </div>
            </div>
        </div>
    </footer>
</div>
</html>

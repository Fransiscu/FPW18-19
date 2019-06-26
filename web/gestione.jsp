<%-- 
    Document   : gestione
    Created on : May 4, 2019, 6:03:26 PM
    Author     : franc
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page import="fpw.model.User"%>
<%@page import="fpw.model.Paper"%>
<%@page import="fpw.model.PaperFactory"%>
<%@page import="fpw.model.Grade"%>
<%@page import="fpw.model.GradesFactory"%>
<!DOCTYPE html>
<html>
    <head>
        <title>Gestione</title>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <meta name="author" content="Francesco Soru">
        <meta name="keywords" content="FPW, Progetto, HTML, CSS, JAVA">
        <meta name="description" content="Pagina per la gestione del sito, riservata all'organizzatore">
        <link rel="stylesheet" type="text/css" href="style.css" media="screen">
    </head>
    <body>

        <div class="header">
            <div class="left_header">
                <a href="login.html" id="logo">Free Peer Review</a>
                <form action="/milestone-1-Fransiscu/logout.html?logout" method="post">
                    <input type="submit" value="Sign out" class="hide_other"/>
                </form>
            </div>
            <div class="right_header">
                <a href="gestione.html" class="top_button current">Gestione</a>
                <a href="#" class="top_button" style="visibility:hidden"></a>
                <a href="#" class="top_button" style="visibility:hidden"></a>
            </div>
        </div>
        <div class="row">
            <div class="col-2 col-s-2">
            <c:choose>
                <c:when test="${user.getName() != null && showNavbarName eq true && user.isAuthor() eq true}">
                    <h1 class="centered">Benvenuto ${user.getName()}!</h1>
                    <div class="hide_mobile">
                        <form action="/milestone-1-Fransiscu/logout.html?logout=true" method="post">
                            <input type="submit" value="Sign out"  class="sign_out_button"/>
                        </form>
                    </div>
                    <div class="hide_other mobile_side_links">
                        <a href="./articoli.html">I miei articoli</a>
                    </div>
                    <div class="hide_other mobile_side_links">
                        <a href="./valutazione.jsp">Le mie valutazioni</a>
                    </div>
                </c:when>
                <c:when test="${user.isOrganizer() eq true && showNavbarName eq true}">
                    <h1 class="centered">Benvenuto ${user.getName()}!</h1>
                    <div class="hide_mobile">
                        <form action="/milestone-1-Fransiscu/logout.html?logout=true" method="post">
                            <input type="submit" value="Sign out"  class="sign_out_button"/>
                        </form>
                    </div>
                </c:when>
                <c:otherwise>
                    <h1 class="centered"><a href="./login.html">Login!</a></h1>
                </c:otherwise>
            </c:choose>
            </div>

            <div class="col-10 col-s-10 content">
                <c:choose>
                    <c:when test="${user.isOrganizer()}" >
                        <h1>Gestione</h1>
                        <h2> Qui di seguito verranno visualizzati tutti gli articoli inviati per la valutazione </h2>

                        <table class="articles_table">
                            <tr>
                                <th class="articles_table-title_row date">Data</th>
                                <th class="articles_table-title_row title">Titolo</th>
                                <th class="articles_table-title_row state">Valutazione</th>
                                <th class="articles_table-title_row actions">Decisione</th>
                            </tr>

                            <!-- Inizio dalla row 0 -->
                            <!-- for each item in papers, if number odd/even, write -->

                            <c:forEach items="${papers}" var="p" varStatus="count">
                                <c:if test="${count.index % 2 == 0}">
                                    <tr> 
                                        <td class="articles_table-even_row articles_table_date"> ${p.getSubmissionDate()} </td>
                                        <td class="articles_table-even_row"> ${p.getTitle()} </td>
                                        <td class="articles_table-even_row">
                                            <c:choose>
                                                <c:when test="${p.getStatus() == 'VALUTAZIONE' && p.getGrade().getIsGraded() ne null}">
                                                    <a href="#">Scegli valutatori</a>
                                                </c:when>
                                                <c:otherwise>     
                                                    ${p.getGrade().getGrading()}/5       
                                                </c:otherwise>
                                            </c:choose>
                                        </td>
                                        <td class="articles_table-even_row articles_table_buttons ${p.getStatus()}"> 
                                            <c:choose>
                                                <c:when test="${p.getStatus() == 'ACCETTATO' || p.getStatus() == 'RIFIUTATO'}">
                                                    ${p.getStatus()}
                                                </c:when>
                                                <c:when test="${p.getStatus() == 'VALUTAZIONE'}">
                                                    Attesa valutazioni 
                                                </c:when>
                                                <c:when test="${p.getStatus() == 'APERTO'}">
                                                    <a href="#">Decidi</a>
                                                </c:when>
                                            </c:choose>
                                        </td>
                                    </tr>
                                </c:if>

                                <c:if test="${count.index % 2 != 0}">

                                    <tr>
                                        <td class="articles_table-odd_row articles_table_date"> ${p.getSubmissionDate()} </td>
                                        <td class="articles_table-odd_row"> ${p.getTitle()} </td>
                                        <td class="articles_table-odd_row">
                                            <c:choose>
                                                <c:when test="${p.getGrade().getIsGraded() eq true}">
                                                    ${p.getGrade().getGrading()}/5
                                                </c:when>
                                                <c:otherwise>
                                                    <a href="#">Scegli valutatori</a>
                                                </c:otherwise>
                                            </c:choose>
                                        </td>

                                        <td class="articles_table-odd_row articles_table_buttons ${p.getStatus()}"> 
                                            <c:choose>
                                                <c:when test="${p.getStatus() == 'ACCETTATO' || p.getStatus() == 'RIFIUTATO'}">
                                                    ${p.getStatus()}
                                                </c:when>
                                                <c:when test="${p.getStatus() == 'VALUTAZIONE'}">
                                                    Attesa valutazioni 
                                                </c:when>
                                                <c:when test="${p.getStatus() == 'APERTO'}">
                                                    <a href="#">Decidi</a>
                                                </c:when>
                                            </c:choose>
                                        </td>
                                    </tr>
                                </c:if>
                            </c:forEach>
                            <tr> 
                        </table>

                    </c:when>
                    <c:otherwise>
                        <div class="centered forbidden">
                            <h1> Accesso Negato </h1>
                            <p> Non hai i diritti necessari per visualizzare questa pagina </p>
                        </div>
                    </c:otherwise>
                </c:choose>
            </div>
        </div>
        <jsp:include page="footer.jsp"/>
    </body>
</html>

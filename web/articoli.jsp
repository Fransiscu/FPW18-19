<%-- 
    Document   : articoli
    Created on : Apr 19, 2019, 6:31:45 PM
    Author     : franc
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page import="fpw.model.User"%>
<%@page import="fpw.model.Paper"%>
<!DOCTYPE html>
<html lang="it">
    <head>
        <title>Articoli</title>
        <meta charset="UTF-8"> 
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <meta name="author" content="Francesco Soru">
        <meta name="keywords" content="FPW, Progetto, HTML, CSS, JAVA">
        <meta name="description" content="Pagina degli articoli">
        <link rel="stylesheet" type="text/css" href="style.css" media="screen">
    </head>

    <body>
        <jsp:include page="header.jsp"/>
        <div class="row">
            <jsp:include page="nav.jsp"/>

            <div class="col-10 col-s-10 content">
                <c:choose>
                    <c:when test="${user.isAuthor()}" >
                        <h1>I miei articoli</h1>
                        <h4>In questa pagina verranno elencati tutti gli articoli legati all'utente</h4>

                        <table class="articles_table">
                            <tr>
                                <th class="articles_table-title_row date">Data</th>
                                <th class="articles_table-title_row title">Titolo</th>
                                <th class="articles_table-title_row state">Stato</th>
                                <th class="articles_table-title_row actions">Azioni</th>
                            </tr>
                            <!-- Inizio dalla row 0 -->
                            <!-- for each item in papers, if number odd/even, write -->

                            <c:forEach items="${papers}" var="p" varStatus="count">
                                <c:if test="${count.index % 2 == 0}">
                                    <tr> 
                                        <td class="articles_table-even_row articles_table_date"> ${p.getSubmissionDate()} </td>
                                        <td class="articles_table-even_row"> ${p.getTitle()} </td>
                                        <td class="articles_table-even_row ${p.getStatus()}">${p.getStatus()}</td>
                                        <td class="articles_table-even_row  articles_table_buttons">
                                            <c:if test="${p.getStatus() == 'APERTO'}">
                                                <a href="scriviArticolo.html?id=${p.getID()}">
                                                    <img src="img/edit_button.png" alt="Modifica">
                                                </a> 
                                                <a href="#"><img src="img/delete_button.png" alt="Elimina"></a></td>
                                        </c:if>
                                        <c:if test="${p.getStatus() == 'VALUTAZIONE'}">
                                        <a href="#"><img src="img/delete_button.png" alt="Elimina"></a></td>
                                        </c:if>
                                    </tr>
                                </c:if>
                                <c:if test="${count.index % 2 != 0}">
                                    <tr>
                                        <td class="articles_table-odd_row articles_table_date"> ${p.getSubmissionDate()} </td>
                                        <td class="articles_table-odd_row"> ${p.getTitle()} </td>
                                        <td class="articles_table-odd_row ${p.getStatus()}">${p.getStatus()}</td>
                                        <td class="articles_table-odd_row articles_table_buttons"> 
                                            <c:if test="${p.getStatus() == 'APERTO'}">
                                                <a href="scriviArticolo.html?id=${p.getID()}"><img src="img/edit_button.png" alt="Modifica"></a> <a href="#"><img src="img/delete_button.png" alt="Elimina"></a></td>
                                                </c:if>
                                                <c:if test="${p.getStatus() == 'VALUTAZIONE'}">
                                        <a href="#"><img src="img/delete_button.png" alt="Elimina"></a></td>
                                        </c:if>
                                    </tr> 
                                </c:if>
                            </c:forEach>
                            <tr> 
                        </table>
                        <br/>
                        <form action="./scriviArticolo.html" method="post">
                            <input type="submit" name="new" value="Invia un nuovo articolo" id="submit_form" class="button"/>
                        </form>
                        <br/>
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


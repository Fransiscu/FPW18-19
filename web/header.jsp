<%-- 
    Document   : header
    Created on : Apr 15, 2019, 9:31:56 PM
    Author     : franc
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html>

<div class="header">
    <div class="left_header">
        <a href="login.html" id="logo">Free Peer Review</a>
        <form action="/milestone-1-Fransiscu/logout.html?logout=true" method="post">
            <input type="submit" value="Sign out" class="hide_other"/>
        </form>
    </div>
    <div class="right_header">
        <a href="articoli.html" class="top_button ${pageContext.request.requestURI eq '/milestone-1-Fransiscu/articoli.jsp' || pageContext.request.requestURI eq '/milestone-1-Fransiscu/scriviArticolo.jsp' ? ' current' : ''} ">Articoli</a>
        <a href="valutazione.jsp" class="top_button ${pageContext.request.requestURI eq '/milestone-1-Fransiscu/valutazione.jsp' ? ' current' : ''}">Valutazione</a>
        <a href="registrazione.html" class="top_button ${pageContext.request.requestURI eq '/milestone-1-Fransiscu/registrazione.jsp' ? ' current' : ''}">Profilo</a>
    </div>
    <script src="js/jquery.js"></script>
</div>
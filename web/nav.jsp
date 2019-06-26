<%-- 
    Document   : nav
    Created on : Apr 15, 2019, 9:32:21 PM
    Author     : franc
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page import="fpw.model.User"%>
<%@page import="fpw.model.Paper"%>
<!DOCTYPE html>
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
            <div class="side_bar hide_mobile">
                <h3> I miei articoli: </h3>
                <div class="side_list">

                    <ul>
                        <c:forEach items="${papers}" var="p" begin="0" end="2">
                            <li><a href="scriviArticolo.html?id=${p.getID()}">  ${p.getTitle()} </a> </li>
                            </c:forEach>
                    </ul>
                </div>
            </div>

            <div class="side_bar hide_mobile">
                <h3> Da valutare: </h3>
                <div class="side_list">
                    <ul>
                        <c:forEach items="${toGrade}" var="p" begin="0" end="2">
                            <li><a href="scriviArticolo.html?id=${p.getID()}">  ${p.getTitle()} </a> </li>
                            </c:forEach>
                    </ul>
                </div>
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
<%-- 
    Document   : utentiTrovati
    Created on : Jun 10, 2019, 11:15:59 AM
    Author     : fran
--%>


<%@page contentType="application/json" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="json" uri="http://www.atg.com/taglibs/json"%>

<json:array>
    <c:forEach var="user" items="${usersList}">
        <json:object>
            <json:property name="nome" value="${user.getName()}"/>
            <json:property name="cognome" value="${user.getSurname()}" />
            <json:property name="id" value="${user.getUserID()}" />
        </json:object>
    </c:forEach>
</json:array>
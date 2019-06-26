<%-- 
    Document   : login
    Created on : Apr 22, 2019, 7:52:11 PM
    Author     : franc
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="it">
    <head>
        <title>Log in</title>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <meta name="author" content="Francesco Soru">
        <meta name="keywords" content="FPW, Progetto, HTML, CSS, JAVA">
        <meta name="description" content="Pagina di login">
        <link rel="stylesheet" type="text/css" href="style.css" media="screen">
    </head>

    <body>
        <div class="header">
            <div class="left_header">
                <a href="login.html" id="logo">Free Peer Review</a>
            </div>
            <div class="right_header"></div>
        </div>

        <div class="row">
            <div class="col-2 col-s-2">
            </div>
            <div class="col-10 col-s-10 content">
                
                <h1 class="centered">Effettua il login</h1>
                <div class="login_form">
                    <form action="login.html" method="post"> 
                        <label for="id_login_email">Email: </label>
                        <br/>
                        <input type="text" name="emailAddress" id="id_login_email">
                        <br/><br/>
                        <label for="id_login_password">Password: </label>
                        <br/>
                        <input type="password" name="password" id="id_login_password"/>
                        <br/><br/>
                        <input type="submit" value="Login" class="button"/>
                        <br/><br/>
                        <div id="signup_request">
                            Non hai un profilo? <a href="registrazione.html"> Registrati  </a>
                        </div>
                    </form>                
                </div>
                </div>
                
                
        </div>

        <jsp:include page="footer.jsp"/>

    </body>
</html>
<%-- 
    Document   : registrazione
    Created on : Apr 15, 2019, 9:33:05 PM
    Author     : franc
--%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="it">
    <head>
        <title>Registrazione</title>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <meta name="author" content="Francesco Soru">
        <meta name="keywords" content="FPW, Progetto, HTML, CSS, JAVA">
        <meta name="description" content="Pagina per la registrazione al sito">
        <link rel="stylesheet" type="text/css" href="style.css" media="screen">
    </head>
    <body> 
        <jsp:include page="header.jsp"/>
        <div class="row">
            <jsp:include page="nav.jsp"/>
            <div class="col-10 col-s-10 content">
                
                <div class="edit_profile">
                   
                    <c:choose>
                        <c:when test="${registered eq false}">
                             <h1>Benvenuto</h1>
                             <h4>Compila il form di seguito per procedere con la registrazione</h4>   
                            <form action="registrazione" method="post">

                                <div class="edit_profile_tag">
                                    <label for="id_name"><b>Nome: </b></label>
                                </div>
                                <div class="edit_profile_input">
                                    <input type="text" name="Nome" id="id_name">
                                </div>
                                <div class="edit_profile_tag">
                                    <label for="id_surname"><b>Cognome: </b></label>
                                </div>
                                <div class="edit_profile_input">
                                    <input type="text" name="Cognome" id="id_surname">
                                </div>
                                <div class="edit_profile_tag">
                                    <label for ="id_username"><b>Username: </b></label>
                                </div>
                                <div class="edit_profile_input">
                                    <input type="text" name="Username" id="id_username">
                                </div>
                                <div class="edit_profile_tag">
                                    <label for="id_image"><b>Foto: </b></label>
                                </div>
                                <div class="edit_profile_input">
                                    <input type="text" name="Immagine" id="id_image"/>
                                </div>
                                <div class="edit_profile_tag">
                                    <label for="id_text"><b>Email: </b></label>
                                </div>
                                <div class="edit_profile_input">
                                    <input type="text" name="Email" id="id_text">
                                </div>
                                <div class="edit_profile_tag">
                                    <label for="id_password"><b>Password: </b></label>
                                </div>
                                <div class="edit_profile_input">
                                    <input type="password" name="Password" id="id_password">
                                </div>
                                <div class="edit_profile_tag">
                                    <label for="id_entity"><b>Ente: </b></label>
                                </div>
                                <div class="edit_profile_input">
                                    <input type="text" name="Ente" id="id_entity">
                                </div>
                                <div class="edit_profile_tag">

                                </div>
                                <div class="edit_profile_input">
                                    <input type="submit" value="Registrazione" id="submit_profile" class="button" />
                                </div>                                
                            </form>
                        </c:when>
                        <c:otherwise>
                            <div class="edit_profile_header">
                    <div class="edit_profile_tag">
                        <img src="img/blankprofile.png" alt="Immagine del profilo" id="profile_picture">
                    </div>
                    <br/>
                    <h1 class="centered">Il tuo Profilo!</h1> 
                </div>
                <div class="edit_profile">
                    <form action="#" method="post" id="edit_profile">
                        <div class="edit_profile_tag">
                            <label for="id_name"><b>Nome: </b></label>
                        </div>
                        <div class="edit_profile_input">
                            <input type="text" name="Nome" id="id_name" value="${user.getName()}">
                        </div>
                        <div class="edit_profile_tag">
                            <label for="id_surname"><b>Cognome: </b></label>
                        </div>
                        <div class="edit_profile_input">
                            <input type="text" name="Cognome" id="id_surname" value="${user.getSurname()}">
                        </div>
                        <div class="edit_profile_tag">
                            <label for="id_image"><b>Foto: </b></label>
                        </div>
                        <div class="edit_profile_input">
                            <input type="text" name="Immagine" id="id_image"/>
                        </div>
                        <div class="edit_profile_tag">
                            <label for="id_text"><b>Email: </b></label>
                        </div>
                        <div class="edit_profile_input">
                            <input type="text" name="Email" id="id_text" value="${user.getEmail()}">
                        </div>
                        <div class="edit_profile_tag">
                            <label for="id_password"><b>Password: </b></label>
                        </div>
                        <div class="edit_profile_input">
                            <input type="password" name="Password" id="id_password">
                        </div>
                        <div class="edit_profile_tag">
                            <label for="id_entity"><b>Ente: </b></label>
                        </div>
                        <div class="edit_profile_input">
                            <input type="text" name="Ente" id="id_entity" value="${user.getEntity()}">
                        </div>
                        <div class="edit_profile_tag">

                        </div>
                        <div class="edit_profile_input">
                            <input type="submit" value="Salva" id="submit_profile" class="button" />
                        </div>                                
                    </form>
                </div>
                    <div class="delete_account"> 
                        <a href="/milestone-1-Fransiscu/registrazione.html?deleteAccount=true">Cancellati</a>
                    </div>
                        </c:otherwise>
                    </c:choose>
                </div>

                <br/><br/>
            </div>
        </div>

        <jsp:include page="footer.jsp"/>

    </body>
</html>
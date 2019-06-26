<%-- 
    Document   : profilo
    Created on : Apr 16, 2019, 3:15:03 PM
    Author     : franc
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="fpw.model.User"%>
<!DOCTYPE html>
<html lang="it">
    <head>
        <title>Il tuo profilo</title>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <meta name="author" content="Francesco Soru">
        <meta name="keywords" content="FPW, Progetto, HTML, CSS, JAVA">
        <meta name="description" content="Pagino di visualizzazione e modifica del profilo">
        <link rel="stylesheet" type="text/css" href="style.css" media="screen">
    </head>
    <body>
        <jsp:include page="header.jsp"/>
        <div class="row">
            <jsp:include page="nav.jsp"/>
            <div class="col-10 col-s-10 content">
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
                            <input type="file" name="Immagine" id="id_image" accept="image/*"/>
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
                            <input type="submit" value="Invia" id="submit_profile" class="button" />
                        </div>                                
                    </form>
                </div>
                <div class="delete_account"> 
                    <a href="#">Cancellati</a>
                </div>
            </div>
        </div>
        <jsp:include page="footer.jsp"/>

    </body>
</html>
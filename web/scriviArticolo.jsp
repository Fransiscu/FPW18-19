<%-- 
    Document   : profilo
    Created on : Apr 16, 2019, 3:15:03 PM
    Author     : franc
--%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="fpw.model.User"%>
<%@page import="fpw.model.Paper"%>
<%@page import="fpw.model.PaperFactory"%>   
<!DOCTYPE html>
<html lang="it">
    <head>
        <title>Scrivi un articolo</title>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <meta name="author" content="Francesco Soru">
        <meta name="keywords" content="FPW, Progetto, HTML, CSS, JAVA">
        <meta name="description" content="Pagina per la composizione e invio di un articolo">
        <link rel="stylesheet" type="text/css" href="style.css" media="screen">
    </head>
    <body>
        <jsp:include page="header.jsp"/>
        <script src="js/search.js"></script>
        <div class="row">
            <jsp:include page="nav.jsp"/>
            <div class="col-10 col-s-10 content">
                <c:choose>
                    <c:when test="${user.isAuthor() && paper.getAuthor().getName() eq user.getName() && valid}" >               
                        <div class="scriviArticolo_header">
                            <h1> Composizione di un articolo </h1>
                            <h4>In questa pagina puoi scrivere e inviare un articolo</h4>
                        </div>
                        <div class="compose_article">
                            <form action="#" method="post">
                                <div class="compose_article_tag">
                                    <label for="id_title"><b>Titolo: </b></label>
                                </div>
                                <div class="compose_article_input">
                                    <input type="text" name="Titolo" id="id_title" value="${paper.getTitle()}"  autocomplete="off">
                                </div>
                                <div class="compose_article_tag">
                                    <label for="id_authors"><b>Autori:</b> </label>
                                </div>
                                <!-- Autori -->
                                <div class="compose_article_input">
                                    <ul id="current_author">
                                        <c:forEach items="${currentAuthors}" var="author" begin="0">
                                             <li>${author.getName()}, ${author.getSurname()} (${author.getUserID()})</li>
                                        </c:forEach> 
                                    </ul>
                                    <input type="text" id="author" name="author" autocomplete="off"><input type="submit" name="addAuthor" value="+" id="add_button">
                                    <div class="compose_article_input" id="div-utenti">
                                    <ul id="authorPick" class="author_list">    
                                        
                                    </ul>
                                    </div>
                                </div>
                                <div class="compose_article_tag">
                                    <label><b>Categorie:</b></label>
                                </div>
                                <div class="compose_article_input">
                                    <div class="pick_category"> <input type="checkbox" name="category" value="HTML" ${html}>HTML</div>
                                    <div class="pick_category"> <input type="checkbox" name="category" value="JSP" ${jsp}>JSP</div>    
                                    <div class="pick_category"> <input type="checkbox" name="category" value="CSS" ${css}>CSS</div>
                                    <br/>
                                    <div class="pick_category"> <input type="checkbox" name="category" value="JAVASCRIPT" ${javascript}>JS</div>
                                    <div class="pick_category"> <input type="checkbox" name="category" value="SERVLET" ${servlet}>SERVLET</div>
                                    <div class="pick_category"> <input type="checkbox" name="category" value="AJAX" ${ajax}>AJAX</div>
                                </div> 
                                <div class="compose_article_tag">
                                    <label for="id_image"><b>Immagine: </b></label>
                                </div>
                                <div class="compose_article_input">
                                    <input type="text" name="Immagine" id="id_image" value="${paper.getPicture()}"  autocomplete="off"/>
                                </div>
                                <div class="compose_article_tag">
                                    <label for="id_date"><b>Data: (YYYY-MM-DD)</b></label>
                                </div>
                                <div class="compose_article_input">
                                    <input type="text" name="Data" id="id_date" value="${paper.getSubmissionDate()}"  autocomplete="off">
                                </div>
                                <div class="compose_article_tag">
                                    <label for="id_text"><b>Testo: </b></label>
                                </div>
                                <div class="compose_article_input">
                                    <textarea rows="8" cols="20" name="Testo" id="id_text" placeholder="Inserisci qui il tuo testo" style="margin-bottom: 1%;">${paper.getContent()}</textarea>
                                </div>
                                <div class="compose_article_tag">
                                </div>
                                <div class="compose_article_input">
                                    <input type="submit" name="edit_paper" value="Invia" id="submit_article" class="button" />
                                </div>
                            </form>
                        </div>
                    </c:when>
                    <c:when test="${user.isAuthor() and paper.getAuthor() eq null}" >               
                        <div class="scriviArticolo_header">
                            <h1>Composizione di un articolo</h1>
                            <h4>In questa pagina puoi scrivere e inviare un articolo</h4>
                        </div>
                        <div class="compose_article">
                            <form action="#" method="post">
                                <div class="compose_article_tag">
                                    <label for="id_title"><b>Titolo: </b></label>
                                </div>
                                <div class="compose_article_input">
                                    <input type="text" name="Titolo" id="id_title" value="${paper.getTitle()}"  autocomplete="off">
                                </div>
                                <div class="compose_article_tag">
                                    <label for="id_authors"><b>Autori:</b> </label>
                                </div>
                                <!-- Autori --> 
                                <div class="compose_article_input">
                                    <ul id="current_author">
                                        <c:forEach items="${currentAuthors}" var="author" begin="0">
                                            
                                        </c:forEach> 
                                    </ul>
                                    <input type="text" id="author" name="author" autocomplete="off"><input type="submit" method="post" name="addAuthor" value="+" id="add_button">
                                    <div class="compose_article_input" id="div-utenti">
                                    <ul id="authorPick" class="author_list">    
                                        
                                    </ul>
                                    </div>
                                </div>
                                <div class="compose_article_tag">
                                    <label><b>Categorie:</b></label>
                                </div>
                                <div class="compose_article_input">
                                    <div class="pick_category"> <input type="checkbox" name="category" value="HTML" ${html}>HTML</div>
                                    <div class="pick_category"> <input type="checkbox" name="category" value="JSP" ${jsp}>JSP</div>    
                                    <div class="pick_category"> <input type="checkbox" name="category" value="CSS" ${css}>CSS</div>
                                    <br/>
                                    <div class="pick_category"> <input type="checkbox" name="category" value="JAVASCRIPT" ${javascript}>JS</div>
                                    <div class="pick_category"> <input type="checkbox" name="category" value="SERVLET" ${servlet}>SERVLET</div>
                                    <div class="pick_category"> <input type="checkbox" name="category" value="AJAX" ${ajax}>AJAX</div>
                                </div> 
                                <div class="compose_article_tag">
                                    <label for="id_image"><b>Immagine: </b></label>
                                </div>
                                <div class="compose_article_input">
                                    <input type="text" name="Immagine" id="id_image" value="${paper.getPicture()}" autocomplete="off"/>
                                </div>
                                <div class="compose_article_tag">
                                    <label for="id_date"><b>Data: (YYYY-MM-DD)</b></label>
                                </div>
                                <div class="compose_article_input">
                                    <input type="text" name="Data" id="id_date" value="${paper.getSubmissionDate()}" autocomplete="off">
                                </div>
                                <div class="compose_article_tag">
                                    <label for="id_text"><b>Testo: </b></label>
                                </div>
                                <div class="compose_article_input">
                                    <textarea rows="8" cols="20" name="Testo" id="id_text" placeholder="Inserisci qui il tuo testo" style="margin-bottom: 1%;">${paper.getContent()}</textarea>
                                </div>
                                <div class="compose_article_tag">
                                </div>
                                <div class="compose_article_input">
                                    <input type="submit" name="edit_paper" value="Invia" id="submit_article" class="button" />
                                </div>
                            </form>
                        </div>
                    </c:when>
                    <c:otherwise>
                        <div class="centered forbidden">
                            <h1> Accesso Negato </h1>
                            <p> Non hai i diritti necessari per visualizzare questa pagina<br> Assicurati di essere loggato e controlla che l'ID sia corretto</p>
                        </div>
                    </c:otherwise>
                </c:choose>

            </div>
        </div>
        <jsp:include page="footer.jsp"/>

    </body>
</html>
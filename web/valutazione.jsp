<%-- 
    Document   : valutazione
    Created on : Apr 22, 2019, 7:56:45 PM
    Author     : franc
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="it">
    <head>
        <title>Valutazione</title>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <meta name="author" content="Francesco Soru">
        <meta name="keywords" content="FPW, Progetto, HTML, CSS, JAVA">
        <meta name="description" content="Pagina di valutazione">
        <link rel="stylesheet" type="text/css" href="style.css" media="screen">
    </head>

    <body>
        <jsp:include page="header.jsp"/>
        <div class="row">
            <jsp:include page="nav.jsp"/>   
            <div class="col-10 col-s-10 content">
                <h1>Inbox chiude a marzo 2019. Ora Google spinge gli utenti verso Gmail</h1>
                <h2>Google ha deciso di chiudere l'app email Inbox: gli utenti avranno qualche mese di tempo per completare la transizione verso Gmail. Inbox doveva rappresentare la fusione tra Gmail e Google Now; per l'azienda è stato soltanto un bell'esperimento.</h2>
                <h4>> Autori: dday</h4>

                <img class="top_image" id="banner_top" src="img/top_picture.png" alt="Immagine di presentazione">
                <p class="picture_tag" id="categories">Categorie: <a href="#">HTML</a> <a href="#">CSS</a> <a href="#">PHP</a> </p>
                <p class="picture_tag">Data: <b class="date_text">19/03/2019</b></p><br/>
                <p>
                    <b class="sub_title">Google ha deciso di chiudere l'app email Inbox: gli utenti avranno qualche
                        mese di tempo per completare la transizione verso Gmail. Inbox doveva rappresentare la fusione 
                        tra Gmail e Google Now; per l'azienda è stato soltanto un bell'esperimento.</b><br/><br/>

                    <b>Google Inbox</b> Doveva rappresentare una nuova era per le email, "progettata per concentrarsi 
                    su ciò che conta davvero", o almeno così Google presentava Inbox quattro anni fa. Anche se era 
                    stata sviluppata per coesistere con Gmail, Inbox ha portato con sé tutta una serie di idee innovative 
                    su come l'email dovesse funzionare.
                    Voleva essere l'evoluzione naturale della "semplice" posta elettronica: si trattava praticamente di un
                    potenziamento con a supporto l'intelligenza artificiale che organizzava i messaggi e le attività. 
                    <b>Ora invece si chiude davvero baracca.</b>
                </p>
                <p>
                    <img class="side_image" id="preview_picture" src="img/content_left.png" alt="Immagine laterale">
                    Ad annunciare la chiusura è stata la stessa Google con un post sul blog ufficiale, senza 
                    però fare riferimento alle ragioni specifiche di questa netta decisione. Forse non ha mai 
                    fatto breccia nel grande pubblico, quindi Google ha deciso di concentrare gli sforzi solo su Gmail, 
                    che continua ad essere il punto di riferimento di tantissimi utenti quando si parla di email.
                </p>

                <p>
                    <b class="sub_title">Inbox come area sperimentale per le nuove funzioni</b> <br/>
                    "Dopo quattro anni abbiamo imparato molto su come rendere migliore il servizio di email e 
                    abbiamo inserito alcune esperienze di Inbox in Gmail [...] Abbiamo introdotto il nuovo Gmail 
                    ad aprile, incorporando tante funzionalità che avete amato in Inbox, più altre come Smart Compose, 
                    che aiuta a scrivere le email più velocemente", si legge sul blog.
                </p>

                <p>
                    Nel tempo Inbox si è trasformato (e forse lo è sempre stato) in uno spazio 
                    all'interno del quale testare le funzionalità più innovative e magari anche
                    non digeribili da tutti. 
                    Insomma un passo indietro per Google che da tempo cerca di portare ai propri utenti 
                    novità in seno ai propri servizi e molto spesso, con tanta carne al fuoco, capita di 
                    non avere la fortuna di proporre applicazioni, software o servizi davvero convincenti. 
                    <br/>Un esperimento che ormai volge alla conclusione:<br/>
                    "Sappiamo che cambiare è difficile, per questo abbiamo creato una guida di 
                    transizione per aiutarvi a passare da Inbox a Gmail facilmente", conclude Google.
                </p>
                <hr>

                <h1>Valutazione</h1>

                <form action="#" method="post">
                    <b style="margin-right: 20px">VOTO:</b> 
                    <label for="one">1</label>
                    <input type="radio" name="grade" id="one" value="one" />
                    <label for="two">2</label>
                    <input type="radio" name="grade" id="two" value="two" />
                    <label for="three">3</label>
                    <input type="radio" name="grade" id="three" value="three" />
                    <label for="four">4</label>
                    <input type="radio" name="grade" id="four" value="four" />
                    <label for="five">5</label>
                    <input type="radio" name="grade" id="five" value="five" />
                    <br/><br/><br/>
                    <b>COMMENTI PER GLI AUTORI:</b><br/><br/>
                    <textarea rows="4" name="author_comments" class="custom_textarea" maxlength="100" placeholder="Inserisci un commento qui"></textarea>
                    <br/><br/>
                    <b>COMMENTI PER GLI ORGANIZZATORI:</b><br/><br/>
                    <textarea rows="4" name="organizers_comments" class="custom_textarea" maxlength="100" placeholder="Inserisci un commento qui"></textarea>
                    <br/><br/>
                    <input type="submit" value="Invia" id="submit_form" class="button"/>
                </form>
                <br/>
            </div>
        </div> 
        <jsp:include page="footer.jsp"/>
    </body>
</html>
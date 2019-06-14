/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fpw.controller;

import fpw.model.DatabaseConnection;
import fpw.model.Paper;
import fpw.model.User;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import fpw.model.PaperFactory;
import fpw.model.UserFactory;
import utils.Utility;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.http.HttpSession;
import utils.AuthorTokenizer;

/**
 *
 * @author fran
 */
@WebServlet(name = "WritePaper", urlPatterns = {"/scriviArticolo.html"})
public class WritePaper extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        HttpSession session = request.getSession();
        String[] categories = null;
        String title = null;
        String authorName = null;
        String submissionDate = null;
        String content = null;
        String picture = null;
        String empty = "";
        Paper current = new Paper();
        Connection conn = null;
        int idInt;

        if (request.getSession().getAttribute("loggedIn") != null) {
            String id = request.getParameter("id");

            /* Se sto aggiungendo un nuovo articolo */
            if (request.getParameter("new") != null) {
                try {
                    conn = DatabaseConnection.getInstance().connectToDB();
                    conn.setAutoCommit(false);
                    Statement stmt = conn.createStatement();
                    String query = "insert into paper (id_paper, status, category) " + "values (default, 'APERTO', 'TEMP');";   // assegno aperto come status e temp come placeholder per la categoria.
                    System.out.println(query);
                    int rows = stmt.executeUpdate(query, Statement.RETURN_GENERATED_KEYS);
                    if (rows == 1) {
                        System.out.println("Insert ok!");
                    }
                    ResultSet set = stmt.getGeneratedKeys();    // controllo la entry appena generata
                    if (set.next()) {
                        idInt = set.getInt(1);
                        request.setAttribute("new_id", idInt);
                        System.out.println("id = " + idInt);
                        conn.commit();
                        set.close();
                        stmt.close();
                        conn.close();
                        response.sendRedirect("scriviArticolo.html?id=" + idInt);
                        return;
                    }
                    set.close();
                    stmt.close();
                    conn.close();

                } catch (SQLException e) {
                    Logger.getLogger(UserFactory.class.getName()).
                            log(Level.SEVERE, null, e);
                    if (conn != null) {
                        try {
                            conn.rollback();
                            System.out.println("rollback");
                        } catch (SQLException ex) {
                            Logger.getLogger(UserFactory.class.getName()).
                                    log(Level.SEVERE, null, ex);
                        }
                    }
                }
                /* Se l'id fornito non è null */
            } else if (id != null) {
                if (Paper.isIdValid(id)) {  // controllo sia valido
                    idInt = Integer.parseInt(id);   //parse to int
                    /* Assegno a paper il valore dell'articolo in questione preso dal db */
                    if (idInt == (int) idInt) {
                        request.setAttribute("id", idInt);
                        current = PaperFactory.getInstance().getPaperById(idInt);   // faccio un fetch qui
                        request.setAttribute("paper", current);
                        request.setAttribute("valid", true);

                        /* Pre-check del campo */
                        if (current.getCategory() != null) {
                            switch (current.getCategory()) {
                                case HTML:
                                    request.setAttribute("html", "checked");
                                    break;
                                case JSP:
                                    request.setAttribute("jsp", "checked");
                                    break;
                                case CSS:
                                    request.setAttribute("css", "checked");
                                    break;
                                case JAVASCRIPT:
                                    request.setAttribute("javascript", "checked");
                                    break;
                                case SERVLET:
                                    request.setAttribute("servlet", "checked");
                                    break;
                                case AJAX:
                                    request.setAttribute("ajax", "checked");
                                    break;
                                case TEMP:
                                    System.out.println("temp value assigned");  // placeholder
                                    break;
                                default:
                                    break;
                            }
                        }

                    }

                    /* Se viene premuto il tasto "+" per aggiungere un autore */
                    if (request.getParameter("addAuthor") != null) {
                        String user = request.getParameter("author");
                        System.out.println("input string = " + user);
                        
                        /* Controllo base sulla stringa */
                        if (!Utility.isStringNullOrEmpty(user)) {
                            AuthorTokenizer temp = new AuthorTokenizer(user);   // uso la classe fornita per splittare la stringa
                            String authToken = temp.getName() + " " + temp.getSurname();
                            System.out.println("authToken = " + authToken);
                            User author = UserFactory.getInstance().getUserByName(authToken); // cerco l'utente
                            if (author != null) {
                                try {
                                    // se esiste
                                    conn = DatabaseConnection.getInstance().connectToDB();
                                    conn.setAutoCommit(false);
                                    PreparedStatement stmt = null;
                                    int id_user = author.getUserID();   // ne prendo l'id
                                    System.out.println("autore = " + author.getName());
                                    String queryAuthor = "update paper set id_user = ? where id_paper = ?";
                                    stmt = conn.prepareStatement(queryAuthor);
                                    stmt.setInt(1, id_user);
                                    stmt.setInt(2, idInt);
                                    stmt.executeUpdate();
                                    
                                    title = request.getParameter("Titolo");
                                    if (!Utility.isStringNullOrEmpty(title)) {
                                        String queryTitle = "update paper set title = ? where id_paper = ?";
                                        stmt = conn.prepareStatement(queryTitle);
                                        stmt.setString(1, title);
                                        stmt.setInt(2, idInt);
                                        stmt.executeUpdate();
                                    } else {
                                        String queryTitle = "update paper set title = ? where id_paper = ?";
                                        stmt = conn.prepareStatement(queryTitle);
                                        stmt.setString(1, "temp title");
                                        stmt.setInt(2, idInt);
                                        stmt.executeUpdate();
                                    }
                                    
                                    conn.commit();
                                    conn.close();
                                    stmt.close();
                                    response.sendRedirect("scriviArticolo.html?id=" + idInt);
                                    return;
                                } catch (SQLException ex) {
                                    Logger.getLogger(WritePaper.class.getName()).log(Level.SEVERE, null, ex);
                                }
                            }
                        }

                    }

                    /* Se viene premuto il tasto per modificare l'articolo allora.. */
                    if (request.getParameter("edit_paper") != null) {
                        try {
                            conn = DatabaseConnection.getInstance().connectToDB();
                            conn.setAutoCommit(false);
                            PreparedStatement stmt = null;
                            /*  Utilizzo per tener traccia delle condizioni, che tutte sia soddisfatte.
                                Ad ogni passo faccio un logic and con false se la condizione non è soddisfatta.
                                Se alla fine ci sarà anche un solo false vuol dire che non sono stati inseriti
                                tutti i dati correttamente quindi non devo mandare alcun update all'articolo.
                             */
                            boolean check = true;

                            /* Gestisco il titolo */
                            title = request.getParameter("Titolo");
                            if (!Utility.isStringNullOrEmpty(title)) {
                                String queryTitle = "update paper set title = ? where id_paper = ?";
                                stmt = conn.prepareStatement(queryTitle);
                                stmt.setString(1, title);
                                stmt.setInt(2, idInt);
                                stmt.executeUpdate();
                            } else {
                                check = check && false; // e lo aggiorno con un and logico su false se non vengono rispettate le condizioni
                            }

                            /* Gestisco le categorie */
                            categories = request.getParameterValues("category");
                            if (categories != null && !categories.equals(empty)) {
                                System.out.println(categories[0]);
                                String queryCategory = "update paper set category = ? where id_paper = ?";
                                stmt = conn.prepareStatement(queryCategory);
                                stmt.setString(1, categories[0]);
                                stmt.setInt(2, idInt);
                                stmt.executeUpdate();
                            } else {
                                check = check && false;
                            }

                            /* Gestisco autore */
                            authorName = request.getParameter("Autori");
                            if (!Utility.isStringNullOrEmpty(authorName)) {
                                User author = UserFactory.getInstance().getUserByName(authorName); // cerco l'utente
                                if (author != null) {    // se esiste
                                    int id_user = author.getUserID();   // ne prendo l'id
                                    System.out.println("autore = " + author.getName());
                                    String queryAuthor = "update paper set id_user = ? where id_paper = ?";
                                    stmt = conn.prepareStatement(queryAuthor);
                                    stmt.setInt(1, id_user);
                                    stmt.setInt(2, idInt);
                                    stmt.executeUpdate();
                                }
                            }   

                            /* Gestisco data */
                            submissionDate = request.getParameter("Data");
                            if (!Utility.isStringNullOrEmpty(submissionDate)) {
                                if (Utility.isDateValid(submissionDate)) {
                                    System.out.println("data = " + submissionDate);
                                    String queryDate = "update paper set submissionDate = ? where id_paper = ?";
                                    PreparedStatement stmtDate = conn.prepareStatement(queryDate);
                                    stmtDate.setString(1, submissionDate);
                                    stmtDate.setInt(2, idInt);
                                    stmtDate.executeUpdate();
                                }
                            } else {
                                check = check && false;
                            }

                            /* Gestisco l'immagine */
                            picture = request.getParameter("Immagine");
                            System.out.println("immagine = " + picture);
                            if (!Utility.isStringNullOrEmpty(picture) && Utility.isUrlValid(picture)) {
                                String queryPicture = "update paper set picture = ? where id_paper = ?";
                                stmt = conn.prepareStatement(queryPicture);
                                stmt.setString(1, picture);
                                stmt.setInt(2, idInt);
                                stmt.executeUpdate();
                            } else {
                                check = check && false;
                            }

                            /* Gestisco content */
                            content = request.getParameter("Testo");
                            if (!Utility.isStringNullOrEmpty(content)) {
                                String queryContent = "update paper set content = ? where id_paper = ?";
                                stmt = conn.prepareStatement(queryContent);
                                stmt.setString(1, content);
                                stmt.setInt(2, idInt);
                                stmt.executeUpdate();
                            }

                            System.out.println("check value = " + check);
                            if (check) {
                                System.out.println("check true");
                                conn.commit();  // commit
                                System.out.println("commit chiuso");
                                conn.close();
                            } else { // non committo
                                System.out.println("check false");
                                conn.close();
                            }

                            if (stmt != null) { // se mai dovesse capitare che nessuno dei campi inseriti andasse bene
                                stmt.close();
                            }

                            //conn.close();
                            response.sendRedirect("articoli.html");
                            return;
                            /* Exceptions */
                        } catch (SQLException e) {
                            Logger.getLogger(UserFactory.class.getName()).
                                    log(Level.SEVERE, null, e);
                            if (conn != null) {
                                try {
                                    conn.rollback();
                                    System.out.println("Rollback");
                                } catch (SQLException ex) {
                                    Logger.getLogger(UserFactory.class.getName()).
                                            log(Level.SEVERE, null, ex);
                                }
                            }
                        }
                    }

                } else {
                    request.setAttribute("valid", false);
                    request.getRequestDispatcher("scriviArticolo.jsp").forward(request, response);
                }
            }
            request.getRequestDispatcher("scriviArticolo.jsp").forward(request, response);
        } else {
            request.getRequestDispatcher("scriviArticolo.jsp").forward(request, response);
            session.setAttribute("showNavbarName", false);
        }

    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}

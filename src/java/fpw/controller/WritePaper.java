/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fpw.controller;

import fpw.model.DatabaseConnection;
import fpw.model.Paper;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import fpw.model.PaperFactory;
import fpw.model.User;
import fpw.model.UserFactory;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.http.HttpSession;
import utils.Utility;

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
        Paper current = new Paper();
        int idInt;

        if (request.getSession().getAttribute("loggedIn") != null) {
            String id = request.getParameter("id");

            /* Se sto aggiungendo un nuovo articolo */
            if (request.getParameter("new") != null) {
                PaperFactory.getInstance().addPaper(request, response);
                return;
                
                /* Se l'id fornito non è null */
            } else if (id != null) {
                if (Paper.isIdValid(id)) {  // controllo sia valido
                    idInt = Integer.parseInt(id);   //parse to int
                    /* Assegno a paper il valore dell'articolo in questione preso dal db */
                    if (idInt == (int) idInt) {
                        ArrayList<User> currentAuthors = new ArrayList<User>();
                        request.setAttribute("id", idInt);
                        current = PaperFactory.getInstance().getPaperById(idInt);   // faccio un fetch qui
                        request.setAttribute("paper", current);
                        currentAuthors = current.getAuthors();
                        request.setAttribute("currentAuthors", currentAuthors);
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
                        System.out.println("adding author input string = " + user);
                        PaperFactory.getInstance().addAuthor(user, idInt);
                        response.sendRedirect("scriviArticolo.html?id=" + idInt);
                        return;
                    }

                    /* Se viene premuto il tasto per modificare l'articolo allora.. */
                    if (request.getParameter("edit_paper") != null) {
                        try {
                            title = request.getParameter("Titolo");
                            categories = request.getParameterValues("category");
                            authorName = request.getParameter("Autori");
                            submissionDate = request.getParameter("Data");
                            picture = request.getParameter("Immagine");
                            content = request.getParameter("Testo");
                            PaperFactory.getInstance().updatePaper(title, idInt, categories, submissionDate, picture, content, authorName);
                            response.sendRedirect("articoli.html");
                            return;
                            /* Exceptions */
                        } catch (Exception e) {
                            Logger.getLogger(UserFactory.class.getName()).
                                    log(Level.SEVERE, null, e);
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

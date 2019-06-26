/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fpw.controller;

import utils.Utility;
import fpw.model.User;
import fpw.model.UserFactory;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import utils.AuthorTokenizer;

/**
 *
 * @author fran
 */
@WebServlet(name = "SuggestAuthors", urlPatterns = {"/suggest.json"})
public class SuggestAuthors extends HttpServlet {

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
        List<User> foundUsers = null;
        String command = request.getParameter("cmd");

        if (command != null) {
            if (command.equals("author")) {
                System.out.println("suggestauthors");
                try {
                    String toSearch = request.getParameter("toSearch");
                    if (Utility.isStringNullOrEmpty(toSearch)) {
                        return;
                    }
                    foundUsers = UserFactory.getInstance().searchUsers(toSearch);
                    request.setAttribute("usersList", foundUsers);
                    response.setContentType("application/json");
                    response.setHeader("Cache-Control", "no-store, no-cache, must-revalidate"); 
                    request.getRequestDispatcher("utentiTrovati.jsp").forward(request, response); // nuovo jsp per json
                } catch (SQLException ex) {
                    Logger.getLogger(SuggestAuthors.class.getName()).log(Level.SEVERE, null, ex);
                }

            }
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

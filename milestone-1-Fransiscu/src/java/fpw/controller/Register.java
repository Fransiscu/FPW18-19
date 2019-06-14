package fpw.controller;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import fpw.model.DatabaseConnection;
import fpw.model.Paper;
import fpw.model.User;
import fpw.model.UserFactory;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author franc
 */
@WebServlet(name = "Register", urlPatterns = {"/registrazione.html"})
public class Register extends HttpServlet {

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
        Connection conn = null;
        User user = new User();

        if (request.getSession().getAttribute("loggedIn") != null) {

            /* Gestisco deleteAccount come parametro di richiesta */
            if (request.getParameter("deleteAccount") != null) {
                user = (User) session.getAttribute("user");

                try {
                    conn = DatabaseConnection.getInstance().connectToDB();
                    PreparedStatement stmtPapers = null;
                    PreparedStatement stmtUser = null;
                    PreparedStatement stmtReview = null;
                    int userID = user.getUserID();
                    System.out.println("User id = " + userID);
                    conn.setAutoCommit(false);
                                            
                    String reviewQuery = "delete from reviewer where id_user in (select * from reviewer where id_user = ?)";
                    String papersQuery = "delete from paper where id_user = ?";
                    String userQuery = "delete from user where id_user = ?";
                    
                    stmtReview = conn.prepareStatement(reviewQuery);
                    stmtReview.setInt(1, userID);
                    stmtReview.setInt(2, userID);
                    stmtReview.executeUpdate();

                    stmtPapers = conn.prepareStatement(papersQuery);
                    stmtPapers.setInt(1, userID);
                    stmtPapers.executeUpdate();
                    
                    stmtUser = conn.prepareStatement(userQuery);
                    stmtUser.setInt(1, userID);
                    stmtUser.executeUpdate();
                    
                    System.out.println("User id = " + userID);
                    System.out.println(papersQuery);
                    System.out.println(userQuery);
                    System.out.println(reviewQuery);

                    conn.commit();
                    
                    stmtReview.close();
                    stmtPapers.close();
                    stmtUser.close();
                    conn.close();
                    session.invalidate();
                    response.sendRedirect("login.html");

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
            } else {
                request.getRequestDispatcher("registrazione.jsp").forward(request, response);
                request.setAttribute("registered", true);
                session.setAttribute("showNavbarName", true);
            }

        } else {
            user = (User) session.getAttribute("user");
            request.setAttribute("registered", false);
            request.getRequestDispatcher("registrazione.jsp").forward(request, response);
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

package fpw.controller;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import fpw.model.User;
import fpw.model.Paper;
import fpw.model.Paper.Status;
import fpw.model.PaperFactory;
import fpw.model.UserFactory;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author franc
 */
@WebServlet(name = "Login", urlPatterns = {"/login.html"})
public class Login extends HttpServlet {

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


        // inizializzo la sessione e liste
        HttpSession session = request.getSession();
        List<Paper> papersList = new ArrayList<>();
        List<Paper> notGraded = new ArrayList<>();
        List<Paper> myPapers = new ArrayList<>();
        User user = (User) session.getAttribute("user");

        if (user == null) {
            try {
                /* Provo a prendere in ingresso i parametri inseriti dall'utente */
                String email = request.getParameter("emailAddress");
                String password = request.getParameter("password");
                user = UserFactory.getInstance().getUser(email, password);  // query al db per cercare e assegno il valore a user

                /* Se trovo un matach */
                if (user != null) {
                    myPapers = PaperFactory.getInstance().getPapersByAuthor(user); 
                    notGraded = PaperFactory.getInstance().getPapersByGrader(user);
                    /* Attributi di sessione vari */
                    session.setAttribute("papers", myPapers);
                    session.setAttribute("user", user);
                    session.setAttribute("loggedIn", true);
                    session.setAttribute("showNavbarName", true);
                    session.setAttribute("registered", true);
                    System.out.println("Logged in");
                    if (user.isAuthor()) {
                        session.setAttribute("toGrade", notGraded); // query per articoli da valutare dal db
                        response.sendRedirect("articoli.html");
                    } else if (user.isOrganizer()) {
                        response.sendRedirect("gestione.html");
                    }
                } else {
                    //request.setAttribute("loginError","Incorrect password");
                    request.getRequestDispatcher("login.jsp").forward(request, response);
                }
            } catch (SQLException ex) {
                Logger.getLogger(Login.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else {  // se l'utente è già autenticato
            if (user.isAuthor()) {
                response.sendRedirect("articoli.html");
            } else if (user.isOrganizer()) {
                response.sendRedirect("gestione.html");
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

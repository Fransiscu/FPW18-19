package fpw.controller;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import fpw.model.Paper;
import fpw.model.PaperFactory;
import fpw.model.User;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
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
@WebServlet(name = "Manage", urlPatterns = {"/gestione.html"})
public class Manage extends HttpServlet {

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
        /* Inizializzo variabili di sessione */
        HttpSession session = request.getSession(false);
        User user = new User();

        if(request.getSession().getAttribute("loggedIn") != null) {
            user = (User) session.getAttribute("user"); // prendo user se loggedIn true
        System.out.println(user.getName() + "entrato in gestione");
          if (user.isOrganizer()) { // se l'user è organizer
              List<Paper> allPapers = new ArrayList<>();
              allPapers = PaperFactory.getInstance().getPapers();
              session.setAttribute("papers", allPapers);  // setto tutti gli articoli per il gestore
          request.getRequestDispatcher("gestione.jsp").forward(request, response);
          return;
          }
          else if (user.isAuthor()) { 
              request.getRequestDispatcher("gestione.jsp").forward(request, response);
              return;
          }
        } else {
            request.getRequestDispatcher("articoli.jsp").forward(request, response);
            return;
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

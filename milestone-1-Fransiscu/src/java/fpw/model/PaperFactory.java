/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fpw.model;

import fpw.model.Paper.*;
import fpw.model.Paper.Category;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author franc
 */
public class PaperFactory {

    private static PaperFactory puppet;

    public static PaperFactory getInstance() {
        if (puppet == null) {
            puppet = new PaperFactory();
        }
        return puppet;
    }

    public List<Paper> getPapers() {
        List<Paper> papers = new ArrayList<>();
        String category = new String();
        String status = new String();
        try {
            Connection conn = DatabaseConnection.getInstance().connectToDB();
            Statement stmt = conn.createStatement();
            String query = "select * from paper;";
            ResultSet set = stmt.executeQuery(query);

            while (set.next()) {
                Paper current = new Paper();
                Grade temp = new Grade();
                current.setID(set.getInt("id_paper"));
                current.setAuthor(UserFactory.getInstance().getUserByID(set.getInt("id_user")));
                current.setTitle(set.getString("title"));
                current.setSubmissionDate(set.getString("submissionDate"));
                current.setContent(set.getString("content"));
                current.setPicture(set.getString("picture"));

                category = set.getString("category");    // trasformo string to enum
                Category categoryValue = Category.valueOf(category);
                current.setCategory(categoryValue);

                status = set.getString("status");       // stesso discorso
                Status statusValue = Status.valueOf(status);
                current.setStatus(statusValue);
                
                temp = GradesFactory.getInstance().getGradeByPaperId(current.getID());
                current.setGrade(temp);

                papers.add(current);
            }
            stmt.close();
            conn.close();
            return papers;
        } catch (SQLException e) {
            Logger.getLogger(DatabaseConnection.class.getName())
                    .log(Level.SEVERE, null, e);
        }
        return papers;
    }

    public Paper getPaperById(int id) {
        Boolean loggedIn;
        String category = new String();
        String status = new String();

        try {
            Connection conn = DatabaseConnection.getInstance().connectToDB();
            String query = "select * from paper where " + "id_paper = ?";
            PreparedStatement stmt = conn.prepareStatement(query);
            stmt.setInt(1, id);
            ResultSet set = stmt.executeQuery();
            loggedIn = set.next();  // controllo se c'è almeno una riga

            if (loggedIn) {
                Paper current = new Paper();
                current.setID(set.getInt("id_paper"));
                current.setAuthor(UserFactory.getInstance().getUserByID(set.getInt("id_user")));
                current.setTitle(set.getString("title"));
                current.setSubmissionDate(set.getString("submissionDate"));
                current.setContent(set.getString("content"));
                current.setPicture(set.getString("picture"));

                category = set.getString("category");    // trasformo string to enum
                Category categoryValue = Category.valueOf(category);
                current.setCategory(categoryValue);

                status = set.getString("status");       // stesso discorso
                Status statusValue = Status.valueOf(status);
                current.setStatus(statusValue);
                stmt.close();
                conn.close();
                return current;
            } else {
                set.close();
                stmt.close();
                conn.close();
                return null;
            }
        } catch (SQLException e) {
            Logger.getLogger(DatabaseConnection.class.getName())
                    .log(Level.SEVERE, null, e);
        }
        return null;
    }

    public List<Paper> getPapersByAuthor(User usr) {
        String category = new String();
        String status = new String();
        ArrayList<Paper> papers = new ArrayList<>();
        int userId = usr.getUserID();

        try {
            Connection conn = DatabaseConnection.getInstance().connectToDB();
            String query = "select * from paper where " + "id_user = ?";
            PreparedStatement stmt = conn.prepareStatement(query);
            stmt.setInt(1, userId);
            ResultSet set = stmt.executeQuery();

            while (set.next()) {
                Paper current = new Paper();
                current.setID(set.getInt("id_paper"));
                current.setAuthor(UserFactory.getInstance().getUserByID(set.getInt("id_user")));
                current.setTitle(set.getString("title"));
                current.setSubmissionDate(set.getString("submissionDate"));
                current.setContent(set.getString("content"));
                current.setPicture(set.getString("picture"));

                category = set.getString("category");    // trasformo string to enum
                Category categoryValue = Category.valueOf(category);
                current.setCategory(categoryValue);

                status = set.getString("status");       // stesso discorso
                Status statusValue = Status.valueOf(status);
                current.setStatus(statusValue);

                papers.add(current);
            }
            stmt.close();
            conn.close();
            return papers;
        } catch (SQLException e) {
            Logger.getLogger(DatabaseConnection.class.getName())
                    .log(Level.SEVERE, null, e);
        }
        return null;
    }

    public List<Paper> getPaperByCategory(Category cat) {
        String category = new String();
        String tmpCategory = cat.name();
        String status = new String();
        ArrayList<Paper> papers = new ArrayList<>();

        try {
            Connection conn = DatabaseConnection.getInstance().connectToDB();
            String query = "select * from paper where " + "category = ?";
            PreparedStatement stmt = conn.prepareStatement(query);
            stmt.setString(1, tmpCategory);
            ResultSet set = stmt.executeQuery();

            while (set.next()) {
                Paper current = new Paper();
                current.setID(set.getInt("id_paper"));
                current.setAuthor(UserFactory.getInstance().getUserByID(set.getInt("id_user")));
                current.setTitle(set.getString("title"));
                current.setSubmissionDate(set.getString("submissionDate"));
                current.setContent(set.getString("content"));
                current.setPicture(set.getString("picture"));

                category = set.getString("category");    // trasformo string to enum
                Category categoryValue = Category.valueOf(category);
                current.setCategory(categoryValue);

                status = set.getString("status");       // stesso discorso
                Status statusValue = Status.valueOf(status);
                current.setStatus(statusValue);

                papers.add(current);
            }
            stmt.close();
            conn.close();
            return papers;
        } catch (SQLException e) {
            Logger.getLogger(DatabaseConnection.class.getName())
                    .log(Level.SEVERE, null, e);
        }
        return null;
    }

    public List<Paper> getPaperByStatus(Status stat) {
        String category = new String();
        String tmpStatus = stat.toString();
        String status = new String();
        ArrayList<Paper> papers = new ArrayList<>();

        try {
            Connection conn = DatabaseConnection.getInstance().connectToDB();
            String query = "select * from paper where " + "status = ?";
            PreparedStatement stmt = conn.prepareStatement(query);
            stmt.setString(1, tmpStatus);
            ResultSet set = stmt.executeQuery();

            while (set.next()) {
                Paper current = new Paper();
                current.setID(set.getInt("id_paper"));
                current.setAuthor(UserFactory.getInstance().getUserByID(set.getInt("id_user")));
                current.setTitle(set.getString("title"));
                current.setSubmissionDate(set.getString("submissionDate"));
                current.setContent(set.getString("content"));
                current.setPicture(set.getString("picture"));

                category = set.getString("category");    // trasformo string to enum
                //System.out.println(category);
                Category categoryValue = Category.valueOf(category);
                current.setCategory(categoryValue);

                status = set.getString("status");       // stesso discorso
                Status statusValue = Status.valueOf(status);
                current.setStatus(statusValue);

                papers.add(current);
            }
            stmt.close();
            conn.close();
            return papers;
        } catch (SQLException e) {
            Logger.getLogger(DatabaseConnection.class.getName())
                    .log(Level.SEVERE, null, e);
        }
        return null;
    }
    
    public List<Paper> getPapersByGrader(User usr) {
        ArrayList<Paper> papers = new ArrayList<>();
        
        System.out.println("List papers by grader from " + usr.getName());
        try {
            Connection conn = DatabaseConnection.getInstance().connectToDB();
            String query = "select * from reviewer where id_user = ?";
            PreparedStatement stmt = conn.prepareStatement(query);
            stmt.setInt(1, usr.getUserID());
            ResultSet set = stmt.executeQuery();

            while (set.next()) {
                Paper current = new Paper();
                current = this.getPaperById(set.getInt("id_paper"));
                papers.add(current);
            }
            stmt.close();
            conn.close();
            return papers;
        } catch (SQLException e) {
            Logger.getLogger(DatabaseConnection.class.getName())
                    .log(Level.SEVERE, null, e);
        }
        return null;
    }
    
       public void deleteLastPaper() {
        Connection conn = null;

        try {
            conn = DatabaseConnection.getInstance().connectToDB();
            PreparedStatement stmt = null;
            conn.setAutoCommit(false);
            String queryDelete = "delete from paper order by id_paper desc limit 1";
            System.out.println("String delete = " + queryDelete);
            stmt = conn.prepareStatement(queryDelete);
            stmt.executeUpdate();
            conn.commit();
            stmt.close();
            conn.close();
            
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
}

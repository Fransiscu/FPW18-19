/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fpw.model;

import fpw.controller.WritePaper;
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
import utils.AuthorTokenizer;
import utils.Utility;

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

    public void updatePaper(String title, int idInt, String[] categories, String submissionDate, String picture, String content) {
        Connection conn = null;
        String empty = "";

        try {
            conn = DatabaseConnection.getInstance().connectToDB();
            conn.setAutoCommit(false);
            PreparedStatement stmt = null;

            /* Gestisco il titolo */
            if (!Utility.isStringNullOrEmpty(title)) {
                String queryTitle = "update paper set title = ? where id_paper = ?";
                stmt = conn.prepareStatement(queryTitle);
                stmt.setString(1, title);
                stmt.setInt(2, idInt);
                stmt.executeUpdate();
            }

            /* Gestisco le categorie */
            if (categories != null && !categories.equals(empty)) {
                System.out.println(categories[0]);
                String queryCategory = "update paper set category = ? where id_paper = ?";
                stmt = conn.prepareStatement(queryCategory);
                stmt.setString(1, categories[0]);
                stmt.setInt(2, idInt);
                stmt.executeUpdate();
            }

            /* Gestisco l'autore 
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
            */

            /* Gestisco la data */
            if (!Utility.isStringNullOrEmpty(submissionDate)) {
                if (Utility.isDateValid(submissionDate)) {
                    System.out.println("data = " + submissionDate);
                    String queryDate = "update paper set submissionDate = ? where id_paper = ?";
                    PreparedStatement stmtDate = conn.prepareStatement(queryDate);
                    stmtDate.setString(1, submissionDate);
                    stmtDate.setInt(2, idInt);
                    stmtDate.executeUpdate();
                }
            }

            /* Gestisco l'immagine */
            if (!Utility.isStringNullOrEmpty(picture) && Utility.isUrlValid(picture)) {
                String queryPicture = "update paper set picture = ? where id_paper = ?";
                stmt = conn.prepareStatement(queryPicture);
                stmt.setString(1, picture);
                stmt.setInt(2, idInt);
                stmt.executeUpdate();
            }

            /* Gestisco il contenuto */
            if (!Utility.isStringNullOrEmpty(content)) {
                String queryContent = "update paper set content = ? where id_paper = ?";
                stmt = conn.prepareStatement(queryContent);
                stmt.setString(1, content);
                stmt.setInt(2, idInt);
                stmt.executeUpdate();
            }

            conn.commit();
            conn.close();
            if (stmt != null) { // se mai dovesse capitare che nessuno dei campi inseriti andasse bene
                stmt.close();
            }
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

    public void addAuthor(String user, int idInt) {
        Connection conn = null;

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

                    conn.commit();
                    conn.close();
                    stmt.close();

                } catch (SQLException ex) {
                    Logger.getLogger(WritePaper.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }
}

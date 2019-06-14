/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fpw.model;

import java.util.ArrayList;
import java.util.List;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author franc
 */
public class GradesFactory {
    private static GradesFactory puppet;
    
    public static GradesFactory getInstance() {
        if(puppet == null) {
            puppet = new GradesFactory();
        }
        return puppet;
    }
   public List<Grade> getGrades() {
    List<Grade> grades = new ArrayList<>();
                try (Connection conn = DatabaseConnection.getInstance().connectToDB()) {
                    Statement stmt = conn.createStatement();
                    String query = "select * from grade;";
                    ResultSet set = stmt.executeQuery(query);

                    while (set.next()) {
                        Grade current = new Grade();
                        current.setPaper(PaperFactory.getInstance().getPaperById(set.getInt("id_paper")));
                        current.setIsGraded(set.getBoolean("isGraded"));
                        current.setGrade(set.getInt("grade"));
                        current.setGradingDate(set.getString("gradingDate"));
                        current.setAuthorComment(set.getString("authorComments"));
                        current.setOrganizersComment(set.getString("organizerComments"));
                        grades.add(current);
                    }
                    stmt.close();
                    conn.close();
                    return grades;
        } catch (SQLException e) {
            Logger.getLogger(DatabaseConnection.class.getName())
                    .log(Level.SEVERE, null, e);
        }
    return grades;
   }
    
    public List<Grade> getPapersByGrade(int grade) {
        String category = new String();
        String status = new String();
        ArrayList<Grade> grades = new ArrayList<>();

        try {
            Connection conn = DatabaseConnection.getInstance().connectToDB();
            String query = "select * from grade where " + "grade = ?";
            PreparedStatement stmt = conn.prepareStatement(query);
            stmt.setInt(1, grade);
            ResultSet set = stmt.executeQuery();

            while (set.next()) {
                Grade current = new Grade();
                current.setPaper(PaperFactory.getInstance().getPaperById(set.getInt("id_paper")));
                current.setIsGraded(set.getBoolean("isGraded"));
                current.setGrade(set.getInt("grade"));
                current.setGradingDate(set.getString("gradingDate"));
                current.setAuthorComment(set.getString("authorComments"));
                current.setOrganizersComment(set.getString("organizerComments"));
                grades.add(current);
            }
            stmt.close();
            conn.close();
            return grades;
        } catch (SQLException e) {
            Logger.getLogger(DatabaseConnection.class.getName())
                    .log(Level.SEVERE, null, e);
        }
        return null;
    }
    
    public Grade getGradeByPaperId (int id) {
        try {
            Connection conn = DatabaseConnection.getInstance().connectToDB();
            String query = "select * from grade where id_paper = ?";
            PreparedStatement stmt = conn.prepareStatement(query);
            stmt.setInt(1, id);
            ResultSet set = stmt.executeQuery();
                System.out.println("Grade for paper id = " + id);
                while (set.next()) {
                Grade grade = new Grade();
                grade.setPaper(PaperFactory.getInstance().getPaperById(set.getInt("id_paper")));
                grade.setIsGraded(set.getBoolean("isGraded"));
                grade.setGrade(set.getInt("grade"));
                grade.setGradingDate(set.getString("gradingDate"));
                grade.setAuthorComment(set.getString("authorComments"));
                grade.setOrganizersComment(set.getString("organizerComments"));
                return grade;
                }
                
            stmt.close();
            conn.close();
            
        } catch (SQLException e) {
            Logger.getLogger(DatabaseConnection.class.getName())
                    .log(Level.SEVERE, null, e);
        }
        return null;
    }
    
    public List<Grade> getGradesByAuthor(User author) {
        String category = new String();
        String status = new String();
        int userID = author.getUserID();
        ArrayList<Grade> grades = new ArrayList<>();

        try {
            Connection conn = DatabaseConnection.getInstance().connectToDB();
            String query = "select * from grade where " + "id_user = ?";
            PreparedStatement stmt = conn.prepareStatement(query);
            stmt.setInt(1, userID);
            ResultSet set = stmt.executeQuery();

            while (set.next()) {
                Grade current = new Grade();
                current.setPaper(PaperFactory.getInstance().getPaperById(set.getInt("id_paper")));
                current.setIsGraded(set.getBoolean("isGraded"));
                current.setGrade(set.getInt("grade"));
                current.setGradingDate(set.getString("gradingDate"));
                current.setAuthorComment(set.getString("authorComments"));
                current.setOrganizersComment(set.getString("organizerComments"));
                grades.add(current);
            }
            stmt.close();
            conn.close();
            return grades;
        } catch (SQLException e) {
            Logger.getLogger(DatabaseConnection.class.getName())
                    .log(Level.SEVERE, null, e);
        }
        return null;
    }
    
}

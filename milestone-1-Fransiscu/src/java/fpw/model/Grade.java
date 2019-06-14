/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fpw.model;

/**
 *
 * @author franc
 */
public class Grade {
    Paper paper;
    public boolean isGraded;
    public String gradingDate;
    public int grade;
    public String authorComments;
    public String organizersComments;   
    
    public Grade() {
        
    }
    
    public Grade(boolean graded, String gradingDate, int grade, String authorComments, String organizersComments) {
        this.isGraded = graded;
        this.gradingDate = gradingDate;
        this.grade = grade;
        this.authorComments = authorComments;
        this.organizersComments = organizersComments;
    }
    
    public Grade(boolean graded) {
        this.isGraded = graded;
    }
    
    public Paper getPaper() {
        return paper;
    }
    
    public void setPaper(Paper paper) {
        this.paper = paper;
    }
    
    public boolean isGradeValid(int grade) {
        return (grade >= 0 && grade <= 5);
    }
    
    public boolean getIsGraded() {
        return isGraded;
    }
    
    public void setIsGraded(boolean value) {
        isGraded = value;
    }
    
    public String getGradingDate() {
        return gradingDate;
    }
    
    public void setGradingDate(String date) {
        this.gradingDate = date;
    }
    
    public int getGrading() {
        return grade;
    }
    
    public void setGrade(int grade){
        this.grade = grade;
    }
    
    public String getAuthorComment() {
        return authorComments;
    }
    
    public void setAuthorComment(String comment) {
        this.authorComments = comment;
    }
    
    public String getOrganizersComment() {
        return organizersComments;
    }
    
    public void setOrganizersComment(String comment) {
        this.organizersComments = comment;
    }
}

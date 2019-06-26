/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fpw.model;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author franc
 */
public class Paper {

    public enum Status {
        APERTO, VALUTAZIONE, ACCETTATO, RIFIUTATO
    };

    public enum Category {
        HTML, CSS, SERVLET, JSP, JAVASCRIPT, AJAX, TEMP
    };

    Grade grading;
    public ArrayList<User> author = new ArrayList<User>();
    public int ID;
    public String title = "";
    public String submissionDate = "";
    public String content = "";
    public ArrayList<User> graders;
    public String picture;
    Status status;
    Category category;

    public Paper() {

    }

    public Paper(User author, int ID, String title, String submissionDate, String content, Status status, Category category, Grade grading) {
        this.author.add(author);
        this.ID = ID;
        this.title = title;
        this.submissionDate = submissionDate;
        this.content = content;
        this.status = status;
        this.category = category;
        this.grading = grading;
    }

    public Grade getGrade() {
        return grading;
    }

    public void setGrade(Grade grading) {
        this.grading = grading;
    }

    public User getAuthor() {
        return author.get(0);
    }
    
    public ArrayList<User> getAuthors() {
        return author;
    }

    public void setAuthor(User authors) {
        author.add(authors);
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public static boolean isIdValid(String id) {
        for (int i = 0; i < id.length(); i++) {
            if (Character.isDigit(id.charAt(i))) {
                //do nothing
            } else {
                return false;
            }
        }
        return (id.length() > 0);
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSubmissionDate() {
        return submissionDate;
    }

    public void setSubmissionDate(String date) {
        this.submissionDate = date;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }

    public ArrayList<User> getGraders() {
        return this.graders;
    }

    public void setGraders(ArrayList<User> graders) {
        this.graders = graders;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fpw.model;

import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;

/**
 *
 * @author franc
 */
public class User {
    public int userID;
    public String name = "";
    public String surname = "";
    public String email = "";
    public String password = "";
    public String birthDate = "";
    public String entity = "";
    public boolean isAuthor;
    public boolean isOrganizer;
    
    public User() {
        
    }
    
    public User(int userID, String name, String surname, String email, String password, String birthDate, boolean isAuthor, boolean isOrganizer) {
        this.userID = userID;
        this.name = name;
        this.surname = surname;
        this.email = email;
        this.password = password;
        this.birthDate = birthDate;
        this.isAuthor = isAuthor;
        this.isOrganizer = isOrganizer;
    }
    
    public int getUserID(){
        return userID;
    }
    
    public void setUserID(int userID) {
        this.userID = userID;
    }
    
    public String getName() {
        return name;
    }
    
    public void setName(String name) {
        this.name = name;
    }
    
    public String getSurname() {
        return surname;
    }
    
    public void setSurname(String surname) {
        this.surname = surname;
    }
    
    public String getEmail() {
        return email;
    }
    
    public void setEmail(String email) {
        if(isEmailValid(email)){
            this.email = email;
        }
        else {
            this.email = "invalid";
        }
    }
    
    public String getPassword() {
        return password;
    }
    
    public void setPassword(String password) {
        this.password = password;
    }
    
    public String getBirthdate() {
        return birthDate;
    }
    
    public void setBirthdate(String birthdate) {
        if(isEmailValid(birthdate)){
            this.birthDate = birthdate;
        }
        else {
           this.birthDate = birthdate;
        }
    }
    
    public boolean isBirthdateValid(String birthdate) {
        return true;
    }
    
    public boolean isEmailValid(String email){
        boolean result = true;
        try {
           InternetAddress check = new InternetAddress(email);
           check.validate();
        } catch (AddressException ex) {
           result = false;
        }
        return result;
    }
    
    public String getEntity() {
        return entity;
    }
    
    public void setEntity(String entity) {
        this.entity = entity;
    }
    
    public void setIsAuthor(boolean statement) {
        this.isAuthor = statement;
    }
    
    public boolean isAuthor() {
        return isAuthor;
    }
    
    public void setIsOrganizer(boolean statement) {
        this.isOrganizer = statement;
    }
    
    public boolean isOrganizer() {
        return isOrganizer;
    }
}

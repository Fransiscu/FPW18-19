/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fpw.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author fran
 */
public class DatabaseConnection {
    private static DatabaseConnection instance;
    private static final String DatabaseURL = "jdbc:mysql://localhost:3306/fpw19_DbSoruFrancesco";
    private static final String AdminUsername = "fpw19_SoruFrancesco";
    private static final String AdminPassword = "freePeerReview";
    Connection conn = null;
    

    private DatabaseConnection() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
        }
        catch (ClassNotFoundException e) {
            Logger.getLogger(DatabaseConnection.class.getName())
                    .log(Level.SEVERE,null, e);
        }
    }

    public static DatabaseConnection getInstance() {
        if (instance == null) {
            instance = new DatabaseConnection();
        }
        return instance;
    }
    
    public Connection connectToDB(){
        try {
            Connection conn = DriverManager.getConnection(DatabaseURL, AdminUsername, AdminPassword);
            return conn; // db connection succeded
        } catch (SQLException e) {
            Logger.getLogger(DatabaseConnection.class.getName())
                    .log(Level.SEVERE,null, e);
        }
        return null; // db connection failed
    }
}

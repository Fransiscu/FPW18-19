/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fpw.model;

import utils.Utility;
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
public class UserFactory {

    private static UserFactory puppet;

    public UserFactory() {
    }

    public static UserFactory getInstance() {
        if (puppet == null) {
            puppet = new UserFactory();
        }
        return puppet;
    }

    public List<User> getUsers() throws SQLException {
        List<User> user = new ArrayList<>();
        try {
            Connection conn = DatabaseConnection.getInstance().connectToDB();
            Statement stmt = conn.createStatement();
            String query = "select * from user;";
            ResultSet set = stmt.executeQuery(query);

            while (set.next()) {
                User current = new User();
                current.setUserID(set.getInt("id_user"));
                current.setName(set.getString("name"));
                current.setSurname(set.getString("surname"));
                current.setEmail(set.getString("email"));
                current.setPassword(set.getString("password"));
                current.setBirthdate(set.getString("birthDate")); // ??
                current.setEntity(set.getString("entity"));
                current.setIsOrganizer(set.getBoolean("organizer"));
                current.setIsAuthor(set.getBoolean("author"));
                user.add(current);
            }
            stmt.close();
            conn.close();
            return user;
        } catch (SQLException e) {
            Logger.getLogger(DatabaseConnection.class.getName())
                    .log(Level.SEVERE, null, e);
        }
        return user;
    }

    public User getUserByName(String fullName) {
        User usr = null;
        Connection conn = null;
        String name = Utility.splitIntoWords(fullName);
        Boolean loggedIn;

        System.out.println("nome = " + name);

        try {
            conn = DatabaseConnection.getInstance().connectToDB();
            conn.setAutoCommit(false);

            String query = "select * from user where " + "name = ? or surname = ?";
            PreparedStatement stmt = conn.prepareStatement(query);
            stmt.setString(1, name);
            stmt.setString(2, name);
            ResultSet set = stmt.executeQuery();
            loggedIn = set.next();  // controllo se c'è almeno una riga

            if (loggedIn) {
                int idUser = set.getInt("id_user");
                usr = this.getUserByID(idUser);
                set.close();
                stmt.close();
                conn.close();
                return usr;
            } else {
                set.close();
                stmt.close();
                conn.close();
                return null;
            }
        } catch (SQLException e) {
            Logger.getLogger(UserFactory.class.getName()).
                    log(Level.SEVERE, null, e);
            if (conn != null) {
                try {
                    conn.rollback();
                } catch (SQLException ex) {
                    Logger.getLogger(UserFactory.class.getName()).
                            log(Level.SEVERE, null, ex);
                }
            }
            return usr;
        }
    }

    public User getUser(String email, String password) throws SQLException {
        Boolean loggedIn;

        if (Utility.isStringNullOrEmpty(email) || Utility.isStringNullOrEmpty(password)) {
            return null;
        }

        try {
            Connection conn = DatabaseConnection.getInstance().connectToDB();
            String query = "select * from user where " + "email = ? and password = ?";
            PreparedStatement stmt = conn.prepareStatement(query);
            stmt.setString(1, email);
            stmt.setString(2, password);
            ResultSet set = stmt.executeQuery();
            loggedIn = set.next();  // controllo se c'è almeno una riga

            if (loggedIn) {
                User current = new User();
                current.setUserID(set.getInt("id_user"));
                current.setName(set.getString("name"));
                current.setSurname(set.getString("surname"));
                current.setEmail(set.getString("email"));
                current.setPassword(set.getString("password"));
                current.setBirthdate(set.getString("birthDate")); // ??
                current.setEntity(set.getString("entity"));
                current.setIsOrganizer(set.getBoolean("organizer"));
                current.setIsAuthor(set.getBoolean("author"));

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

    public User getUserByID(int id) throws SQLException {
        Boolean loggedIn;

        try {
            Connection conn = DatabaseConnection.getInstance().connectToDB();
            String query = "select * from user where " + "id_user = ?";
            PreparedStatement stmt = conn.prepareStatement(query);
            stmt.setInt(1, id);
            ResultSet set = stmt.executeQuery();
            loggedIn = set.next();  // controllo se c'è almeno una riga

            if (loggedIn) {
                User current = new User();
                current.setUserID(set.getInt("id_user"));
                current.setName(set.getString("name"));
                current.setSurname(set.getString("surname"));
                current.setEmail(set.getString("email"));
                current.setPassword(set.getString("password"));
                current.setBirthdate(set.getString("birthDate")); // ??
                current.setEntity(set.getString("entity"));
                current.setIsOrganizer(set.getBoolean("organizer"));
                current.setIsAuthor(set.getBoolean("author"));
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

    public List<User> searchUsers(String input) throws SQLException {
        List<User> result = new ArrayList<>();
        System.out.println("ricerca ajax");
        for (User user : this.getUsers()) {
            if (user.getName().contains(input) || user.getSurname().contains(input)) {
                result.add(user);
            }
        }
        return result;
    }
}

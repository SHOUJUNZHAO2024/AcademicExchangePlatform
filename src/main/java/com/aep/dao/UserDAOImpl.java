package com.aep.dao;

import com.aep.model.UserDTO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Implementation of the UserDAO interface.
 * 
 * Handles database interactions for the User table.
 * 
 * @author 
 * @version 1.0, November 2024
 */
public class UserDAOImpl implements UserDAO {

    private Connection connection;

    /**
     * Constructor initializes the database connection.
     */
    public UserDAOImpl() {
        try {
            this.connection = DBConnection.getInstance().getConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void createUser(UserDTO user) {
        String sql = "INSERT INTO User (username, password, email, user_type) VALUES (?, ?, ?, ?)";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setString(1, user.getUsername());
            ps.setString(2, user.getPassword());
            ps.setString(3, user.getEmail());
            ps.setString(4, user.getUserType());
            ps.executeUpdate();
            System.out.println("User registered successfully: " + user.getUsername());
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public UserDTO getUserByUsername(String username) {
        String sql = "SELECT * FROM User WHERE username = ?";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setString(1, username);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return mapResultSetToUser(rs);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public boolean authenticateUser(String username, String password) {
        String sql = "SELECT * FROM User WHERE username = ? AND password = ?";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setString(1, username);
            ps.setString(2, password);
            ResultSet rs = ps.executeQuery();
            return rs.next();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    /**
     * Helper method to map a ResultSet row to a UserDTO object.
     * 
     * @param rs the ResultSet from which to extract user data
     * @return a UserDTO object representing the user
     * @throws SQLException if a database access error occurs
     */
    private UserDTO mapResultSetToUser(ResultSet rs) throws SQLException {
        UserDTO user = new UserDTO();
        user.setUserId(rs.getInt("user_id"));
        user.setUsername(rs.getString("username"));
        user.setPassword(rs.getString("password"));
        user.setEmail(rs.getString("email"));
        user.setUserType(rs.getString("user_type"));
        return user;
    }
}


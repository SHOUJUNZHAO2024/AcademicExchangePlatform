package com.aep.dao;

import java.sql.*;

/**
 * Implementation of the AcademicInstitutionDAO interface.
 * 
 * Handles database interactions for the AcademicInstitution table.
 * 
 */
public class AcademicInstitutionDAOImpl implements AcademicInstitutionDAO {

    /**
     * Database connection object used for executing SQL queries.
     */
    private Connection connection;

    /**
     * Constructor initializes the database connection using a singleton DBConnection instance.
     */
    public AcademicInstitutionDAOImpl() {
        try {
            this.connection = DBConnection.getInstance().getConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    /**
     * Creates a new institution profile and stores it in the database.
     *
     * @param userId          the user ID associated with the institution
     * @param institutionName the name of the institution
     * @param address         the address of the institution
     */
    @Override
    public void createInstitutionProfile(int userId, String institutionName, String address) {
        String sql = "INSERT INTO AcademicInstitution (institution_id, institution_name, address) VALUES (?, ?, ?)";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setInt(1, userId);
            ps.setString(2, institutionName);
            ps.setString(3, address);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    /**
     * Checks if an institution profile exists for the given user ID.
     *
     * @param userId the user ID to check
     * @return true if an institution profile exists, false otherwise
     */
    @Override
    public boolean existsByUserId(int userId) {
        String sql = "SELECT 1 FROM AcademicInstitution WHERE institution_id = ?";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setInt(1, userId);
            ResultSet rs = ps.executeQuery();
            return rs.next(); // Returns true if a row exists
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
}


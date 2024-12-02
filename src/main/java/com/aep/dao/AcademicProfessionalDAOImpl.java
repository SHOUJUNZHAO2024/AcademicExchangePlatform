package com.aep.dao;

import java.sql.*;

/**
 * Implementation of the AcademicProfessionalDAO interface.
 * Handles database interactions for the AcademicProfessional table.
 * 
 */
public class AcademicProfessionalDAOImpl implements AcademicProfessionalDAO {

    /**
     * Database connection object used for executing SQL queries.
     */
    private Connection connection;

    /**
     * Constructor initializes the database connection using a singleton DBConnection instance.
     */
    public AcademicProfessionalDAOImpl() {
        try {
            this.connection = DBConnection.getInstance().getConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    /**
     * Creates a professional profile for the given user ID.
     *
     * @param userId the user ID of the professional
     * @param firstName the first name of the professional
     * @param lastName the last name of the professional
     * @param currentInstitution the current institution where the professional is employed
     * @param academicPosition the academic position of the professional (e.g., Professor, Lecturer)
     * @param educationBackground the educational background of the professional
     * @param expertise the area of expertise of the professional
     */
    @Override
    public void createProfessionalProfile(int userId, String firstName, String lastName, String currentInstitution,
                                          String academicPosition, String educationBackground, String expertise) {
        String sql = "INSERT INTO AcademicProfessional (professional_id, first_name, last_name, current_institution, academic_position, education_background, expertise) "
                   + "VALUES (?, ?, ?, ?, ?, ?, ?)";
        
        if (userId <= 0 || firstName == null || lastName == null || currentInstitution == null ||
        	    academicPosition == null || educationBackground == null || expertise == null) {
        	    throw new IllegalArgumentException("Invalid input data for creating professional profile");
        	}

        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setInt(1, userId);
            ps.setString(2, firstName);
            ps.setString(3, lastName);
            ps.setString(4, currentInstitution);
            ps.setString(5, academicPosition);
            ps.setString(6, educationBackground);
            ps.setString(7, expertise);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    /**
     * Checks if a professional profile exists for the given user ID.
     *
     * @param userId the user ID to check
     * @return true if a profile exists, false otherwise
     */
    @Override
    public boolean existsByUserId(int userId) {
        String sql = "SELECT 1 FROM AcademicProfessional WHERE professional_id = ?";
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

package com.aep.dao;

import com.aep.model.AcademicProfessionalDTO;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Implementation of the AcademicProfessionalDAO interface.
 * 
 * Handles database interactions for the AcademicProfessional table.
 * 
 * @author 
 * @version 1.0, November 2024
 */
public class AcademicProfessionalDAOImpl implements AcademicProfessionalDAO {

    private Connection connection;

    /**
     * Constructor initializes the database connection.
     */
    public AcademicProfessionalDAOImpl() {
        try {
            this.connection = DBConnection.getInstance().getConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void addAcademicProfessional(AcademicProfessionalDTO professional) {
        String sql = "INSERT INTO AcademicProfessional (professional_id, first_name, last_name, current_institution, academic_position, education_background, expertise) "
                   + "VALUES (?, ?, ?, ?, ?, ?, ?)";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setInt(1, professional.getProfessionalId());
            ps.setString(2, professional.getFirstName());
            ps.setString(3, professional.getLastName());
            ps.setString(4, professional.getCurrentInstitution());
            ps.setString(5, professional.getAcademicPosition());
            ps.setString(6, professional.getEducationBackground());
            ps.setString(7, professional.getExpertise());
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public AcademicProfessionalDTO getAcademicProfessionalById(int professionalId) {
        String sql = "SELECT * FROM AcademicProfessional WHERE professional_id = ?";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setInt(1, professionalId);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return mapResultSetToProfessional(rs);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<AcademicProfessionalDTO> getAllAcademicProfessionals() {
        List<AcademicProfessionalDTO> professionals = new ArrayList<>();
        String sql = "SELECT * FROM AcademicProfessional";
        try (Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                professionals.add(mapResultSetToProfessional(rs));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return professionals;
    }

    /**
     * Helper method to map a ResultSet row to an AcademicProfessionalDTO object.
     * 
     * @param rs the ResultSet
     * @return an AcademicProfessionalDTO object
     * @throws SQLException if a database access error occurs
     */
    private AcademicProfessionalDTO mapResultSetToProfessional(ResultSet rs) throws SQLException {
        AcademicProfessionalDTO professional = new AcademicProfessionalDTO();
        professional.setProfessionalId(rs.getInt("professional_id"));
        professional.setFirstName(rs.getString("first_name"));
        professional.setLastName(rs.getString("last_name"));
        professional.setCurrentInstitution(rs.getString("current_institution"));
        professional.setAcademicPosition(rs.getString("academic_position"));
        professional.setEducationBackground(rs.getString("education_background"));
        professional.setExpertise(rs.getString("expertise"));
        return professional;
    }
    
    @Override
    public void createProfessionalProfile(int userId, String firstName, String lastName, String currentInstitution,
                                          String academicPosition, String educationBackground, String expertise) {
        String sql = "INSERT INTO AcademicProfessional (professional_id, first_name, last_name, current_institution, academic_position, education_background, expertise) "
                   + "VALUES (?, ?, ?, ?, ?, ?, ?)";
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

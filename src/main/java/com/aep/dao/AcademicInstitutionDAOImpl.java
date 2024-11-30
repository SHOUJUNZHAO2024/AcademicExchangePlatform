package com.aep.dao;

import com.aep.model.AcademicInstitutionDTO;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Implementation of the AcademicInstitutionDAO interface.
 * 
 * Handles database interactions for the AcademicInstitution table.
 * 
 * @author 
 * @version 1.0, November 2024
 */
public class AcademicInstitutionDAOImpl implements AcademicInstitutionDAO {

    private Connection connection;

    /**
     * Constructor initializes the database connection.
     */
    public AcademicInstitutionDAOImpl() {
        try {
            this.connection = DBConnection.getInstance().getConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void addAcademicInstitution(AcademicInstitutionDTO institution) {
        String sql = "INSERT INTO AcademicInstitution (institution_id, institution_name, address) VALUES (?, ?, ?)";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setInt(1, institution.getInstitutionId());
            ps.setString(2, institution.getInstitutionName());
            ps.setString(3, institution.getAddress());
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public AcademicInstitutionDTO getAcademicInstitutionById(int institutionId) {
        String sql = "SELECT * FROM AcademicInstitution WHERE institution_id = ?";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setInt(1, institutionId);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return mapResultSetToInstitution(rs);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<AcademicInstitutionDTO> getAllAcademicInstitutions() {
        List<AcademicInstitutionDTO> institutions = new ArrayList<>();
        String sql = "SELECT * FROM AcademicInstitution";
        try (Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                institutions.add(mapResultSetToInstitution(rs));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return institutions;
    }

    /**
     * Helper method to map a ResultSet row to an AcademicInstitutionDTO object.
     * 
     * @param rs the ResultSet
     * @return an AcademicInstitutionDTO object
     * @throws SQLException if a database access error occurs
     */
    private AcademicInstitutionDTO mapResultSetToInstitution(ResultSet rs) throws SQLException {
        AcademicInstitutionDTO institution = new AcademicInstitutionDTO();
        institution.setInstitutionId(rs.getInt("institution_id"));
        institution.setInstitutionName(rs.getString("institution_name"));
        institution.setAddress(rs.getString("address"));
        return institution;
    }
    
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


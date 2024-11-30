package com.aep.dao;

import com.aep.model.TeachRequestDTO;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Implementation of the TeachRequestDAO interface.
 * 
 * Handles database interactions for the TeachRequest table.
 * 
 * Provides methods to create, read, and update teach requests in the database.
 * 
 * @author 
 * @version 1.0, November 2024
 */
public class TeachRequestDAOImpl implements TeachRequestDAO {

    private Connection connection;

    /**
     * Constructor initializes the database connection.
     */
    public TeachRequestDAOImpl() {
        try {
            this.connection = DBConnection.getInstance().getConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void addTeachRequest(TeachRequestDTO request) {
        String sql = "INSERT INTO TeachRequest (course_id, professional_id, status, notification) VALUES (?, ?, ?, ?)";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setInt(1, request.getCourseId());
            ps.setInt(2, request.getProfessionalId());
            ps.setString(3, request.getStatus());
            ps.setBoolean(4, request.isNotification());
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public TeachRequestDTO getTeachRequestById(int requestId) {
        String sql = "SELECT * FROM TeachRequest WHERE request_id = ?";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setInt(1, requestId);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return mapResultSetToTeachRequest(rs);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<TeachRequestDTO> getTeachRequestsByProfessional(int professionalId) {
        List<TeachRequestDTO> requests = new ArrayList<>();
        String sql = "SELECT * FROM TeachRequest WHERE professional_id = ?";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setInt(1, professionalId);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                requests.add(mapResultSetToTeachRequest(rs));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return requests;
    }

    @Override
    public List<TeachRequestDTO> getTeachRequestsByCourse(int courseId) {
        List<TeachRequestDTO> requests = new ArrayList<>();
        String sql = "SELECT * FROM TeachRequest WHERE course_id = ?";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setInt(1, courseId);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                requests.add(mapResultSetToTeachRequest(rs));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return requests;
    }
    
    @Override
    public List<TeachRequestDTO> getNotificationsByProfessional(int professionalId) {
        List<TeachRequestDTO> notifications = new ArrayList<>();
        String sql = "SELECT tr.*, c.course_code, c.term, ai.institution_name " +
                     "FROM TeachRequest tr " +
                     "JOIN Course c ON tr.course_id = c.course_id " +
                     "JOIN AcademicInstitution ai ON c.institution_id = ai.institution_id " +
                     "WHERE tr.professional_id = ? AND tr.notification = true";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setInt(1, professionalId);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                TeachRequestDTO notification = new TeachRequestDTO();
                notification.setRequestId(rs.getInt("request_id"));
                notification.setCourseId(rs.getInt("course_id"));
                notification.setProfessionalId(rs.getInt("professional_id"));
                notification.setStatus(rs.getString("status"));
                notification.setCourseCode(rs.getString("course_code"));
                notification.setTerm(rs.getString("term"));
                notification.setInstitutionName(rs.getString("institution_name"));
                notifications.add(notification);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return notifications;
    }

    @Override
    public void updateTeachRequestStatus(int requestId, String status) {
        String sql = "UPDATE TeachRequest SET status = ? WHERE request_id = ?";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setString(1, status);
            ps.setInt(2, requestId);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void updateTeachRequestNotification(int requestId, boolean notification) {
        String sql = "UPDATE TeachRequest SET notification = ? WHERE request_id = ?";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setBoolean(1, notification);
            ps.setInt(2, requestId);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Helper method to map a ResultSet row to a TeachRequestDTO object.
     * 
     * @param rs the ResultSet
     * @return a TeachRequestDTO object
     * @throws SQLException if a database access error occurs
     */
    private TeachRequestDTO mapResultSetToTeachRequest(ResultSet rs) throws SQLException {
        TeachRequestDTO request = new TeachRequestDTO();
        request.setRequestId(rs.getInt("request_id"));
        request.setCourseId(rs.getInt("course_id"));
        request.setProfessionalId(rs.getInt("professional_id"));
        request.setStatus(rs.getString("status"));
        request.setNotification(rs.getBoolean("notification"));
        return request;
    }
}


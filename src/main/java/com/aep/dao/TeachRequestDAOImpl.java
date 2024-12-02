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
 */
public class TeachRequestDAOImpl implements TeachRequestDAO {

    /**
     * Database connection object used for executing SQL queries.
     */
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

    /**
     * Creates a new teach request and stores it in the database.
     * 
     * @param request the {@link TeachRequestDTO} object containing details of the teach request
     */
    @Override
    public void createTeachRequest(TeachRequestDTO request) {
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
    
    /**
     * Updates the status of a teach request (e.g., Pending, Accepted, Rejected).
     * 
     * @param requestId the ID of the teach request
     * @param status the new status of the request
     */
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

    /**
     * Retrieves a list of teach requests associated with a specific institution.
     * 
     * @param institutionId the ID of the institution
     * @return a list of {@link TeachRequestDTO} objects representing the teach requests
     */
    @Override
    public List<TeachRequestDTO> getTeachRequestsByInstitution(int institutionId) {
        String sql = "SELECT tr.request_id, c.course_title, p.first_name, p.last_name, tr.status " +
                     "FROM TeachRequest tr " +
                     "JOIN Course c ON tr.course_id = c.course_id " +
                     "JOIN AcademicProfessional p ON tr.professional_id = p.professional_id " +
                     "WHERE c.institution_id = ?";
        List<TeachRequestDTO> requests = new ArrayList<>();
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setInt(1, institutionId);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                TeachRequestDTO request = new TeachRequestDTO();
                request.setRequestId(rs.getInt("request_id"));
                request.setCourseTitle(rs.getString("course_title"));
                request.setProfessionalName(rs.getString("first_name") + " " + rs.getString("last_name"));
                request.setStatus(rs.getString("status"));
                requests.add(request);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return requests;
    }

    /**
     * Sets the notification flag for a specific teach request.
     * 
     * @param requestId    the ID of the teach request
     * @param notification the notification flag to set (true to enable, false to disable)
     */
    @Override
    public void setNotificationForProfessional(int requestId, boolean notification) {
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
     * Retrieves a list of teach requests with notifications enabled for a specific professional.
     * 
     * @param professionalId the ID of the academic professional
     * @return a list of {@link TeachRequestDTO} objects representing the notifications
     */
    @Override
    public List<TeachRequestDTO> getNotificationsForProfessional(int professionalId) {
        List<TeachRequestDTO> notifications = new ArrayList<>();
        String sql = "SELECT tr.request_id, tr.status, c.course_title, ai.institution_name " +
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
                notification.setStatus(rs.getString("status"));
                notification.setCourseTitle(rs.getString("course_title"));
                notification.setInstitutionName(rs.getString("institution_name"));
                notifications.add(notification);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return notifications;
    }

    



}


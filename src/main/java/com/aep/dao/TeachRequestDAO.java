package com.aep.dao;

import com.aep.model.TeachRequestDTO;
import java.util.List;

/**
 * TeachRequestDAO interface defines methods for interacting with the TeachRequest table in the database.
 * 
 * Provides CRUD operations and methods to fetch or update teach requests.
 * 
 * @author 
 * @version 1.0, November 2024
 */
public interface TeachRequestDAO {

    /**
     * Adds a new teach request to the database.
     * 
     * @param request the TeachRequestDTO object to add
     */
    void addTeachRequest(TeachRequestDTO request);

    /**
     * Fetches a teach request by its ID.
     * 
     * @param requestId the ID of the teach request
     * @return the TeachRequestDTO object, or null if not found
     */
    TeachRequestDTO getTeachRequestById(int requestId);

    /**
     * Fetches all teach requests for a specific academic professional.
     * 
     * @param professionalId the ID of the academic professional
     * @return a list of TeachRequestDTO objects
     */
    List<TeachRequestDTO> getTeachRequestsByProfessional(int professionalId);
    
    /**
     * Retrieves notifications for a specific academic professional.
     * 
     * @param professionalId the ID of the academic professional
     * @return a list of TeachRequestDTO objects representing the notifications
     */
    List<TeachRequestDTO> getNotificationsByProfessional(int professionalId);

    /**
     * Fetches all teach requests for a specific course.
     * 
     * @param courseId the ID of the course
     * @return a list of TeachRequestDTO objects
     */
    List<TeachRequestDTO> getTeachRequestsByCourse(int courseId);

    /**
     * Updates the status of a teach request (e.g., Pending, Accepted, Rejected).
     * 
     * @param requestId the ID of the teach request
     * @param status the new status of the request
     */
    void updateTeachRequestStatus(int requestId, String status);

    /**
     * Updates the notification status of a teach request.
     * 
     * @param requestId the ID of the teach request
     * @param notification the new notification status (true/false)
     */
    void updateTeachRequestNotification(int requestId, boolean notification);
    
    void submitTeachRequest(TeachRequestDTO teachRequest);
    
    List<TeachRequestDTO> getNotifications(int professionalId);
    
    void createTeachRequest(TeachRequestDTO request);
    void updateRequestStatus(int requestId, String status);
    List<TeachRequestDTO> getTeachRequestsByInstitution(int institutionId);
    
    void setNotificationForProfessional(int requestId, boolean notification);
    List<TeachRequestDTO> getNotificationsForProfessional(int professionalId);

}


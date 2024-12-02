package com.aep.dao;

import com.aep.model.TeachRequestDTO;
import java.util.List;

/**
 * TeachRequestDAO interface defines methods for interacting with the TeachRequest table in the database.
 * 
 * 
 */
public interface TeachRequestDAO {


    /**
     * Creates a new teach request and stores it in the database.
     * 
     * @param request the {@link TeachRequestDTO} object containing details of the teach request
     */
    void createTeachRequest(TeachRequestDTO request);
    
    /**
     * Updates the status of a teach request (e.g., Pending, Accepted, Rejected).
     * 
     * @param requestId the ID of the teach request
     * @param status the new status of the request
     */
    void updateTeachRequestStatus(int requestId, String status);

    /**
     * Retrieves a list of teach requests associated with a specific institution.
     * 
     * @param institutionId the ID of the institution
     * @return a list of {@link TeachRequestDTO} objects representing the teach requests
     */
    List<TeachRequestDTO> getTeachRequestsByInstitution(int institutionId);
    
    /**
     * Sets the notification flag for a specific teach request.
     * 
     * @param requestId    the ID of the teach request
     * @param notification the notification flag to set (true to enable, false to disable)
     */
    void setNotificationForProfessional(int requestId, boolean notification);
    
    /**
     * Retrieves a list of teach requests with notifications enabled for a specific professional.
     * 
     * @param professionalId the ID of the academic professional
     * @return a list of {@link TeachRequestDTO} objects representing the notifications
     */
    List<TeachRequestDTO> getNotificationsForProfessional(int professionalId);

}


package com.aep.junit;

import com.aep.dao.TeachRequestDAO;
import com.aep.dao.TeachRequestDAOImpl;
import com.aep.model.TeachRequestDTO;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Invalid test cases for the TeachRequestDAOImpl class.
 * Verifies the behavior of methods with invalid inputs.
 */
public class TeachRequestDAOImplInvalidTest {

    /**
     * Tests that creating a teach request with invalid data does not throw an exception.
     */
    @Test
    public void testCreateTeachRequestInvalid() {
        TeachRequestDAO teachRequestDAO = new TeachRequestDAOImpl();
        TeachRequestDTO request = new TeachRequestDTO();
        request.setCourseId(-1); // Invalid course ID
        request.setProfessionalId(-1); // Invalid professional ID
        request.setStatus(null); // Null status
        request.setNotification(false);

        assertDoesNotThrow(() -> teachRequestDAO.createTeachRequest(request), "Teach request creation with invalid data should not throw an exception");
    }

    /**
     * Tests that fetching teach requests for an invalid institution ID returns an empty list.
     */
    @Test
    public void testGetTeachRequestsByInvalidInstitution() {
        TeachRequestDAO teachRequestDAO = new TeachRequestDAOImpl();
        int institutionId = -1; // Invalid institution ID
        List<TeachRequestDTO> requests = teachRequestDAO.getTeachRequestsByInstitution(institutionId);

        assertNotNull(requests, "Teach requests list should not be null for an invalid institution ID");
        assertTrue(requests.isEmpty(), "Teach requests list should be empty for an invalid institution ID");
    }

    /**
     * Tests that fetching notifications for an invalid professional ID returns an empty list.
     */
    @Test
    public void testGetNotificationsForInvalidProfessional() {
        TeachRequestDAO teachRequestDAO = new TeachRequestDAOImpl();
        int professionalId = -1; // Invalid professional ID
        List<TeachRequestDTO> notifications = teachRequestDAO.getNotificationsForProfessional(professionalId);

        assertNotNull(notifications, "Notifications list should not be null for an invalid professional ID");
        assertTrue(notifications.isEmpty(), "Notifications list should be empty for an invalid professional ID");
    }
}

package com.aep.junit;

import com.aep.dao.TeachRequestDAO;
import com.aep.dao.TeachRequestDAOImpl;
import com.aep.model.TeachRequestDTO;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Valid test cases for the TeachRequestDAOImpl class.
 * Verifies the expected behavior of methods with valid inputs.
 */
public class TeachRequestDAOImplValidTest {

    /**
     * Tests that a teach request can be created successfully.
     */
    @Test
    public void testCreateTeachRequestValid() {
        TeachRequestDAO teachRequestDAO = new TeachRequestDAOImpl();
        TeachRequestDTO request = new TeachRequestDTO();
        request.setCourseId(1); // Assume a valid course ID
        request.setProfessionalId(1); // Assume a valid professional ID
        request.setStatus("Pending");
        request.setNotification(false);

        assertDoesNotThrow(() -> teachRequestDAO.createTeachRequest(request), "Teach request creation should not throw an exception");
    }

    /**
     * Tests that teach requests can be fetched for a valid institution ID.
     */
    @Test
    public void testGetTeachRequestsByValidInstitution() {
        TeachRequestDAO teachRequestDAO = new TeachRequestDAOImpl();
        int institutionId = 2; // Assume a valid institution ID
        List<TeachRequestDTO> requests = teachRequestDAO.getTeachRequestsByInstitution(institutionId);

        assertNotNull(requests, "Teach requests should not be null for a valid institution ID");
        assertFalse(requests.isEmpty(), "Teach requests should not be empty for a valid institution ID");
    }

    /**
     * Tests that notifications can be fetched for a valid professional ID.
     */
    @Test
    public void testGetNotificationsForValidProfessional() {
        TeachRequestDAO teachRequestDAO = new TeachRequestDAOImpl();
        int professionalId = 1; // Assume a valid professional ID
        List<TeachRequestDTO> notifications = teachRequestDAO.getNotificationsForProfessional(professionalId);

        assertNotNull(notifications, "Notifications should not be null for a valid professional ID");
    }
}


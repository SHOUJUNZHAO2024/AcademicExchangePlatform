package com.aep.junit;

import com.aep.dao.AcademicInstitutionDAO;
import com.aep.dao.AcademicInstitutionDAOImpl;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Valid test cases for the AcademicInstitutionDAOImpl class.
 * Verifies the expected behavior of methods when valid inputs are provided.
 */
public class AcademicInstitutionDAOImplValidTest {

    /**
     * Test case to verify that the `createInstitutionProfile` method creates a new institution profile without errors.
     */
    @Test
    public void testCreateInstitutionProfileValid() {
        AcademicInstitutionDAO institutionDAO = new AcademicInstitutionDAOImpl();

        int userId = 1; // valid user ID
        String institutionName = "Test Institution";
        String address = "123 Test Street";

        assertDoesNotThrow(() -> {
            institutionDAO.createInstitutionProfile(userId, institutionName, address);
        }, "Creating institution profile should not throw an exception");
    }

    /**
     * Test case to verify that the `existsByUserId` method returns true for an existing institution profile.
     */
    @Test
    public void testExistsByValidUserId() {
        AcademicInstitutionDAO institutionDAO = new AcademicInstitutionDAOImpl();

        int userId = 1; // valid user ID that exists in the database
        boolean exists = institutionDAO.existsByUserId(userId);

        assertTrue(exists, "existsByUserId should return true for an existing user ID");
    }
}


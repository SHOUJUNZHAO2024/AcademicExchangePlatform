package com.aep.junit;

import com.aep.dao.AcademicProfessionalDAOImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Test class for validating the functionality of the AcademicProfessionalDAOImpl class.
 * Focuses on handling invalid or incorrect inputs.
 */
class AcademicProfessionalDAOImplInvalidTest {

    /**
     * Declare a instance of the AcademicProfessionalDAOImpl.
     */
    private AcademicProfessionalDAOImpl academicProfessionalDAO;

    /**
     * Initializes the AcademicProfessionalDAOImpl instance before each test.
     */
    @BeforeEach
    void setUp() {
        academicProfessionalDAO = new AcademicProfessionalDAOImpl();
    }

    /**
     * Tests checking if a professional profile exists for an invalid user ID.
     */
    @Test
    void testExistsByInvalidUserId() {
        int invalidUserId = -1; //  A non-existent user ID from test database
        assertFalse(academicProfessionalDAO.existsByUserId(invalidUserId), "Profile should not exist for an invalid user ID");
    }

    /**
     * Tests creating a professional profile with invalid data to ensure it fails gracefully.
     */
    @Test
    void testCreateProfessionalProfileInvalid() {
        int userId = 0; // Invalid user ID
        String firstName = null;
        String lastName = null;
        String currentInstitution = null;
        String academicPosition = null;
        String educationBackground = null;
        String expertise = null;

        assertThrows(Exception.class, () -> academicProfessionalDAO.createProfessionalProfile(userId, firstName, lastName, currentInstitution, academicPosition, educationBackground, expertise),
                "Creating a professional profile with invalid data should throw an exception");
    }
}


package com.aep.junit;

import com.aep.dao.AcademicProfessionalDAOImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Test class for validating the functionality of the AcademicProfessionalDAOImpl class.
 * Focuses on correct behavior for valid use cases.
 */
class AcademicProfessionalDAOImplValidTest {

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
     * Tests creating a professional profile to ensure it works without exceptions.
     */
    @Test
    void testCreateProfessionalProfile() {
        int userId = 1; 
        String firstName = "John";
        String lastName = "Doe";
        String currentInstitution = "Harvard University";
        String academicPosition = "Professor";
        String educationBackground = "PhD in Computer Science";
        String expertise = "Artificial Intelligence";

        assertDoesNotThrow(() -> academicProfessionalDAO.createProfessionalProfile(userId, firstName, lastName, currentInstitution, academicPosition, educationBackground, expertise),
                "Creating a professional profile should not throw an exception");
    }

    /**
     * Tests checking if a professional profile exists for a valid user ID.
     */
    @Test
    void testExistsByUserId() {
        int userId = 1;
        assertTrue(academicProfessionalDAO.existsByUserId(userId), "Profile should exist for a valid user ID");
    }
}


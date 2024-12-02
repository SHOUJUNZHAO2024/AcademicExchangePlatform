package com.aep.junit;

import com.aep.dao.AcademicInstitutionDAO;
import com.aep.dao.AcademicInstitutionDAOImpl;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Invalid test cases for the AcademicInstitutionDAOImpl class.
 * Verifies the behavior of methods when invalid inputs are provided.
 */
public class AcademicInstitutionDAOImplInvalidTest {

    /**
     * Test case to verify that the `createInstitutionProfile` method does not throw an exception with invalid inputs.
     */
    @Test
    public void testCreateInstitutionProfileInvalid() {
        AcademicInstitutionDAO institutionDAO = new AcademicInstitutionDAOImpl();

        int userId = -1; // invalid user ID
        String institutionName = ""; // Empty institution name
        String address = ""; // Empty address

        assertDoesNotThrow(() -> {
            institutionDAO.createInstitutionProfile(userId, institutionName, address);
        }, "Creating institution profile with invalid inputs should not throw an exception");
    }

    /**
     * Test case to verify that the `existsByUserId` method returns false for a non-existing user ID.
     */
    @Test
    public void testExistsByInvalidUserId() {
        AcademicInstitutionDAO institutionDAO = new AcademicInstitutionDAOImpl();

        int userId = -1; // invalid user ID that does not exist in the database
        boolean exists = institutionDAO.existsByUserId(userId);

        assertFalse(exists, "existsByUserId should return false for a non-existing user ID");
    }
}

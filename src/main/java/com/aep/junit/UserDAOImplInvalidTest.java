package com.aep.junit;

import com.aep.dao.UserDAOImpl;
import com.aep.model.UserDTO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Test class for validating the functionality of the UserDAOImpl class.
 * Focuses on handling invalid or incorrect inputs.
 */
class UserDAOImplInvalidTest {

    /**
     * Declare a instance if UserDAOImpl.
     */
    private UserDAOImpl userDAO;

    /**
     * Initializes the UserDAOImpl instance before each test.
     */
    @BeforeEach
    void setUp() {
        userDAO = new UserDAOImpl();
    }

    /**
     * Tests fetching a non-existent user by username to ensure it returns null.
     */
    @Test
    void testGetUserByUsernameInvalid() {
        UserDTO user = userDAO.getUserByUsername("nonExistentUser");
        assertNull(user, "User should be null for non-existent username");
    }

    /**
     * Tests authenticating a user with incorrect credentials to ensure failure.
     */
    @Test
    void testAuthenticateUserInvalid() {
        boolean isAuthenticated = userDAO.authenticateUser("testUser", "wrongPassword");
        assertFalse(isAuthenticated, "Authentication should fail for incorrect password");

        isAuthenticated = userDAO.authenticateUser("nonExistentUser", "password123");
        assertFalse(isAuthenticated, "Authentication should fail for non-existent username");
    }
}


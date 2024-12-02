package com.aep.junit;

import com.aep.dao.UserDAOImpl;
import com.aep.model.UserDTO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Test class for validating the functionality of the UserDAOImpl class.
 * Focuses on correct behavior for valid use cases.
 */
class UserDAOImplValidTest {

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
     * Tests the creation of a user to ensure the method works without exceptions.
     */
    @Test
    void testCreateUser() {
        UserDTO user = new UserDTO();
        user.setUsername("testUser");
        user.setPassword("password123");
        user.setEmail("test@example.com");
        user.setUserType("Professional");

        assertDoesNotThrow(() -> userDAO.createUser(user), "Creating user should not throw an exception");
    }

    /**
     * Tests fetching a user by username to verify correct data retrieval.
     */
    @Test
    void testGetUserByUsername() {
        UserDTO user = userDAO.getUserByUsername("testUser");
        assertNotNull(user, "User should not be null");
        assertEquals("testUser", user.getUsername(), "Username should match");
        assertEquals("test@example.com", user.getEmail(), "Email should match");
    }

    /**
     * Tests authenticating a user with correct credentials to ensure success.
     */
    @Test
    void testAuthenticateUser() {
        boolean isAuthenticated = userDAO.authenticateUser("testUser", "password123");
        assertTrue(isAuthenticated, "User should be authenticated successfully");
    }
}


package com.aep.dao;

import com.aep.model.UserDTO;

/**
 * UserDAO interface defines methods for interacting with the User table in the database.
 * 
 * It provides CRUD operations and user authentication methods.
 * 
 * @author 
 * @version 1.0, November 2024
 */
public interface UserDAO {

    /**
     * Creates a new user in the database.
     * 
     * @param user the UserDTO object representing the user to be added
     */
    void createUser(UserDTO user);

    /**
     * Fetches a user from the database by their username.
     * 
     * @param username the username of the user to fetch
     * @return a UserDTO object representing the fetched user, or null if no user is found
     */
    UserDTO getUserByUsername(String username);

    /**
     * Authenticates a user by their username and password.
     * 
     * @param username the username of the user
     * @param password the password of the user
     * @return true if authentication is successful, false otherwise
     */
    boolean authenticateUser(String username, String password);
}

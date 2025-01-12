/**
 * File name: UserDTO.java
 * Author: Group10
 * Course: CST8288-section 031
 * Term: Fall2024
 * Assignment: Final Project
 * Date: November 30
 * Purpose: UserDTO represents a user in the system.
 */
package com.aep.model;

/**
 * UserDTO represents a user in the system.
 * It serves as a Data Transfer Object for the User table.
 * 
 */
public class UserDTO {

    /**
     * The unique ID of the user.
     */
    private int userId;

    /**
     * The username of the user.
     */
    private String username;

    /**
     * The password of the user.
     */
    private String password;

    /**
     * The email address of the user.
     */
    private String email;

    /**
     * The type of the user, which can be either "Professional" or "Institution".
     */
    private String userType;

    /**
     * Gets the user ID.
     * @return the user ID
     */
    public int getUserId() {
        return userId;
    }

    /**
     * Sets the user ID.
     * @param userId the user ID to set
     */
    public void setUserId(int userId) {
        this.userId = userId;
    }

    /**
     * Gets the username.
     * @return the username
     */
    public String getUsername() {
        return username;
    }

    /**
     * Sets the username.
     * @param username the username to set
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * Gets the password.
     * @return the password
     */
    public String getPassword() {
        return password;
    }

    /**
     * Sets the password.
     * @param password the password to set
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * Gets the email address.
     * @return the email address
     */
    public String getEmail() {
        return email;
    }

    /**
     * Sets the email address.
     * @param email the email address to set
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * Gets the user type (Professional or Institution).
     * @return the user type
     */
    public String getUserType() {
        return userType;
    }

    /**
     * Sets the user type.
     * @param userType the user type to set
     */
    public void setUserType(String userType) {
        this.userType = userType;
    }
}


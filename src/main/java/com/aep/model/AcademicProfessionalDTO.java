/**
 * File name: AcademicProfessionalDTO.java
 * Author: Group10
 * Course: CST8288-section 031
 * Term: Fall 2024
 * Assignment: Final Project
 * Date: November 30
 * Purpose: AcademicProfessionalDTO represents an academic professional in the system.
 */
package com.aep.model;

/**
 * AcademicProfessionalDTO represents an academic professional in the system.
 * It serves as a Data Transfer Object for the AcademicProfessional table.
 * 
 */

public class AcademicProfessionalDTO {

    /**
     * The unique ID of the academic professional.
     */
    private int professionalId;

    /**
     * The first name of the academic professional.
     */
    private String firstName;

    /**
     * The last name of the academic professional.
     */
    private String lastName;

    /**
     * The current institution where the academic professional is employed.
     */
    private String currentInstitution;

    /**
     * The academic position of the professional (e.g., Professor, Lecturer).
     */
    private String academicPosition;

    /**
     * The educational background of the academic professional.
     */
    private String educationBackground;

    /**
     * The area of expertise of the academic professional.
     */
    private String expertise;

    /**
     * Gets the unique ID of the academic professional.
     * @return the professional ID
     */
    public int getProfessionalId() {
        return professionalId;
    }

    /**
     * Sets the unique ID of the academic professional.
     * @param professionalId the professional ID to set
     */
    public void setProfessionalId(int professionalId) {
        this.professionalId = professionalId;
    }

    /**
     * Gets the first name of the academic professional.
     * @return the first name
     */
    public String getFirstName() {
        return firstName;
    }

    /**
     * Sets the first name of the academic professional.
     * @param firstName the first name to set
     */
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    /**
     * Gets the last name of the academic professional.
     * @return the last name
     */
    public String getLastName() {
        return lastName;
    }

    /**
     * Sets the last name of the academic professional.
     * @param lastName the last name to set
     */
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    /**
     * Gets the current institution where the academic professional is employed.
     * @return the current institution
     */
    public String getCurrentInstitution() {
        return currentInstitution;
    }

    /**
     * Sets the current institution where the academic professional is employed.
     * @param currentInstitution the current institution to set
     */
    public void setCurrentInstitution(String currentInstitution) {
        this.currentInstitution = currentInstitution;
    }

    /**
     * Gets the academic position of the professional.
     * @return the academic position
     */
    public String getAcademicPosition() {
        return academicPosition;
    }

    /**
     * Sets the academic position of the professional.
     * @param academicPosition the academic position to set
     */
    public void setAcademicPosition(String academicPosition) {
        this.academicPosition = academicPosition;
    }

    /**
     * Gets the educational background of the academic professional.
     * @return the educational background
     */
    public String getEducationBackground() {
        return educationBackground;
    }

    /**
     * Sets the educational background of the academic professional.
     * @param educationBackground the educational background to set
     */
    public void setEducationBackground(String educationBackground) {
        this.educationBackground = educationBackground;
    }

    /**
     * Gets the area of expertise of the academic professional.
     * @return the expertise
     */
    public String getExpertise() {
        return expertise;
    }

    /**
     * Sets the area of expertise of the academic professional.
     * @param expertise the expertise to set
     */
    public void setExpertise(String expertise) {
        this.expertise = expertise;
    }
}



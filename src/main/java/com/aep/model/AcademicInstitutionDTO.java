/**
 * File name: AcademicInstitutionDTO.java
 * Author: Group10
 * Course: CST8288-section 031
 * Term: Fall 2024
 * Assignment: Final Project
 * Date: November 30
 * Purpose: AcademicInstitutionDTO represents an academic institution in the system.
 */
package com.aep.model;

/**
 * AcademicInstitutionDTO represents an academic institution in the system.
 * It serves as a Data Transfer Object for the AcademicInstitution table.
 * 
 */

public class AcademicInstitutionDTO {

    /**
     * The unique ID of the academic institution.
     */
    private int institutionId;

    /**
     * The name of the academic institution.
     */
    private String institutionName;

    /**
     * The address of the academic institution.
     */
    private String address;

    /**
     * Gets the unique ID of the academic institution.
     * @return the institution ID
     */
    public int getInstitutionId() {
        return institutionId;
    }

    /**
     * Sets the unique ID of the academic institution.
     * @param institutionId the institution ID to set
     */
    public void setInstitutionId(int institutionId) {
        this.institutionId = institutionId;
    }

    /**
     * Gets the name of the academic institution.
     * @return the institution name
     */
    public String getInstitutionName() {
        return institutionName;
    }

    /**
     * Sets the name of the academic institution.
     * @param institutionName the institution name to set
     */
    public void setInstitutionName(String institutionName) {
        this.institutionName = institutionName;
    }

    /**
     * Gets the address of the academic institution.
     * @return the address
     */
    public String getAddress() {
        return address;
    }

    /**
     * Sets the address of the academic institution.
     * @param address the address to set
     */
    public void setAddress(String address) {
        this.address = address;
    }
}



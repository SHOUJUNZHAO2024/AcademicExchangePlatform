package com.aep.model;

/**
 * AcademicInstitutionDTO represents an academic institution in the system.
 * It serves as a Data Transfer Object for the AcademicInstitution table.
 * 
 */
public class AcademicInstitutionDTO {
    private int institutionId;
    private String institutionName;
    private String address;

    // Getters and Setters

    public int getInstitutionId() {
        return institutionId;
    }

    public void setInstitutionId(int institutionId) {
        this.institutionId = institutionId;
    }

    public String getInstitutionName() {
        return institutionName;
    }

    public void setInstitutionName(String institutionName) {
        this.institutionName = institutionName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}


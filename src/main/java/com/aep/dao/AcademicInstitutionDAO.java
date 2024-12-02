package com.aep.dao;

/**
 * AcademicInstitutionDAO interface defines methods for interacting with the
 * AcademicInstitution table in the database.
 * 
 */
public interface AcademicInstitutionDAO {

    /**
     * Creates an institution profile for the given user ID.
     *
     * @param userId the user ID of the institution
     * @param institutionName the name of the institution
     * @param address the address of the institution
     */
    void createInstitutionProfile(int userId, String institutionName, String address);
    
    /**
     * Checks if an institution profile exists for the given user ID.
     *
     * @param userId the user ID to check
     * @return true if an institution profile exists, false otherwise
     */
    boolean existsByUserId(int userId);
}


package com.aep.dao;

/**
 * AcademicProfessionalDAO interface defines methods for interacting with the
 * AcademicProfessional table in the database.
 * 
 */
public interface AcademicProfessionalDAO {
    
    /**
     * Creates a professional profile for the given user ID.
     *
     * @param userId the user ID of the professional
     * @param firstName the first name of the professional
     * @param lastName the last name of the professional
     * @param currentInstitution the current institution where the professional is employed
     * @param academicPosition the academic position of the professional (e.g., Professor, Lecturer)
     * @param educationBackground the educational background of the professional
     * @param expertise the area of expertise of the professional
     */
    void createProfessionalProfile(int userId, String firstName, String lastName, String currentInstitution,
            String academicPosition, String educationBackground, String expertise);
    
    /**
     * Checks if a professional profile exists for the given user ID.
     *
     * @param userId the user ID to check
     * @return true if a profile exists, false otherwise
     */
    boolean existsByUserId(int userId);
}


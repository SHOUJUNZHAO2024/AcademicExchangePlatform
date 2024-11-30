package com.aep.dao;

import com.aep.model.AcademicProfessionalDTO;
import java.util.List;

/**
 * AcademicProfessionalDAO interface defines methods for interacting with the
 * AcademicProfessional table in the database.
 * 
 * Provides CRUD operations and retrieval methods.
 * 
 * @author 
 * @version 1.0, November 2024
 */
public interface AcademicProfessionalDAO {

    /**
     * Adds a new academic professional to the database.
     * 
     * @param professional the AcademicProfessionalDTO object to add
     */
    void addAcademicProfessional(AcademicProfessionalDTO professional);

    /**
     * Fetches an academic professional by their ID.
     * 
     * @param professionalId the ID of the academic professional
     * @return the AcademicProfessionalDTO object, or null if not found
     */
    AcademicProfessionalDTO getAcademicProfessionalById(int professionalId);

    /**
     * Fetches all academic professionals from the database.
     * 
     * @return a list of AcademicProfessionalDTO objects
     */
    List<AcademicProfessionalDTO> getAllAcademicProfessionals();
    
    /**
     * Creates a professional profile for the given user ID.
     *
     * @param userId the user ID of the professional
     * @param firstName the first name of the professional
     * @param lastName the last name of the professional
     * @param expertise the expertise of the professional
     */
    void createProfessionalProfile(int userId, String firstName, String lastName, String currentInstitution,
            String academicPosition, String educationBackground, String expertise);
    
    boolean existsByUserId(int userId);
}


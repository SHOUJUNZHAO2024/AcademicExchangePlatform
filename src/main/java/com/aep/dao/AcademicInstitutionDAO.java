package com.aep.dao;

import com.aep.model.AcademicInstitutionDTO;
import java.util.List;

/**
 * AcademicInstitutionDAO interface defines methods for interacting with the
 * AcademicInstitution table in the database.
 * 
 * Provides CRUD operations and retrieval methods.
 * 
 * @author 
 * @version 1.0, November 2024
 */
public interface AcademicInstitutionDAO {

    /**
     * Adds a new academic institution to the database.
     * 
     * @param institution the AcademicInstitutionDTO object to add
     */
    void addAcademicInstitution(AcademicInstitutionDTO institution);

    /**
     * Fetches an academic institution by its ID.
     * 
     * @param institutionId the ID of the institution
     * @return the AcademicInstitutionDTO object, or null if not found
     */
    AcademicInstitutionDTO getAcademicInstitutionById(int institutionId);

    /**
     * Fetches all academic institutions from the database.
     * 
     * @return a list of AcademicInstitutionDTO objects
     */
    List<AcademicInstitutionDTO> getAllAcademicInstitutions();
    
    /**
     * Creates an institution profile for the given user ID.
     *
     * @param userId the user ID of the institution
     * @param institutionName the name of the institution
     * @param address the address of the institution
     */
    void createInstitutionProfile(int userId, String institutionName, String address);
    
    boolean existsByUserId(int userId);
}


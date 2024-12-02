package com.aep.dao;

import com.aep.model.CourseDTO;
import java.util.List;

/**
 * CourseDAO interface defines methods for interacting with the Course table in the database.
 * 
 */
public interface CourseDAO {

    /**
     * Creates a new course in the database.
     *
     * @param course the Course object to be added
     */
    void createCourse(CourseDTO course);

    /**
     * Fetches a course by its ID.
     *
     * @param courseId the ID of the course to fetch
     * @return the Course object, or null if not found
     */
    CourseDTO getCourseById(int courseId);

    /**
     * Fetches all courses offered by a specific institution.
     *
     * @param institutionId the ID of the institution
     * @return a list of courses offered by the institution
     */
    List<CourseDTO> getCoursesByInstitution(int institutionId);

    /**
     * Searches courses by multiple criteria.
     *
     * @param courseCode the course code
     * @param courseTitle the course title
     * @param institutionName the institution name
     * @param term the term
     * @param schedule the schedule
     * @param deliveryMethod the delivery method
     * @return a list of courses matching the criteria
     */
    List<CourseDTO> searchCoursesByCriteria(String courseCode, String courseTitle, String institutionName, String term, String schedule, String deliveryMethod);
    
    /**
     * Updates an existing course in the database.
     *
     * @param course the Course object with updated details
     */
    void updateCourse(CourseDTO course);
    
    /**
     * Deletes a course from the database by its ID.
     *
     * @param courseId the ID of the course to delete
     */
    void deleteCourse(int courseId);
    
    /**
     * Fetches the names of all institutions offering courses.
     *
     * @return a list of institution names
     */
    List<String> getAllInstitutions();
    
    /**
     * Fetches course codes offered by a specific institution.
     *
     * @param institutionName the name of the institution
     * @return a list of course codes offered by the institution
     */
    List<String> getCourseCodesByInstitution(String institutionName);
    
    /**
     * Fetches course titles associated with a specific course code.
     *
     * @param courseCode the course code
     * @return a list of course titles associated with the course code
     */
    List<String> getCourseTitlesByCourseCode(String courseCode);


}


package com.aep.junit;

import com.aep.dao.CourseDAO;
import com.aep.dao.CourseDAOImpl;
import com.aep.model.CourseDTO;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Invalid test cases for the CourseDAOImpl class.
 * Verifies the behavior of methods with invalid inputs.
 */
public class CourseDAOImplInvalidTest {

    /**
     * Tests that creating a course with invalid data does not throw an exception.
     */
    @Test
    public void testCreateCourseInvalid() {
        CourseDAO courseDAO = new CourseDAOImpl();

        CourseDTO course = new CourseDTO();
        course.setInstitutionId(-1); // Invalid institution ID
        course.setCourseTitle(""); // Empty course title
        course.setCourseCode(null); // Null course code
        course.setTerm(""); // Empty term
        course.setOutline(""); // Empty outline
        course.setSchedule(""); // Empty schedule
        course.setPreferredQualifications(null); // Null preferred qualifications
        course.setDeliveryMethod(""); // Empty delivery method
        course.setCompensation(null); // Null compensation

        assertDoesNotThrow(() -> courseDAO.createCourse(course), "Creating a course with invalid data should not throw an exception");
    }

    /**
     * Tests that fetching courses for an invalid institution ID returns an empty list.
     */
    @Test
    public void testGetCoursesByInvalidInstitution() {
        CourseDAO courseDAO = new CourseDAOImpl();

        int institutionId = -1; // Invalid institution ID
        List<CourseDTO> courses = courseDAO.getCoursesByInstitution(institutionId);

        assertNotNull(courses, "Courses list should not be null");
        assertTrue(courses.isEmpty(), "Courses list should be empty for an invalid institution");
    }

    /**
     * Tests that fetching a course by an invalid course ID returns null.
     */
    @Test
    public void testGetCourseByInvalidId() {
        CourseDAO courseDAO = new CourseDAOImpl();

        int courseId = -1; // Invalid course ID
        CourseDTO course = courseDAO.getCourseById(courseId);

        assertNull(course, "Course should be null for an invalid course ID");
    }
}


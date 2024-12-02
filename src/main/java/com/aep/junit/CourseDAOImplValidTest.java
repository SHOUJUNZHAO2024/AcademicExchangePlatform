package com.aep.junit;

import com.aep.dao.CourseDAO;
import com.aep.dao.CourseDAOImpl;
import com.aep.model.CourseDTO;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Valid test cases for the CourseDAOImpl class.
 * Verifies the expected behavior of methods with valid inputs.
 */
public class CourseDAOImplValidTest {

    /**
     * Tests that a course can be created successfully.
     */
    @Test
    public void testCreateCourseValid() {
        CourseDAO courseDAO = new CourseDAOImpl();

        CourseDTO course = new CourseDTO();
        course.setInstitutionId(1); // Assume a valid institution ID
        course.setCourseTitle("Introduction to Programming");
        course.setCourseCode("CS101");
        course.setTerm("24F");
        course.setOutline("Learn the basics of programming.");
        course.setSchedule("Morning");
        course.setPreferredQualifications("Bachelor's in CS");
        course.setDeliveryMethod("In-Person");
        course.setCompensation(new BigDecimal("5000.00"));

        assertDoesNotThrow(() -> courseDAO.createCourse(course), "Creating a course should not throw an exception");
    }

    /**
     * Tests that courses can be fetched by a valid institution ID.
     */
    @Test
    public void testGetCoursesByValidInstitution() {
        CourseDAO courseDAO = new CourseDAOImpl();

        int institutionId = 1; // Assume this institution exists in the database
        List<CourseDTO> courses = courseDAO.getCoursesByInstitution(institutionId);

        assertNotNull(courses, "Courses list should not be null");
        assertFalse(courses.isEmpty(), "Courses list should not be empty for a valid institution");
    }

    /**
     * Tests that a course can be fetched by a valid course ID.
     */
    @Test
    public void testGetCourseByValidId() {
        CourseDAO courseDAO = new CourseDAOImpl();

        int courseId = 1; // Assume this course ID exists
        CourseDTO course = courseDAO.getCourseById(courseId);

        assertNotNull(course, "Course should not be null for a valid course ID");
        assertEquals(courseId, course.getCourseId(), "Course ID should match the expected value");
    }
}


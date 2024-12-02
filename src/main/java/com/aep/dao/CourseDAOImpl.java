package com.aep.dao;

import com.aep.model.CourseDTO;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Implementation of the CourseDAO interface.
 * Handles database interactions for the Course table.
 * 
 */
public class CourseDAOImpl implements CourseDAO {

    /**
     * Database connection object used for executing SQL queries.
     */
    private Connection connection;

    /**
     * Constructor initializes the database connection.
     */
    public CourseDAOImpl() {
        try {
            this.connection = DBConnection.getInstance().getConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Creates a new course in the database.
     *
     * @param course the Course object to be added
     */
    @Override
    public void createCourse(CourseDTO course) {
        String sql = "INSERT INTO Course (institution_id, course_title, course_code, term, outline, schedule, preferred_qualifications, delivery_method, compensation) "
                   + "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setInt(1, course.getInstitutionId());
            ps.setString(2, course.getCourseTitle());
            ps.setString(3, course.getCourseCode());
            ps.setString(4, course.getTerm());
            ps.setString(5, course.getOutline());
            ps.setString(6, course.getSchedule());
            ps.setString(7, course.getPreferredQualifications());
            ps.setString(8, course.getDeliveryMethod());
            ps.setBigDecimal(9, course.getCompensation());
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Fetches a course by its ID.
     *
     * @param courseId the ID of the course to fetch
     * @return the Course object, or null if not found
     */
    @Override
    public CourseDTO getCourseById(int courseId) {
        String sql = "SELECT * FROM Course WHERE course_id = ?";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setInt(1, courseId);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return mapResultSetToCourse(rs);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * Fetches all courses offered by a specific institution.
     *
     * @param institutionId the ID of the institution
     * @return a list of courses offered by the institution
     */
    @Override
    public List<CourseDTO> getCoursesByInstitution(int institutionId) {
        List<CourseDTO> courses = new ArrayList<>();
        String sql = "SELECT * FROM Course WHERE institution_id = ?";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setInt(1, institutionId);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                courses.add(mapResultSetToCourse(rs));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return courses;
    }

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
    @Override
    public List<CourseDTO> searchCoursesByCriteria(String courseCode, String courseTitle, String institutionName, String term, String schedule, String deliveryMethod) {
        List<CourseDTO> courses = new ArrayList<>();
        String sql = "SELECT c.*, ai.institution_name FROM Course c JOIN AcademicInstitution ai ON c.institution_id = ai.institution_id " +
                     "WHERE (c.course_code = ? OR ? IS NULL) AND " +
                     "(c.course_title LIKE ? OR ? IS NULL) AND " +
                     "(ai.institution_name = ? OR ? IS NULL) AND " +
                     "(c.term = ? OR ? IS NULL) AND " +
                     "(c.schedule = ? OR ? IS NULL) AND " +
                     "(c.delivery_method = ? OR ? IS NULL)";

        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            int index = 1;
            ps.setString(index++, courseCode);
            ps.setString(index++, courseCode);
            ps.setString(index++, courseTitle != null ? "%" + courseTitle + "%" : null);
            ps.setString(index++, courseTitle);
            ps.setString(index++, institutionName);
            ps.setString(index++, institutionName);
            ps.setString(index++, term);
            ps.setString(index++, term);
            ps.setString(index++, schedule);
            ps.setString(index++, schedule);
            ps.setString(index++, deliveryMethod);
            ps.setString(index++, deliveryMethod);

            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                CourseDTO course = new CourseDTO();
                course.setCourseId(rs.getInt("course_id"));
                course.setInstitutionId(rs.getInt("institution_id"));
                course.setCourseTitle(rs.getString("course_title"));
                course.setCourseCode(rs.getString("course_code"));
                course.setTerm(rs.getString("term"));
                course.setOutline(rs.getString("outline"));
                course.setSchedule(rs.getString("schedule"));
                course.setPreferredQualifications(rs.getString("preferred_qualifications"));
                course.setDeliveryMethod(rs.getString("delivery_method"));
                course.setCompensation(rs.getBigDecimal("compensation"));
                course.setInstitutionName(rs.getString("institution_name"));
                courses.add(course);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return courses;
    }

    /**
     * Updates an existing course in the database.
     *
     * @param course the Course object with updated details
     */
    @Override
    public void updateCourse(CourseDTO course) {
        String sql = "UPDATE Course SET course_title = ?, term = ?, outline = ?, schedule = ?, compensation = ? WHERE course_id = ?";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setString(1, course.getCourseTitle());
            ps.setString(2, course.getTerm());
            ps.setString(3, course.getOutline());
            ps.setString(4, course.getSchedule());
            ps.setBigDecimal(5, course.getCompensation());
            ps.setInt(6, course.getCourseId());
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    /**
     * Deletes a course from the database by its ID.
     *
     * @param courseId the ID of the course to delete
     */
    @Override
    public void deleteCourse(int courseId) {
        String sql = "DELETE FROM Course WHERE course_id = ?";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setInt(1, courseId);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Helper method to map a ResultSet row to a Course object.
     *
     * @param rs the ResultSet
     * @return a Course object
     * @throws SQLException if a database access error occurs
     */
    private CourseDTO mapResultSetToCourse(ResultSet rs) throws SQLException {
        CourseDTO course = new CourseDTO();
        course.setCourseId(rs.getInt("course_id"));
        course.setInstitutionId(rs.getInt("institution_id"));
        course.setCourseTitle(rs.getString("course_title"));
        course.setCourseCode(rs.getString("course_code"));
        course.setTerm(rs.getString("term"));
        course.setOutline(rs.getString("outline"));
        course.setSchedule(rs.getString("schedule"));
        course.setPreferredQualifications(rs.getString("preferred_qualifications"));
        course.setDeliveryMethod(rs.getString("delivery_method"));
        course.setCompensation(rs.getBigDecimal("compensation"));
//        course.setInstitutionName(rs.getString("institution_name")); // Set the institution name
        return course;
    }
    
    /**
     * Fetches the names of all institutions offering courses.
     *
     * @return a list of institution names
     */
    @Override
    public List<String> getAllInstitutions() {
        String sql = "SELECT DISTINCT institution_name FROM AcademicInstitution";
        List<String> institutions = new ArrayList<>();
        try (PreparedStatement ps = connection.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                institutions.add(rs.getString("institution_name"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return institutions;
    }
    
    /**
     * Fetches course codes offered by a specific institution.
     *
     * @param institutionName the name of the institution
     * @return a list of course codes offered by the institution
     */
    @Override
    public List<String> getCourseCodesByInstitution(String institutionName) {
        List<String> courseCodes = new ArrayList<>();
        String sql = "SELECT DISTINCT course_code FROM Course c " +
                     "JOIN AcademicInstitution ai ON c.institution_id = ai.institution_id " +
                     "WHERE ai.institution_name = ?";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setString(1, institutionName);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                courseCodes.add(rs.getString("course_code"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return courseCodes;
    }

    /**
     * Fetches course titles associated with a specific course code.
     *
     * @param courseCode the course code
     * @return a list of course titles associated with the course code
     */
    @Override
    public List<String> getCourseTitlesByCourseCode(String courseCode) {
        List<String> courseTitles = new ArrayList<>();
        String sql = "SELECT DISTINCT course_title FROM Course WHERE course_code = ?";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setString(1, courseCode);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                courseTitles.add(rs.getString("course_title"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return courseTitles;
    }


}



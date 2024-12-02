package com.aep.controller;

import com.aep.dao.CourseDAO;
import com.aep.dao.CourseDAOImpl;
import com.aep.model.CourseDTO;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import java.io.IOException;
import java.math.BigDecimal;

/**
 * CourseServlet handles course-related actions such as creating, updating, and deleting course listings.
 * Maps to URL patterns for managing course requests.
 * 
 */
@WebServlet("/course/*")
public class CourseServlet extends HttpServlet {

    /**
     * DAO for interacting with the Course table in the database.
     */
    private CourseDAO courseDAO;

    /**
     * Initializes the CourseServlet with a CourseDAO implementation.
     */
    @Override
    public void init() {
        courseDAO = new CourseDAOImpl();
    }

    /**
     * Handles GET requests for the CourseServlet.
     * 
     * @param request  the HttpServletRequest object
     * @param response the HttpServletResponse object
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException      if an input/output error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getPathInfo();

        if (action == null || action.equals("/")) {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST);
            return;
        }

        switch (action) {
            case "/edit":
                handleEditPage(request, response);
                break;
            default:
                response.sendError(HttpServletResponse.SC_NOT_FOUND);
                break;
        }
    }

    /**
     * Handles POST requests for the CourseServlet.
     * 
     * @param request  the HttpServletRequest object
     * @param response the HttpServletResponse object
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException      if an input/output error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getPathInfo();

        if (action == null || action.equals("/")) {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST);
            return;
        }

        switch (action) {
            case "/create":
                handleCreate(request, response);
                break;
            case "/update":
                handleUpdate(request, response);
                break;
            case "/delete":
                handleDelete(request, response);
                break;
            default:
                response.sendError(HttpServletResponse.SC_NOT_FOUND);
                break;
        }
    }

    /**
     * Handles course creation.
     * 
     * @param request  the HttpServletRequest object
     * @param response the HttpServletResponse object
     * @throws IOException if an input/output error occurs
     */
    private void handleCreate(HttpServletRequest request, HttpServletResponse response) throws IOException {
        try {
            // Extract course details from the request
            CourseDTO course = extractCourseFromRequest(request);

            // Save the course to the database
            courseDAO.createCourse(course);

            // Redirect to the dashboardInstitution.jsp
            response.sendRedirect(request.getContextPath() + "/dashboardInstitution");
        } catch (Exception e) {
            e.printStackTrace();
            response.sendRedirect(request.getContextPath() + "/createCourse.jsp?error=Failed to create the course. Please try again.");
        }
    }

    /**
     * Handles course updates.
     * 
     * @param request  the HttpServletRequest object
     * @param response the HttpServletResponse object
     * @throws IOException if an input/output error occurs
     */
    private void handleUpdate(HttpServletRequest request, HttpServletResponse response) throws IOException {
        try {
            // Extract course details from the request
            int courseId = Integer.parseInt(request.getParameter("courseId"));
            String courseTitle = request.getParameter("courseTitle");
            String term = request.getParameter("term");
            String outline = request.getParameter("outline");
            String schedule = request.getParameter("schedule");
            BigDecimal compensation = new BigDecimal(request.getParameter("compensation"));

            CourseDTO course = new CourseDTO();
            course.setCourseId(courseId);
            course.setCourseTitle(courseTitle);
            course.setTerm(term);
            course.setOutline(outline);
            course.setSchedule(schedule);
            course.setCompensation(compensation);

            // Update the course in the database
            courseDAO.updateCourse(course);

            // Redirect to the dashboardInstitution.jsp
            response.sendRedirect(request.getContextPath() + "/dashboardInstitution");
        } catch (Exception e) {
            e.printStackTrace();
            response.sendRedirect(request.getContextPath() + "/editCourse.jsp?error=Failed to update the course. Please try again.");
        }
    }

    /**
     * Handles course deletion.
     * 
     * @param request  the HttpServletRequest object
     * @param response the HttpServletResponse object
     * @throws IOException if an input/output error occurs
     */
    private void handleDelete(HttpServletRequest request, HttpServletResponse response) throws IOException {
        try {
            int courseId = Integer.parseInt(request.getParameter("courseId"));
            courseDAO.deleteCourse(courseId);

            // Redirect to the dashboardInstitution.jsp
            response.sendRedirect(request.getContextPath() + "/dashboardInstitution");
        } catch (Exception e) {
            e.printStackTrace();
            response.sendRedirect(request.getContextPath() + "/dashboardInstitution?error=Failed to delete the course. Please try again.");
        }
    }

    /**
     * Displays the course edit page with existing course details.
     * 
     * @param request  the HttpServletRequest object
     * @param response the HttpServletResponse object
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException      if an input/output error occurs
     */
    private void handleEditPage(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int courseId = Integer.parseInt(request.getParameter("courseId"));
        CourseDTO course = courseDAO.getCourseById(courseId);
        if (course == null) {
            response.sendRedirect(request.getContextPath() + "/dashboardInstitution?error=Course not found.");
            return;
        }
        request.setAttribute("course", course);
        request.getRequestDispatcher("/editCourse.jsp").forward(request, response);
    }

    /**
     * Extracts course details from the HTTP request and maps them to a CourseDTO object.
     * 
     * @param request the HttpServletRequest object
     * @return a CourseDTO object populated with request data
     */
    private CourseDTO extractCourseFromRequest(HttpServletRequest request) {
        CourseDTO course = new CourseDTO();
        course.setInstitutionId(Integer.parseInt(request.getParameter("institutionId")));
        course.setCourseTitle(request.getParameter("courseTitle"));
        course.setCourseCode(request.getParameter("courseCode"));
        course.setTerm(request.getParameter("term"));
        course.setOutline(request.getParameter("outline"));
        course.setSchedule(request.getParameter("schedule"));
        course.setPreferredQualifications(request.getParameter("preferredQualifications"));
        course.setDeliveryMethod(request.getParameter("deliveryMethod"));
        course.setCompensation(new BigDecimal(request.getParameter("compensation")));
        return course;
    }
}

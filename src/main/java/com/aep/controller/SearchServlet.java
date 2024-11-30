package com.aep.controller;

import com.aep.dao.CourseDAO;
import com.aep.dao.CourseDAOImpl;
import com.aep.model.CourseDTO;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import java.io.IOException;
import java.util.List;

/**
 * SearchServlet handles search functionality for academic professionals to find courses based on various criteria.
 * Maps to URL patterns for managing search requests.
 * 
 * @author 
 * @version 1.1, November 2024
 */
@WebServlet("/search/*")
public class SearchServlet extends HttpServlet {

    private CourseDAO courseDAO;

    /**
     * Initializes the SearchServlet with a CourseDAO implementation.
     */
    @Override
    public void init() {
        courseDAO = new CourseDAOImpl();
    }

    /**
     * Handles GET requests for the SearchServlet.
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
            response.sendRedirect(request.getContextPath() + "/search/form");
            return;
        }

        switch (action) {
            case "/form":
                displaySearchForm(request, response);
                break;
            case "/getCourseCodes":
                fetchCourseCodes(request, response);
                break;
            case "/getCourseTitles":
                fetchCourseTitles(request, response);
                break;
            default:
                response.sendError(HttpServletResponse.SC_NOT_FOUND);
                break;
        }
    }

    /**
     * Handles POST requests for the SearchServlet.
     * 
     * @param request  the HttpServletRequest object
     * @param response the HttpServletResponse object
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException      if an input/output error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        handleSearch(request, response);
        
    }

    /**
     * Displays the search form for academic professionals.
     * Populates dropdown lists dynamically with available data.
     * 
     * @param request  the HttpServletRequest object
     * @param response the HttpServletResponse object
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException      if an input/output error occurs
     */
    private void displaySearchForm(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Fetch data for dropdowns
        List<String> institutions = courseDAO.getAllInstitutions();

        // Set attributes for the dropdown lists
        request.setAttribute("institutions", institutions);

        // Forward to searchForm.jsp
        request.getRequestDispatcher("/searchForm.jsp").forward(request, response);
    }

    /**
     * Handles the search operation by processing search criteria and forwarding results to a JSP page.
     * 
     * @param request  the HttpServletRequest object
     * @param response the HttpServletResponse object
     * @throws IOException      if an input/output error occurs
     * @throws ServletException if a servlet-specific error occurs
     */
    private void handleSearch(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        // Extract search criteria from request
        String institutionName = request.getParameter("institutionName");
        String courseCode = request.getParameter("courseCode");
        String courseTitle = request.getParameter("courseTitle");
        String term = request.getParameter("term");
        String schedule = request.getParameter("schedule");
        String deliveryMethod = request.getParameter("deliveryMethod");

        // Query the database based on criteria
        List<CourseDTO> courses = courseDAO.searchCoursesByCriteria(
                courseCode, courseTitle, institutionName, term, schedule, deliveryMethod
        );

        // Set attributes for the search results
        request.setAttribute("courses", courses);

        // Forward to searchResults.jsp
        request.getRequestDispatcher("/searchResults.jsp").forward(request, response);
    }

    /**
     * Fetches course codes based on the selected institution.
     * 
     * @param request  the HttpServletRequest object
     * @param response the HttpServletResponse object
     * @throws IOException if an input/output error occurs
     */
    private void fetchCourseCodes(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String institutionName = request.getParameter("institutionName");
        List<String> courseCodes = courseDAO.getCourseCodesByInstitution(institutionName);

        // Return as plain text (comma-separated values)
        response.setContentType("text/plain");
        response.getWriter().write(String.join(",", courseCodes));
    }

    /**
     * Fetches course titles based on the selected course code.
     * 
     * @param request  the HttpServletRequest object
     * @param response the HttpServletResponse object
     * @throws IOException if an input/output error occurs
     */
    private void fetchCourseTitles(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String courseCode = request.getParameter("courseCode");
        List<String> courseTitles = courseDAO.getCourseTitlesByCourseCode(courseCode);

        // Return as plain text (comma-separated values)
        response.setContentType("text/plain");
        response.getWriter().write(String.join(",", courseTitles));
    }
}

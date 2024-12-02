package com.aep.controller;

import com.aep.dao.CourseDAO;
import com.aep.dao.CourseDAOImpl;
import com.aep.dao.TeachRequestDAO;
import com.aep.dao.TeachRequestDAOImpl;
import com.aep.model.CourseDTO;
import com.aep.model.TeachRequestDTO;
import com.aep.model.UserDTO;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import java.io.IOException;
import java.util.List;

/**
 * SearchServlet handles search functionality for academic professionals to find courses based on various criteria.
 * Maps to URL patterns for managing search requests.
 * 
 */
@WebServlet("/search/*")
public class SearchServlet extends HttpServlet {

    /**
     * DAO for interacting with the Course table in the database.
     */
    private CourseDAO courseDAO;
    
    /**
     * DAO for interacting with the TeachRequest table in the database.
     */
    private TeachRequestDAO teachRequestDAO; 

    /**
     * Initializes the SearchServlet with a CourseDAO implementation.
     */
    @Override
    public void init() {
        courseDAO = new CourseDAOImpl();
        teachRequestDAO = new TeachRequestDAOImpl();
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
     * Also fetches notifications for the logged-in user.
     *
     * @param request  the HttpServletRequest object
     * @param response the HttpServletResponse object
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException      if an input/output error occurs
     */
    private void displaySearchForm(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession(false);
        if (session == null || session.getAttribute("user") == null) {
            response.sendRedirect(request.getContextPath() + "/login.jsp?error=Session expired. Please log in again.");
            return;
        }

        // Fetch logged-in user
        UserDTO user = (UserDTO) session.getAttribute("user");

        // Fetch data for dropdowns
        List<String> institutions = courseDAO.getAllInstitutions();
        request.setAttribute("institutions", institutions);

        // If the user is a Professional, fetch notifications
        if ("Professional".equals(user.getUserType())) {
            List<TeachRequestDTO> notifications = teachRequestDAO.getNotificationsForProfessional(user.getUserId());
            request.setAttribute("notifications", notifications);
        }

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

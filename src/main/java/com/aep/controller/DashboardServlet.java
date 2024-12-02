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
 * DashboardServlet handles requests for the institution dashboard.
 */
@WebServlet("/dashboardInstitution")
public class DashboardServlet extends HttpServlet {

    /**
     * DAO for interacting with the Course table in the database.
     */
    private CourseDAO courseDAO;
    
    /**
     * DAO for interacting with the TeachRequest table in the database.
     */
    private TeachRequestDAO teachRequestDAO;

    /**
     * Initializes DAOs for handling database operations.
     */
    @Override
    public void init() {
        courseDAO = new CourseDAOImpl();
        teachRequestDAO = new TeachRequestDAOImpl();
    }

    /**
     * Handles GET requests to retrieve and display the institution dashboard.
     *
     * @param request  the HttpServletRequest object
     * @param response the HttpServletResponse object
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException      if an input/output error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession(false);
        if (session == null || session.getAttribute("user") == null) {
            response.sendRedirect(request.getContextPath() + "/login.jsp?error=Session expired. Please log in again.");
            return;
        }

        UserDTO user = (UserDTO) session.getAttribute("user");

        if (!"Institution".equals(user.getUserType())) {
            response.sendError(HttpServletResponse.SC_FORBIDDEN, "Access denied");
            return;
        }

        // Fetch courses for the institution
        int institutionId = user.getUserId();
        List<CourseDTO> courses = courseDAO.getCoursesByInstitution(institutionId);

        // Set the courses in the request scope
        request.setAttribute("courses", courses);
        
        // Fetch teach requests for the institution
        List<TeachRequestDTO> teachRequests = teachRequestDAO.getTeachRequestsByInstitution(user.getUserId());
        request.setAttribute("teachRequests", teachRequests);

        // Forward to dashboardInstitution.jsp
        request.getRequestDispatcher("/dashboardInstitution.jsp").forward(request, response);
    }
}

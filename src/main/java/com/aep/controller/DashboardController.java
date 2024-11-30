package com.aep.controller;

import com.aep.dao.CourseDAO;
import com.aep.dao.CourseDAOImpl;
import com.aep.model.CourseDTO;
import com.aep.model.UserDTO;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import java.io.IOException;
import java.util.List;

/**
 * DashboardController handles requests for the institution dashboard.
 */
@WebServlet("/dashboardInstitution")
public class DashboardController extends HttpServlet {

    private CourseDAO courseDAO;

    @Override
    public void init() {
        courseDAO = new CourseDAOImpl();
    }

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

        // Forward to dashboardInstitution.jsp
        request.getRequestDispatcher("/dashboardInstitution.jsp").forward(request, response);
    }
}

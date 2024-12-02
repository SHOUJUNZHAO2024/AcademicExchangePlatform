package com.aep.controller;

import com.aep.dao.TeachRequestDAO;
import com.aep.dao.TeachRequestDAOImpl;
import com.aep.model.TeachRequestDTO;
import com.aep.model.UserDTO;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import java.io.IOException;

/**
 * RequestTeachServlet handles the creation of teach requests by academic professionals.
 * 
 * Maps to "/teach/request".
 */
@WebServlet("/teach/request")
public class RequestTeachServlet extends HttpServlet {

    /**
     * DAO for interacting with the TeachRequest table in the database.
     */
    private TeachRequestDAO teachRequestDAO;

    /**
     * Initializes the servlet and sets up the TeachRequestDAO instance.
     */
    @Override
    public void init() {
        teachRequestDAO = new TeachRequestDAOImpl();
    }

    /**
     * Handles POST requests for submitting a teach request.
     * 
     * Validates the user session, checks if the user is a professional, 
     * and creates a teach request for a selected course.
     *
     * @param request  the HttpServletRequest object
     * @param response the HttpServletResponse object
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException      if an input/output error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        UserDTO user = (UserDTO) session.getAttribute("user");

        // Validate user session and access
        if (user == null || !"Professional".equals(user.getUserType())) {
            response.sendRedirect(request.getContextPath() + "/login.jsp?error=Session expired or unauthorized access.");
            return;
        }

        // Extract the course ID from the request parameters
        int courseId = Integer.parseInt(request.getParameter("courseId"));

        // Create a new TeachRequest
        TeachRequestDTO teachRequest = new TeachRequestDTO();
        teachRequest.setCourseId(courseId);
        teachRequest.setProfessionalId(user.getUserId());
        teachRequest.setStatus("Pending");
        teachRequest.setNotification(false);

        // Save the teach request to the database
        teachRequestDAO.createTeachRequest(teachRequest);
        
        // Redirect to the search form with a success message
        response.sendRedirect(request.getContextPath() + "/search/form?success=true");

    }
}

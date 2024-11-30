package com.aep.controller;

import com.aep.dao.TeachRequestDAO;
import com.aep.dao.TeachRequestDAOImpl;
import com.aep.dao.CourseDAO;
import com.aep.dao.CourseDAOImpl;
import com.aep.model.CourseDTO;
import com.aep.model.TeachRequestDTO;
import com.aep.model.UserDTO;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import java.io.IOException;
import java.util.List;

/**
 * TeachRequestServlet handles actions related to submitting and managing teach requests.
 * 
 * Maps to URL patterns for managing teach requests.
 * 
 * @author 
 * @version 1.0, November 2024
 */
@WebServlet("/teachRequest/*")
public class TeachRequestServlet extends HttpServlet {

    private TeachRequestDAO teachRequestDAO;

    @Override
    public void init() {
        teachRequestDAO = new TeachRequestDAOImpl();
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        String action = request.getPathInfo();

        if (action == null || action.equals("/")) {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST);
            return;
        }

        switch (action) {
            case "/submit":
                handleRequestToTeach(request, response);
                break;
            default:
                response.sendError(HttpServletResponse.SC_NOT_FOUND);
                break;
        }
    }

    private void handleRequestToTeach(HttpServletRequest request, HttpServletResponse response) throws IOException {
        int courseId = Integer.parseInt(request.getParameter("courseId"));
        HttpSession session = request.getSession(false);
        UserDTO user = (UserDTO) session.getAttribute("user");

        // Check if user is authenticated and is an Academic Professional
        if (user != null && "Professional".equals(user.getUserType())) {
            TeachRequestDTO teachRequest = new TeachRequestDTO();
            teachRequest.setCourseId(courseId);
            teachRequest.setProfessionalId(user.getUserId());
            teachRequest.setStatus("Pending");
            teachRequest.setNotification(false);

            teachRequestDAO.submitTeachRequest(teachRequest);

            // Respond with a success message
            response.getWriter().write("<script>alert('You have successfully requested to teach this course!');"
                + "window.location.href='" + request.getContextPath() + "/search/form';</script>");
        } else {
            response.sendRedirect(request.getContextPath() + "/login.jsp?error=Unauthorized access.");
        }
    }
}



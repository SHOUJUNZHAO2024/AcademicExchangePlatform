package com.aep.controller;

import com.aep.dao.TeachRequestDAO;
import com.aep.dao.TeachRequestDAOImpl;
import com.aep.model.TeachRequestDTO;
import com.aep.model.UserDTO;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import java.io.IOException;

@WebServlet("/teach/request")
public class RequestTeachServlet extends HttpServlet {

    private TeachRequestDAO teachRequestDAO;

    @Override
    public void init() {
        teachRequestDAO = new TeachRequestDAOImpl();
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        UserDTO user = (UserDTO) session.getAttribute("user");

        if (user == null || !"Professional".equals(user.getUserType())) {
            response.sendRedirect(request.getContextPath() + "/login.jsp?error=Session expired or unauthorized access.");
            return;
        }

        int courseId = Integer.parseInt(request.getParameter("courseId"));

        // Create a new TeachRequest
        TeachRequestDTO teachRequest = new TeachRequestDTO();
        teachRequest.setCourseId(courseId);
        teachRequest.setProfessionalId(user.getUserId());
        teachRequest.setStatus("Pending");
        teachRequest.setNotification(false);

        teachRequestDAO.createTeachRequest(teachRequest);

        // Redirect with success message
//        response.sendRedirect(request.getContextPath() + "/search/form?message=Successfully requested to teach the course!");
        
        response.sendRedirect(request.getContextPath() + "/search/form?success=true");

    }
}

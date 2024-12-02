package com.aep.controller;

import java.io.IOException;

import com.aep.dao.TeachRequestDAO;
import com.aep.dao.TeachRequestDAOImpl;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 * RequestTeachResponseServlet handles responses to teach requests from institutions.
 * 
 * Maps to "/teach/respond".
 */
@WebServlet("/teach/respond")
public class RequestTeachResponseServlet extends HttpServlet {

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
     * Handles POST requests for updating the status of a teach request.
     * 
     * @param request  the HttpServletRequest object
     * @param response the HttpServletResponse object
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException      if an input/output error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int requestId = Integer.parseInt(request.getParameter("requestId"));
        String action = request.getParameter("action");
        
        // Determine the status based on the action
        String status = "Pending";
        if ("accept".equals(action)) {
            status = "Accepted";
        } else if ("reject".equals(action)) {
            status = "Rejected";
        }
        // Update the status of the teach request in the database
        teachRequestDAO.updateTeachRequestStatus(requestId, status);

        // Set notification for the professional
        teachRequestDAO.setNotificationForProfessional(requestId, true);

     // Redirect to the institution dashboard with a success message
        response.sendRedirect(request.getContextPath() + "/dashboardInstitution?message=Request status updated!");
    }

}


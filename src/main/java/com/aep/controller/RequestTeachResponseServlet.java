package com.aep.controller;

import java.io.IOException;

import com.aep.dao.TeachRequestDAO;
import com.aep.dao.TeachRequestDAOImpl;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/teach/respond")
public class RequestTeachResponseServlet extends HttpServlet {

    private TeachRequestDAO teachRequestDAO;

    @Override
    public void init() {
        teachRequestDAO = new TeachRequestDAOImpl();
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int requestId = Integer.parseInt(request.getParameter("requestId"));
        String action = request.getParameter("action");
        
        // Update the status of the request
        String status = "Pending";
        if ("accept".equals(action)) {
            status = "Accepted";
        } else if ("reject".equals(action)) {
            status = "Rejected";
        }
        teachRequestDAO.updateTeachRequestStatus(requestId, status);

        // Set notification for the professional
        teachRequestDAO.setNotificationForProfessional(requestId, true);

        response.sendRedirect(request.getContextPath() + "/dashboardInstitution?message=Request status updated!");
    }

}


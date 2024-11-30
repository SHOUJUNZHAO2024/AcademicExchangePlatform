package com.aep.controller;

import com.aep.dao.TeachRequestDAO;
import com.aep.dao.TeachRequestDAOImpl;
import com.aep.dao.CourseDAO;
import com.aep.dao.CourseDAOImpl;
import com.aep.model.CourseDTO;
import com.aep.model.TeachRequestDTO;

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
    private CourseDAO courseDAO;

    /**
     * Initializes the TeachRequestServlet with DAO implementations.
     */
    @Override
    public void init() {
        teachRequestDAO = new TeachRequestDAOImpl();
        courseDAO = new CourseDAOImpl();
    }

    /**
     * Handles GET requests for the TeachRequestServlet.
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
            case "/details":
                displayCourseDetails(request, response);
                break;
            case "/manage":
                manageTeachRequests(request, response);
                break;
            default:
                response.sendError(HttpServletResponse.SC_NOT_FOUND);
                break;
        }
    }

    /**
     * Handles POST requests for the TeachRequestServlet.
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
            case "/submit":
                submitTeachRequest(request, response);
                break;
            case "/updateStatus":
                updateRequestStatus(request, response);
                break;
            default:
                response.sendError(HttpServletResponse.SC_NOT_FOUND);
                break;
        }
    }

    /**
     * Displays detailed information about a course and allows request submission.
     * 
     * @param request  the HttpServletRequest object
     * @param response the HttpServletResponse object
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException      if an input/output error occurs
     */
    private void displayCourseDetails(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int courseId = Integer.parseInt(request.getParameter("courseId"));
        CourseDTO course = courseDAO.getCourseById(courseId);

        if (course == null) {
            response.sendRedirect("searchResults.jsp?error=Course not found.");
            return;
        }

        request.setAttribute("course", course);
        request.getRequestDispatcher("/courseDetails.jsp").forward(request, response);
    }

    /**
     * Handles submission of a teach request for a course.
     * 
     * @param request  the HttpServletRequest object
     * @param response the HttpServletResponse object
     * @throws IOException if an input/output error occurs
     */
    private void submitTeachRequest(HttpServletRequest request, HttpServletResponse response) throws IOException {
        int courseId = Integer.parseInt(request.getParameter("courseId"));
        int professionalId = Integer.parseInt(request.getParameter("professionalId"));

        TeachRequestDTO requestDTO = new TeachRequestDTO();
        requestDTO.setCourseId(courseId);
        requestDTO.setProfessionalId(professionalId);
        requestDTO.setStatus("Pending");
        requestDTO.setNotification(false);

        teachRequestDAO.addTeachRequest(requestDTO);
        response.sendRedirect("searchResults.jsp?message=Request submitted successfully!");
    }

    /**
     * Displays all teach requests for institutions to manage.
     * 
     * @param request  the HttpServletRequest object
     * @param response the HttpServletResponse object
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException      if an input/output error occurs
     */
    private void manageTeachRequests(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int courseId = Integer.parseInt(request.getParameter("courseId"));
        List<TeachRequestDTO> requests = teachRequestDAO.getTeachRequestsByCourse(courseId);

        request.setAttribute("requests", requests);
        request.getRequestDispatcher("/manageRequests.jsp").forward(request, response);
    }

    /**
     * Updates the status of a teach request (accept/reject).
     * 
     * @param request  the HttpServletRequest object
     * @param response the HttpServletResponse object
     * @throws IOException if an input/output error occurs
     */
    private void updateRequestStatus(HttpServletRequest request, HttpServletResponse response) throws IOException {
        int requestId = Integer.parseInt(request.getParameter("requestId"));
        String status = request.getParameter("status");

        teachRequestDAO.updateTeachRequestStatus(requestId, status);
        teachRequestDAO.updateTeachRequestNotification(requestId, true);
        response.sendRedirect("manageRequests.jsp?message=Request status updated successfully!");
    }
}


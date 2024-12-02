package com.aep.controller;

import com.aep.dao.AcademicInstitutionDAO;
import com.aep.dao.AcademicInstitutionDAOImpl;
import com.aep.dao.AcademicProfessionalDAO;
import com.aep.dao.AcademicProfessionalDAOImpl;
import com.aep.model.UserDTO;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import java.io.IOException;

/**
 * ProfileServlet handles profile creation and redirection after user login.
 * Maps to "/profileCreate".
 */
@WebServlet("/profileCreate")
public class ProfileServlet extends HttpServlet {

    /**
     * DAO for interacting with the AcademicInstitution table.
     */
    private AcademicInstitutionDAO institutionDAO;
    
    /**
     * DAO for interacting with the AcademicProfessional table.
     */
    private AcademicProfessionalDAO professionalDAO;

    /**
     * Initializes DAOs for handling database operations.
     */
    @Override
    public void init() {
        institutionDAO = new AcademicInstitutionDAOImpl();
        professionalDAO = new AcademicProfessionalDAOImpl();
    }

    /**
     * Handles POST requests for creating user profiles.
     * Determines the user type (Institution or Professional) and stores the respective profile in the database.
     *
     * @param request  the HttpServletRequest object
     * @param response the HttpServletResponse object
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException      if an input/output error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession(false);
        if (session == null || session.getAttribute("user") == null) {
            response.sendRedirect(request.getContextPath() + "/login.jsp?error=Session expired. Please log in again.");
            return;
        }

        UserDTO user = (UserDTO) session.getAttribute("user");

        if ("Institution".equals(user.getUserType())) {
            String institutionName = request.getParameter("institutionName");
            String address = request.getParameter("address");

            institutionDAO.createInstitutionProfile(user.getUserId(), institutionName, address);
            response.sendRedirect(request.getContextPath() + "/dashboardInstitution");
        } else if ("Professional".equals(user.getUserType())) {
            String firstName = request.getParameter("firstName");
            String lastName = request.getParameter("lastName");
            String currentInstitution = request.getParameter("currentInstitution");
            String academicPosition = request.getParameter("academicPosition");
            String educationBackground = request.getParameter("educationBackground");
            String expertise = request.getParameter("expertise");

            professionalDAO.createProfessionalProfile(user.getUserId(), firstName, lastName, currentInstitution, academicPosition, educationBackground, expertise);
            response.sendRedirect(request.getContextPath() + "/search/form");
        }
    }
}


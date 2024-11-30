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
 * ProfileController handles profile creation and redirection after login.
 */
@WebServlet("/profileCreate")
public class ProfileController extends HttpServlet {

    private AcademicInstitutionDAO institutionDAO;
    private AcademicProfessionalDAO professionalDAO;

    @Override
    public void init() {
        institutionDAO = new AcademicInstitutionDAOImpl();
        professionalDAO = new AcademicProfessionalDAOImpl();
    }

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
            response.sendRedirect(request.getContextPath() + "/dashboardInstitution.jsp");
        } else if ("Professional".equals(user.getUserType())) {
            String firstName = request.getParameter("firstName");
            String lastName = request.getParameter("lastName");
            String currentInstitution = request.getParameter("currentInstitution");
            String academicPosition = request.getParameter("academicPosition");
            String educationBackground = request.getParameter("educationBackground");
            String expertise = request.getParameter("expertise");

            professionalDAO.createProfessionalProfile(user.getUserId(), firstName, lastName, currentInstitution, academicPosition, educationBackground, expertise);
            response.sendRedirect(request.getContextPath() + "/searchForm.jsp");
        }
    }
}


package com.aep.controller;

import com.aep.dao.AcademicInstitutionDAO;
import com.aep.dao.AcademicInstitutionDAOImpl;
import com.aep.dao.AcademicProfessionalDAO;
import com.aep.dao.AcademicProfessionalDAOImpl;
import com.aep.dao.UserDAO;
import com.aep.dao.UserDAOImpl;
import com.aep.model.UserDTO;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import java.io.IOException;

/**
 * UserController handles registration, login, and redirection flows.
 * Maps to "/user/*".
 */
@WebServlet("/user/*")
public class UserController extends HttpServlet {

    private UserDAO userDAO;
    private AcademicProfessionalDAO professionalDAO;
    private AcademicInstitutionDAO institutionDAO;

    @Override
    public void init() {
        userDAO = new UserDAOImpl();
        professionalDAO = new AcademicProfessionalDAOImpl();
        institutionDAO = new AcademicInstitutionDAOImpl();
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getPathInfo();

        if (action == null || action.equals("/")) {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST);
            return;
        }

        switch (action) {
            case "/register":
                handleRegister(request, response);
                break;
            case "/login":
                handleLogin(request, response);
                break;
            default:
                response.sendError(HttpServletResponse.SC_NOT_FOUND);
                break;
        }
    }

    /**
     * Handles user registration.
     *
     * @param request  the HttpServletRequest object
     * @param response the HttpServletResponse object
     * @throws IOException if an input/output error occurs
     */
    private void handleRegister(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String email = request.getParameter("email");
        String userType = request.getParameter("userType");

        UserDTO user = new UserDTO();
        user.setUsername(username);
        user.setPassword(password);
        user.setEmail(email);
        user.setUserType(userType);

        userDAO.createUser(user); // Save user to the database
        response.sendRedirect(request.getContextPath() + "/login.jsp?message=Registration successful! Please log in.");
    }

    /**
     * Handles user login and redirects based on user type and existence in the database.
     *
     * @param request  the HttpServletRequest object
     * @param response the HttpServletResponse object
     * @throws IOException      if an input/output error occurs
     * @throws ServletException if a servlet-specific error occurs
     */
    private void handleLogin(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");

        boolean isAuthenticated = userDAO.authenticateUser(username, password);

        if (isAuthenticated) {
            UserDTO user = userDAO.getUserByUsername(username);
            HttpSession session = request.getSession();
            session.setAttribute("user", user);

            // Check if user data already exists in the database
            if ("Professional".equals(user.getUserType())) {
                if (professionalDAO.existsByUserId(user.getUserId())) {
                    // Redirect to searchForm.jsp if data exists for Academic Professional
//                    response.sendRedirect(request.getContextPath() + "/searchForm.jsp");
                    response.sendRedirect(request.getContextPath() + "/search/form");
                } else {
                    // Redirect to profileCreate.jsp if no data exists
                    response.sendRedirect(request.getContextPath() + "/profileCreate.jsp");
                }
            } else if ("Institution".equals(user.getUserType())) {
                if (institutionDAO.existsByUserId(user.getUserId())) {
                    // Redirect to dashboardInstitution.jsp if data exists for Academic Institution
                    response.sendRedirect(request.getContextPath() + "/dashboardInstitution");
                } else {
                    // Redirect to profileCreate.jsp if no data exists
                    response.sendRedirect(request.getContextPath() + "/profileCreate.jsp");
                }
            }
        } else {
            response.sendRedirect(request.getContextPath() + "/login.jsp?error=Invalid credentials");
        }
    }
}

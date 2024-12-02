package com.aep.controller;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

import java.io.IOException;

/**
 * LogoutServlet handles user logout by invalidating the session and redirecting to the login page.
 */
@WebServlet("/logout")
public class LogoutServlet extends HttpServlet {

    /**
     * Handles GET requests to log out the user.
     * 
     * Invalidates the current user session and redirects to the login page.
     *
     * @param request  the HttpServletRequest object
     * @param response the HttpServletResponse object
     * @throws IOException if an input/output error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        // Invalidate the session to log out the user
        HttpSession session = request.getSession(false); // Get the session if it exists
        if (session != null) {
            session.invalidate();
        }
        // Redirect to the login page
        response.sendRedirect(request.getContextPath() + "/login.jsp");
    }
}

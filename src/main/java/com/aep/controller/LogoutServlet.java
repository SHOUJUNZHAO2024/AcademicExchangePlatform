package com.aep.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

import java.io.IOException;

/**
 * LogoutServlet handles user logout by invalidating the session and redirecting to the login page.
 */
@WebServlet("/logout")
public class LogoutServlet extends HttpServlet {

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

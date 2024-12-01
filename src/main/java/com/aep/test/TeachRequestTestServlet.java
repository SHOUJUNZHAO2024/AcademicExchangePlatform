package com.aep.test;

import com.aep.dao.TeachRequestDAO;
import com.aep.dao.TeachRequestDAOImpl;
import com.aep.model.TeachRequestDTO;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

@WebServlet("/test/teachRequests")
public class TeachRequestTestServlet extends HttpServlet {

    private TeachRequestDAO teachRequestDAO;

    @Override
    public void init() {
        teachRequestDAO = new TeachRequestDAOImpl();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Specify an institution ID for testing
        int institutionId = 2; // Replace with a valid institution ID from your database

        // Fetch teach requests
        List<TeachRequestDTO> requests = teachRequestDAO.getTeachRequestsByInstitution(institutionId);

        // Prepare response output
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        out.println("<html><body>");
        out.println("<h1>Test: Teach Requests for Institution ID " + institutionId + "</h1>");

        if (requests.isEmpty()) {
            out.println("<p>No teach requests found for institution ID: " + institutionId + "</p>");
        } else {
            out.println("<table border='1'>");
            out.println("<tr><th>Request ID</th><th>Course Title</th><th>Professional Name</th><th>Status</th></tr>");

            for (TeachRequestDTO request1 : requests) {
                out.println("<tr>");
                out.println("<td>" + request1.getRequestId() + "</td>");
                out.println("<td>" + request1.getCourseTitle() + "</td>");
                out.println("<td>" + request1.getProfessionalName() + "</td>");
                out.println("<td>" + request1.getStatus() + "</td>");
                out.println("</tr>");
            }

            out.println("</table>");
        }

        out.println("</body></html>");
    }
}


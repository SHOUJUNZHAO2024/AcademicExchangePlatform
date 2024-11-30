package com.aep.test;

import com.aep.dao.CourseDAO;
import com.aep.dao.CourseDAOImpl;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

@WebServlet("/test/course-dao")
public class TestCourseDAOServlet extends HttpServlet {

    private CourseDAO courseDAO;

    @Override
    public void init() {
        courseDAO = new CourseDAOImpl();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/plain");
        PrintWriter out = response.getWriter();

        // Test getAllInstitutions
        out.println("Institutions:");
        List<String> institutions = courseDAO.getAllInstitutions();
        for (String institution : institutions) {
            out.println("- " + institution);
        }

        // Test getAllCourseCodes
        out.println("\nCourse Codes:");
        List<String> courseCodes = courseDAO.getAllCourseCodes();
        for (String code : courseCodes) {
            out.println("- " + code);
        }

        // Test getAllCourseTitles
        out.println("\nCourse Titles:");
        List<String> courseTitles = courseDAO.getAllCourseTitles();
        for (String title : courseTitles) {
            out.println("- " + title);
        }
    }
}

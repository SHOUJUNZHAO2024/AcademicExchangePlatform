package com.aep.test;

import com.aep.dao.CourseDAO;
import com.aep.dao.CourseDAOImpl;

import java.util.List;

public class TestCourseDAO {
    public static void main(String[] args) {
        // Initialize the DAO
        CourseDAO courseDAO = new CourseDAOImpl();

        // Test getAllInstitutions
        List<String> institutions = courseDAO.getAllInstitutions();
        System.out.println("Institutions:");
        for (String institution : institutions) {
            System.out.println("- " + institution);
        }

        // Test getAllCourseCodes
        List<String> courseCodes = courseDAO.getAllCourseCodes();
        System.out.println("\nCourse Codes:");
        for (String code : courseCodes) {
            System.out.println("- " + code);
        }

        // Test getAllCourseTitles
        List<String> courseTitles = courseDAO.getAllCourseTitles();
        System.out.println("\nCourse Titles:");
        for (String title : courseTitles) {
            System.out.println("- " + title);
        }
    }
}


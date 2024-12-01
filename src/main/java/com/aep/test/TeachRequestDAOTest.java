package com.aep.test;

import com.aep.dao.TeachRequestDAO;
import com.aep.dao.TeachRequestDAOImpl;
import com.aep.model.TeachRequestDTO;

import java.util.List;

public class TeachRequestDAOTest {
    public static void main(String[] args) {
        // Initialize the DAO
        TeachRequestDAO teachRequestDAO = new TeachRequestDAOImpl();

        // Specify an institution ID to test
        int institutionId = 2; // Replace with a valid institution ID from your database

        // Fetch teach requests
        List<TeachRequestDTO> requests = teachRequestDAO.getTeachRequestsByInstitution(institutionId);

        // Print the results
        if (requests.isEmpty()) {
            System.out.println("No teach requests found for institution ID: " + institutionId);
        } else {
            for (TeachRequestDTO request : requests) {
                System.out.println("Request ID: " + request.getRequestId());
                System.out.println("Course Title: " + request.getCourseTitle());
                System.out.println("Professional Name: " + request.getProfessionalName());
                System.out.println("Status: " + request.getStatus());
                System.out.println("-------------");
            }
        }
    }
}

<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.aep.model.UserDTO" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%
    UserDTO user = (UserDTO) session.getAttribute("user");
    if (user == null) {
        response.sendRedirect("login.jsp?error=Session expired. Please log in again.");
        return;
    }
%>
<!DOCTYPE html>
<html>
<head>
    <title>Create Profile</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            background-color: #f8f9fa; /* Light gray background */
            color: #343a40; /* Dark gray text */
            margin: 0;
            padding: 0;
        }
        .container {
            max-width: 600px;
            margin: 40px auto;
            padding: 20px;
            background: #ffffff;
            border-radius: 8px;
            box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
        }
        h1 {
            text-align: center;
            color: #0056b3; /* Blue accent */
        }
        label {
            display: block;
            margin-top: 10px;
            font-weight: bold;
        }
        input, button {
            width: 100%;
            padding: 10px;
            margin-top: 5px;
            margin-bottom: 15px;
            border: 1px solid #ccc;
            border-radius: 4px;
            font-size: 14px;
        }
        button {
            background-color: #0056b3;
            color: #fff;
            border: none;
            cursor: pointer;
            font-size: 16px;
        }
        button:hover {
            background-color: #003d80;
        }
        .note {
            font-size: 14px;
            color: #6c757d;
            text-align: center;
            margin-top: 20px;
        }
        .note a {
            color: #0056b3;
            text-decoration: none;
        }
        .note a:hover {
            text-decoration: underline;
        }
    </style>
</head>
<body>
    <div class="container">
        <h1>Create Your Profile</h1>
        <form action="profileCreate" method="post">
            <!-- Conditional section for Academic Institution -->
            <c:if test="${user.userType == 'Institution'}">
                <label for="institutionName">Institution Name:</label>
                <input type="text" id="institutionName" name="institutionName" required />

                <label for="address">Address:</label>
                <input type="text" id="address" name="address" required />
            </c:if>
            <!-- Conditional section for Academic Professional -->
            <c:if test="${user.userType == 'Professional'}">
                <label for="firstName">First Name:</label>
                <input type="text" id="firstName" name="firstName" required />

                <label for="lastName">Last Name:</label>
                <input type="text" id="lastName" name="lastName" required />

                <label for="currentInstitution">Current Institution:</label>
                <input type="text" id="currentInstitution" name="currentInstitution" required />

                <label for="academicPosition">Academic Position:</label>
                <input type="text" id="academicPosition" name="academicPosition" required />

                <label for="educationBackground">Education Background:</label>
                <input type="text" id="educationBackground" name="educationBackground" required />

                <label for="expertise">Expertise:</label>
                <input type="text" id="expertise" name="expertise" required />
            </c:if>
            <button type="submit">Save Profile</button>
        </form>
        <p class="note">
            Need help? <a href="${pageContext.request.contextPath}/help">Contact support</a>
        </p>
    </div>
</body>
</html>

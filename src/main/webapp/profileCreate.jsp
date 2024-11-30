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
</head>
<body>
    <h1>Create Your Profile</h1>
    <form action="profileCreate" method="post">
        <!-- Conditional section for Academic Institution -->
        <c:if test="${user.userType == 'Institution'}">
            <label for="institutionName">Institution Name:</label>
            <input type="text" id="institutionName" name="institutionName" required /><br/>

            <label for="address">Address:</label>
            <input type="text" id="address" name="address" required /><br/>
        </c:if>
        <!-- Conditional section for Academic Professional -->
        <c:if test="${user.userType == 'Professional'}">
            <label for="firstName">First Name:</label>
            <input type="text" id="firstName" name="firstName" required /><br/>

            <label for="lastName">Last Name:</label>
            <input type="text" id="lastName" name="lastName" required /><br/>

            <label for="currentInstitution">Current Institution:</label>
            <input type="text" id="currentInstitution" name="currentInstitution" required /><br/>
            
            <label for="academicPosition">Academic Position:</label>
            <input type="text" id="academicPosition" name="academicPosition" required /><br/>
            
            <label for="educationBackground">Education Background:</label>
            <input type="text" id="educationBackground" name="educationBackground" required /><br/>

            <label for="expertise">Expertise:</label>
            <input type="text" id="expertise" name="expertise" required /><br/>
        </c:if>
        <button type="submit">Save Profile</button>
    </form>
</body>
</html>

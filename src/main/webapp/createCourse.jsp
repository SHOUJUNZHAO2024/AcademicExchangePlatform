<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%
    if (session == null || session.getAttribute("user") == null) {
        response.sendRedirect("login.jsp?error=Session expired. Please log in again.");
        return;
    }
%>
<!DOCTYPE html>
<html>
<head>
    <title>Create Course</title>
</head>
<body>
    <h1>Create New Course</h1>
    <form action="${pageContext.request.contextPath}/course/create" method="post">
        <label for="institutionId">Institution ID:</label>
        <input type="number" id="institutionId" name="institutionId" required /><br/>

        <label for="courseTitle">Course Title:</label>
        <input type="text" id="courseTitle" name="courseTitle" required /><br/>

        <label for="courseCode">Course Code:</label>
        <input type="text" id="courseCode" name="courseCode" required /><br/>

        <label for="term">Term:</label>
        <input type="text" id="term" name="term" required /><br/>

        <label for="outline">Outline:</label>
        <textarea id="outline" name="outline" required></textarea><br/>

        <label for="schedule">Schedule:</label>
        <input type="text" id="schedule" name="schedule" required /><br/>

        <label for="preferredQualifications">Preferred Qualifications:</label>
        <textarea id="preferredQualifications" name="preferredQualifications"></textarea><br/>

        <label for="deliveryMethod">Delivery Method:</label>
        <input type="text" id="deliveryMethod" name="deliveryMethod" required /><br/>

        <label for="compensation">Compensation:</label>
        <input type="number" id="compensation" name="compensation" step="0.01" required /><br/>

        <button type="submit">Create Course</button>
    </form>
    </br>
    <a href="dashboardInstitution.jsp">Back to Dashboard</a>
</body>
</html>

<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.aep.model.CourseDTO" %>
<%
    CourseDTO course = (CourseDTO) request.getAttribute("course");
    if (course == null) {
        response.sendRedirect("searchResults.jsp?error=Course not found.");
    }
%>
<!DOCTYPE html>
<html>
<head>
    <title>Course Details</title>
</head>
<body>
    <h1>Course Details</h1>
    <p><strong>Institution:</strong> ${course.institutionId}</p>
    <p><strong>Course Title:</strong> ${course.courseTitle}</p>
    <p><strong>Course Code:</strong> ${course.courseCode}</p>
    <p><strong>Term:</strong> ${course.term}</p>
    <p><strong>Outline:</strong> ${course.outline}</p>
    <p><strong>Schedule:</strong> ${course.schedule}</p>
    <p><strong>Delivery Method:</strong> ${course.deliveryMethod}</p>
    <p><strong>Compensation:</strong> ${course.compensation}</p>

    <h2>Submit Request to Teach</h2>
    <form action="${pageContext.request.contextPath}/teachRequest/submit" method="post">
        <input type="hidden" name="courseId" value="${course.courseId}" />
        <label for="professionalId">Your Professional ID:</label>
        <input type="number" id="professionalId" name="professionalId" required /><br/>
        <button type="submit">Submit Request</button>
    </form>
    <a href="searchResults.jsp">Back to Search Results</a>
</body>
</html>

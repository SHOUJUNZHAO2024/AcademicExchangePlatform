<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.aep.model.CourseDTO" %>
<%
    CourseDTO course = (CourseDTO) request.getAttribute("course");
    if (course == null) {
        response.sendRedirect("dashboardInstitution.jsp?error=No course found.");
        return;
    }
%>
<!DOCTYPE html>
<html>
<head>
    <title>Edit Course</title>
</head>
<body>
    <h1>Edit Course</h1>
    <form action="${pageContext.request.contextPath}/course/update" method="post">
        <input type="hidden" name="courseId" value="${course.courseId}" />

        <label for="courseTitle">Course Title:</label>
        <input type="text" id="courseTitle" name="courseTitle" value="${course.courseTitle}" required /><br/>

        <label for="term">Term:</label>
        <input type="text" id="term" name="term" value="${course.term}" required /><br/>

        <label for="outline">Outline:</label>
        <textarea id="outline" name="outline" required>${course.outline}</textarea><br/>

        <label for="schedule">Schedule:</label>
        <input type="text" id="schedule" name="schedule" value="${course.schedule}" required /><br/>

        <label for="compensation">Compensation:</label>
        <input type="number" id="compensation" name="compensation" value="${course.compensation}" step="0.01" required /><br/>

        <button type="submit">Update</button>
    </form>
    <form action="${pageContext.request.contextPath}/course/delete" method="post">
        <input type="hidden" name="courseId" value="${course.courseId}" />
        <button type="submit">Delete</button>
    </form>
    <a href="${pageContext.request.contextPath}/dashboardInstitution">Cancel</a>
</body>
</html>

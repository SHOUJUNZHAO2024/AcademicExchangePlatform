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
    <style>
        body {
            font-family: Arial, sans-serif;
            background-color: #f8f9fa; /* Light background */
            color: #343a40; /* Dark gray text */
            margin: 0;
            padding: 0;
        }
        .container {
            margin: 20px auto;
            max-width: 800px;
            background: #ffffff;
            padding: 20px;
            border-radius: 8px;
            box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
        }
        h1 {
            color: #0056b3; /* Blue accent */
            text-align: center;
        }
        label {
            display: block;
            margin-top: 10px;
            font-weight: bold;
        }
        input, textarea, button {
            width: 100%;
            padding: 10px;
            margin-top: 5px;
            margin-bottom: 20px;
            border: 1px solid #ccc;
            border-radius: 4px;
            font-size: 14px;
        }
        textarea {
            height: 100px;
            resize: none;
        }
        button {
            background-color: #0056b3;
            color: white;
            border: none;
            cursor: pointer;
            font-size: 16px;
        }
        button:hover {
            background-color: #003d80;
        }
        a {
            display: block;
            text-align: center;
            margin-top: 10px;
            color: #0056b3;
            text-decoration: none;
        }
        a:hover {
            text-decoration: underline;
        }
        .form-group {
            margin-bottom: 15px;
        }
        .actions {
            display: flex;
            justify-content: space-between;
        }
        .delete-button {
            background-color: #dc3545;
            color: white;
        }
        .delete-button:hover {
            background-color: #a71d2a;
        }
    </style>
</head>
<body>
    <div class="container">
        <h1>Edit Course</h1>
        <!-- Update Course Form -->
        <form action="${pageContext.request.contextPath}/course/update" method="post">
            <input type="hidden" name="courseId" value="${course.courseId}" />

            <div class="form-group">
                <label for="courseTitle">Course Title:</label>
                <input type="text" id="courseTitle" name="courseTitle" value="${course.courseTitle}" required />
            </div>

            <div class="form-group">
                <label for="term">Term:</label>
                <input type="text" id="term" name="term" value="${course.term}" required />
            </div>

            <div class="form-group">
                <label for="outline">Outline:</label>
                <textarea id="outline" name="outline" required>${course.outline}</textarea>
            </div>

            <div class="form-group">
                <label for="schedule">Schedule:</label>
                <input type="text" id="schedule" name="schedule" value="${course.schedule}" required />
            </div>

            <div class="form-group">
                <label for="compensation">Compensation:</label>
                <input type="number" id="compensation" name="compensation" value="${course.compensation}" step="0.01" required />
            </div>

            <div class="actions">
                <button type="submit">Update</button>
            </div>
        </form>

        <!-- Delete Course Form -->
        <form action="${pageContext.request.contextPath}/course/delete" method="post">
            <input type="hidden" name="courseId" value="${course.courseId}" />
            <button type="submit" class="delete-button">Delete</button>
        </form>

        <!-- Cancel Button -->
        <a href="${pageContext.request.contextPath}/dashboardInstitution">Cancel</a>
    </div>
</body>
</html>

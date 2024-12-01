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
            display: inline-block;
            margin-top: 10px;
            color: #0056b3;
            text-decoration: none;
            text-align: center;
        }
        a:hover {
            text-decoration: underline;
        }
    </style>
</head>
<body>
    <div class="container">
        <h1>Create New Course</h1>
        <form action="${pageContext.request.contextPath}/course/create" method="post">
            <label for="institutionId">Institution ID:</label>
            <input type="number" id="institutionId" name="institutionId" required />

            <label for="courseTitle">Course Title:</label>
            <input type="text" id="courseTitle" name="courseTitle" required />

            <label for="courseCode">Course Code:</label>
            <input type="text" id="courseCode" name="courseCode" required />

            <label for="term">Term:</label>
            <input type="text" id="term" name="term" required />

            <label for="outline">Outline:</label>
            <textarea id="outline" name="outline" required></textarea>

            <label for="schedule">Schedule:</label>
            <input type="text" id="schedule" name="schedule" required />

            <label for="preferredQualifications">Preferred Qualifications:</label>
            <textarea id="preferredQualifications" name="preferredQualifications"></textarea>

            <label for="deliveryMethod">Delivery Method:</label>
            <input type="text" id="deliveryMethod" name="deliveryMethod" required />

            <label for="compensation">Compensation:</label>
            <input type="number" id="compensation" name="compensation" step="0.01" required />

            <button type="submit">Create Course</button>
        </form>
        <a href="${pageContext.request.contextPath}/dashboardInstitution">Back to Dashboard</a>
    </div>
</body>
</html>

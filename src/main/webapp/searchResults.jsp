<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <title>Search Results</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            background-color: #f8f9fa; /* Match index.jsp */
            color: #343a40; /* Dark gray text */
            margin: 0;
            padding: 0;
        }
        .container {
            margin: 20px auto;
            max-width: 1000px;
            background: #ffffff;
            padding: 20px;
            border-radius: 8px;
            box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
        }
        h1 {
            color: #0056b3; /* Blue accent */
            text-align: center;
        }
        table {
            width: 100%;
            border-collapse: collapse;
            margin-top: 20px;
        }
        th, td {
            border: 1px solid #ccc;
            padding: 10px;
            text-align: left;
        }
        th {
            background-color: #f2f2f2;
        }
        td {
            background-color: #ffffff;
        }
        button {
            background-color: #0056b3;
            color: white;
            padding: 8px 16px;
            border: none;
            border-radius: 4px;
            cursor: pointer;
            font-size: 14px;
        }
        button:hover {
            background-color: #003d80;
        }
        a {
            color: #0056b3;
            text-decoration: none;
            margin-top: 20px;
            display: inline-block;
        }
        a:hover {
            text-decoration: underline;
        }
    </style>
</head>
<body>
    <div class="container">
        <h1>Search Results</h1>

        <table>
            <thead>
                <tr>
                    <th>Institution</th>
                    <th>Course Title</th>
                    <th>Course Code</th>
                    <th>Term</th>
                    <th>Outline</th>
                    <th>Schedule</th>
                    <th>Preferred Qualifications</th>
                    <th>Delivery Method</th>
                    <th>Compensation</th>
                    <th>Action</th>
                </tr>
            </thead>
            <tbody>
                <c:forEach var="course" items="${courses}">
                    <tr>
                        <td>${course.institutionName}</td>
                        <td>${course.courseTitle}</td>
                        <td>${course.courseCode}</td>
                        <td>${course.term}</td>
                        <td>${course.outline}</td>
                        <td>${course.schedule}</td>
                        <td>${course.preferredQualifications}</td>
                        <td>${course.deliveryMethod}</td>
                        <td>${course.compensation}</td>
                        <td>
                            <form action="${pageContext.request.contextPath}/teach/request" method="post">
                                <input type="hidden" name="courseId" value="${course.courseId}" />
                                <button type="submit">Request to Teach</button>
                            </form>
                        </td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>

        <a href="${pageContext.request.contextPath}/search/form">New Search</a>
        <br><br>
        <a href="${pageContext.request.contextPath}/logout">Logout</a>
    </div>
</body>
</html>

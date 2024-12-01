<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.aep.model.CourseDTO" %>
<%@ page import="java.util.List" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%
    List<CourseDTO> courses = (List<CourseDTO>) request.getAttribute("courses");
%>
<!DOCTYPE html>
<html>
<head>
    <title>Institution Dashboard</title>
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
            max-width: 1200px;
            background: #ffffff;
            padding: 20px;
            border-radius: 8px;
            box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
        }
        h1, h2, h3 {
            color: #0056b3; /* Blue accent */
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
        }
        a:hover {
            text-decoration: underline;
        }
        .message {
            margin: 20px 0;
            padding: 10px;
            border-radius: 4px;
        }
        .message.success {
            background-color: #d4edda;
            color: #155724;
        }
        .message.error {
            background-color: #f8d7da;
            color: #721c24;
        }
    </style>
</head>
<body>
    <div class="container">
        <h1>Dashboard - Institution</h1>
        <p>Welcome to your institution's dashboard! Below is a list of courses offered by your institution.</p>

        <h2>Courses Offered</h2>
        <table>
            <thead>
                <tr>
                    <th>Course ID</th>
                    <th>Course Title</th>
                    <th>Course Code</th>
                    <th>Term</th>
                    <th>Outline</th>
                    <th>Schedule</th>
                    <th>Preferred Qualifications</th>
                    <th>Delivery Method</th>
                    <th>Compensation</th>
                    <th>Actions</th>
                </tr>
            </thead>
            <tbody>
                <c:forEach var="course" items="${courses}">
                    <tr>
                        <td>${course.courseId}</td>
                        <td>${course.courseTitle}</td>
                        <td>${course.courseCode}</td>
                        <td>${course.term}</td>
                        <td>${course.outline}</td>
                        <td>${course.schedule}</td>
                        <td>${course.preferredQualifications}</td>
                        <td>${course.deliveryMethod}</td>
                        <td>${course.compensation}</td>
                        <td>
                            <a href="${pageContext.request.contextPath}/course/edit?courseId=${course.courseId}">Edit</a>
                        </td>
                    </tr>
                </c:forEach>
                <c:if test="${empty courses}">
                    <tr>
                        <td colspan="10" style="text-align:center;">No courses found.</td>
                    </tr>
                </c:if>
            </tbody>
        </table>
        <br/>
        <a href="createCourse.jsp">Create a New Course</a>
        <br/>

        <h3>Requests to Teach</h3>
        <table>
            <thead>
                <tr>
                    <th>Request ID</th>
                    <th>Course Title</th>
                    <th>Professional Name</th>
                    <th>Status</th>
                    <th>Actions</th>
                </tr>
            </thead>
            <tbody>
                <c:forEach var="request" items="${teachRequests}">
                    <tr>
                        <td>${request.requestId}</td>
                        <td>${request.courseTitle}</td>
                        <td>${request.professionalName}</td>
                        <td>${request.status}</td>
                        <td>
                            <form action="${pageContext.request.contextPath}/teach/respond" method="post" style="display:inline;">
                                <input type="hidden" name="requestId" value="${request.requestId}" />
                                <input type="hidden" name="action" value="accept" />
                                <button type="submit">Accept</button>
                            </form>
                            <form action="${pageContext.request.contextPath}/teach/respond" method="post" style="display:inline;">
                                <input type="hidden" name="requestId" value="${request.requestId}" />
                                <input type="hidden" name="action" value="reject" />
                                <button type="submit">Reject</button>
                            </form>
                        </td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>

        <h3>Messages</h3>
        <c:if test="${not empty param.message}">
            <div class="message success">${param.message}</div>
        </c:if>
        <c:if test="${not empty param.error}">
            <div class="message error">${param.error}</div>
        </c:if>

        <a href="login.jsp">Logout</a>
    </div>
</body>
</html>

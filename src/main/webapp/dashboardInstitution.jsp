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
        table {
            width: 100%;
            border-collapse: collapse;
        }
        table, th, td {
            border: 1px solid black;
        }
        th, td {
            padding: 10px;
            text-align: left;
        }
        th {
            background-color: #f2f2f2;
        }
    </style>
</head>
<body>
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
                    <td colspan="9" style="text-align:center;">No courses found.</td>
                </tr>
            </c:if>
        </tbody>
    </table>
    <br/>
    <a href="createCourse.jsp">Create a New Course</a>
    <br/>
    
<h3>Requests to Teach</h3>
<table border="1">
    <tr>
        <th>Request ID</th>
        <th>Course Title</th>
        <th>Professional Name</th>
        <th>Status</th>
        <th>Actions</th>
    </tr>
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

    
    <h3>Messages</h3>
    <c:if test="${not empty param.message}">
        <p style="color:green;">${param.message}</p>
    </c:if>
    <c:if test="${not empty param.error}">
        <p style="color:red;">${param.error}</p>
    </c:if>

    <a href="login.jsp">Logout</a>
</body>
</html>

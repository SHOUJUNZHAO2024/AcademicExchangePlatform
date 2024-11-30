<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <title>Search Results</title>
</head>
<body>
    <h1>Search Results</h1>
    <table border="1">
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
        </tr>
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
            </tr>
        </c:forEach>
    </table>
    <a href="${pageContext.request.contextPath}/search/form">New Search</a>

</body>
</html>

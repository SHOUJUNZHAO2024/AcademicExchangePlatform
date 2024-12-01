<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.aep.model.CourseDTO" %>
<%@ page import="java.util.List" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<h2>Notifications</h2>
<c:forEach var="notification" items="${notifications}">
    <p>Your request to teach ${notification.courseCode} for ${notification.term} is ${notification.status} by ${notification.institutionName}.</p>
</c:forEach>

</body>
</html>
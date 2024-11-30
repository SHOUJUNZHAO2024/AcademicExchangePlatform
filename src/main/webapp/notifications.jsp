<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.List" %>
<%@ page import="com.aep.model.TeachRequestDTO" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%
    Object notificationsObject = request.getAttribute("notifications");
    List<TeachRequestDTO> notifications = null;
    if (notificationsObject instanceof List) {
        notifications = (List<TeachRequestDTO>) notificationsObject;
    }
%>
<!DOCTYPE html>
<html>
<head>
    <title>Notifications</title>
</head>
<body>
    <h1>Your Notifications</h1>
    <c:if test="${not empty notifications}">
        <table border="1">
            <tr>
                <th>Course Code</th>
                <th>Term</th>
                <th>Status</th>
                <th>Institution</th>
            </tr>
            <c:forEach var="notification" items="${notifications}">
                <tr>
                    <td>${notification.courseCode}</td>
                    <td>${notification.term}</td>
                    <td>${notification.status}</td>
                    <td>${notification.institutionName}</td>
                </tr>
            </c:forEach>
        </table>
    <else>
        <p>No notifications available.</p>
    </c:if>
</body>
</html>

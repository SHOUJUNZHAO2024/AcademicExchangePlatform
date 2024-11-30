<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.List" %>
<%@ page import="com.aep.model.TeachRequestDTO" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%
    List<TeachRequestDTO> requests = (List<TeachRequestDTO>) request.getAttribute("requests");
    if (requests == null) {
        response.sendRedirect("dashboard.jsp?error=No requests found for this course.");
    }
%>
<!DOCTYPE html>
<html>
<head>
    <title>Manage Teach Requests</title>
</head>
<body>
    <h1>Manage Teach Requests</h1>
    <table border="1">
        <tr>
            <th>Request ID</th>
            <th>Professional ID</th>
            <th>Status</th>
            <th>Actions</th>
        </tr>
        <c:forEach var="request" items="${requests}">
            <tr>
                <td>${request.requestId}</td>
                <td>${request.professionalId}</td>
                <td>${request.status}</td>
                <td>
                    <form action="${pageContext.request.contextPath}/teachRequest/updateStatus" method="post" style="display:inline;">
                        <input type="hidden" name="requestId" value="${request.requestId}" />
                        <button type="submit" name="status" value="Accepted">Accept</button>
                    </form>
                    <form action="${pageContext.request.contextPath}/teachRequest/updateStatus" method="post" style="display:inline;">
                        <input type="hidden" name="requestId" value="${request.requestId}" />
                        <button type="submit" name="status" value="Rejected">Reject</button>
                    </form>
                </td>
            </tr>
        </c:forEach>
    </table>
    <a href="dashboard.jsp">Back to Dashboard</a>
</body>
</html>

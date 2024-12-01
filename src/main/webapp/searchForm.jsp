<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <title>Search Courses</title>
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
            margin: 10px 0 5px;
            font-weight: bold;
        }
        select, button {
            width: 100%;
            padding: 10px;
            margin-bottom: 20px;
            border: 1px solid #ccc;
            border-radius: 4px;
        }
        button {
            background-color: #0056b3;
            color: white;
            font-size: 16px;
            cursor: pointer;
            border: none;
        }
        button:hover {
            background-color: #003d80;
        }
        ul {
            list-style-type: none;
            padding: 0;
        }
        ul li {
            background: #e6f7ff;
            padding: 10px;
            margin: 5px 0;
            border: 1px solid #b3e0ff;
            border-radius: 4px;
        }
        a {
            color: #0056b3;
            text-decoration: none;
        }
        a:hover {
            text-decoration: underline;
        }
    </style>
    <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
    <script>
        $(document).ready(function () {
            // Display success message if present in the URL
            const urlParams = new URLSearchParams(window.location.search);
            if (urlParams.has('success')) {
                alert('You have successfully requested to teach this course!');
            }

            // Fetch course codes when an institution is selected
            $('#institutionName').change(function () {
                var institutionName = $(this).val();
                if (institutionName) {
                    $.ajax({
                        url: '${pageContext.request.contextPath}/search/getCourseCodes',
                        type: 'GET',
                        data: { institutionName: institutionName },
                        success: function (data) {
                            var courseCodes = data.split(',');
                            var courseCodeDropdown = $('#courseCode');
                            courseCodeDropdown.empty();
                            courseCodeDropdown.append('<option value="">--Select--</option>');
                            courseCodes.forEach(function (code) {
                                courseCodeDropdown.append('<option value="' + code + '">' + code + '</option>');
                            });
                        }
                    });
                } else {
                    $('#courseCode').empty().append('<option value="">--Select--</option>');
                    $('#courseTitle').empty().append('<option value="">--Select--</option>');
                }
            });

            // Fetch course titles when a course code is selected
            $('#courseCode').change(function () {
                var courseCode = $(this).val();
                if (courseCode) {
                    $.ajax({
                        url: '${pageContext.request.contextPath}/search/getCourseTitles',
                        type: 'GET',
                        data: { courseCode: courseCode },
                        success: function (data) {
                            var courseTitles = data.split(',');
                            var courseTitleDropdown = $('#courseTitle');
                            courseTitleDropdown.empty();
                            courseTitleDropdown.append('<option value="">--Select--</option>');
                            courseTitles.forEach(function (title) {
                                courseTitleDropdown.append('<option value="' + title + '">' + title + '</option>');
                            });
                        }
                    });
                } else {
                    $('#courseTitle').empty().append('<option value="">--Select--</option>');
                }
            });
        });
    </script>
</head>
<body>
    <div class="container">
        <h1>Search for Available Courses</h1>
        <form action="${pageContext.request.contextPath}/search" method="post">
            <label for="institutionName">Institution Name:</label>
            <select id="institutionName" name="institutionName">
                <option value="">--Select--</option>
                <c:forEach var="institution" items="${institutions}">
                    <option value="${institution}">${institution}</option>
                </c:forEach>
            </select>

            <label for="courseCode">Course Code:</label>
            <select id="courseCode" name="courseCode">
                <option value="">--Select--</option>
            </select>

            <label for="courseTitle">Course Title:</label>
            <select id="courseTitle" name="courseTitle">
                <option value="">--Select--</option>
            </select>

            <label for="term">Term:</label>
            <select id="term" name="term">
                <option value="">--Select--</option>
                <option value="24F">24F</option>
                <option value="24S">24S</option>
                <option value="24W">24W</option>
            </select>

            <label for="schedule">Schedule:</label>
            <select id="schedule" name="schedule">
                <option value="">--Select--</option>
                <option value="Morning">Morning</option>
                <option value="Afternoon">Afternoon</option>
                <option value="Evening">Evening</option>
            </select>

            <label for="deliveryMethod">Delivery Method:</label>
            <select id="deliveryMethod" name="deliveryMethod">
                <option value="">--Select--</option>
                <option value="In-Person">In-Person</option>
                <option value="Remote">Remote</option>
                <option value="Hybrid">Hybrid</option>
            </select>

            <button type="submit">Search</button>

            <c:if test="${not empty notifications}">
                <h3>Notifications</h3>
                <ul>
                    <c:forEach var="notification" items="${notifications}">
                        <li>
                            Your request to teach <strong>${notification.courseTitle}</strong>
                            for the institution <strong>${notification.institutionName}</strong>
                            is <strong>${notification.status}</strong>.
                        </li>
                    </c:forEach>
                </ul>
            </c:if>

            <a href="${pageContext.request.contextPath}/logout">Logout</a>
        </form>
    </div>
</body>
</html>


<!DOCTYPE html>
<html>
<head>
    <title>Register</title>
</head>
<body>
    <h1>Register</h1>
    <form action="user/register" method="post">
        <label for="username">Username:</label>
        <input type="text" id="username" name="username" required /><br/>

        <label for="password">Password:</label>
        <input type="password" id="password" name="password" required /><br/>

        <label for="email">Email:</label>
        <input type="email" id="email" name="email" required /><br/>

        <label for="userType">User Type:</label>
        <select id="userType" name="userType" required>
            <option value="Professional">Academic Professional</option>
            <option value="Institution">Academic Institution</option>
        </select><br/>

        <button type="submit">Register</button>
    </form>
    <p>Already have an account? <a href="login.jsp">Login here</a></p>
</body>
</html>

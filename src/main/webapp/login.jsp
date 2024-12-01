<!DOCTYPE html>
<html>
<head>
    <title>Login</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            background-color: #f0f8ff; /* Match the light background color */
            color: #333333; /* Match text color */
            margin: 0;
            padding: 0;
            display: flex;
            justify-content: center;
            align-items: center;
            height: 100vh;
        }

        .container {
            background-color: #ffffff;
            border-radius: 8px;
            padding: 20px 30px;
            box-shadow: 0px 4px 8px rgba(0, 0, 0, 0.1);
            width: 350px;
            text-align: center;
        }

        h1 {
            font-size: 28px;
            margin-bottom: 20px;
            color: #0056b3; /* Match the blue accent color */
        }

        label {
            font-size: 14px;
            display: block;
            margin-top: 15px;
            color: #333333;
        }

        input {
            width: 100%;
            padding: 10px;
            margin-top: 5px;
            margin-bottom: 15px;
            border: 1px solid #cccccc;
            border-radius: 4px;
            font-size: 14px;
        }

        button {
            background-color: #0056b3; /* Match the blue button color */
            color: white;
            border: none;
            padding: 10px 20px;
            border-radius: 4px;
            cursor: pointer;
            font-size: 16px;
            margin-top: 10px;
        }

        button:hover {
            background-color: #003d80; /* Darker blue on hover */
        }

        p {
            margin-top: 15px;
            font-size: 14px;
        }

        a {
            color: #0056b3; /* Match the blue accent color */
            text-decoration: none;
            font-weight: bold;
        }

        a:hover {
            text-decoration: underline;
        }
    </style>
</head>
<body>
    <div class="container">
        <h1>Login</h1>
        <form action="user/login" method="post">
            <label for="username">Username:</label>
            <input type="text" id="username" name="username" placeholder="Enter your username" required />

            <label for="password">Password:</label>
            <input type="password" id="password" name="password" placeholder="Enter your password" required />

            <button type="submit">Login</button>
        </form>
        <p>Don't have an account? <a href="register.jsp">Register here</a></p>
    </div>
</body>
</html>

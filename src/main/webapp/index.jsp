<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Academic Exchange Platform</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            background: linear-gradient(to bottom, #4facfe, #00f2fe);
            color: #333;
            margin: 0;
            padding: 0;
        }

        header {
            background-color: #0066cc;
            color: white;
            padding: 20px;
            text-align: center;
            font-size: 24px;
        }

        .container {
            display: flex;
            flex-direction: column;
            align-items: center;
            justify-content: center;
            height: 80vh;
            text-align: center;
        }

        h1 {
            font-size: 36px;
            color: #004080;
            margin-bottom: 10px;
        }

        p {
            font-size: 18px;
            margin-bottom: 20px;
        }

        a {
            text-decoration: none;
            font-size: 18px;
            padding: 10px 20px;
            border-radius: 5px;
            margin: 5px;
            transition: background-color 0.3s, color 0.3s;
        }

        a:hover {
            color: white;
        }

        a[href="login.jsp"] {
            background-color: #0066cc;
            color: white;
            border: 1px solid #0066cc;
        }

        a[href="login.jsp"]:hover {
            background-color: #004080;
        }

        a[href="register.jsp"] {
            background-color: white;
            color: #0066cc;
            border: 1px solid #0066cc;
        }

        a[href="register.jsp"]:hover {
            background-color: #0066cc;
            color: white;
        }

        footer {
            text-align: center;
            background-color: #004080;
            color: white;
            padding: 10px 0;
            position: fixed;
            bottom: 0;
            width: 100%;
        }
    </style>
</head>
<body>
    <header>Academic Exchange Platform</header>
    <div class="container">
        <h1>Welcome to the Academic Exchange Platform</h1>
        <p>Connecting institutions and academic professionals worldwide!</p>
        <div>
            <a href="login.jsp">Login</a>
            <a href="register.jsp">Register</a>
        </div>
    </div>
    <footer>
        © 2024 Academic Exchange Platform. All Rights Reserved.
    </footer>
</body>
</html>


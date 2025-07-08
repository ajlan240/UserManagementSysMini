<%--
  Created by IntelliJ IDEA.
  User: ajlan
  Date: 07-07-2025
  Time: 15:06
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Responsive Navbar</title>
    <style>
        * {
            margin: 0;
            padding: 0;
            box-sizing: border-box;
        }

        body {
            font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;
            background: linear-gradient(to right, #654ea3, #eaafc8);
        }

        nav {
            background-color: #2c2c54;
            color: white;
            display: flex;
            justify-content: space-between;
            align-items: center;
            padding: 15px 30px;
            box-shadow: 0 2px 10px rgba(0,0,0,0.2);
        }

        .logo {
            font-size: 22px;
            font-weight: bold;
        }

        .nav-links {
            list-style: none;
            display: flex;
            gap: 20px;
        }

        .nav-links li {
            display: inline;
        }

        .nav-links a {
            text-decoration: none;
            color: white;
            padding: 8px 15px;
            border-radius: 5px;
            transition: background 0.3s ease;
        }

        .nav-links a:hover {
            background-color: #40407a;
        }

        @media (max-width: 600px) {
            nav {
                flex-direction: column;
                align-items: flex-start;
            }

            .nav-links {
                flex-direction: column;
                width: 100%;
            }

            .nav-links a {
                display: block;
                width: 100%;
            }
        }
    </style>
</head>
<body>

<nav>
    <div class="logo">UserGrid</div>
    <ul class="nav-links">
        <li><a href="<%=request.getContextPath()%>/list"
               class="nav-link">Users</a></li>
        <li><a href="#">Login</a></li>
        <li><a href="#">Logout</a></li>
    </ul>
</nav>

</body>
</html>


<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>User Registration</title>
    <style>
        body {
            margin: 0;
            font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;
            background: linear-gradient(to right, #654ea3, #eaafc8);
            display: flex;
            justify-content: center;
            align-items: center;
            height: 100vh;
        }

        .form-container {
            background-color: rgba(255, 255, 255, 0.95);
            padding: 30px 40px;
            border-radius: 12px;
            box-shadow: 0 8px 20px rgba(0, 0, 0, 0.2);
            max-width: 400px;
            width: 100%;
        }

        .form-container h2 {
            text-align: center;
            color: #2c2c54;
            margin-bottom: 25px;
        }

        label {
            display: block;
            margin-bottom: 8px;
            font-weight: bold;
            color: #2c2c54;
        }

        input, select {
            width: 100%;
            padding: 10px;
            margin-bottom: 20px;
            border: 2px solid #ccc;
            border-radius: 8px;
            font-size: 14px;
            transition: border-color 0.3s ease;
        }

        input:focus, select:focus {
            border-color: #2c2c54;
            outline: none;
        }

        button {
            background-color: #2c2c54;
            color: white;
            padding: 12px 20px;
            border: none;
            border-radius: 8px;
            font-size: 16px;
            width: 100%;
            cursor: pointer;
            transition: background-color 0.3s ease;
        }

        button:hover {
            background-color: #40407a;
        }
    </style>
</head>
<body>

<div class="form-container">

        <h2>
            <c:if test="${user != null}">
                Edit User Data
            </c:if>

            <c:if test="${user == null}">
                Add New User Data
            </c:if>
            </h2>
        <c:if test="${user != null}">
        <form action="update" method="post">
        </c:if>

        <c:if test="${user == null}">
        <form action="insert" method="post">
        </c:if>

        <c:if test="${user != null}">
            <input type="hidden" name="id" value="<c:out value="${user.id}"/>">
        </c:if>
        <label for="name">Name</label>
        <input type="text" id="name" name="name" required value="<c:out value="${user.name}"/>">

        <label for="email">Email</label>
        <input type="email" id="email" name="email" value="<c:out value="${user.email}"/>" required>

        <label for="country">Country</label>
        <select id="country" name="country" value="<c:out value="${user.country}"/>" required>
            <option value="">-- Select Country --</option>
            <option value="India">India</option>
            <option value="USA">USA</option>
            <option value="UK">UK</option>
            <option value="Canada">Canada</option>
            <!-- Add more if needed -->
        </select>

        <button type="submit">Register</button>
    </form>
</div>

</body>
</html>

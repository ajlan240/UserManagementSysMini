<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Order Table</title>
    <style>
        body {
            margin: 0;
            font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;
            background: linear-gradient(to right, #654ea3, #eaafc8);
        }

        .table-container {
            max-width: 1000px;
            margin: 50px auto;
            background-color: white;
            border-radius: 10px;
            overflow: hidden;
            box-shadow: 0 10px 20px rgba(0,0,0,0.1);
        }

        table {
            width: 100%;
            border-collapse: collapse;
        }

        thead {
            background-color: #2c2c54;
            color: white;
        }

        th, td {
            padding: 15px 20px;
            text-align: left;
        }

        tbody tr:nth-child(even) {
            background-color: #f3f3f3;
        }

        tbody tr:hover {
            background-color: #ffeaa7; /* Change this color to your preferred hover color */
            cursor: pointer;
        }
    </style>
</head>
<body>
<%@include file="components/navbar.jsp"%>

<div style="background-color: transparent;
            overflow: hidden;">
<%--   add new user button--%>
<div>
    <a href="<%= request.getContextPath()%>/new" style="text-decoration: none;">
        <button style="
                        background-color: #2c2c54;
                        color: white;
                        padding: 10px 20px;
                        border: none;
                        border-radius: 6px;
                        font-size: 16px;
                        cursor: pointer;
                        margin: 20px 270px;
                        transition: background 0.3s ease;">
            + Add New User
        </button>
    </a>
</div>
<div class="table-container">
    <table>
        <thead>
        <tr>
            <th>Id</th>
            <th>Name</th>
            <th>Email</th>
            <th>Country</th>
            <th>Actions</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach var="user" items="${userList}">
            <tr>
                <td><c:out value="${user.id}" /></td>
                <td><c:out value="${user.name}" /></td>
                <td><c:out value="${user.email}" /></td>
                <td><c:out value="${user.country}" /></td>
                <td><a href="edit?id=<c:out value='${user.id}' />">Edit</a>
                    &nbsp;&nbsp;&nbsp;&nbsp; <a
                            href="delete?id=<c:out value='${user.id}' />">Delete</a></td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
</div>
</div>
</body>
</html>

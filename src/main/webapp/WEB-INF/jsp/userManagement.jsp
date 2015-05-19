<%@ page import="net.petrovsky.flights.util.TimeUtil" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
<head>
    <title>User Management</title>
</head>
<body>

<jsp:include page="parts/adminControlPanel.jsp"/>
<form action="/admin/users/select/by/secondname" method="get">
    <div>Second name:</div>
    <input type="text" name="second_name" required>
    <input type="submit" value="Select">
</form>
<form action="/admin/users/select/by/email" method="get">
    <div>Email:</div>
    <input type="email" name="email" required>
    <input type="submit" value="Select">
</form>
<a href="/admin/users/select/all">All users</a>
<c:if test="${not empty msgEmptyResult}"><div>${msgEmptyResult}</div></c:if>
<c:if test="${not empty selectedUsers}">
    <table>
        <thead>
            <tr>
                <th>First name</th>
                <th>Second name</th>
                <th>Email</th>
                <th>Registration</th>
                <th>Enabled</th>
                <th>Role</th>
            </tr>
        </thead>
        <c:forEach items="${selectedUsers}" var="user">
            <jsp:useBean id="user" scope="page" type="net.petrovsky.flights.model.User"/>
            <tr>
                <td>${user.firstName}</td>
                <td>${user.secondName}</td>
                <td><a href="mailto:${user.email}">${user.email}</a></td>
                <td><%=TimeUtil.toString(user.getRegistration())%></td>
                <td>${user.enabled}</td>
                <td>${user.role}</td>
                <td><a href="/admin/users/state?user_id=${user.id}">Change state</a> </td>
                <td><a href="/admin/orders?user_id=${user.id}">Orders</a> </td>
            </tr>
        </c:forEach>
    </table>
</c:if>
</body>
</html>

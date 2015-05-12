<%@ page import="net.petrovsky.flights.util.TimeUtil" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
<head>
    <title></title>
</head>
<body>

<form action="/admin/users/select/bysecondname" method="get">
    <div>Second name:</div>
    <input type="text" name="second_name">
    <input type="submit" value="Select">
    <c:if test="${not empty errorName}">
        <div>
            ${errorName}
        </div>
    </c:if>
</form>
<form action="/admin/users/select/byemail" method="get">
    <div>Email:</div>
    <input type="email" name="email">
    <input type="submit" value="Select">
    <c:if test="${not empty errorEmail}">
        <div>
            ${errorEmail}
        </div>
    </c:if>
</form>
<a href="/admin/users/select/all">All users</a>
<c:if test="${not empty emptyResult}">
    <div>
        ${emptyResult}
    </div>
</c:if>
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
                <td><c:out value="${user.firstName}"/></td>
                <td><c:out value="${user.secondName}"/></td>
                <td><a href="mailto:${user.email}">${user.email}</a></td>
                <td><%=TimeUtil.toString(user.getRegistration())%></td>
                <td><%=user.isEnabled()%></td>
                <td>${user.role}</td>
            </tr>
        </c:forEach>
    </table>
</c:if>
</body>
</html>

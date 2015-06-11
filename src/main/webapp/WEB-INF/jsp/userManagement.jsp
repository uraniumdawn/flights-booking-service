<%@ page import="net.petrovsky.flights.util.TimeUtil" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
<head>
    <title>User Management</title>
    <link  href="/resources/css/style.css" rel="stylesheet">
</head>
<body>
<jsp:include page="parts/adminControlPanel.jsp"/>

    <form class="form" action="/admin/users/select/by/secondname" method="get">
        <div class="row">
            <div class="brick lbl">
                Second name:
            </div>
            <div class="brick">
                <input type="text" name="second_name" required>
            </div>
            <div class="brick elem">
                <input class="btn" type="submit" value="Select">
            </div>
        </div>
    </form>
    <form class="form" action="/admin/users/select/by/email" method="get">
        <div class="row">
            <div class="brick lbl">
                Email:
            </div>
            <div class="brick">
                <input type="email" name="email" required>
            </div>
            <div class="brick elem">
                <input class="btn" type="submit" value="Select">
            </div>
        </div>
    </form>
    <div class="embedment"></div>
    <div>
        <a class="btn" href="/admin/users/select/all">All users</a>
    </div>
    <c:if test="${not empty msgEmptyResult}"><div class="res_msg">${msgEmptyResult}</div></c:if>
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
                    <td>${user.email}</td>
                    <td><%=TimeUtil.toString(user.getRegistration())%></td>
                    <td>${user.enabled}</td>
                    <td>${user.role}</td>
                    <td><a class="btn" href="/admin/users/state?user_id=${user.id}">Change state</a> </td>
                    <td><a class="btn" href="/admin/orders?user_id=${user.id}">Orders</a> </td>
                </tr>
            </c:forEach>
        </table>
    </c:if>

<jsp:include page="parts/footer.jsp"/>
</body>
</html>

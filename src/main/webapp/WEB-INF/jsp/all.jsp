<%@ page import="net.petrovsky.flights.util.TimeUtil" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
<head>
    <title>All data</title>
    <link  href="/resources/css/style.css" rel="stylesheet">
</head>
<body>
<hr/>
<hr/>
<c:if test="${not empty users}">
    <table>
        <thead>
            <tr>
                <th>ID</th>
                <th>First name</th>
                <th>Second name</th>
                <th>Email</th>
                <th>Registration</th>
                <th>Enabled</th>
                <th>Role</th>
            </tr>
        </thead>
        <c:forEach items="${users}" var="user">
            <jsp:useBean id="user" scope="page" type="net.petrovsky.flights.model.User"/>
            <tr>
                <td>${user.id}</td>
                <td>${user.firstName}</td>
                <td>${user.secondName}</td>
                <td>${user.email}</td>
                <td><%=TimeUtil.toString(user.getRegistration())%></td>
                <td>${user.enabled}</td>
                <td>${user.role}</td>
            </tr>
        </c:forEach>
    </table>
</c:if>
<hr/>
<hr/>
<c:if test="${not empty airports}">
    <table>
        <thead>
        <tr>
            <th>IATA code</th>
            <th>Name</th>
            <th>City</th>
            <th>Country</th>
        </tr>
        </thead>
        <c:forEach items="${airports}" var="airport">
            <jsp:useBean id="airport" scope="page" type="net.petrovsky.flights.model.Airport"/>
            <tr>
                <td>${airport.IATAcode}</td>
                <td>${airport.name}</td>
                <td>${airport.city}</td>
                <td>${airport.country}</td>
            </tr>
        </c:forEach>
    </table>
</c:if>
<hr/>
<hr/>
<c:if test="${not empty flights}">
    <table>
        <thead>
        <tr>
            <th>ID</th>
            <th>Point of departure</th>
            <th>Destination</th>
            <th>Time</th>
            <th>Price</th>
        </tr>
        </thead>
        <c:forEach items="${flights}" var="flight">
            <jsp:useBean id="flight" scope="page" type="net.petrovsky.flights.model.Flight"/>
            <tr>
                <td>${flight.id}</td>
                <td>${flight.pointOfDeparture.name}</td>
                <td>${flight.destination.name}</td>
                <td><%=TimeUtil.toString(flight.getTime())%></td>
                <td>${flight.price}</td>
            </tr>
        </c:forEach>
    </table>
</c:if>
<c:if test="${not empty orders}">
    <table>
        <thead>
        <tr>
            <th>ID</th>
            <th>User</th>
            <th>Flight</th>
        </tr>
        </thead>
        <c:forEach items="${orders}" var="order">
            <jsp:useBean id="order" scope="page" type="net.petrovsky.flights.model.Order"/>
            <tr>
                <td>${order.id}</td>
                <td>${order.user.firstName} ${order.user.secondName}</td>
                <td>${order.flight.pointOfDeparture.name}//${order.flight.destination.name}//<%=TimeUtil.toString(order.getFlight().getTime())%>//${order.flight.price}</td>
            </tr>
        </c:forEach>
    </table>
</c:if>

<jsp:include page="parts/footer.jsp"/>
</body>
</html>

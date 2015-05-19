<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page import="net.petrovsky.flights.util.TimeUtil" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
    <title>Your all orders</title>
</head>
<body>
<jsp:include page="parts/adminControlPanel.jsp"/>
    <div>
        <c:choose>
            <c:when test="${not empty orders}">
                <table>
                    <thead>
                    <tr>
                        <th>Point of departure</th>
                        <th>Destination</th>
                        <th>Time</th>
                        <th>Price</th>
                    </tr>
                    </thead>
                    <c:forEach items="${orders}" var="order">
                        <jsp:useBean id="order" scope="page" type="net.petrovsky.flights.model.Order"/>
                        <tr>
                            <td>${order.flight.pointOfDeparture.name}</td>
                            <td>${order.flight.destination.name}</td>
                            <td><%=TimeUtil.toString(order.getFlight().getTime())%></td>
                            <td>${order.flight.price}</td>
                        </tr>
                    </c:forEach>
                </table>
            </c:when>
            <c:otherwise>
                <div>You do not have any booked flights</div>
            </c:otherwise>
        </c:choose>
    </div>
</body>
</html>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page import="net.petrovsky.flights.util.TimeUtil" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>


<html>
<head>
    <title>Main</title>
</head>
<body>
<jsp:include page="parts/header.jsp"/>
    <div>
        <c:if test="${not empty successfullOrder}">
            <div>
                ${successfullOrder}
            </div>
        </c:if>
        <div>
            <p>Your previous orders</p>
        </div>
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
                        <jsp:useBean id="order" scope="page" type="net.petrovsky.flights.model.Booking"/>
                        <tr>
                            <td><c:out value="${order.flight.pointOfDeparture.name}"/></td>
                            <td><c:out value="${order.flight.destination.name}"/></td>
                            <td><%=TimeUtil.toString(order.getFlight().getTime())%></td>
                            <td><c:out value="${order.flight.price}"/></td>
                        </tr>
                    </c:forEach>
                </table>
            </c:when>
            <c:otherwise>
                <p>You do nor have any booked flights</p>
            </c:otherwise>
        </c:choose>
    </div>
</body>
</html>

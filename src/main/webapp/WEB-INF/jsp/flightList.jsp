<%@ page import="net.petrovsky.flights.util.TimeUtil" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
<head>
    <title></title>
</head>
<body>
<table>
    <thead>
        <tr>
            <th>Point of departure</th>
            <th>Destination</th>
            <th>Time</th>
            <th>Price</th>
        </tr>
    </thead>
        <c:forEach items="${flightList}" var="flight">
            <jsp:useBean id="flight" scope="page" type="net.petrovsky.flights.model.Flight"/>
            <tr>
                <td><c:out value="${flight.pointOfDeparture}"/></td>
                <td><c:out value="${flight.destination}"/></td>
                <td><%=TimeUtil.toString(flight.getTime())%></td>
                <td><%=flight.getPrice()%></td>
            </tr>
        </c:forEach>
</table>
</body>
</html>

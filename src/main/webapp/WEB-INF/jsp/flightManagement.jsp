<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="net.petrovsky.flights.util.TimeUtil" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
<head>
    <title>Flight Management</title>
</head>
<body>

<jsp:include page="parts/adminControlPanel.jsp"/>
<form action="/admin/flights/select/by/destination" method="get">
    <div>Destination:</div>
    <select name="destination" required>
        <option value="NONE">---Select---</option>
        <c:forEach items="${airportList}" var="airportD">
            <jsp:useBean id="airportD" scope="page" class="net.petrovsky.flights.model.Airport"/>
            <c:choose>
                <c:when test="${not empty choiceD}">
                    <option value="${airportD.IATAcode}" ${choiceD.equals(airportD.IATAcode) ? "selected" : ""}>${airportD.name}</option>
                </c:when>
                <c:otherwise>
                    <option value="${airportD.IATAcode}">${airportD.name}</option>
                </c:otherwise>
            </c:choose>
        </c:forEach>
    </select>
    <input type="submit" value="Select">
</form>
<form action="/admin/flights/select/by/pointod" method="get">
    <div>Point of departure:</div>
    <select name="point_of_departure" required>
        <option value="NONE">---Select---</option>
        <c:forEach items="${airportList}" var="airportPOD">
            <jsp:useBean id="airportPOD" scope="page" type="net.petrovsky.flights.model.Airport"/>
            <c:choose>
                <c:when test="${not empty choicePOD}">
                    <option value="${airportPOD.IATAcode}" ${choicePOD.equals(airportPOD.IATAcode) ? "selected" : ""}>${airportPOD.name}</option>
                </c:when>
                <c:otherwise>
                    <option value="${airportPOD.IATAcode}">${airportPOD.name}</option>
                </c:otherwise>
            </c:choose>
        </c:forEach>
    </select>
    <input type="submit" value="Select">
</form>
<a href="/admin/flights/select/all">All flights</a>
<a href="/admin/flights/addnew">Add flight</a>
<c:if test="${not empty emptyResult}">
    <div>
        ${emptyResult}
    </div>
</c:if>
<div>
    <c:if test="${not empty selectedFlights}">
        <table>
            <thead>
            <tr>
                <th>Point of departure</th>
                <th>Destination</th>
                <th>Time</th>
                <th>Price</th>
            </tr>
            </thead>
            <c:forEach items="${selectedFlights}" var="flight">
                <jsp:useBean id="flight" scope="page" type="net.petrovsky.flights.model.Flight"/>
                <tr>
                    <td>${flight.pointOfDeparture.name}</td>
                    <td>${flight.destination.name}</td>
                    <td><%=TimeUtil.toString(flight.getTime())%></td>
                    <td>${flight.price}</td>
                </tr>
            </c:forEach>
        </table>
    </c:if>
</div>
</body>
</html>

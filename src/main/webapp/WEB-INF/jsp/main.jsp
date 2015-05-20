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
    <form action="/flights/select" method="get">
        <div>Destination:</div>
        <select name="destination" required>
            <option value="NONE">---Select---</option>
            <c:forEach items="${airportList}" var="airportD">
                <jsp:useBean id="airportD" scope="page" class="net.petrovsky.flights.model.Airport"/>
                <c:choose>
                    <c:when test="${not empty choice}">
                        <option value="${airportD.IATAcode}" ${choice['destination'].equals(airportD.IATAcode) ? "selected" : ""}>${airportD.name}</option>
                    </c:when>
                    <c:otherwise>
                        <option value="${airportD.IATAcode}">${airportD.name}</option>
                    </c:otherwise>
                </c:choose>
            </c:forEach>
        </select>
        <div>Point Of Departure:</div>
        <select name="point_of_departure" required>
            <option value="NONE">---Select---</option>
            <c:forEach items="${airportList}" var="airportPOD">
                <jsp:useBean id="airportPOD" scope="page" type="net.petrovsky.flights.model.Airport"/>
                <c:choose>
                    <c:when test="${not empty choice}">
                        <option value="${airportPOD.IATAcode}" ${choice['point_of_departure'].equals(airportPOD.IATAcode) ? "selected" : ""}>${airportPOD.name}</option>
                    </c:when>
                    <c:otherwise>
                        <option value="${airportPOD.IATAcode}">${airportPOD.name}</option>
                    </c:otherwise>
                </c:choose>
            </c:forEach>
        </select>
        <div>From:</div><input type="date" name="from" value="${choice['from']}" required>
        <div>To:</div><input type="date" name="to" value="${choice['to']}" required>
        <c:if test="${not empty msgIncorrectDates}"><div>${msgIncorrectDates}</div></c:if>
        <input type="submit" value="Select">
    </form>
    <c:choose>
        <c:when test="${not empty selectedFlights}">
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
                        <td>
                            <c:choose>
                                <c:when test="${not empty user}">
                                    <a href="/preorder/add?flight_id=${flight.id}">Add to preorder list</a>
                                </c:when>
                                <c:otherwise>
                                    <div>Only for registered users</div>
                                </c:otherwise>
                            </c:choose>
                        </td>
                    </tr>
                </c:forEach>
            </table>
        </c:when>
        <c:otherwise>
            <div>You did not chose any flights</div>
        </c:otherwise>
    </c:choose>
</div>
<jsp:include page="parts/preorderList.jsp"/>
<div>
    <br/>
    Admin user: Laura Palmer email: WhoKillLauraPalmer@lynch.com, pass: FireWalkWithMe<br/>
    https://flights-booking-service.herokuapp.com/admin<br/>
    App has some pre-defined data
</div>
</body>
</html>

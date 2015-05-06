<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page import="net.petrovsky.flights.util.TimeUtil" %>
<%@ page import="java.util.List" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>


<html>
<head>
    <title>Main</title>
</head>
<body>
<jsp:include page="parts/header.jsp"/>
<% List selectedFlightList = (List)session.getAttribute("selectedFlightList"); %>
    <div>
        <form action="/selectflights" method="get">
            <p>Destination:</p>
                <select name="destination">
                    <option value="NONE">---Select---</option>
                    <c:forEach items="${airportList}" var="airport">
                        <jsp:useBean id="airport" scope="page" type="net.petrovsky.flights.model.Airport"/>
                        <option value="${airport.IATAcode}">${airport.name}</option>
                    </c:forEach>
                </select>
            <p>Point Of Departure:</p>
            <select name="point_of_departure">
                <option value="NONE">---Select---</option>
                <c:forEach items="${airportList}" var="airport">
                    <option value="${airport.IATAcode}">${airport.name}</option>
                </c:forEach>
            </select>
            <p>Returning:</p><input type="date" name="from">
            <p>Departing:</p><input type="date" name="to">
            <input type="submit" value="Select">
        </form>
        <c:choose>
            <c:when test="<%= selectedFlightList != null %>">
                <table>
                    <thead>
                    <tr>
                        <th>Point of departure</th>
                        <th>Destination</th>
                        <th>Time</th>
                        <th>Price</th>
                    </tr>
                    </thead>
                    <c:forEach items="<%= selectedFlightList %>" var="flight">
                        <jsp:useBean id="flight" scope="page" type="net.petrovsky.flights.model.Flight"/>
                        <tr>
                            <td><c:out value="${flight.pointOfDeparture.name}"/></td>
                            <td><c:out value="${flight.destination.name}"/></td>
                            <td><%=TimeUtil.toString(flight.getTime())%></td>
                            <td><c:out value="${flight.price}"/></td>
                            <td>
                                <c:choose>
                                    <c:when test='<%= session.getAttribute("user") != null %>'>
                                        <a href="/addtopreorder?flight_id=${flight.id}">Add to preorder list</a>
                                    </c:when>
                                    <c:otherwise>
                                        <p>Only for registered users</p>
                                    </c:otherwise>
                                </c:choose>
                            </td>
                        </tr>
                    </c:forEach>
                </table>
            </c:when>
            <c:otherwise>
                <p>You did not chose any flights</p>
            </c:otherwise>
        </c:choose>
    </div>
    <jsp:include page="parts/preorderList.jsp"/>
</body>
</html>

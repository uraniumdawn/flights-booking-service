<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page import="net.petrovsky.flights.util.TimeUtil" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.Map" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>


<html>
<head>
    <title>Main</title>
</head>
<body>

<jsp:include page="parts/header.jsp"/>

<% List selectedFlightList = (List)session.getAttribute("selectedFlightList"); %>
<% Map choiceExist = (Map)session.getAttribute("choice"); %>
<div>

    <form action="/selectflights" method="get">
        <p>Destination:</p>
        <select name="destination" required>
                <option value="NONE">---Select---</option>
                <c:forEach items="${airportList}" var="airportD">
                    <jsp:useBean id="airportD" scope="page" class="net.petrovsky.flights.model.Airport"/>
                    <c:choose>
                        <c:when test="<%= choiceExist != null %>">
                            <option value="${airportD.IATAcode}" <%= choiceExist.get("destination").equals(airportD.getIATAcode())? "selected" : "" %>>${airportD.name}</option>
                        </c:when>
                        <c:otherwise>
                            <option value="${airportD.IATAcode}">${airportD.name}</option>
                        </c:otherwise>
                    </c:choose>
                </c:forEach>
            </select>
            <p>Point Of Departure:</p>
            <select name="point_of_departure" required>
                <option value="NONE">---Select---</option>
                <c:forEach items="${airportList}" var="airportPOD">
                    <jsp:useBean id="airportPOD" scope="page" type="net.petrovsky.flights.model.Airport"/>
                    <c:choose>
                        <c:when test="<%= choiceExist != null %>">
                            <option value="${airportPOD.IATAcode}" <%= choiceExist.get("point_of_departure").equals(airportPOD.getIATAcode())? "selected" : "" %>>${airportPOD.name}</option>
                        </c:when>
                        <c:otherwise>
                            <option value="${airportPOD.IATAcode}">${airportPOD.name}</option>
                        </c:otherwise>
                    </c:choose>
                </c:forEach>
            </select>
            <p>Returning:</p><input type="date" name="from" value='<%= choiceExist != null ? (String)choiceExist.get("from") : "NONE" %>' required>
            <p>Departing:</p><input type="date" name="to" value='<%= choiceExist != null ? (String)choiceExist.get("to") : "NONE" %>' required>
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

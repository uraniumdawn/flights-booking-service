<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="net.petrovsky.flights.util.TimeUtil" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
<head>
    <title>Flight Management</title>
    <link  href="/resources/css/style.css" rel="stylesheet">
</head>
<body>
<jsp:include page="parts/adminControlPanel.jsp"/>

            <form class="form" action="/admin/flights/select/by/destination" method="get">
    <div class="row">
        <div class="left">
            Destination:
        </div>
        <div class="right">
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
        </div>
        <div class="select">
            <input class="btn" type="submit" value="Select">
        </div>
    </div>
    </form>
    <form class="form" action="/admin/flights/select/by/pointod" method="get">
        <div class="row">
            <div class="left">
                Point of departure:
            </div>
            <div class="right">
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
            </div>
            <div class="select">
                <input class="btn" type="submit" value="Select">
            </div>
        </div>
    </form>
    <div>
        <a class="btn" href="/admin/flights/select/all">All flights</a>
        <a class="btn" href="/admin/flights/add">Add flight</a>
    </div>
    <c:if test="${not empty msgEmptyResult}"><div class="res_msg">${msgEmptyResult}</div></c:if>
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
                        <td><a class="btn" href="/admin/flights/edit?flight_id=${flight.id}">Edit</a> </td>
                    </tr>
                </c:forEach>
            </table>
        </c:if>
    </div>

<jsp:include page="parts/footer.jsp"/>
</body>
</html>

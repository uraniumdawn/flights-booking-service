<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<html>
<head>
    <title>Editing a Flight</title>
</head>
<body>
    <form action="/admin/flights/edit" method="post">
        <table>
            <tr>
                <td>Destination:</td>
                <td>
                    <select name="destination" required>
                        <option value="NONE">---Select---</option>
                        <c:forEach items="${airportList}" var="airportD">
                            <jsp:useBean id="airportD" scope="page" class="net.petrovsky.flights.model.Airport"/>
                            <c:choose>
                                <c:when test="${not empty currentFlight}">
                                    <option value="${airportD.IATAcode}" ${(currentFlight.destination.IATAcode).equals(airportD.IATAcode) ? "selected" : ""}>${airportD.name}</option>
                                </c:when>
                                <c:otherwise>
                                    <option value="${airportD.IATAcode}">${airportD.name}</option>
                                </c:otherwise>
                            </c:choose>
                        </c:forEach>
                    </select>
                </td>
            </tr>
            <tr>
                <td>Point of departure:</td>
                <td>
                    <select name="point_of_departure" required>
                        <option value="NONE">---Select---</option>
                        <c:forEach items="${airportList}" var="airportPOD">
                            <jsp:useBean id="airportPOD" scope="page" type="net.petrovsky.flights.model.Airport"/>
                            <c:choose>
                                <c:when test="${not empty currentFlight}">
                                    <option value="${airportPOD.IATAcode}" ${(currentFlight.pointOfDeparture.IATAcode).equals(airportPOD.IATAcode) ? "selected" : ""}>${airportPOD.name}</option>
                                </c:when>
                                <c:otherwise>
                                    <option value="${airportPOD.IATAcode}">${airportPOD.name}</option>
                                </c:otherwise>
                            </c:choose>
                        </c:forEach>
                    </select>
                </td>
            </tr>
            <tr>
                <td>Time:</td>
                <td><input type="datetime-local" name="time" value="${currentFlight.time}" required/></td>
            </tr>
            <tr>
                <td>Price:</td>
                <td><input type="number" step="0.01" name="price" value="${currentFlight.price}" required/></td>
            </tr>
            <tr>
                <td><input type="submit" name="save" value="Save"/></td>
                <td><input type="button" onclick="window.location='/admin/flights/management'" value="Cancel"/></td>
            </tr>
        </table>
    </form>
</body>
</html>

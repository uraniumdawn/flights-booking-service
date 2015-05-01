<%@ page import="net.petrovsky.flights.util.TimeUtil" %>
<%@ page import="java.util.Map" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<div>
    <hr/>
    <c:choose>
        <c:when test='<%= (session.getAttribute("user") != null)&& (session.getAttribute("preorder") != null) %>'>
            <table>
                <thead>
                <tr>
                    <th>Point of departure</th>
                    <th>Destination</th>
                    <th>Time</th>
                    <th>Price</th>
                    <th>PreOrder</th>
                </tr>
                </thead>
                <c:forEach items='<%= ((Map)session.getAttribute("preorder")).values() %>' var="flight">
                    <jsp:useBean id="flight" scope="page" type="net.petrovsky.flights.model.Flight"/>
                    <tr>
                        <td><c:out value="${flight.pointOfDeparture.name}"/></td>
                        <td><c:out value="${flight.destination.name}"/></td>
                        <td><%=TimeUtil.toString(flight.getTime())%></td>
                        <td><%=flight.getPrice()%></td>
                        <td>
                            <a href="/delfrompreorder?flight_id=${flight.id}">Delete</a>
                        </td>
                    </tr>
                </c:forEach>
            </table>
        </c:when>
        <c:otherwise>
            <p>You do not have any booking flights</p>
        </c:otherwise>
    </c:choose>
</div>

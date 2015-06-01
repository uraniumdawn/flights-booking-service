<%@ page import="net.petrovsky.flights.util.TimeUtil" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<div>
    <hr/>
    <div class="title">Preorder list of flights</div>
    <c:choose>
        <c:when test="${(not empty user) && (not empty preorder)}">
            <table>
                <thead>
                <tr>
                    <th>Point of departure</th>
                    <th>Destination</th>
                    <th>Time</th>
                    <th>Price</th>
                </tr>
                </thead>
                <c:forEach items="${preorder.values()}" var="flight">
                    <jsp:useBean id="flight" scope="page" type="net.petrovsky.flights.model.Flight"/>
                    <tr>
                        <td>${flight.pointOfDeparture.name}</td>
                        <td>${flight.destination.name}</td>
                        <td><%=TimeUtil.toString(flight.getTime())%></td>
                        <td>${flight.price}</td>
                        <td>
                            <a class="btn" href="/preorder/delete?flight_id=${flight.id}">Delete</a>
                            <a class="btn" href="/order?flight_id=${flight.id}">Book</a>
                            <c:if test="${ordersIndex.contains(flight.id)}">
                                <span>Ordered</span>
                            </c:if>
                            <c:if test="${not empty msgExistentOrder}">
                                <span>This order already exist</span>
                            </c:if>
                        </td>
                    </tr>
                </c:forEach>
            </table>
        </c:when>
        <c:otherwise>
            <div class="res_msg">You do not have any booking flights</div>
        </c:otherwise>
    </c:choose>
</div>

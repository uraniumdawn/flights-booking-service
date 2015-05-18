<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
<head>
    <title>Airport Management</title>
</head>
<body>

<jsp:include page="parts/adminControlPanel.jsp"/>
<form action="/admin/airports/select/by/iatacode" method="get">
    <div>IATA code:</div>
    <input type="text" name="IATA_code" required>
    <input type="submit" value="Select">
</form>
<form action="/admin/airports/select/by/name" method="get">
    <div>Name:</div>
    <input type="text" name="name" required>
    <input type="submit" value="Select">
</form>
<form action="/admin/airports/select/by/country" method="get">
    <div>Country:</div>
    <input type="text" name="country" required>
    <input type="submit" value="Select">
</form>
<a href="/admin/airports/select/all">All airports</a>
<a href="/admin/airports/add">Add a new airport</a>
<c:if test="${not empty msgEmptyResult}">
    <div>${msgEmptyResult}</div>
</c:if>
<c:if test="${not empty selectedAirports}">
    <table>
        <thead>
            <tr>
                <th>IATA code</th>
                <th>Name</th>
                <th>City</th>
                <th>Country</th>
            </tr>
        </thead>
        <c:forEach items="${selectedAirports}" var="airport">
            <jsp:useBean id="airport" scope="page" type="net.petrovsky.flights.model.Airport"/>
            <tr>
                <td>${airport.IATAcode}</td>
                <td>${airport.name}</td>
                <td>${airport.city}</td>
                <td>${airport.country}</td>
                <td><a href="/admin/airports/edit?IATAcode=${airport.IATAcode}">Edit</a> </td>
            </tr>
        </c:forEach>
    </table>
</c:if>
</body>
</html>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
<head>
    <title>Airport Management</title>
    <link  href="/resources/css/style.css" rel="stylesheet">
</head>
<body>
<jsp:include page="parts/adminControlPanel.jsp"/>

    <form class="form" action="/admin/airports/select/by/iatacode" method="get">
        <div class="row">
            <div class="left">
                IATA code:
            </div>
            <div class="right">
                <input type="text" name="IATA_code" required>
            </div>
            <div class="select">
                <input class="btn" type="submit" value="Select">
            </div>
        </div>
    </form>
    <form class="form" action="/admin/airports/select/by/name" method="get">
        <div class="row">
            <div class="left">
                Name:
            </div>
            <div class="right">
                <input type="text" name="name" required>
            </div>
            <div class="select">
                <input class="btn" type="submit" value="Select">
            </div>
        </div>
    </form>
    <form class="form" action="/admin/airports/select/by/country" method="get">
        <div class="row">
            <div class="left">
                Country:
            </div>
            <div class="right">
                <input type="text" name="country" required>
            </div>
            <div class="select">
                <input class="btn" type="submit" value="Select">
            </div>
        </div>
    </form>
    <div class="row">
        <div class="left">
            <a class="btn" href="/admin/airports/select/all">All airports</a>
            <a class="btn" href="/admin/airports/add">Add a new airport</a>
        </div>
    </div>
    <c:if test="${not empty msgEmptyResult}"><div class="res_msg">${msgEmptyResult}</div></c:if>
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
                    <td><a class="btn" href="/admin/airports/edit?IATAcode=${airport.IATAcode}">Edit</a> </td>
                </tr>
            </c:forEach>
        </table>
    </c:if>

<jsp:include page="parts/footer.jsp"/>
</body>
</html>

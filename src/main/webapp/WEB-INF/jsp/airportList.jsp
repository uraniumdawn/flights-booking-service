<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
<head>
    <title></title>
</head>
<body>
<table>
    <thead>
        <tr>
            <th>IATA code</th>
            <th>Name</th>
            <th>City</th>
            <th>Country</th>
        </tr>
    </thead>
        <c:forEach items="${airportList}" var="airport">
            <jsp:useBean id="airport" scope="page" type="net.petrovsky.flights.model.Airport"/>
            <tr>
                <td><c:out value="${airport.IATAcode}"/></td>
                <td><c:out value="${airport.name}"/></td>
                <td><c:out value="${airport.city}"/></td>
                <td><c:out value="${airport.country}"/></td>
            </tr>
        </c:forEach>
</table>
</body>
</html>

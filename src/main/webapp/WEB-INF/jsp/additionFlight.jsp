<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<html>
<head>
    <title>Addition of a new Airport</title>
</head>
<body>
    <form action="/admin/flights/addnew" method="post">
        <table>
            <tr>
                <td>Destination:</td>
                <td>
                    <select name="destination" required>
                        <option value="NONE">---Select---</option>
                        <c:forEach items="${airportList}" var="airportD">
                            <jsp:useBean id="airportD" scope="page" class="net.petrovsky.flights.model.Airport"/>
                            <option value="${airportD.IATAcode}">${airportD.name}</option>
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
                            <option value="${airportPOD.IATAcode}">${airportPOD.name}</option>
                        </c:forEach>
                    </select>
                </td>
            </tr>
            <tr>
                <td>Time:</td>
                <td><input type="datetime-local" name="time" required/></td>
            </tr>
            <tr>
                <td>Price:</td>
                <td><input type="number" step="0.01" name="price" required/></td>
            </tr>
            <tr>
                <td><input type="submit" name="add" value="Add"/></td>
                <td><input type="button" onclick="window.location='/admin/flights/management'" value="Cancel"/></td>
            </tr>
        </table>
    </form>
</body>
</html>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<html>
<head>
    <title>Addition of a new Airport</title>
    <link href="/resources/css/style.css" rel="stylesheet">
</head>
<body>
<jsp:include page="parts/adminControlPanel.jsp"/>

    <form class="form" action="/admin/flights/add" method="post">
        <div class="row">
            <div class="brick lbl">
                Destination:
            </div>
            <div class="brick">
                <select name="destination" required>
                    <option value="NONE">---Select---</option>
                    <c:forEach items="${airportList}" var="airportD">
                        <jsp:useBean id="airportD" scope="page" class="net.petrovsky.flights.model.Airport"/>
                        <option value="${airportD.IATAcode}">${airportD.name}</option>
                    </c:forEach>
                </select>
            </div>
        </div>
        <div class="row">
            <div class="brick lbl">
                Point of departure:
            </div>
            <div class="brick">
                <select name="point_of_departure" required>
                    <option value="NONE">---Select---</option>
                    <c:forEach items="${airportList}" var="airportPOD">
                        <jsp:useBean id="airportPOD" scope="page" type="net.petrovsky.flights.model.Airport"/>
                        <option value="${airportPOD.IATAcode}">${airportPOD.name}</option>
                    </c:forEach>
                </select>
            </div>
        </div>
        <div class="row">
            <div class="brick lbl">
                Time:
            </div>
            <div class="brick">
                <input type="datetime-local" name="time" required/>
            </div>
        </div>
        <div class="row">
            <div class="brick lbl">
                Price:
            </div>
            <div class="brick">
                <input type="number" step="0.01" name="price" required/>
            </div>
        </div>
        <div class="row">
            <input class="btn" type="submit" name="add" value="Add"/>
            <input class="btn" type="button" onclick="window.location='/admin/flights/management'" value="Cancel"/>
        </div>
    </form>

<jsp:include page="parts/footer.jsp"/>
</body>
</html>

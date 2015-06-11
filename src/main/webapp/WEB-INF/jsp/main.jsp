<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page import="net.petrovsky.flights.util.TimeUtil" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
    <title>Main</title>
    <link  href="/resources/css/style.css" rel="stylesheet">
</head>
<body>
<jsp:include page="parts/header.jsp"/>

<div>
    <form class="form" action="/flights/select" method="get">
        <div class="row">
            <div class="brick lbl">Destination:</div>
            <div class="brick">
                <select name="destination" required>
                    <option value="NONE">---Select---</option>
                    <c:forEach items="${airportList}" var="airportD">
                        <jsp:useBean id="airportD" scope="page" class="net.petrovsky.flights.model.Airport"/>
                        <c:choose>
                            <c:when test="${not empty choice}">
                                <option value="${airportD.IATAcode}" ${choice['destination'].equals(airportD.IATAcode) ? "selected" : ""}>${airportD.name}</option>
                            </c:when>
                            <c:otherwise>
                                <option value="${airportD.IATAcode}">${airportD.name}</option>
                            </c:otherwise>
                        </c:choose>
                    </c:forEach>
                </select>
            </div>
        </div>
        <div class="row">
            <div class="brick lbl">Point of departure:</div>
            <div class="brick">
                <select name="point_of_departure" required>
                    <option value="NONE">---Select---</option>
                    <c:forEach items="${airportList}" var="airportPOD">
                        <jsp:useBean id="airportPOD" scope="page" type="net.petrovsky.flights.model.Airport"/>
                        <c:choose>
                            <c:when test="${not empty choice}">
                                <option value="${airportPOD.IATAcode}" ${choice['point_of_departure'].equals(airportPOD.IATAcode) ? "selected" : ""}>${airportPOD.name}</option>
                            </c:when>
                            <c:otherwise>
                                <option value="${airportPOD.IATAcode}">${airportPOD.name}</option>
                            </c:otherwise>
                        </c:choose>
                    </c:forEach>
                </select>
            </div>
        </div>
        <div class="row">
            <div class="brick lbl">from:</div>
            <div class="brick"><input type="date" name="from" value="${choice['from']}" required/></div>
            <c:if test="${not empty msgIncorrectDateFrom}">
                <div class="brick elem wr_msg">${msgIncorrectDateFrom}</div>
            </c:if>
        </div>
        <div class="row">
            <div class="brick lbl">to:</div>
            <div class="brick"><input type="date" name="to" value="${choice['to']}" required/></div>
            <c:if test="${not empty msgIncorrectDateTo}">
                <div class="brick elem wr_msg">${msgIncorrectDateTo}</div>
            </c:if>
        </div>
        <input class="btn" type="submit" value="Select">
    </form>
    <c:choose>
        <c:when test="${not empty selectedFlights}">
            <table class="flights">
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
                        <td class="clmn_1">${flight.pointOfDeparture.name}</td>
                        <td class="clmn_2">${flight.destination.name}</td>
                        <td class="clmn_3"><%=TimeUtil.toString(flight.getTime())%></td>
                        <td class="clmn_4">${flight.price}</td>
                        <td class="clmn_5">
                            <c:choose>
                                <c:when test="${not empty user}">
                                    <a class="btn" href="/preorder/add?flight_id=${flight.id}">Add to preorder list</a>
                                </c:when>
                                <c:otherwise>
                                    <div>Booking is only for registered users</div>
                                </c:otherwise>
                            </c:choose>
                        </td>
                    </tr>
                </c:forEach>
            </table>
        </c:when>
        <c:otherwise>
            <div class="res_msg">You did not chose any flights</div>
        </c:otherwise>
    </c:choose>
</div>
<jsp:include page="parts/preorderList.jsp"/>

<div class="note">
    <br/>
    <a href="/admin">Admin link</a>
    Admin user: Laura Palmer email: WhoKillLauraPalmer@lynch.com, pass: FireWalkWithMe<br/>
    list of pre-defined data <a href="/all">here</a>
    Preferably to use Google Chrome browser.
</div>



<jsp:include page="parts/footer.jsp"/>

<script>
    (function(i,s,o,g,r,a,m){i['GoogleAnalyticsObject']=r;i[r]=i[r]||function(){
        (i[r].q=i[r].q||[]).push(arguments)},i[r].l=1*new Date();a=s.createElement(o),
            m=s.getElementsByTagName(o)[0];a.async=1;a.src=g;m.parentNode.insertBefore(a,m)
    })(window,document,'script','//www.google-analytics.com/analytics.js','ga');

    ga('create', 'UA-63711550-1', 'auto');
    ga('send', 'pageview');
</script>

</body>
</html>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<html>
<head>
    <title>Addition of a new Airport</title>
    <link href="/resources/css/style.css" rel="stylesheet">
</head>
<body>
<jsp:include page="parts/adminControlPanel.jsp"/>

    <form class="form" action="/admin/airports/add" method="post">
        <div class="row">
            <div class="brick lbl">
                IATA code:
            </div>
            <div class="brick">
                <input type="text" name="IATA_code" required/>
            </div>
            <c:if test="${not empty msgIATALength}">
                <div class="brick elem wr_msg">${msgIATALength}</div>
            </c:if>
        </div>
        <div class="row">
            <div class="brick lbl">
                Name:
            </div>
            <div class="brick">
                <input type="text" name="name" required/>
            </div>
        </div>
        <div class="row">
            <div class="brick">
                City:
            </div>
            <div class="brick">
                <input type="text" name="city" required/>
            </div>
        </div>
        <div class="row">
            <div class="brick">
                Country:
            </div>
            <div class="brick">
                <input type="text" name="country" required/>
            </div>
        </div>
        <div class="row">
            <input class="btn" type="submit" name="add" value="Add"/>
            <input class="btn" type="button" onclick="window.location='/admin/airports/management'" value="Cancel"/>
        </div>
    </form>

<jsp:include page="parts/footer.jsp"/>
</body>
</html>

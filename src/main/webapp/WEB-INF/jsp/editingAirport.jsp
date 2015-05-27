<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<html>
<head>
    <title>Editing an Airport</title>
</head>
<body>
<jsp:include page="parts/adminControlPanel.jsp"/>

    <form class="form" action="/admin/airports/edit" method="post">
        <div class="row">
            <div class="left">
                IATA code:
            </div>
            <div class="right">
                <input type="text" name="IATA_code" value="${airport.IATAcode}" required/>
                <c:if test="${not empty msgIATALength}">
                    <div class="wr_msg">${msgIATALength}</div>
                </c:if>
            </div>
        </div>
        <div class="row">
            <div class="left">
                Name:
            </div>
            <div class="right">
                <input type="text" name="name" value="${airport.name}" required/>
            </div>
        </div>
        <div class="row">
            <div class="left">
                City:
            </div>
            <div class="right">
                <input type="text" name="city" value="${airport.city}" required/>
            </div>
        </div>
        <div class="row">
            <div class="left">
                Country:
            </div>
            <div class="right">
                <input type="text" name="country" value="${airport.country}" required/>
            </div>
        </div>
        <div class="row">
            <input class="btn" type="submit" name="save" value="Save"/>
            <input class="btn" type="button" onclick="window.location='/admin/airports/management'" value="Cancel"/>
        </div>
    </form>

<jsp:include page="parts/footer.jsp"/>
</body>
</html>

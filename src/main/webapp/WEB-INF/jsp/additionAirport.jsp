<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<html>
<head>
    <title>Addition of a new Airport</title>
</head>
<body>
    <form action="/admin/airports/addnew" method="post">
        <table>
            <tr>
                <td>IATA code:</td>
                <td><input type="text" name="IATA_code" value="${enteredValueIATA}" required/></td>
                <c:if test="${not empty msgIATALength}"><td>${msgIATALength}</td></c:if>
            </tr>
            <tr>
                <td>Name:</td>
                <td><input type="text" name="name" value="${enteredValueName}" required/></td>
            </tr>
            <tr>
                <td>City:</td>
                <td><input type="text" name="city" value="${enteredValueCity}" required/></td>
            </tr>
            <tr>
                <td>Country:</td>
                <td><input type="text" name="country" value="${enteredValueCountry}" required/></td>
            </tr>
            <tr>
                <td><input type="submit" name="add" value="Add"/></td>
                <td><input type="button" onclick="window.location='/admin/airports/management'" value="Cancel"/></td>
            </tr>
        </table>
    </form>
</body>
</html>

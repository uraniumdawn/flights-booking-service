<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<html>
<head>
    <title></title>
</head>
<body>
    <c:if test="${not empty requestScope.accessDenied}"><div>${requestScope.accessDenied}</div></c:if>
    <c:if test="${not empty error}"><div>${error}</div></c:if>
    <form action="/login" method="post">
        <table>
            <tr>
                <td>Email:</td>
                <td><input type="email" name="email"/></td>
            </tr>
            <tr>
                <td>Password:</td>
                <td><input type="password" name="password"/></td>
            </tr>
            <tr>
                <td><input type="submit" name="login" value="Login"/></td>
                <td><input type="button" onclick="window.location='/main'" value="Cancel"/></td>
            </tr>
        </table>
    </form>
</body>
</html>

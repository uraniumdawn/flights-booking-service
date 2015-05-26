<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<html>
<head>
    <title>Log In</title>
    <link href="/resources/css/style.css" rel="stylesheet">
</head>
<body>
    <div id="login">
        <c:if test="${not empty requestScope.accessDenied}"><div>${requestScope.accessDenied}</div></c:if>
        <c:if test="${not empty msgIncorrectCredentials}"><div>${msgIncorrectCredentials}</div></c:if>
        <form class="form" action="/login" method="post">
            <div class="row">
                <div class="left">
                    Email:
                </div>
                <div class="right">
                    <input type="email" name="email"/>
                </div>
            </div>
            <div class="row">
                <div class="left">
                    Password:
                </div>
                <div class="right">
                    <input type="password" name="password"/>
                </div>
            </div>
            <div class="row">
                <input class="btn" type="submit" name="login" value="Login"/>
                <input class="btn" type="button" onclick="window.location='/'" value="Cancel"/>
            </div>
        </form>
    </div>
</body>
</html>

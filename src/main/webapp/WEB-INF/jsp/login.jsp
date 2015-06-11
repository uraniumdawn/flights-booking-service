<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<html>
<head>
    <title>Log In</title>
    <link href="/resources/css/style.css" rel="stylesheet">
</head>
<body>
    <div id="login">

        <form class="form" action="/login" method="post">
            <div class="row">
                <div class="brick lbl">
                    Email:
                </div>
                <div class="brick">
                    <input type="email" name="email"/>
                </div>
            </div>
            <div class="row">
                <div class="brick lbl">
                    Password:
                </div>
                <div class="brick">
                    <input type="password" name="password"/>
                </div>
            </div>
            <div class="row">
                <input class="btn" type="submit" name="login" value="Login"/>
                <input class="btn" type="button" onclick="window.location='/'" value="Cancel"/>
            </div>
            <div class="row">
                <c:if test="${not empty requestScope.accessDenied}"><div class="wr_msg">${requestScope.accessDenied}</div></c:if>
                <c:if test="${not empty msgIncorrectCredentials}"><div class="wr_msg">${msgIncorrectCredentials}</div></c:if>
            </div>
        </form>
    </div>

<jsp:include page="parts/footer.jsp"/>
</body>
</html>

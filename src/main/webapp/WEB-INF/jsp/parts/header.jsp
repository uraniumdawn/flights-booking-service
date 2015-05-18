<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<div>
    <c:choose>
        <c:when test="${not empty user}">
            <c:if test="${!user.enabled}">
                <div>You are blocked</div>
            </c:if>
            <div>
                Profile: ${user.firstName} ${user.secondName}
            </div>
            <a href="/profile/edit">Edit profile</a>
            <a href="/logout">Log out</a>
            <a href="/orders">Ordered flights</a>
        </c:when>
        <c:otherwise>
            <input type="button" onclick="window.location='/login'" value="Log In"/>
            <input type="button" onclick="window.location='/signup'" value="Sign Up"/>
        </c:otherwise>
    </c:choose>
    <hr/>
</div>

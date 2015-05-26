<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<div class="header">
    <c:choose>
        <c:when test="${not empty user}">
            <c:if test="${!user.enabled}">
                <div id="block_msg">You are blocked</div>
            </c:if>
            <div class="profile-text">
                Profile: ${user.firstName} ${user.secondName}
            </div>
            <a class="btn" href="/profile/edit">Edit profile</a>
            <a class="btn" href="/logout">Log out</a>
            <a class="btn" href="/orders">Ordered flights</a>
        </c:when>
        <c:otherwise>
            <input class="btn" type="button" onclick="window.location='/login'" value="Log In"/>
            <input class="btn" type="button" onclick="window.location='/signup'" value="Sign Up"/>
        </c:otherwise>
    </c:choose>
    <hr/>
</div>

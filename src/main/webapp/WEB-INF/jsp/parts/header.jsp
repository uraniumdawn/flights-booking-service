<%@ page import="net.petrovsky.flights.model.User" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<% User user = (User)session.getAttribute("user"); %>

<div>
    <c:choose>
        <c:when test="<%= user != null %>">
            <p>Profile:</p>
            <%=user.getFirstName()%>
            <%=user.getSecondName()%>
            <a href="/editprofile">Edit profile</a>
            <a href="/logout">Log out</a>
            <a href="/userbookinglist">Ordered flights</a>
        </c:when>
        <c:otherwise>
            <input type="button" onclick="window.location='/login'" value="Log In"/>
            <input type="button" onclick="window.location='/signup'" value="Sign Up"/>
        </c:otherwise>
    </c:choose>
        <hr/>
</div>

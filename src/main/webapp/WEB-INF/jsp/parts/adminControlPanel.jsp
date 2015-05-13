<%@ page import="net.petrovsky.flights.model.User" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<% User user = (User)session.getAttribute("user"); %>
<div>
    <div>
        Administrator:
    </div>
    <div>
        <%= user.getFirstName() %>
        <%= user.getSecondName() %>
    </div>
    <div>
        <a href="/admin/users/management">Users management</a>
        <a href="/admin/airports/management">Airport management</a>
        <a href="">Flight management</a>
        <a href="">Booking management</a>
    </div>
    <hr/>
</div>


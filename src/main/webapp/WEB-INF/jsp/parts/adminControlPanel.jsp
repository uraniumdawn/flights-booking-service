<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<div>
    <div class="header">
        <div class="profile-text">
            Administrator: ${user.firstName} ${user.secondName}
        </div>
        <div>
            <a class="btn" href="/admin/users/management">Users management</a>
            <a class="btn" href="/admin/airports/management">Airport management</a>
            <a class="btn" href="/admin/flights/management">Flight management</a>
        </div>
        <hr/>
    </div>
</div>


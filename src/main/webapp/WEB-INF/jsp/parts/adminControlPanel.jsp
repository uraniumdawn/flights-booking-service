<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<div>
    <div>
        Administrator:
        ${user.firstName} ${user.secondName}
    </div>
    <div>
        <a href="/admin/users/management">Users management</a>
        <a href="/admin/airports/management">Airport management</a>
        <a href="/admin/flights/management">Flight management</a>
    </div>
    <hr/>
</div>


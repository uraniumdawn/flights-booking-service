<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<html>
<head>
    <title>Edit profile</title>
</head>
<body>
    <form action="/editprofile" method="post">
        <table>
            <tr>
                <td>First Name:</td>
                <td><input type="text" name="first_name" value="${user.firstName}"/></td>
            </tr>
            <tr>
                <td>Second Name:</td>
                <td><input type="text" name="second_name" value="${user.secondName}"/></td>
            </tr>
            <tr>
                <td>Email:</td>
                <td><input type="email" name="email" value="${user.email}"/></td>
            </tr>
            <tr>
                <td>Password:</td>
                <td><input type="password" name="password" value="${user.password}"/></td>
            </tr>
            <tr>
                <td><input type="submit" name="save" value="Save"/></td>
                <td><input type="button" onclick="window.location='/main'" value="Cancel"/></td>
            </tr>
        </table>
    </form>
</body>
</html>

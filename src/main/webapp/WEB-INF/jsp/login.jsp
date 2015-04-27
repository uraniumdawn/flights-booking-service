<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<html>
<head>
    <title></title>
</head>
<body>
    <form action="/login" method="post">
        <table>
            <tr>
                <td>Email:</td>
                <td><input type="email" name="email"></td>
            </tr>
            <tr>
                <td>Password:</td>
                <td><input type="password" name="password"></td>
            </tr>
            <tr>
                <td><input type="submit" name="Login" value="Login"></td>
            </tr>
        </table>
    </form>
</body>
</html>

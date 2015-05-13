<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<html>
<head>
    <title></title>
</head>
<body>
    <form action="/signup" method="post">
        <table>
            <tr>
                <td>First Name:</td>
                <td><input type="text" name="first_name" required/></td>
            </tr>
            <tr>
                <td>Second Name:</td>
                <td><input type="text" name="second_name" required/></td>
            </tr>
            <tr>
                <td>Email:</td>
                <td><input type="email" name="email" required/></td>
            </tr>
            <tr>
                <td>Password:</td>
                <td><input type="password" name="password" required/></td>
            </tr>
            <tr>
                <td><input type="submit" name="signup" value="Sign Up"/></td>
                <td><input type="button" onclick="window.location='/main'" value="Cancel"/></td>
            </tr>
        </table>
    </form>
</body>
</html>

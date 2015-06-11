<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<html>
<head>
    <title>Edit profile</title>
    <link href="/resources/css/style.css" rel="stylesheet">
</head>
<body>
<jsp:include page="parts/header.jsp"/>

    <form class="form" action="/profile/edit" method="post">
        <div class="row">
            <div class="brick lbl">
                First name:
            </div>
            <div class="brick">
                <input type="text" name="first_name" value="${user.firstName}" required/>
            </div>
        </div>
        <div class="row">
            <div class="brick lbl">
                Second name:
            </div>
            <div class="brick">
                <input type="text" name="second_name" value="${user.secondName}" required/>
            </div>
        </div>
        <div class="row">
            <div class="brick lbl">
                Email:
            </div>
            <div class="brick">
                <input type="email" name="email" value="${user.email}" required/>
            </div>
        </div>
        <div class="row">
            <div class="brick lbl">
                Password:
            </div>
            <div class="brick">
                <input type="password" name="password" required/>
            </div>
        </div>
        <div>
            <input class="btn" type="submit" name="save" value="Save"/>
            <input class="btn" type="button" onclick="window.location='/'" value="Cancel"/>
        </div>
    </form>

<jsp:include page="parts/footer.jsp"/>
</body>
</html>

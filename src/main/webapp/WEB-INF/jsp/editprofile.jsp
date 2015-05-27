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
            <div class="left">
                First name:
            </div>
            <div class="right">
                <input type="text" name="first_name" required/>
            </div>
        </div>
        <div class="row">
            <div class="left">
                Second name:
            </div>
            <div class="right">
                <input type="text" name="second_name" required/>
            </div>
        </div>
        <div class="row">
            <div class="left">
                Email:
            </div>
            <div class="right">
                <input type="email" name="email" required/>
            </div>
        </div>
        <div class="row">
            <div class="left">
                Password:
            </div>
            <div class="right">
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

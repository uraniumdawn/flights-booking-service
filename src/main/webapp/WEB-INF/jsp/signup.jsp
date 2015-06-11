<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<html>
<head>
    <title>Sign Up</title>
    <link href="/resources/css/style.css" rel="stylesheet">
</head>
<body>
    <div id="signup">
        <form class="form" action="/signup" method="post">
            <div class="row">
                <div class="brick lbl">
                    First name:
                </div>
                <div class="brick">
                    <input type="text" name="first_name" required/>
                </div>
            </div>
            <div class="row">
                <div class="brick lbl">
                    Second name:
                </div>
                <div class="brick">
                    <input type="text" name="second_name" required/>
                </div>
            </div>
            <div class="row">
                <div class="brick lbl">
                    Email:
                </div>
                <div class="brick">
                    <input type="email" name="email" required/>
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
            <div class="row">
                <input class="btn" type="submit" name="signup" value="Sign Up"/>
                <input class="btn" type="button" onclick="window.location='/'" value="Cancel"/>
            </div>
        </form>
    </div>

<jsp:include page="parts/footer.jsp"/>
</body>
</html>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
    <title>contact-us</title>
</head>

<div class="topnav">
    <a class="active" href="/home">Home</a>
    <a href="/logout">Logout</a>
    <a href="/register">Register</a>
    <a href="/feedback">Feedback</a>
    <a href="/contactUs">Contact Us</a>
    <br>
</div>

<body>
<%-- div is for grouping items --%>
<div>
    <form method="post" action="/contactUs">
        <div>
            <label>Subject</label>
            <input type="text" name="subject">
        </div>
        <div>
            <textarea id="msgBox" name="msg" rows="4" cols="50"></textarea>
            <br>
        </div>
        <button type="submit">Send</button>
    </form>
</div>
</body>

</html>

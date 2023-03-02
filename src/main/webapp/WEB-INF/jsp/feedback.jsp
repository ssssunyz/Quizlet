<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
    <title>feedback</title>
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
    <form method="post" action="/feedback">
        <div>
            <br>
            <label for="rating">Rate your experience with us:</label>
            <select name="rating" id="rating">
                <option value="1">1</option>
                <option value="2">2</option>
                <option value="3">3</option>
                <option value="4">4</option>
                <option value="5" selected>5</option>
            </select>
        </div>

        <div>
            <textarea id="msgBox" name="text" rows="4" cols="50"></textarea>
            <br>
        </div>

        <button type="submit">Send</button>
    </form>
</div>
</body>

</html>

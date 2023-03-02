<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
    <title>Register</title>
</head>

<div class="topnav">
    <a class="active" href="/home">Home</a>
    <a href="/login">Logout</a>
    <a href="/register">Register</a>  <%-- 在home里点这个没啥用 因为会 redirect to /home if already logged in --%>
    <a href="/feedback">Feedback</a>
    <a href="/contactUs">Contact Us</a>
    <br>
</div>

<body>
<%-- div is for grouping items --%>
<div>
    <form method="post" action="/register">
        <div>
            <label>First Name</label>
            <input type="text" name="firstname">
        </div>
        <div>
            <label>Last Name</label>
            <input type="text" name="lastname">
        </div>
        <div>
            <label>Email</label>
            <input type="text" name="email">
        </div>
        <div>
            <label>Password</label>
            <input type="password" name="password">
        </div>
        <div>
            <label>Phone</label>
            <input type="text" name="phone">
        </div>
        <button type="submit">Register</button>
    </form>
</div>
</body>

</html>
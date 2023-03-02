<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
    <title>admin-home</title>
</head>

<div class="topnav">
    <a class="active" href="/admin-home">Home</a>
    <a href="/logout">Logout</a>
</div>
<br>

<body>
<%-- div is for grouping items --%>
<div>
    Select An Action:<br>
    <a href="/admin-home/1">View All Users</a><br>
    <a href="/admin-home/2">View All Quiz Results</a><br>
    <a href="/admin-home/3">View All Feedback</a><br>
<%--    <a href="/admin-home/4">View All Contact Messages</a><br>--%>
    <a href="/admin-home/5">View All Questions</a><br>
</div>
</body>

</html>

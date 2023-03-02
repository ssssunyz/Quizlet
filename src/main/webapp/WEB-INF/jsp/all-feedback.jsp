<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>all-feedback</title>
</head>

<div class="topnav">
    <a class="active" href="/admin-home">Home</a>
    <a href="/logout">Logout</a>
    <br>
</div>

<body>
<div>
    <table>
        <tr>
            <th>Feedback</th>
            <th>Rating</th>
            <th>Date</th>
        </tr>
        <c:forEach var="feedback" items="${all_feedbacks}">
            <tr>
                <td>${feedback.text}</td>
                <td>${feedback.rating}</td>
                <td>${feedback.date}</td>
            </tr>
        </c:forEach>
    </table>

</div>
</body>
</html>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Homepage</title>
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
<div>

    <%-- if else statement in JSP: choose, when, otherwise --%>
    <c:choose>
        <c:when test="${empty quizzes_taken}">
            <p>You have not taken any quiz.</p>
        </c:when>
        <c:otherwise>
            <p>Quizzes you have taken:</p>
            <table>
                <tr>
                    <th>Quiz Name</th>
                    <th>Date Taken</th>
                    <th>Quiz Result</th>
                </tr>
                <c:forEach var="quiz" items="${quizzes_taken}">
                    <tr>
                        <td>${quiz.name}</td>
                        <td>${quiz.name.substring(7)}</td>
                        <td>
                            <a href="/all-quiz-result/${quiz.quiz_id}">Details</a>
                        </td>
                    </tr>
                </c:forEach>
            </table>
        </c:otherwise>
    </c:choose>

</div>
    <p>Choose a category to take quiz from:</p>
    <form method="post" action="/home">
        <c:forEach var="c" items="${categories}">
            <%-- radio: 单选 --%>
            <input type="radio" name="selectedCategoryId" value="${c.category_id}">${c.name}<br>
<%--            <img src="${c.image_url}" width="300" height="200">--%>
        </c:forEach>
        <button type="submit">Submit</button>
    </form>
<div>

</div>
</body>
</html>

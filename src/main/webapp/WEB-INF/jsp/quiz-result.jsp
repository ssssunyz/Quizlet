<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Quiz Result</title>
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
    User Name: ${sessionScope.user.firstname} ${sessionScope.user.lastname}<br>
    Quiz Name: ${quiz_name}<br>
    Start Time: ${start_time}<br>
    End Time: ${end_time}<br>
    <br>
</div>

<div>
    <c:choose>
        <c:when test="${correct_count > 3}">
            Congrats! You passed.<br>
        </c:when>
        <c:otherwise>
            Oops! You failed. You have to get at least 4 questions correct.<br>
        </c:otherwise>
    </c:choose>
    Number of correct answers: ${correct_count}
</div>
<br>
<div>
    <c:forEach var="i" begin="0" end="4" step="1">
        For Question: ${question_descriptions.get(i)} <br>
        <c:forEach var="option" items="${options_to_question.get(i)}">
            * ${option}<br>
        </c:forEach>
        You selected "${selected_choice_descriptions.get(i)}",<br>
        which is ${results.get(i)}. <br>

        <c:if test="${results.get(i) == 'WRONG'}">
            Correct answer: "${correct_answers.get(i)}". <br>
        </c:if>
        <br>
    </c:forEach>

</div>
<a href="/home">Take Another Quiz</a>
</body>
</html>

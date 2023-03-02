<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Quiz</title>
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
    <br>
    <form method="post" action="/quiz">
        <%-- qcMap是在QuizController里被model add attribute时加进来了 --%>
        <%-- qcMap: key: Question, value: List<Choice> --%>
        <div>
            <c:forEach var="entry" items="${qcMap}">
                <%-- Question description --%>
                <p>${entry.key.description}</p>

                <%-- Dynamically render the choices --%>
                <c:forEach var="choice" items="${entry.value}" varStatus="loop">
                    <%-- radio: 单选 --%>
                    <input type="radio"
                           name="question+${entry.key.question_id}"
                           value="${choice.choice_id}">${choice.choice_description}<br>
                </c:forEach>
            </c:forEach>
        </div>

        <%-- Button to submit quiz --%>
        <button type="submit">submit</button>
    </form>
</div>
</body>
</html>

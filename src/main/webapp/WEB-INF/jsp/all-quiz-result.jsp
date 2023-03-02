<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>all-quiz-result</title>
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
            <th>Date</th>
            <th>Category</th>
            <th>Name</th>
            <th>#Question</th>
            <th>Score</th>
            <th>Details</th>
        </tr>
        <c:forEach var="i" begin="0" end="${all_quiz.size()-1}" step="1">
            <c:set var="quiz" value="${all_quiz.get(i)}"></c:set>
            <tr>
                <td>${quiz.name.substring(7)}</td>
                <td>
                    <c:choose>
                        <c:when test="${quiz.category_id == 1}">
                            Java Core
                        </c:when>
                        <c:when test="${quiz.category_id == 2}">
                            Tennis
                        </c:when>
                        <c:otherwise>
                            Fun Facts
                        </c:otherwise>
                    </c:choose>
                </td>
                <td>${corresponding_users.get(i).firstname} ${corresponding_users.get(i).lastname}</td>
                <td>5</td>
                <td>${quiz.score}</td>
                <td>
                    <a href="/all-quiz-result/${quiz.quiz_id}">Details</a>
                </td>
            </tr>
        </c:forEach>
    </table>

</div>
</body>
</html>

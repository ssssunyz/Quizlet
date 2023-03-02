<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Quiz Detail</title>
</head>

<div class="topnav">
    <a class="active" href="/admin-home">Home</a>
    <a href="/logout">Logout</a>
    <br>
</div>

<body>
<div>
    User Name: ${firstname} ${lastname}<br>
    Start Time: ${start_time}<br>
    End Time: ${end_time}<br>
    Score: ${score}<br>
    Detail: ${detail}
</div>
<br>

<%--<div>--%>
<%--    <c:forEach var="i" begin="0" end="4" step="1">--%>
<%--        For "${question_descriptions.get(i)}",<br>--%>
<%--        You selected "${selected_choice_descriptions.get(i)}",<br>--%>
<%--        which is ${results.get(i)}. <br>--%>

<%--        <c:if test="${results.get(i) == 'WRONG'}">--%>
<%--            Correct answer: "${correct_answers.get(i)}". <br>--%>
<%--        </c:if>--%>
<%--        <br>--%>
<%--    </c:forEach>--%>

<%--</div>--%>

</body>
</html>

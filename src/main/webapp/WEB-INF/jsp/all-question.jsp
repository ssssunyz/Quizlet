<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>all-question</title>
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
            <th>Category</th>
            <th>QID</th>
            <th>Question</th>
            <th>Status</th>
            <th>Toggle</th>
            <th>Update</th>
        </tr>

        <c:forEach var="jc_question" items="${java_core}">
            <tr>
                <td>Java</td>
                <td>${jc_question.question_id}</td>
                <td>${jc_question.description}</td>
                <td>
                    <c:choose>
                        <c:when test="${jc_question.is_active()}">
                            Active
                        </c:when>
                        <c:otherwise>
                            Suspended
                        </c:otherwise>
                    </c:choose>
                </td>
                <td>
                    <form method="post" action="/toggle-question">
                        <button type="submit" name="toggleButton" value="${jc_question.question_id}">
                            <c:choose>
                                <c:when test="${jc_question.is_active()}">
                                    Suspend
                                </c:when>
                                <c:otherwise>
                                    Activate
                                </c:otherwise>
                            </c:choose>
                        </button>
                    </form>
                </td>
                <td>
                    <form method="post" action="/update-question">
                        <button type="submit" name="updateButton" value="${jc_question.question_id}">
                            Update
                        </button>
                    </form>
                </td>
            </tr>
        </c:forEach>

        <c:forEach var="t_question" items="${tennis}">
            <tr>
                <td>Tennis</td>
                <td>${t_question.question_id}</td>
                <td>${t_question.description}</td>
                <td>
                    <c:choose>
                        <c:when test="${t_question.is_active()}">
                            Active
                        </c:when>
                        <c:otherwise>
                            Suspended
                        </c:otherwise>
                    </c:choose>
                </td>
                <td>
                    <form method="post" action="/toggle-question">
                        <button type="submit" name="toggleButton" value="${t_question.question_id}">
                            <c:choose>
                                <c:when test="${t_question.is_active()}">
                                    Suspend
                                </c:when>
                                <c:otherwise>
                                    Activate
                                </c:otherwise>
                            </c:choose>
                        </button>
                    </form>
                </td>
                <td>
                    <form method="post" action="/update-question">
                        <button type="submit" name="updateButton" value="${t_question.question_id}">
                            Update
                        </button>
                    </form>
                </td>
            </tr>
        </c:forEach>

        <c:forEach var="ff_question" items="${fun_facts}">
            <tr>
                <td>Facts</td>
                <td>${ff_question.question_id}</td>
                <td>${ff_question.description}</td>
                <td>
                    <c:choose>
                        <c:when test="${ff_question.is_active()}">
                            Active
                        </c:when>
                        <c:otherwise>
                            Suspended
                        </c:otherwise>
                    </c:choose>
                </td>
                <td>
                    <form method="post" action="/toggle-question">
                        <button type="submit" name="toggleButton" value="${ff_question.question_id}">
                            <c:choose>
                                <c:when test="${ff_question.is_active()}">
                                    Suspend
                                </c:when>
                                <c:otherwise>
                                    Activate
                                </c:otherwise>
                            </c:choose>
                        </button>
                    </form>
                </td>
                <td>
                    <form method="post" action="/update-question">
                        <button type="submit" name="updateButton" value="${ff_question.question_id}">
                            Update
                        </button>
                    </form>
                </td>
            </tr>
        </c:forEach>
    </table>

</div>
<br>

<div>
    <form method="post" action="/add-question">
        <div>
            Add A Question:<br>
            <label for="categoryId">Category: </label>
            <select name="categoryId" id="categoryId">
                <option value="1" selected>Java Core</option>
                <option value="2">Tennis</option>
                <option value="3">Fun Facts</option>
            </select>
        </div>

        <div>
            <label>Question Description: </label>
            <input type="text" name="description">
        </div>

        <button type="submit">Add</button>
    </form>
</div>

</body>
</html>

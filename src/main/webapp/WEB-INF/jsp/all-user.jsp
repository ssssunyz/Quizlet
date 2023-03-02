<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>all-user</title>
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
            <th>FirstName</th>
            <th>LastName</th>
            <th>Email</th>
            <th>Phone</th>
            <th>Status</th>
            <th>Toggle</th>
        </tr>
        <c:forEach var="user" items="${all_users}">
            <tr>
                <td>${user.firstname}</td>
                <td>${user.lastname}</td>
                <td>${user.email}</td>
                <td>${user.phone}</td>
                <%-- <td>${user.is_active()}</td> --%>
                <%-- 为啥boolean就要加括号啊 莫名其妙 --%>
                <td>
                    <c:choose>
                        <c:when test="${user.is_active()}">
                            Active
                        </c:when>
                        <c:otherwise>
                            Suspended
                        </c:otherwise>
                    </c:choose>
                </td>
                <td>
                    <form method="post" action="/toggle-user">
                        <button type="submit" name="toggleButton" value="${user.user_id}">
                            <c:choose>
                                <c:when test="${user.is_active()}">
                                    Suspend
                                </c:when>
                                <c:otherwise>
                                    Activate
                                </c:otherwise>
                            </c:choose>
                        </button>
                    </form>
                </td>
            </tr>
        </c:forEach>
    </table>

</div>
</body>
</html>

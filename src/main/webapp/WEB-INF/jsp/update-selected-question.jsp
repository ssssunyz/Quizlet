<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
    <title>update question</title>
</head>

<div class="topnav">
    <a class="active" href="/admin-home">Home</a>
    <a href="/login">Logout</a>
    <br>
</div>

<body>
<%-- div is for grouping items --%>
<div>

    <form method="post" action="/edited-question">
        <div contenteditable="true">
            <label>Question ID</label>
            <input contenteditable="true" type="text" name="question_id" placeholder="${question_id}">
        </div>
        <div>
            <label>Category ID</label>
            <input contenteditable="true" type="text" name="category_id" placeholder="${category_id}">
        </div>
        <div>
            <label>Description</label><br>
            <textarea contenteditable="true" id="msgBox" name="description" rows="4" cols="50" placeholder="${description}"></textarea>
            <br>
        </div>
        <button type="submit">Update</button>
    </form>
</div>
</body>

</html>
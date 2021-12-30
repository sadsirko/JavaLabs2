<%@ include file="/WEB-INF/jspf/page.jspf" %>
<html>
<c:set var="title" value="Register"/>
<%@ include file="/WEB-INF/jspf/head.jspf" %>
<body>
<div class="container">
    <%@ include file="/WEB-INF/jspf/header.jspf" %>

    <form action="/jsp/register" method="post" name="registration">
        <div class="form-group">
            <label for="email">Email:</label>
            <input type="email" class="form-control" placeholder="Enter email" id="email" name="email">
        </div>
        <div class="form-group">
            <label for="password">Password:</label>
            <input type="password" class="form-control" placeholder="Enter password" id="password" name="password" required minlength="8">
        </div>
        <button type="submit" class="btn btn-primary">Register</button>
    </form>

    <%@ include file="/WEB-INF/jspf/footer.jspf" %>
</div>
<script src="/js/form-validation.js"></script>
</body>
</html>

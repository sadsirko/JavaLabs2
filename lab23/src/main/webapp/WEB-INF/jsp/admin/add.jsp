<%@ include file="/WEB-INF/jspf/page.jspf" %>
<html>
<c:set var="title" value="Edit room"/>
<%@ include file="/WEB-INF/jspf/head.jspf" %>
<body>
<div class="container">
    <%@ include file="/WEB-INF/jspf/header.jspf" %>
    <input type="hidden" name="printCenter_id" value="${printCenter.id}">
    <form action="/jsp/admin/editPrintCenter" method="post" name="edit" >

        <div class="form-group">
            <input class="form-control" placeholder="Title" id = "name" name="name">
        </div>
        <div class="form-group">
            <label for="price">Price:</label>
            <input type="number" class="form-control" id="price" name="price"
                   placeholder="Enter price" min="0" max="1000000"
                   autocomplete="off">

        </div>

        <div class="form-group">
            <label for="themes">Select status:</label>
            <select class="custom-select" id="themes" name="themes">
                <c:forEach var="theme" items="${themeList}">
                            <option value="${theme.id}">${theme.name}</option>
                </c:forEach>
            </select>
        </div>
        <button type="submit" class="btn btn-primary">Edit printCenter</button>
        <input type="hidden" name="theme_id" value="${theme.id}">
        <input type="hidden" name="theme_id" value="${printCenter}">
        <input type="hidden" name="printCenter_id" value="${printCenter.id}">

    </form>


    <%@ include file="/WEB-INF/jspf/footer.jspf" %>
</div>


<script src="/js/form-validation.js"></script>
<script src="/js/bootstrap.min.js"></script>
</body>
<!-- this should go after your </body> -->
<link rel="stylesheet" type="text/css" />

</html>
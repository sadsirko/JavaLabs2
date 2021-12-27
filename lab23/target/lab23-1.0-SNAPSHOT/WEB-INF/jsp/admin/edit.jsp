<%@ include file="/WEB-INF/jspf/page.jspf" %>
<html>
<c:set var="title" value="Edit room"/>
<%@ include file="/WEB-INF/jspf/head.jspf" %>
<body>
<div class="container">
    <%@ include file="/WEB-INF/jspf/header.jspf" %>

    <form action="/jsp/admin/editPrintCenter" method="post" name="add-application" enctype="multipart/form-data">
        <input type="hidden" name="printCenter_id" value="${printCenter.id}">
        <div class="form-group">
            <label>Name</label>
            <input class="form-control" placeholder="Enter email" id="email" name="email">
        </div>
        <div class="form-group">
            <label for="price">Price:</label>
            <input type="number" class="form-control" id="price" name="price"
                   placeholder="Enter price" min="0" max="1000000"
                   autocomplete="off" value="${room.price}">

        </div>

        <div class="form-group">
            <label for="themes">Select status:</label>
            <select class="custom-select" id="themes" name="themes">
                <c:forEach var="status" items="${themeList}">
                    <c:choose>
                        <c:when test="${status eq room.status}">
                            <option value="${status}" selected>${status}</option>
                        </c:when>
                        <c:otherwise>
                            <option value="${status}">${status}</option>
                        </c:otherwise>
                    </c:choose>
                </c:forEach>
            </select>
        </div>
        <button type="submit" class="btn btn-primary">Edit room</button>
    </form>


    <%@ include file="/WEB-INF/jspf/footer.jspf" %>
</div>


<script src="/js/form-validation.js"></script>
<script src="/js/bootstrap.min.js"></script>
</body>
<!-- this should go after your </body> -->
<link rel="stylesheet" type="text/css" href="/css/jquery.datetimepicker.css"/>

</html>
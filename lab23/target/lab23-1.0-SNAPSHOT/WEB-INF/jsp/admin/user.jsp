<%@ include file="/WEB-INF/jspf/page.jspf" %>
<html>
<c:set var="title" value="Home"/>
<%@ include file="/WEB-INF/jspf/head.jspf" %>
<body>
<div class="container">
    <%@ include file="/WEB-INF/jspf/header.jspf" %>
    <div class="row">
        <div class="col-md-9">
            <c:if test="${personRole == 1}">
                <c:forEach var="reader" items="${readerList}">
                    <i class="title"><i>Id</i> ${reader.id}</i>
                    <i>Balance </i>: ${reader.balance}
                    <i>Status </i>: ${reader.status}<br>
                    <ul class="list-inline">
                        <li class="list-inline-item"></li>
                        <li class="list-inline-item">
                            <form class="form-inline" action="/jsp/admin/user" method="post">
                                <input type="hidden" name="reader_id" value="${reader.id}">
                                <button type="submit" class="btn btn-outline-danger">Change Status</button>
                            </form>
                        </li>
                    </ul>
                    <hr>
                </c:forEach></c:if>
        </div>
    </div>
    <%@ include file="/WEB-INF/jspf/footer.jspf" %>
</div>

</body>
</html>

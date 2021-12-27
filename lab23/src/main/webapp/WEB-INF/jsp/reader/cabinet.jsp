<%@ include file="/WEB-INF/jspf/page.jspf" %>
<html>
<c:set var="title" value="Home"/>
<%@ include file="/WEB-INF/jspf/head.jspf" %>
<body>
<div class="container">
    <%@ include file="/WEB-INF/jspf/header.jspf" %>

    <div class="row">
        <h2> Balance: ${balance} </h2><br>
        <form action="/jsp/reader/balance" method="post" name="upBalance">
            <div class="form-group">
                <label>sum:</label>
                <input type="hidden" name="person_id" value="${person.id}">
                <input type="hidden" name="printCenter_id" value="${printCenter.id}">
                <input type="hidden" name="printCenter_price" value="${printCenter.price}">
                <input class="form-control" name="upBal">
            </div>
            <button type="submit" class="btn btn-primary">Top Up Balance</button>
        </form>
        <div class="col-md-9">
            <h1> Subscriptions: </h1><br><br>

            <c:forEach var="printCenter" items="${subscriptionList}">

                <h2 class="title"><i>*</i> ${printCenter.name}</h2>
            </c:forEach>
        </div>
    </div>
    <%@ include file="/WEB-INF/jspf/footer.jspf" %>
</div>

</body>
</html>

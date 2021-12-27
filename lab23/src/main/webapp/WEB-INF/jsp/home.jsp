<%@ include file="/WEB-INF/jspf/page.jspf" %>
<html>
<c:set var="title" value="Home"/>
<%@ include file="/WEB-INF/jspf/head.jspf" %>
<body>
<div class="container">
    <%@ include file="/WEB-INF/jspf/header.jspf" %>
    <div class="row">

            <form class="form-inline" action="/jsp/home/price/min" method="get">
                <input type="hidden" name="person_id" value="${person.id}">
                <input type="hidden" name="printCenter_id" value="${printCenter.id}">
                <input type="hidden"  name="printCenter_price" value="${printCenter.price}">
                <button type="submit" class="btn btn-primary"> Sort by price min </button>
            </form>
            <form class="form-inline" action="/jsp/home/price/max" method="get">
                <input type="hidden" name="person_id" value="${person.id}">
                <input type="hidden" name="printCenter_id" value="${printCenter.id}">
                <input type="hidden" name="printCenter_price" value="${printCenter.price}">
                <button type="submit" class="btn btn-primary" > Sort by price max </button>
            </form>
            <form class="form-inline" action="/jsp/home" method="get">
                <input type="hidden" name="person_id" value="${person.id}">
                <input type="hidden" name="printCenter_id" value="${printCenter.id}">
                <input type="hidden" name="printCenter_price" value="${printCenter.price}">
                <button type="submit" class="btn btn-primary" >Sort by name </button>
            </form>
        <div class="col-md-9">

            <c:forEach var="printCenter" items="${printCenterList}">

                <h3 class="title"><i>Name</i> ${printCenter.name}</h3>
                <i>Price </i>: ${printCenter.price}<br>
                <c:forEach var="theme" items="${themeList}">
                    <c:choose>
                    <c:when test="${printCenter.themeId == theme.id}">
                    <i>Theme </i>: ${theme.name} </p>
                    </c:when>
                    </c:choose>
                </c:forEach>

                <ul class="list-inline">
                    <li class="list-inline-item">
                        <c:choose>
                            <c:when test="${empty personRole}">
                                <form class="form-inline" action="/jsp/login"  method="get">
                                    <button type="submit" class="btn btn-outline-primary">Order</button>
                                </form>
                            </c:when>
                            <c:otherwise>
                                <c:choose>
                                    <c:when test="${reader.balance >= printCenter.price and  personRole == 2}">
                                        <form class="form-inline" action="/jsp/reader/subscribe" method="post">
                                            <input type="hidden" name="person_id" value="${person.id}">
                                            <input type="hidden" name="printCenter_id" value="${printCenter.id}">
                                            <input type="hidden" name="printCenter_price" value="${printCenter.price}">
                                            <button type="submit" class="btn btn-outline-primary"> OrderBuy</button>
                                        </form>
                                    </c:when>
                                    <c:otherwise>
                                        <button type="button" class="btn btn-outline-primary" disabled>Order(Lack of Balance)</button></c:otherwise>
                                </c:choose>
                            </c:otherwise>
                        </c:choose>
                        <!-- Modal -->

                    </li>
                    <c:if test="${personRole == 1}">
                        <li class="list-inline-item">
                            <form class="form-inline" action="/jsp/admin/editPrintCenter"  method="get">
                                <input type="hidden" name="printCenter_id" value="${printCenter.id}">
                                <button type="submit" class="btn btn-outline-secondary">Edit</button>
                            </form>
                        </li>
                        <li class="list-inline-item">
                            <form class="form-inline" action="/jsp/admin/deletePrintCenter"  method="post">
                                <input type="hidden" name="printCenter_id" value="${printCenter.id}">
                                <button type="submit" class="btn btn-outline-danger">Delete</button>
                            </form>
                        </li>
                    </c:if>
                </ul>
                <hr>
            </c:forEach>
        </div>
    </div>
    <%@ include file="/WEB-INF/jspf/footer.jspf" %>
</div>

</body>
</html>

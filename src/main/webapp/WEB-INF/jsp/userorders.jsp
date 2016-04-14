<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="my" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<fmt:setLocale value="${locale}"/>
<fmt:bundle basename="i18n">

</fmt:bundle>

<%--@elvariable id="cart" type="com.epam.alexandrli.paintballshop.entity.Order"--%>
<my:genericpage pagetitle="${pagetitle}">
    <div class="row row-offcanvas row-offcanvas-right" style="width: 960px; margin: auto;">
        <div class="col-lg-9" align="center">
            <p>Cart</p>
            <c:choose>
                <c:when test="${empty cart.orderItems}">
                    <p>Empty</p>
                </c:when>
                <c:otherwise>
                    <table class="table table-bordered">
                        <thead>
                        <tr>
                            <th>Product</th>
                            <th>Amount</th>
                            <th>Price</th>
                            <th>Total</th>
                            <th></th>
                        </tr>
                        </thead>
                        <tbody>
                        <c:forEach items="${cart.orderItems}" var="item" varStatus="status">
                            <tr>
                                <td>${item.product.name}</td>
                                <td>${item.amount}</td>
                                <td>${item.product.price}</td>
                                <td>${item.price}</td>
                                <td>
                                    <a class="btn btn-default"
                                       href="<c:url value="/do/removeitem?item=${status.index}"></c:url>"
                                    >Remove
                                    </a>
                                </td>
                            </tr>
                        </c:forEach>
                        <tr>
                            <td colspan="3" align="right">Total price:</td>
                            <td><b>${cart.price}</b></td>
                        </tr>
                        </tbody>
                    </table>
                    <div align="center">
                        <a class="btn btn-default" href="<c:url value="/do/placeorder"></c:url>">Place order</a>
                        <a class="btn btn-default" href="<c:url value="/do/clearcart"></c:url>">Clear cart</a>
                        <button type="button" class="btn btn-default" data-toggle="modal" data-target="#Description">add
                            description
                        </button>
                    </div>
                </c:otherwise>
            </c:choose>
            <div class="modal fade" id="Description" role="dialog">
                <div class="modal-dialog" style="margin:100px">
                    <!-- Modal content-->
                    <div class="modal-content" style="width: 440px">
                        <div class="modal-header">
                            <h4 class="modal-title">Fill your description</h4>
                        </div>
                        <div class="modal-body">
                            <form action="<c:url value="/do/addcartdescription"></c:url>" method="post">
                            <textarea name="description"
                                      style="width: 400px;height: 100px;">${cart.description}</textarea>
                        </div>
                        <div class="modal-footer">
                            <button type="submit" class="btn btn-default">Apply</button>
                            </form>
                            <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
                        </div>
                    </div>

                </div>
            </div>
            <hr>
            <p>Orders:</p>
            <table class="table table-bordered">
                <thead>
                <tr>
                    <th>Created</th>
                    <th>Description</th>
                    <th>Price</th>
                    <th>Status</th>
                    <th></th>
                </tr>
                </thead>
                <tbody>
                    <%--@elvariable id="orders" type="java.util.List"--%>
                    <%--@elvariable id="order" type="com.epam.alexandrli.paintballshop.entity.Order"--%>
                <c:forEach items="${orders}" var="order">
                    <tr>
                        <td>${order.formattedCreatedTime}</td>
                        <td>${order.description}</td>
                        <td>${order.price}</td>
                        <td>${order.status.getName(locale)}</td>
                        <td><a class="btn btn-default"
                               href="<c:url value="/do/order?id=${order.id}"></c:url>"
                        >details
                        </a></td>
                    </tr>
                </c:forEach>
                </tbody>
            </table>
        </div>
        <my:usermenu user="${user}"/>
    </div>
</my:genericpage>

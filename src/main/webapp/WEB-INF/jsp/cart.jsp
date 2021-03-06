<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="my" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<fmt:bundle basename="i18n">
    <fmt:message key="userorders.pagetitle" var="pagetitle"/>
    <fmt:message key="userorders.cart" var="cart_title"/>
    <fmt:message key="common.empty" var="empty_title"/>
    <fmt:message key="common.product" var="product"/>
    <fmt:message key="common.quantity" var="quantity"/>
    <fmt:message key="common.price" var="price"/>
    <fmt:message key="common.total" var="total"/>
    <fmt:message key="common.button.delete" var="delete"/>
    <fmt:message key="common.totalprice" var="total_price"/>
    <fmt:message key="userorders.button.placeorder" var="b_placeorder"/>
    <fmt:message key="userorders.button.clearcart" var="b_clearcart"/>
    <fmt:message key="userorders.button.add_description" var="b_add_description"/>
    <fmt:message key="description.modal.title" var="modal_title"/>
    <fmt:message key="common.orders" var="orders_title"/>
    <fmt:message key="common.created" var="created"/>
    <fmt:message key="common.description" var="description"/>
    <fmt:message key="common.status" var="status"/>
    <fmt:message key="error.amount" var="amount_error_message"/>
    <fmt:message key="common.button.details" var="b_details"/>
    <fmt:message key="common.button.recountPrice" var="b_recount"/>
    <fmt:message key="common.button.save" var="b_save"/>
    <fmt:message key="common.button.close" var="b_close"/>
</fmt:bundle>

<%--@elvariable id="cart" type="com.epam.alexandrli.paintballshop.entity.Order"--%>
<%--@elvariable id="errorMap" type="java.util.Map"--%>
<my:generic-page pagetitle="${pagetitle}">
    <div class="row row-offcanvas row-offcanvas-right" style="width: 1200px; margin: auto;">
        <div class="col-lg-10" align="center">
            <c:choose>
                <c:when test="${empty cart.orderItems}">
                    <p>${empty_title}</p>
                </c:when>
                <c:otherwise>
                    <table class="table table-bordered">
                        <thead>
                        <tr>
                            <th>${product}</th>
                            <th>${quantity}</th>
                            <th>${price}</th>
                            <th>${total}</th>
                            <th></th>
                        </tr>
                        </thead>
                        <tbody>
                        <form action="<c:url value="/do/cart/recount"/>" method="post">
                            <c:forEach items="${cart.orderItems}" var="item" varStatus="itemRow">
                                <tr>
                                    <td>${item.product.name}</td>
                                    <td><input type="number" min="1" max="9999" value="${item.amount}"
                                               style="width: 100px"
                                               name="item${itemRow.index}" onchange="this.form.submit()">
                                        <c:if test="${errorMap.get(itemRow.index).equals('true')}">
                                            <p class="text-danger"
                                               style="height: 20px;font-size: 12px;">${amount_error_message}</p>
                                        </c:if>
                                    </td>
                                    <fmt:formatNumber var="formattedProductPrice" type="currency" currencyCode="KZT"
                                                      maxFractionDigits="0"
                                                      value="${item.product.price.amount}"/>
                                    <td>${formattedProductPrice}</td>
                                    <fmt:formatNumber var="formattedItemPrice" type="currency" currencyCode="KZT"
                                                      maxFractionDigits="0"
                                                      value="${item.price.amount}"/>
                                    <td>${formattedItemPrice}</td>
                                    <td>
                                        <a class="btn btn-default"
                                           href="<c:url value="/do/cart/deleteitem?item=${itemRow.index}"/>"
                                        >${delete}
                                        </a>
                                    </td>
                                </tr>
                            </c:forEach>
                            <tr>
                                <td colspan="3" align="right">${total_price}:</td>
                                    <fmt:formatNumber var="formattedCartPrice" type="currency" currencyCode="KZT"
                                                      maxFractionDigits="0"
                                                      value="${cart.price.amount}"/>
                                <td><b>${formattedCartPrice}</b></td>
                        </form>
                        </tr>
                        </tbody>
                    </table>
                    <div align="center">
                        <a class="btn btn-default" href="<c:url value="/do/cart/buy"/>">${b_placeorder}</a>
                        <a class="btn btn-default" href="<c:url value="/do/cart/clear"/>">${b_clearcart}</a>
                        <button type="button" class="btn btn-default" data-toggle="modal"
                                data-target="#Description">${b_add_description}
                        </button>
                    </div>
                </c:otherwise>
            </c:choose>
            <div class="modal fade" id="Description" role="dialog">
                <div class="modal-dialog" style="margin:100px">
                    <div class="modal-content" style="width: 440px">
                        <div class="modal-header">
                            <h4 class="modal-title">${modal_title}</h4>
                        </div>
                        <div class="modal-body">
                            <form action="<c:url value="/do/cart/description"/>" method="post">
                            <textarea name="description"
                                      style="width: 400px;height: 100px;">${cart.description}</textarea>
                                <div class="modal-footer">
                                    <button type="submit" class="btn btn-default">${b_save}</button>
                                    <button type="button" class="btn btn-default"
                                            data-dismiss="modal">${b_close}</button>
                                </div>
                            </form>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <my:user-menu user="${loggedUser}"/>
    </div>
</my:generic-page>

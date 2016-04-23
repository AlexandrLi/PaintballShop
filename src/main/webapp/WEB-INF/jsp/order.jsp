<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="my" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<fmt:bundle basename="i18n">
    <fmt:message key="userorders.pagetitle" var="pagetitle"/>
    <fmt:message key="common.product" var="product"/>
    <fmt:message key="common.quantity" var="quantity"/>
    <fmt:message key="common.price" var="price"/>
    <fmt:message key="common.total" var="total"/>
    <fmt:message key="common.totalprice" var="total_price"/>
    <fmt:message key="common.button.back" var="b_back"/>
</fmt:bundle>

<my:generic-page pagetitle="${pagetitle}">
    <div class="row row-offcanvas row-offcanvas-right" style="width: 1200px; margin: auto;">
        <div class="col-lg-10" align="center">
                <%--@elvariable id="order" type="com.epam.alexandrli.paintballshop.entity.Order"--%>
            <table class="table table-bordered">
                <thead>
                <tr>
                    <th>${product}</th>
                    <th>${quantity}</th>
                    <th>${price}</th>
                    <th>${total}</th>
                </tr>
                </thead>
                <tbody>
                <c:forEach items="${order.orderItems}" var="item">
                    <tr>
                        <td>${item.product.name}</td>
                        <td>${item.amount}</td>
                        <td>${item.product.price}</td>
                        <td>${item.price}</td>
                    </tr>
                </c:forEach>
                </tbody>
            </table>
            <p class="pull-right"><b>${total_price}: ${order.price}</b></p><span class="pull-left"><button
                type="button" class="btn btn-default" onclick="history.back()">${b_back}</button></span>
        </div>
        <my:user-menu user="${loggedUser}"/>
    </div>
</my:generic-page>

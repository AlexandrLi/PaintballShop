<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="my" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<fmt:bundle basename="i18n">
    <fmt:message key="common.price" var="price"/>
    <fmt:message key="common.quantity" var="quantity"/>
    <fmt:message key="error.amount" var="amount_error_message"/>
    <fmt:message key="common.addtocart" var="addToCart"/>
    <fmt:message key="common.characteristics" var="characteristics_label"/>
    <fmt:message key="common.description" var="description"/>
</fmt:bundle>

<%--@elvariable id="product" type="com.epam.alexandrli.paintballshop.entity.Product"--%>
<%--@elvariable id="loggedUser" type="com.epam.alexandrli.paintballshop.entity.User"--%>
<my:generic-page pagetitle="${product.name}">
    <div class="row" style="width: 1200px; margin: auto;">
        <div class="col-lg-10" align="center">
            <h4 align="center"><b>${product.name}</b></h4>
            <div class="col-lg-6">
                <img class="pull-left" style="height: 200px;" alt="image"
                     src="<c:url value="/img/${product.id}"/>"/>
            </div>
            <div class="col-lg-6">
                <fmt:formatNumber var="formattedPrice" type="currency" currencyCode="KZT" maxFractionDigits="0"
                                  value="${product.price.amount}"/>
                <p>${price}: ${formattedPrice}</p>
                <form action="<c:url value="/do/cart/add"/>" method="post">
                    <input type="hidden" name="product" value="${product.id}"/>
                    <div class="form-group input-group">
                            ${quantity}: <input type="number" value="1" min="1" max="9999" name="amount"
                                                style="width: 70px">
                        <c:if test="${not empty amountError}">
                            <p class="text-danger" style="height: 20px;font-size: 12px;">${amount_error_message}</p>
                        </c:if>
                    </div>
                    <div class="form-group input-group">
                        <input type="submit" class="btn btn-default" value="${addToCart}">
                    </div>
                </form>
            </div>
            <div class="col-lg-12">
                <c:if test="${not empty product.characteristics}">
                    <hr>
                    <h4>${characteristics_label}</h4></c:if>
                <c:forEach items="${product.characteristics}" var="characteristicItem">
                    <p>${characteristicItem.characteristic.getName(locale)}: ${characteristicItem.value}</p>
                </c:forEach>
                <hr>
                <h4>${description}:</h4>
                <p align="center">${product.getDescription(locale)}</p>
            </div>
        </div>
        <my:user-menu user="${loggedUser}"/>
    </div>
</my:generic-page>

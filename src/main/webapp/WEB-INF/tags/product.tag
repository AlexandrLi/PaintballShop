<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@tag description="Product preview template" pageEncoding="UTF-8" %>
<%@attribute name="product" required="true" type="com.epam.alexandrli.paintballshop.entity.Product" %>

<fmt:bundle basename="i18n">
    <fmt:message key="common.button.details" var="b_details"/>
</fmt:bundle>

<div class="thumbnail">
    <img style="height: 200px;" alt="image" src="<c:url value="/img/${product.id}"/>"/>
    <h4 align="center"><b>${product.name}</b></h4>
    <p align="justify" style="height: 150px">${product.getDescription(locale)}</p>
    <fmt:formatNumber var="formattedPrice" type="currency" currencyCode="KZT" maxFractionDigits="0"
                      value="${product.price.amount}"/>
    <p align="right"><span class="pull-left" style="color: red;font-size: 14px; margin-top: 8px">${formattedPrice}</span><a
            class="btn btn-default" href="<c:url value="/do/product?id=${product.id}"/> " role="button"
            style="font-size: 12px">${b_details}</a></p>
</div>
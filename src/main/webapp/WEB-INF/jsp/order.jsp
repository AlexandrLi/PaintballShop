<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="my" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<fmt:bundle basename="i18n">
    <fmt:message key="userprofile.form.title" var="form_title"/>
    <fmt:message key="order.customer.data" var="customer_data"/>
    <fmt:message key="common.email" var="email"/>
    <fmt:message key="common.firstname" var="firstname"/>
    <fmt:message key="common.lastname" var="lastname"/>
    <fmt:message key="common.phonenumber" var="phonenumber"/>
    <fmt:message key="common.role" var="role"/>
    <fmt:message key="common.gender.title" var="gender_title"/>
    <fmt:message key="order.customer.address" var="customer_address"/>
    <fmt:message key="common.country" var="country"/>
    <fmt:message key="common.city" var="city"/>
    <fmt:message key="common.street" var="street"/>
    <fmt:message key="common.building" var="building"/>
    <fmt:message key="common.apartment" var="apartment"/>
    <fmt:message key="common.order" var="order_label"/>
    <fmt:message key="userorders.pagetitle" var="pagetitle"/>
    <fmt:message key="common.product" var="product"/>
    <fmt:message key="common.quantity" var="quantity"/>
    <fmt:message key="common.price" var="price"/>
    <fmt:message key="common.total" var="total"/>
    <fmt:message key="common.totalprice" var="total_price"/>
    <fmt:message key="common.button.back" var="b_back"/>
</fmt:bundle>

<%--@elvariable id="order" type="com.epam.alexandrli.paintballshop.entity.Order"--%>
<%--@elvariable id="user" type="com.epam.alexandrli.paintballshop.entity.User"--%>
<my:generic-page pagetitle="${pagetitle}">
    <div class="row row-offcanvas row-offcanvas-right" style="width: 1200px; margin: auto;">
        <div class="col-lg-10">

            <div class="col-lg-4">
                <h4>${customer_data}</h4>
                <hr>
                <p><b>${firstname}:</b> ${user.firstName}</p>
                <p><b>${lastname}:</b> ${user.lastName}</p>
                <p><b>${email}:</b> ${user.email}</p>
                <p><b>${phonenumber}:</b> ${user.phoneNumber}</p>
                <p><b>${gender_title}:</b> ${user.gender.getName(locale)}</p>
                <p><b>${role}:</b> ${user.role}</p>
            </div>
            <div class="col-lg-4">
                <h4>${customer_address}</h4>
                <hr>
                <p><b>${country}:</b> ${user.address.country}</p>
                <p><b>${city}:</b> ${user.address.city}</p>
                <p><b>${street}:</b> ${user.address.street}</p>
                <p><b>${building}:</b> ${user.address.buildingNumber}</p>
                <p><b>${apartment}:</b> ${user.address.apartmentNumber}</p>
            </div>
            <div class="col-lg-12">
                <hr>
                <h4>${order_label}</h4>
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
                            <fmt:formatNumber var="formattedProductPrice" type="currency" currencyCode="KZT"
                                              maxFractionDigits="0"
                                              value="${item.product.price.amount}"/>
                            <td>${formattedProductPrice}</td>
                            <fmt:formatNumber var="formattedItemPrice" type="currency" currencyCode="KZT"
                                              maxFractionDigits="0"
                                              value="${item.price.amount}"/>
                            <td>${formattedItemPrice}</td>
                        </tr>
                    </c:forEach>
                    </tbody>
                </table>
                <fmt:formatNumber var="formattedOrderPrice" type="currency" currencyCode="KZT" maxFractionDigits="0"
                                  value="${order.price.amount}"/>
                <p class="pull-right"><b>${total_price}: ${formattedOrderPrice}</b></p><span class="pull-left"><button
                    type="button" class="btn btn-default" onclick="history.back()">${b_back}</button></span>
            </div>
        </div>
        <my:user-menu user="${loggedUser}"/>
    </div>
</my:generic-page>

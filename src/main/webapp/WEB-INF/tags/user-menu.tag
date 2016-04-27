<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@tag description="User menu" pageEncoding="UTF-8" %>
<%@attribute name="user" required="true" type="com.epam.alexandrli.paintballshop.entity.User" %>

<fmt:bundle basename="i18n">
    <fmt:message key="common.user_label" var="greetings"/>
    <fmt:message key="common.balance" var="balance"/>
    <fmt:message key="usermenu.myprofile" var="myprofile"/>
    <fmt:message key="usermenu.myorders" var="myorders"/>
    <fmt:message key="usermenu.manage.users" var="manage_users"/>
    <fmt:message key="usermenu.manage.products" var="manage_products"/>
    <fmt:message key="usermenu.manage.orders" var="manage_orders"/>
    <fmt:message key="usermenu.manage.storage" var="manage_storages"/>
</fmt:bundle>

<%--@elvariable id="loggedUser" type="com.epam.alexandrli.paintballshop.entity.User"--%>
<c:if test="${loggedUser.role=='user'}">
    <div class="col-lg-2 sidebar-offcanvas" id="sidebar">
        <div class="list-group">
            <p align="center">${greetings} ${loggedUser.firstName}</p>
            <fmt:formatNumber var="formattedBalance" type="currency" currencyCode="KZT" maxFractionDigits="0"
                              value="${loggedUser.cash.amount}"/>
            <p align="center">${balance}: ${formattedBalance}</p>
            <a href="<c:url value="/do/user/profile"/>" class="list-group-item">${myprofile}</a>
            <a href="<c:url value="/do/user/orders"/>" class="list-group-item">${myorders}</a>
        </div>
    </div>
</c:if>
<c:if test="${loggedUser.role=='admin'}">
    <div class="col-lg-2 sidebar-offcanvas" id="sidebar">
        <div class="list-group">
            <p align="center">${greetings} ${loggedUser.firstName}</p>
            <fmt:formatNumber var="formattedBalance" type="currency" currencyCode="KZT" maxFractionDigits="0"
                              value="${loggedUser.cash.amount}"/>
            <p align="center">${balance}: ${formattedBalance}</p>
            <a href="<c:url value="/do/user/profile"/>" class="list-group-item">${myprofile}</a>
            <a href="<c:url value="/do/user/orders"/>" class="list-group-item">${myorders}</a>
            <a href="<c:url value="/do/manage/users"/>" class="list-group-item">${manage_users}</a>
            <a href="<c:url value="/do/manage/products"/>" class="list-group-item">${manage_products}</a>
            <a href="<c:url value="/do/manage/orders"/>" class="list-group-item">${manage_orders}</a>
            <a href="<c:url value="/do/manage/storage"/>" class="list-group-item">${manage_storages}</a>
        </div>
    </div>
</c:if>
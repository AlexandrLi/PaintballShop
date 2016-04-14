<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@tag description="User menu" pageEncoding="UTF-8" %>
<%@attribute name="user" required="true" type="com.epam.alexandrli.paintballshop.entity.User" %>

<fmt:setLocale value="${locale}"/>
<fmt:bundle basename="i18n">
    <fmt:message key="usermenu.greetings" var="greetings"/>
    <fmt:message key="usermenu.myprofile" var="myprofile"/>
    <fmt:message key="usermenu.myorders" var="myorders"/>
    <fmt:message key="usermenu.manage.users" var="manage_users"/>
    <fmt:message key="usermenu.manage.products" var="manage_products"/>
    <fmt:message key="usermenu.manage.orders" var="manage_orders"/>
    <fmt:message key="usermenu.manage.storages" var="manage_storages"/>
</fmt:bundle>

<%--@elvariable id="user" type="com.epam.alexandrli.paintballshop.entity.User"--%>
<c:if test="${user.role=='user'}">
    <div class="col-lg-6 col-lg-3 sidebar-offcanvas" id="sidebar" style="padding-right: 0">
        <div class="list-group">
            <p align="center">${greetings} ${user.firstName}</p>
            <p align="center">cash: ${user.cash}</p>
            <a href="<c:url value="/do/userprofile"></c:url>" class="list-group-item">${myprofile}</a>
            <a href="<c:url value="/do/userorders"></c:url>" class="list-group-item">${myorders}</a>
        </div>
    </div>
</c:if>
<c:if test="${user.role=='admin'}">
    <div class="col-lg-6 col-lg-3 sidebar-offcanvas" id="sidebar" style="padding-right: 0">
        <div class="list-group">
            <p align="center">${greetings} ${user.firstName}</p>
            <p align="center">cash: ${user.cash}</p>
            <a href="<c:url value="/do/userprofile"></c:url>" class="list-group-item">${myprofile}</a>
            <a href="<c:url value="/do/manage-users"></c:url>" class="list-group-item">${manage_users}</a>
            <a href="<c:url value="/do/manage-products"></c:url>" class="list-group-item">${manage_products}</a>
            <a href="<c:url value="/do/manage-orders"></c:url>" class="list-group-item">${manage_orders}</a>
            <a href="<c:url value="/do/manage-storage"></c:url>" class="list-group-item">${manage_storages}</a>
        </div>
    </div>
</c:if>
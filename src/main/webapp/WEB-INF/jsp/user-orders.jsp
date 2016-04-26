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
    <fmt:message key="common.button.remove" var="remove"/>
    <fmt:message key="common.totalprice" var="total_price"/>
    <fmt:message key="userorders.button.placeorder" var="b_placeorder"/>
    <fmt:message key="userorders.button.clearcart" var="b_clearcart"/>
    <fmt:message key="userorders.button.add_description" var="b_add_description"/>
    <fmt:message key="description.modal.title" var="modal_title"/>
    <fmt:message key="common.orders" var="orders_title"/>
    <fmt:message key="common.created" var="created"/>
    <fmt:message key="common.description" var="description"/>
    <fmt:message key="common.status" var="status"/>
    <fmt:message key="common.button.details" var="b_details"/>
    <fmt:message key="common.button.save" var="b_save"/>
    <fmt:message key="common.button.close" var="b_close"/>
</fmt:bundle>

<%--@elvariable id="cart" type="com.epam.alexandrli.paintballshop.entity.Order"--%>
<my:generic-page pagetitle="${pagetitle}">
    <div class="row row-offcanvas row-offcanvas-right" style="width: 1200px; margin: auto;">
        <div class="col-lg-10" align="center">
            <c:choose>
                <c:when test="${empty orders}">
                    <p>${empty_title}</p>
                </c:when>
                <c:otherwise>
                    <table class="table table-bordered">
                        <thead>
                        <tr>
                            <th>${created}</th>
                            <th>${description}</th>
                            <th>${price}</th>
                            <th>${status}</th>
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
                                >${b_details}
                                </a></td>
                            </tr>
                        </c:forEach>
                        </tbody>
                    </table>
                </c:otherwise>
            </c:choose>
        </div>
        <my:user-menu user="${loggedUser}"/>
    </div>
</my:generic-page>

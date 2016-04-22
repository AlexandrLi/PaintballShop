<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="my" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<fmt:bundle basename="i18n">
    <fmt:message key="orders.pagetitle" var="pagetitle"/>
    <fmt:message key="common.product" var="product"/>
    <fmt:message key="common.amount" var="amount"/>
    <fmt:message key="common.price" var="price"/>
    <fmt:message key="common.created" var="created"/>
    <fmt:message key="orders.customer" var="customer"/>
    <fmt:message key="common.description" var="description"/>
    <fmt:message key="common.status" var="status"/>
    <fmt:message key="common.button.edit" var="b_edit"/>
    <fmt:message key="common.button.delete" var="b_delete"/>
</fmt:bundle>

<my:generic-page pagetitle="${pagetitle}">
    <div class="row row-offcanvas row-offcanvas-right" style="width: 1200px; margin: auto;">
        <div class="col-lg-10" align="center">
            <nav>
                <ul class="pagination">
                    <c:forEach begin="1" end="${pagesCount}" varStatus="loop">
                        <li <c:if test="${page==loop.count}">class="active" </c:if>><a
                                href="<c:url value="/do/manage/orders?page=${loop.count}"></c:url>">${loop.count}</a>
                        </li>
                    </c:forEach>
                </ul>
            </nav>
            <hr>
            <table class="table table-bordered">
                <thead>
                <tr>
                    <th>${created}</th>
                    <th>${customer}</th>
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
                        <td>${order.user.firstName}</td>
                        <td>${order.description}</td>
                        <td>${order.price}</td>
                        <td>${order.status.getName(locale)}</td>
                        <td><a class="btn btn-default"
                               href="<c:url value="/do/edit/order?id=${order.id}"></c:url>"
                        >${b_edit}</a>
                            <a class="btn btn-default"
                               href="<c:url value="/do/delete/order?id=${order.id}"></c:url>"
                            >${b_delete}</a>
                        </td>
                    </tr>
                </c:forEach>
                </tbody>
            </table>
        </div>
        <my:user-menu user="${loggedUser}"/>
    </div>
</my:generic-page>
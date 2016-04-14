<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="my" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<fmt:setLocale value="${locale}"/>
<fmt:bundle basename="i18n">

</fmt:bundle>

<my:genericpage pagetitle="${pagetitle}">
    <div class="row row-offcanvas row-offcanvas-right" style="width: 960px; margin: auto;">
        <div class="col-lg-9" align="center">
            <p>Cart</p>
                <%--@elvariable id="order" type="com.epam.alexandrli.paintballshop.entity.Order"--%>
            <table class="table table-bordered">
                <thead>
                <tr>
                    <th>Product</th>
                    <th>Amount</th>
                    <th>Price</th>
                    <th>Total</th>
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
            total price: ${order.price}
        </div>
        <my:usermenu user="${user}"/>
    </div>
</my:genericpage>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="my" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<fmt:setLocale value="${locale}"/>
<fmt:bundle basename="i18n">

</fmt:bundle>

<my:genericpage pagetitle="${pagetitle}">
    <div class="row row-offcanvas row-offcanvas-right" style="width: 960px; margin: auto;">
        <div class="col-lg-9" style="padding-left: 0">
            <p>Catalog</p>
            <div class="row">
                <%--@elvariable id="products" type="java.util.List"--%>
                <%--@elvariable id="products" type="com.epam.alexandrli.paintballshop.entity.product"--%>
                <c:forEach items="${products}" var="product">
                    <div class="col-lg-4">
                        <my:product product="${product}"/>
                    </div>
                </c:forEach>
            </div>
        </div>
        <my:usermenu user="${user}"/>
    </div>
</my:genericpage>

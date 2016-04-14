<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="my" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<fmt:setLocale value="${locale}"/>
<fmt:bundle basename="i18n">
</fmt:bundle>
<%--@elvariable id="product" type="com.epam.alexandrli.paintballshop.entity.Product"--%>
<my:genericpage pagetitle="${pagetitle}">
    <div class="row" style="width: 960px; margin: auto;">
        <div class="col-lg-9" align="center">
            <h4 align="center"><b>${product.name}</b></h4>
            <div class="col-lg-9">
                <img class="pull-left" style="height: 200px;" alt="image"
                     src="<c:url value="/img/${product.id}"/>"/>
            </div>
            <div class="col-lg-3">
                <p>Price: ${product.price}</p>
                <form action="<c:url value="/do/addtocart"></c:url>" method="post">
                    <input type="hidden" name="product" value="${product.id}"/>
                    <div class="form-group input-group">
                        Amount: <input type="number" value="1" min="1" name="amount" style="width: 70px">
                    </div>
                    <div class="form-group input-group">
                        <input type="submit" class="btn btn-default" value="Add to cart">
                    </div>
                </form>
            </div>
            <div class="col-lg-12">
                <hr>
                <h4>Characteristics</h4>
                <c:forEach items="${product.characteristics}" var="characteristic">
                    <p>${characteristic.characteristic.getName(locale)}: ${characteristic.value}</p>
                </c:forEach>
                <hr>
                <h4>Description:</h4>
                <p align="center">${product.getDescription(locale)}</p>
            </div>
        </div>
        <my:usermenu user="${user}"/>
    </div>
</my:genericpage>

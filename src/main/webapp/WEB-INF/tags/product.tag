<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@tag description="Product preview template" pageEncoding="UTF-8" %>
<%@attribute name="product" required="true" type="com.epam.alexandrli.paintballshop.entity.Product" %>
<%--<c:set var="locale" value="${pageContext.request.locale}" scope="session"/>--%>
<div class="thumbnail">
    <img style="height: 200px;" src="<c:url value="/images/img-none.gif"/>"/>
    <h4 align="center"><b>${product.name}</b></h4>
    <p align="justify">${product.getDescription(locale)}</p>
    <p align="right"><span style="color: red;font-size: 12px;margin-right: 20px;">${product.price}</span><a
            class="btn btn-default" href="#" role="button">View
        details »</a></p>
</div>
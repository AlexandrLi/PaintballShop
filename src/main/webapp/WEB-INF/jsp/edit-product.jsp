<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="my" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<fmt:bundle basename="i18n">
    <fmt:message key="editProduct.pagetitle" var="pagetitle"/>
</fmt:bundle>

<%--@elvariable id="product" type="com.epam.alexandrli.paintballshop.entity.Product"--%>
<%--@elvariable id="types" type="java.util.List"--%>
<%--@elvariable id="type" type="com.epam.alexandrli.paintballshop.entity.ProductType"--%>
<%--@elvariable id="loggedUser" type="com.epam.alexandrli.paintballshop.entity.User"--%>
<my:generic-page pagetitle="${pagetitle}">
    <div class="row row-offcanvas row-offcanvas-right" style="width: 1200px; margin: auto;">
        <div class="col-lg-10" align="center" style="padding-left: 100px">
            <my:product-form action="/do/edit/product" types="${types}" product="${product}"/>
        </div>
        <my:user-menu user="${loggedUser}"/>
    </div>
</my:generic-page>

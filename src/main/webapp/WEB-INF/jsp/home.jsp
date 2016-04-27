<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="my" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<fmt:bundle basename="i18n">
    <fmt:message key="home.pagetitle" var="pagetitle"/>
    <fmt:message key="home.aboutcompanytitle" var="titleabout"/>
    <fmt:message key="home.aboutcompany" var="about"/>
</fmt:bundle>

<%--@elvariable id="pagesCount" type="java.lang.Integer"--%>
<%--@elvariable id="products" type="java.util.List"--%>
<%--@elvariable id="loggedUser" type="com.epam.alexandrli.paintballshop.entity.User"--%>
<my:generic-page pagetitle="${pagetitle}">
    <div class="row row-offcanvas row-offcanvas-right" style="width: 1200px; margin: auto;">
        <div class="col-lg-10" align="center">
            <div>
                <p align="center" style="font-size: 18px"><b>${titleabout}</b></p>
                <p align="justify">${about}</p>
                <hr>
                <my:pagination pagesCount="${pagesCount}" url="/do/home"/>
            </div>
            <div class="row">
                <c:forEach items="${products}" var="product">
                    <div class="col-lg-4">
                        <my:product product="${product}"/>
                    </div>
                </c:forEach>
            </div>
        </div>
        <my:user-menu user="${loggedUser}"/>
    </div>
</my:generic-page>

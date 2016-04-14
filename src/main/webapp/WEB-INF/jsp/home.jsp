<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="my" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<fmt:setLocale value="${locale}"/>
<fmt:bundle basename="i18n">
    <fmt:message key="home.pagetitle" var="pagetitle"/>
    <fmt:message key="home.aboutcompanytitle" var="titleabout"/>
    <fmt:message key="home.aboutcompany" var="about"/>
</fmt:bundle>

<my:genericpage pagetitle="${pagetitle}">
    <div class="row row-offcanvas row-offcanvas-right" style="width: 960px; margin: auto;">
        <div class="col-lg-9" style="padding-left: 0">
            <div class="">
                <p align="center" style="font-size: 18px"><b>${titleabout}</b></p>
                <p align="justify">${about}</p>
                <hr>
            </div>
            <p>Featured</p>
            <div class="row">
                <c:forEach items="${featuredList}" var="product">
                    <div class="col-lg-4">
                        <my:product product="${product}"/>
                    </div>
                </c:forEach>
            </div>
        </div>
        <my:usermenu user="${user}"/>
    </div>
</my:genericpage>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="my" tagdir="/WEB-INF/tags" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<my:genericpage pagetitle="${pagetitle}">
    <div class="row row-offcanvas row-offcanvas-right" style="width: 960px; margin: auto;">
        <div class="col-xs-12 col-sm-9" style="padding-left: 0">
            <div class="">
                <p align="center" style="font-size: 18px"><b>О компании</b></p>
                <p align="justify">Пейнтбольное оборудование на любой вкус в наличии и под заказ! Мы работаем напрямую с
                    производителями ведущих брендов пейнтбольного мира, поэтому предоставляем лучшие цены в регионе.</p>
                <hr>
            </div>
            <p>Featured</p>
            <div class="row">
                <div class="col-xs-6 col-lg-4">
                    <my:product product="${product}"/>
                </div>
                <div class="col-xs-6 col-lg-4">
                    <my:product product="${product}"/>
                </div>
                <div class="col-xs-6 col-lg-4">
                    <my:product product="${product}"/>
                </div>
            </div>
        </div>
        <c:if test="${not empty user}">
            <div class="col-xs-6 col-sm-3 sidebar-offcanvas" id="sidebar" style="padding-right: 0">
                <div class="list-group">
                    <p align="center">User menu</p>
                    <a href="#" class="list-group-item">My profile</a>
                    <a href="#" class="list-group-item">My orders</a>
                        <%--<a href="#" class="list-group-item">Link</a>--%>
                </div>
            </div>
        </c:if>
    </div>
</my:genericpage>

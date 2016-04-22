<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="my" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<fmt:bundle basename="i18n">
    <fmt:message key="products.pagetitle" var="pagetitle"/>
    <fmt:message key="common.product.type" var="type"/>
    <fmt:message key="common.product.name" var="name"/>
    <fmt:message key="common.price" var="price"/>
    <fmt:message key="common.description.ru" var="description_ru"/>
    <fmt:message key="common.description.en" var="description_en"/>
    <fmt:message key="common.button.edit" var="b_edit"/>
    <fmt:message key="common.button.delete" var="b_delete"/>
</fmt:bundle>

<%--@elvariable id="cart" type="com.epam.alexandrli.paintballshop.entity.Order"--%>
<my:generic-page pagetitle="${pagetitle}">
    <div class="row row-offcanvas row-offcanvas-right" style="width: 1200px; margin: auto;">
        <div class="col-lg-10" align="center">
            <nav>
                <ul class="pagination">
                    <c:forEach begin="1" end="${pagesCount}" varStatus="loop">
                        <li <c:if test="${page==loop.count}">class="active" </c:if>><a
                                href="<c:url value="/do/manage/products?page=${loop.count}"></c:url>">${loop.count}</a>
                        </li>
                    </c:forEach>
                </ul>
            </nav>
            <hr>
            <table class="table table-bordered" style="font-size: 14px">
                <thead>
                <tr>
                    <th>Id</th>
                    <th>${type}</th>
                    <th>${name}</th>
                    <th>${price}</th>
                    <th>${description_ru}</th>
                    <th>${description_en}</th>
                    <th></th>
                </tr>
                </thead>
                <tbody>
                    <%--@elvariable id="products" type="java.util.List"--%>
                    <%--@elvariable id="product" type="com.epam.alexandrli.paintballshop.entity.Product"--%>
                <c:forEach items="${products}" var="product">
                    <tr>
                        <td>${product.id}</td>
                        <td>${product.type.getName(locale)}</td>
                        <td>${product.name}</td>
                        <td>${product.price}</td>
                        <td>${product.descriptionRu}</td>
                        <td>${product.descriptionEn}</td>
                        <td><a class="btn btn-default"
                               href="<c:url value="/do/edit/product?id=${product.id}"></c:url>"
                        >${b_edit}</a>
                            <a class="btn btn-default"
                               href="<c:url value="/do/delete/product?id=${product.id}"></c:url>"
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

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="my" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<fmt:bundle basename="i18n">
    <fmt:message key="orders.pagetitle" var="pagetitle"/>
    <fmt:message key="storage.label" var="storage_label"/>
    <fmt:message key="common.product" var="product"/>
    <fmt:message key="common.amount" var="amount"/>
    <fmt:message key="common.button.edit" var="b_edit"/>
    <fmt:message key="common.button.delete" var="b_delete"/>
    <fmt:message key="common.button.save" var="b_save"/>
</fmt:bundle>

<my:generic-page pagetitle="${pagetitle}">
    <div class="row row-offcanvas row-offcanvas-right" style="width: 1200px; margin: auto;">
        <div class="col-lg-10" align="center">
            <nav>
                <ul class="pagination">
                    <c:forEach begin="1" end="${pagesCount}" varStatus="loop">
                        <li <c:if test="${page==loop.count}">class="active" </c:if>><a
                                href="<c:url value="/do/manage/storage?page=${loop.count}"></c:url>">${loop.count}</a>
                        </li>
                    </c:forEach>
                </ul>
            </nav>
            <hr>
            <table class="table table-bordered">
                <thead>
                <tr>
                    <th>${storage_label}</th>
                    <th>${product}</th>
                    <th>${amount}</th>
                    <th></th>
                </tr>
                </thead>
                <tbody>
                    <%--@elvariable id="storageItems" type="java.util.List"--%>
                    <%--@elvariable id="storageItem" type="com.epam.alexandrli.paintballshop.entity.StorageItem"--%>
                <c:forEach items="${storageItems}" var="storageItem">
                    <tr>
                        <td>${storageItem.storage.name}</td>
                        <td>${storageItem.product.name}</td>
                        <form action="<c:url value="/do/edit/storage/itemAmount"/>" method="post">
                            <td>
                                <div class="form-group input-group">
                                    <input hidden name="itemId" value="${storageItem.id}">
                                    <input type="number" min="0" value="${storageItem.amount}" style="width: 70px"
                                           name="amount">
                                </div>
                            </td>
                            <td>
                                <button value="submit" class="btn btn-default">${b_save}</button>
                                <a class="btn btn-default"
                                   href="<c:url value="/do/delete/storageItem?id=${storageItem.id}"></c:url>"
                                >${b_delete}</a>
                            </td>
                        </form>
                    </tr>
                </c:forEach>
                </tbody>
            </table>
        </div>
        <my:user-menu user="${loggedUser}"/>
    </div>
</my:generic-page>

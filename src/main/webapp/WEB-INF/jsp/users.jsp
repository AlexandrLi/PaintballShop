<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="my" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<fmt:bundle basename="i18n">
    <fmt:message key="users.pagetitle" var="pagetitle"/>
    <fmt:message key="common.email" var="email"/>
    <fmt:message key="common.password" var="password"/>
    <fmt:message key="common.role" var="role"/>
    <fmt:message key="common.firstname" var="firstname"/>
    <fmt:message key="common.lastname" var="lastname"/>
    <fmt:message key="common.phonenumber" var="phonenumber"/>
    <fmt:message key="common.gender.title" var="gender_title"/>
    <fmt:message key="common.balance" var="balance"/>
    <fmt:message key="common.button.edit" var="b_edit"/>
    <fmt:message key="common.button.delete" var="b_delete"/>
    <fmt:message key="error.delete" var="delete_error_message"/>
</fmt:bundle>

<%--@elvariable id="users" type="java.util.List"--%>
<%--@elvariable id="user" type="com.epam.alexandrli.paintballshop.entity.User"--%>
<%--@elvariable id="loggedUser" type="com.epam.alexandrli.paintballshop.entity.User"--%>
<my:generic-page pagetitle="${pagetitle}">
    <div class="row row-offcanvas row-offcanvas-right" style="width: 1200px; margin: auto;">
        <div class="col-lg-10" align="center">
            <my:pagination url="/do/manage/users" pagesCount="${pagesCount}"/>
            <table class="table table-bordered" style="font-size: 14px">
                <thead>
                <tr>
                    <th>Id</th>
                    <th>${email}</th>
                    <th>${password}</th>
                    <th>${role}</th>
                    <th>${firstname}</th>
                    <th>${lastname}</th>
                    <th>${gender_title}</th>
                    <th>${phonenumber}</th>
                    <th>${balance}</th>
                    <th></th>
                </tr>
                </thead>
                <tbody>
                <c:forEach items="${users}" var="user">
                    <fmt:formatNumber var="formattedBalance" type="currency" currencyCode="KZT" maxFractionDigits="0"
                                      value="${user.cash.amount}"/>
                    <tr>
                        <td>${user.id}</td>
                        <td>${user.email}</td>
                        <td>${user.password}</td>
                        <td>${user.role}</td>
                        <td>${user.firstName}</td>
                        <td>${user.lastName}</td>
                        <td>${user.gender.getName(locale)}</td>
                        <td>${user.phoneNumber}</td>
                        <td>${formattedBalance}</td>
                        <td><a class="btn btn-default"
                               href="<c:url value="/do/edit/user?id=${user.id}"/>"
                        >${b_edit}</a>
                            <a class="btn btn-default"
                               href="<c:url value="/do/delete/user?id=${user.id}"/>"
                            >${b_delete}</a>
                        </td>
                    </tr>
                </c:forEach>
                </tbody>
            </table>
            <c:if test="${deleteError.equals('true')}">
                <p class="text-danger"
                   style="height: 10px;font-size: 12px;">${delete_error_message}</p>
            </c:if>
        </div>
        <my:user-menu user="${loggedUser}"/>
    </div>
</my:generic-page>

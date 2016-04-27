<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="my" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<fmt:bundle basename="i18n">
    <fmt:message key="home.pagetitle" var="pagetitle"/>
    <fmt:message key="common.forbidden" var="forbidden"/>
    <fmt:message key="common.button.back" var="b_back"/>
    <fmt:message key="error.statusCode" var="statusCode_label"/>
    <fmt:message key="error.message" var="message"/>
</fmt:bundle>

<%--@elvariable id="loggedUser" type="com.epam.alexandrli.paintballshop.entity.User"--%>
<my:generic-page pagetitle="${pagetitle}">
    <div class="row row-offcanvas row-offcanvas-right" style="width: 1200px; margin: auto;">
        <div class="col-lg-10" align="center">
            <img style="width: 700px;margin-bottom: 20px" src="<c:url value="/images/${statusCode}.jpg"/>"
                 class="img-responsive"
                 alt="error">
            <table class="table table-bordered" style="width: 400px;">
                <tbody>
                <tr>
                    <td>${statusCode_label}</td>
                    <td>${statusCode}</td>
                </tr>
                <tr>
                    <td>${message}</td>
                    <td>${errorMessage}</td>
                </tr>
                </tbody>
            </table>
            <button type="button" class="btn btn-default" onclick="history.back()">${b_back}</button>
        </div>
        <my:user-menu user="${loggedUser}"/>
    </div>
</my:generic-page>

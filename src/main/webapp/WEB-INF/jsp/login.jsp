<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="my" tagdir="/WEB-INF/tags" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<c:set var="locale" value="${pageContext.request.locale}" scope="session"/>
<c:set var="locale" value="en" scope="session"/>

<fmt:setLocale value="${locale}"/>
<fmt:bundle basename="i18n">
    <fmt:message key="login.pagetitle" var="pagetitle"/>
    <fmt:message key="login.welcome" var="welcome"/>
    <fmt:message key="login.email" var="email"/>
    <fmt:message key="login.password" var="password"/>
    <fmt:message key="login.button.login" var="b_login"/>
    <fmt:message key="login.button.register" var="b_register"/>
    <fmt:message key="login.error" var="error"/>
</fmt:bundle>

<c:url var="login_url" value="/do/login"/>
<c:url var="register_url" value="/do/register"/>
<my:genericpage pagetitle="${pagetitle}">
    <div align="center">
        <div class="h3">${welcome}</div>
        <form role="form" action="${login_url}" method="post">
            <div class="form-group input-group" style="width: 300px">
                <span class="input-group-addon" id="basic-addon1">@</span>
                <input type="text" class="form-control" aria-describedby="basic-addon1" placeholder="${email}"
                       name="email">
            </div>
            <div class="form-group input-group" style="width: 300px">
                <span class="input-group-addon"><i class="glyphicon glyphicon-lock"></i></span>
                <input type="password" class="form-control" placeholder="${password}" name="password">
            </div>
            <div class="form-group">
                <c:if test="${not empty loginError}">
                    <p class="alert alert-danger" style="width: 250px;height: 30px;padding: 5px">${error}</p>
                </c:if>
                <button type="submit" class="btn btn-primary btn-primary-spacing"
                        style="margin-right: 50px;width: 100px">
                        ${b_login}
                </button>
                <a href="<c:url value="/do/register"/>" class="btn btn-primary btn-primary-spacing"
                   style="width: 100px">${b_register}</a>
            </div>
        </form>
    </div>
</my:genericpage>

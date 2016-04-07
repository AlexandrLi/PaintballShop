<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="my" tagdir="/WEB-INF/tags" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<fmt:setLocale value="${locale}"/>
<fmt:bundle basename="i18n">
    <fmt:message key="register.pagetitle" var="pagetitle"/>
    <fmt:message key="register.form.title" var="form_title"/>
    <fmt:message key="register.email" var="email"/>
    <fmt:message key="register.password" var="password"/>
    <fmt:message key="register.firstname" var="firstname"/>
    <fmt:message key="register.lastname" var="lastname"/>
    <fmt:message key="register.phonenumber" var="phonenumber"/>
    <fmt:message key="register.gender.title" var="gender_title"/>
    <fmt:message key="register.button.register" var="b_register"/>
    <fmt:message key="register.button.cancel" var="b_cancel"/>
</fmt:bundle>

<c:url var="register_url" value="/do/register"/>
<my:genericpage pagetitle="${pagetitle}">
    <div align="center">
        <div>
            <div class="h3">${form_title}</div>
        </div>
        <form role="form" action="${register_url}" method="post">
            <div class="form-group input-group">
                <input type="text" class="form-control" placeholder="${email}" name="email">
                <c:if test="${not empty emailError}">
                    <p class="text-danger" style="height: 10px;font-size: 12px;">${emailError}</p>
                </c:if>
            </div>
            <div class="form-group input-group">
                <input type="password" class="form-control" placeholder="${password}" name="password">
                <c:if test="${not empty passwordError}">
                    <p class="text-danger" style="height: 10px;font-size: 12px;">${passwordError}</p>
                </c:if>
            </div>
            <div class="form-group input-group">
                <input type="text" class="form-control" placeholder="${firstname}" name="firstName">
                <c:if test="${not empty firstNameError}">
                    <p class="text-danger" style="height: 10px;font-size: 12px;">${firstNameError}</p>
                </c:if>
            </div>
            <div class="form-group input-group">
                <input type="text" class="form-control" placeholder="${lastname}" name="lastName">
                <c:if test="${not empty lastNameError}">
                    <p class="text-danger" style="height: 10px;font-size: 12px;">${lastNameError}</p>
                </c:if>
            </div>
            <div class="form-group input-group">
                <input type="text" class="form-control" placeholder="${phonenumber}" name="phoneNumber">
                <c:if test="${not empty phoneNumberError}">
                    <p class="text-danger" style="height: 10px;font-size: 12px;">${phoneNumberError}</p>
                </c:if>
            </div>
            <div class="form-group input-group">
                <label for="gender">${gender_title}</label>
                <select class="form-control" id="gender" name="gender">
                        <%--@elvariable id="gender" type="com.epam.alexandrli.paintballshop.entity.Gender"--%>
                    <c:forEach items="${genders}" var="gender">
                        <option value="${gender.id}">${gender.getName(locale)}</option>
                    </c:forEach>
                </select>
            </div>
            <button value="submit" class="btn btn-default">${b_register}</button>
        </form>
    </div>
</my:genericpage>

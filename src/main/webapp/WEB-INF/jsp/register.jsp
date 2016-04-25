<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="my" tagdir="/WEB-INF/tags" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<fmt:bundle basename="i18n">
    <fmt:message key="register.pagetitle" var="pagetitle"/>
    <fmt:message key="register.form.title" var="form_title"/>
    <fmt:message key="common.email" var="email"/>
    <fmt:message key="common.password" var="password"/>
    <fmt:message key="common.firstname" var="firstname"/>
    <fmt:message key="common.lastname" var="lastname"/>
    <fmt:message key="common.phonenumber" var="phonenumber"/>
    <fmt:message key="common.country" var="country"/>
    <fmt:message key="common.city" var="city"/>
    <fmt:message key="common.street" var="street"/>
    <fmt:message key="common.building" var="building"/>
    <fmt:message key="common.apartment" var="apartment"/>
    <fmt:message key="common.gender.title" var="gender_title"/>
    <fmt:message key="register.button.register" var="b_register"/>
    <fmt:message key="register.button.cancel" var="b_cancel"/>
</fmt:bundle>

<c:url var="register_url" value="/do/register"/>
<my:generic-page pagetitle="${pagetitle}">
    <div class="row row-offcanvas row-offcanvas-right" style="width: 1200px; margin: auto;">
        <div class="col-lg-12" align="center">
            <div class="h3" style="margin-bottom: 20px">${form_title}</div>
            <form role="form" action="${register_url}" method="post" style="width: 460px">
                <div class="col-lg-6" align="center">
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
                </div>
                <div class="col-lg-6">
                    <div class="form-group input-group">
                        <input type="text" class="form-control" placeholder="${country}" name="country">
                        <c:if test="${not empty countryError}">
                            <p class="text-danger" style="height: 10px;font-size: 12px;">${countryError}</p>
                        </c:if>
                    </div>
                    <div class="form-group input-group">
                        <input type="text" class="form-control" placeholder="${city}" name="city">
                        <c:if test="${not empty cityError}">
                            <p class="text-danger" style="height: 10px;font-size: 12px;">${cityError}</p>
                        </c:if>
                    </div>
                    <div class="form-group input-group">
                        <input type="text" class="form-control" placeholder="${street}" name="street">
                        <c:if test="${not empty streetError}">
                            <p class="text-danger" style="height: 10px;font-size: 12px;">${streetError}</p>
                        </c:if>
                    </div>
                    <div class="form-group input-group">
                        <input type="text" class="form-control" placeholder="${building}" name="buildingNumber">
                        <c:if test="${not empty buildingError}">
                            <p class="text-danger" style="height: 10px;font-size: 12px;">${buildingError}</p>
                        </c:if>
                    </div>
                    <div class="form-group input-group">
                        <input type="text" class="form-control" placeholder="${apartment}" name="apartmentNumber">
                        <c:if test="${not empty apartmentError}">
                            <p class="text-danger" style="height: 10px;font-size: 12px;">${apartmentError}</p>
                        </c:if>
                    </div>
                </div>
                <div class="col-lg-12">
                    <div class="form-group input-group" style="width: 120px">
                        <label for="gender">${gender_title}</label>
                        <select class="form-control" id="gender" name="gender">
                                <%--@elvariable id="gender" type="com.epam.alexandrli.paintballshop.entity.Gender"--%>
                            <c:forEach items="${genders}" var="gender">
                                <option value="${gender.id}">${gender.getName(locale)}</option>
                            </c:forEach>
                        </select>
                    </div>
                    <button value="submit" class="btn btn-default" style="width: 120px">${b_register}</button>
                </div>
            </form>
        </div>
    </div>
</my:generic-page>

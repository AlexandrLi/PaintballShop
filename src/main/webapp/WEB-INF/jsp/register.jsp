<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="my" tagdir="/WEB-INF/tags" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<fmt:bundle basename="i18n">
    <fmt:message key="register.pagetitle" var="pagetitle"/>
    <fmt:message key="register.form.title" var="form_title"/>
    <fmt:message key="common.email" var="email_label"/>
    <fmt:message key="common.password" var="password_label"/>
    <fmt:message key="common.firstname" var="firstname_label"/>
    <fmt:message key="common.lastname" var="lastname_label"/>
    <fmt:message key="common.phonenumber" var="phonenumber_label"/>
    <fmt:message key="common.country" var="country_label"/>
    <fmt:message key="common.city" var="city_label"/>
    <fmt:message key="common.street" var="street_label"/>
    <fmt:message key="common.building" var="building_label"/>
    <fmt:message key="common.apartment" var="apartment_label"/>
    <fmt:message key="common.gender.title" var="gender_title"/>
    <fmt:message key="error.emailUsed" var="email_used_message"/>
    <fmt:message key="error.password" var="password_error_message"/>
    <fmt:message key="error.invalid" var="invalid_error_message"/>
    <fmt:message key="error.phoneNumber" var="phoneNumber_error_message"/>
    <fmt:message key="register.button.register" var="b_register"/>
    <fmt:message key="register.button.cancel" var="b_cancel"/>
</fmt:bundle>

<%--@elvariable id="gender" type="com.epam.alexandrli.paintballshop.entity.Gender"--%>
<%--@elvariable id="genders" type="java.util.List"--%>
<c:url var="register_url" value="/do/register"/>
<my:generic-page pagetitle="${pagetitle}">
    <div class="row row-offcanvas row-offcanvas-right" style="width: 1200px; margin: auto;">
        <div class="col-lg-12" align="center">
            <div class="h3" style="margin-bottom: 20px">${form_title}</div>
            <form role="form" action="${register_url}" method="post" style="width: 460px">
                <div class="col-lg-6" align="center">
                    <div class="form-group input-group">
                        <input type="text" class="form-control" placeholder="${email_label}" name="email"
                               value="${email}">
                        <c:if test="${emailError.equals('true')}">
                            <p class="text-danger" style="height: 20px;font-size: 12px;">${invalid_error_message}</p>
                        </c:if>
                        <c:if test="${emailError.equals('used')}">
                            <p class="text-danger" style="height: 20px;font-size: 12px;">${email_used_message}</p>
                        </c:if>
                    </div>
                    <div class="form-group input-group">
                        <input type="password" class="form-control" placeholder="${password_label}" name="password"
                               value="${password}">
                        <c:if test="${not empty passwordError}">
                            <p class="text-danger" style="height: 20px;font-size: 12px">${password_error_message}</p>
                        </c:if>
                    </div>
                    <div class="form-group input-group">
                        <input type="text" class="form-control" placeholder="${firstname_label}" name="firstName"
                               value="${firstName}">
                        <c:if test="${not empty firstNameError}">
                            <p class="text-danger" style="height: 20px;font-size: 12px;">${invalid_error_message}</p>
                        </c:if>
                    </div>
                    <div class="form-group input-group">
                        <input type="text" class="form-control" placeholder="${lastname_label}" name="lastName"
                               value="${lastName}">
                        <c:if test="${not empty lastNameError}">
                            <p class="text-danger" style="height: 20px;font-size: 12px;">${invalid_error_message}</p>
                        </c:if>
                    </div>
                    <div class="form-group input-group">
                        <input type="text" class="form-control" placeholder="${phonenumber_label}" name="phoneNumber"
                               value="${phoneNumber}">
                        <c:if test="${not empty phoneNumberError}">
                            <p class="text-danger"
                               style="height: 20px;font-size: 12px;">${phoneNumber_error_message}</p>
                        </c:if>
                    </div>
                </div>
                <div class="col-lg-6">
                    <div class="form-group input-group">
                        <input type="text" class="form-control" placeholder="${country_label}" name="country"
                               value="${country}">
                        <c:if test="${not empty countryError}">
                            <p class="text-danger" style="height: 20px;font-size: 12px;">${invalid_error_message}</p>
                        </c:if>
                    </div>
                    <div class="form-group input-group">
                        <input type="text" class="form-control" placeholder="${city_label}" name="city"
                               value="${city}">
                        <c:if test="${not empty cityError}">
                            <p class="text-danger" style="height: 20px;font-size: 12px;">${invalid_error_message}</p>
                        </c:if>
                    </div>
                    <div class="form-group input-group">
                        <input type="text" class="form-control" placeholder="${street_label}" name="street"
                               value="${street}">
                        <c:if test="${not empty streetError}">
                            <p class="text-danger" style="height: 20px;font-size: 12px;">${invalid_error_message}</p>
                        </c:if>
                    </div>
                    <div class="form-group input-group">
                        <input type="text" class="form-control" placeholder="${building_label}" name="buildingNumber"
                               value="${buildingNumber}">
                        <c:if test="${not empty buildingNumberError}">
                            <p class="text-danger" style="height: 20px;font-size: 12px;">${invalid_error_message}</p>
                        </c:if>
                    </div>
                    <div class="form-group input-group">
                        <input type="text" class="form-control" placeholder="${apartment_label}" name="apartmentNumber"
                               value="${apartmentNumber}">
                        <c:if test="${not empty apartmentNumberError}">
                            <p class="text-danger" style="height: 20px;font-size: 12px;">${invalid_error_message}</p>
                        </c:if>
                    </div>
                </div>
                <div class="col-lg-12">
                    <div class="form-group input-group" style="width: 120px">
                        <label for="gender">${gender_title}</label>
                        <select class="form-control" id="gender" name="gender">
                            <c:forEach items="${genders}" var="genderItem">
                                <option value="${genderItem.id}" <c:if
                                        test="${genderItem.id==gender}"> selected </c:if>>${genderItem.getName(locale)}</option>
                            </c:forEach>
                        </select>
                    </div>
                    <button value="submit" class="btn btn-default" style="width: 120px">${b_register}</button>
                </div>
            </form>
        </div>
    </div>
</my:generic-page>

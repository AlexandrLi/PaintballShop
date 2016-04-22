<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="my" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<fmt:bundle basename="i18n">
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
    <fmt:message key="common.admin_role" var="admin_role"/>
    <fmt:message key="common.user_role" var="user_role"/>
    <fmt:message key="common.role" var="role"/>
    <fmt:message key="common.button.save" var="b_save"/>
    <fmt:message key="common.button.cancel" var="b_cancel"/>
</fmt:bundle>

<my:generic-page pagetitle="${pagetitle}">
    <div class="row row-offcanvas row-offcanvas-right" style="width: 1200px; margin: auto;">
        <div class="col-lg-10" align="center">
            <div class="h3" style="margin-bottom: 20px">${form_title}</div>
            <form role="form" action="<c:url value="/do/edit/user"></c:url>" method="post" style="width: 500px">
                <div class="col-lg-6" align="center">
                    <input hidden name="userId" value="${user.id}">
                    <div class="form-group input-group">
                        <label for="email">${email}</label>
                        <input type="email" class="form-control" id="email" name="email"
                               value="${user.email}">
                        <c:if test="${not empty passwordError}">
                            <p class="text-danger" style="height: 10px;font-size: 12px;">${passwordError}</p>
                        </c:if>
                    </div>
                    <div class="form-group input-group">
                        <label for="password">${password}</label>
                        <input type="password" class="form-control" id="password" name="password"
                               value="${user.password}">
                        <c:if test="${not empty passwordError}">
                            <p class="text-danger" style="height: 10px;font-size: 12px;">${passwordError}</p>
                        </c:if>
                    </div>
                    <div class="form-group input-group">
                        <label for="firstName">${firstname}</label>
                        <input type="text" class="form-control" id="firstName" name="firstName"
                               value="${user.firstName}">
                        <c:if test="${not empty firstNameError}">
                            <p class=" text-danger" style="height: 10px;font-size: 12px;">${firstNameError}</p>
                        </c:if>
                    </div>
                    <div class="form-group input-group">
                        <label for="lastName">${lastname}</label>
                        <input type="text" class="form-control" id="lastName" name="lastName"
                               value="${user.lastName}">
                        <c:if test="${not empty lastNameError}">
                            <p class=" text-danger" style="height: 10px;font-size: 12px;">${lastNameError}</p>
                        </c:if>
                    </div>
                    <div class="form-group input-group">
                        <label for="phoneNumber">${phonenumber}</label>
                        <input type="text" class="form-control" id="phoneNumber" name="phoneNumber"
                               value="${user.phoneNumber}">
                        <c:if test="${not empty phoneNumberError}">
                            <p class=" text-danger" style="height: 10px;font-size: 12px;">${phoneNumberError}</p>
                        </c:if>
                    </div>
                    <div class="form-group input-group">
                        <label for="gender">${gender_title}:</label>
                        <select class="form-control" id="gender" name="gender">
                                <%--@elvariable id="gender" type="com.epam.alexandrli.paintballshop.entity.Gender"--%>
                                <%--@elvariable id="user" type="com.epam.alexandrli.paintballshop.entity.User"--%>
                            <c:forEach items="${genders}" var="gender">
                                <option value="${gender.id}"<c:if
                                        test="${user.gender.equals(gender)}"> selected </c:if>>${gender.getName(locale)}</option>
                            </c:forEach>
                        </select>
                    </div>
                </div>
                <div class="col-lg-6">
                    <input hidden name="addressId" value="${address.id}">
                    <div class="form-group input-group">
                        <label for="country">${country}</label>
                        <input type="text" class="form-control" id="country" name="country"
                               value="${address.country}">
                        <c:if test="${not empty countryError}">
                            <p class="text-danger" style="height: 10px;font-size: 12px;">${countryError}</p>
                        </c:if>
                    </div>
                    <div class="form-group input-group">
                        <label for="city">${city}</label>
                        <input type="text" class="form-control" id="city" name="city"
                               value="${address.city}">
                        <c:if test="${not empty cityError}">
                            <p class="text-danger" style="height: 10px;font-size: 12px;">${cityError}</p>
                        </c:if>
                    </div>
                    <div class="form-group input-group">
                        <label for="street">${street}</label>
                        <input type="text" class="form-control" id="street" name="street"
                               value="${address.street}">
                        <c:if test="${not empty streetError}">
                            <p class="text-danger" style="height: 10px;font-size: 12px;">${streetError}</p>
                        </c:if>
                    </div>
                    <div class="form-group input-group">
                        <label for="buildingNumber">${building}</label>
                        <input type="text" class="form-control" id="buildingNumber" name="buildingNumber"
                               value="${address.buildingNumber}">
                        <c:if test="${not empty buildingError}">
                            <p class="text-danger" style="height: 10px;font-size: 12px;">${buildingError}</p>
                        </c:if>
                    </div>
                    <div class="form-group input-group">
                        <label for="apartmentNumber">${apartment}</label>
                        <input type="text" class="form-control" id="apartmentNumber" name="apartmentNumber"
                               value="${address.apartmentNumber}">
                        <c:if test="${not empty apartmentError}">
                            <p class="text-danger" style="height: 10px;font-size: 12px;">${apartmentError}</p>
                        </c:if>
                    </div>
                    <div class="form-group input-group">
                        <label for="role">${role}</label>
                        <p><input type=radio class="radio-button" id="role" name="role"
                                  value="admin" <c:if test="${user.role.name().equals('admin')}">checked</c:if>> <span
                                style="margin-left: 10px">${admin_role}</span>
                            <input style="margin-left: 10px" type=radio class="radio-button" name="role"
                                   value="user" <c:if test="${user.role.name().equals('user')}">checked</c:if>><span
                                    style="margin-left: 10px">${user_role}</span></p>
                    </div>
                </div>
                <div class="col-lg-12">
                    <button value="submit" class="btn btn-default" style="width: 120px">${b_save}</button>
                    <a href="<c:url value="/do/manage/users"></c:url>" class="btn btn-default">${b_cancel}</a>
                </div>
            </form>
        </div>
        <my:user-menu user="${loggedUser}"/>
    </div>
</my:generic-page>

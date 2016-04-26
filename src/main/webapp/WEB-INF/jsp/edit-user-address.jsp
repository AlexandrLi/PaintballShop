<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="my" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<fmt:bundle basename="i18n">
    <fmt:message key="userprofile.pagetitle" var="pagetitle"/>
    <fmt:message key="userprofile.form.title" var="form_title"/>
    <fmt:message key="common.address.title" var="address_title"/>
    <fmt:message key="common.country" var="country"/>
    <fmt:message key="common.city" var="city"/>
    <fmt:message key="common.street" var="street"/>
    <fmt:message key="common.building" var="building"/>
    <fmt:message key="common.apartment" var="apartment"/>
    <fmt:message key="error.invalid" var="invalid_error_message"/>
    <fmt:message key="common.button.save" var="b_save"/>
    <fmt:message key="common.button.cancel" var="b_cancel"/>
</fmt:bundle>

<my:generic-page pagetitle="${pagetitle}">
    <div class="row row-offcanvas row-offcanvas-right" style="width: 1200px; margin: auto;">
        <div class="col-lg-10" align="center">
            <div>
                <div class="h3">${address_title}</div>
            </div>
            <form style="width:200px" role="form" action="<c:url value="/do/edit/address"/>" method="post">
                <div class="form-group input-group">
                    <label for="country">${country}</label>
                    <input type="text" class="form-control" id="country" name="country"
                           value="${address.country}">
                    <c:if test="${not empty countryError}">
                        <p class="text-danger" style="height: 10px;font-size: 12px;">${invalid_error_message}</p>
                    </c:if>
                </div>
                <div class="form-group input-group">
                    <label for="city">${city}</label>
                    <input type="text" class="form-control" id="city" name="city"
                           value="${address.city}">
                    <c:if test="${not empty cityError}">
                        <p class="text-danger" style="height: 10px;font-size: 12px;">${invalid_error_message}</p>
                    </c:if>
                </div>
                <div class="form-group input-group">
                    <label for="street">${street}</label>
                    <input type="text" class="form-control" id="street" name="street"
                           value="${address.street}">
                    <c:if test="${not empty streetError}">
                        <p class="text-danger" style="height: 10px;font-size: 12px;">${invalid_error_message}</p>
                    </c:if>
                </div>
                <div class="form-group input-group">
                    <label for="buildingNumber">${building}</label>
                    <input type="text" class="form-control" id="buildingNumber" name="buildingNumber"
                           value="${address.buildingNumber}">
                    <c:if test="${not empty buildingNumberError}">
                        <p class="text-danger" style="height: 10px;font-size: 12px;">${invalid_error_message}</p>
                    </c:if>
                </div>
                <div class="form-group input-group">
                    <label for="apartmentNumber">${apartment}</label>
                    <input type="text" class="form-control" id="apartmentNumber" name="apartmentNumber"
                           value="${address.apartmentNumber}">
                    <c:if test="${not empty apartmentNumberError}">
                        <p class="text-danger" style="height: 10px;font-size: 12px;">${invalid_error_message}</p>
                    </c:if>
                </div>
                <button value="submit" class="btn btn-default">${b_save}</button>
                <a href="<c:url value="/do/user/profile"></c:url>" class="btn btn-default">${b_cancel}</a>
            </form>
        </div>
        <my:user-menu user="${loggedUser}"/>
    </div>
</my:generic-page>

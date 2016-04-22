<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="my" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<fmt:bundle basename="i18n">
    <fmt:message key="userprofile.pagetitle" var="pagetitle"/>
    <fmt:message key="userprofile.form.title" var="form_title"/>
    <fmt:message key="common.personaldata.title" var="personaldata_title"/>
    <fmt:message key="common.email" var="email"/>
    <fmt:message key="common.firstname" var="firstname"/>
    <fmt:message key="common.lastname" var="lastname"/>
    <fmt:message key="common.phonenumber" var="phonenumber"/>
    <fmt:message key="common.role" var="role"/>
    <fmt:message key="common.gender.title" var="gender_title"/>
    <fmt:message key="common.address.title" var="address_title"/>
    <fmt:message key="common.country" var="country"/>
    <fmt:message key="common.city" var="city"/>
    <fmt:message key="common.street" var="street"/>
    <fmt:message key="common.building" var="building"/>
    <fmt:message key="common.apartment" var="apartment"/>
    <fmt:message key="common.refillbalance.title" var="refillbalance_title"/>
    <fmt:message key="userprofile.button.refill" var="refill"/>
    <fmt:message key="common.button.edit" var="edit"/>
</fmt:bundle>

<my:generic-page pagetitle="${pagetitle}">
    <div class="row row-offcanvas row-offcanvas-right" style="width: 1200px; margin: auto;">
        <div class="col-lg-10">
            <div class="col-lg-4">
                    <%--@elvariable id="loggedUser" type="com.epam.alexandrli.paintballshop.entity.User"--%>
                <p><b>${personaldata_title}</b></p>
                <hr>
                <p><b>${firstname}:</b> ${loggedUser.firstName}</p>
                <p><b>${lastname}:</b> ${loggedUser.lastName}</p>
                <p><b>${email}:</b> ${loggedUser.email}</p>
                <p><b>${phonenumber}:</b> ${loggedUser.phoneNumber}</p>
                <p><b>${gender_title}:</b> ${loggedUser.gender.getName(locale)}</p>
                <p><b>${role}:</b> ${loggedUser.role}</p>
                <p><a href="<c:url value="/do/edit/data"></c:url>" class="btn btn-default">${edit}</a></p>
            </div>
            <div class="col-lg-4">
                    <%--@elvariable id="address" type="com.epam.alexandrli.paintballshop.entity.Address"--%>
                <p><b>${address_title}</b></p>
                <hr>
                <p><b>${country}:</b> ${address.country}</p>
                <p><b>${city}:</b> ${address.city}</p>
                <p><b>${street}:</b> ${address.street}</p>
                <p><b>${building}:</b> ${address.buildingNumber}</p>
                <p><b>${apartment}:</b> ${address.apartmentNumber}</p>
                <p><a style="margin-top: 28px" href="<c:url value="/do/edit/address"></c:url>"
                      class="btn btn-default">${edit}</a></p>
            </div>
            <div class="col-lg-4">
                <p><b>${refillbalance_title}</b></p>
                <hr>
                <form class="form-horizontal" style="width: 200px" action="<c:url value="/do/refill/balance"></c:url>"
                      method="post">
                    <div class="form-group">
                        <div class="input-group">
                            <div class="input-group-addon">KZT</div>
                            <input type="number" class="form-control" id="exampleInputAmount" placeholder="Amount"
                                   name="balance" value="0" min="0">
                            <div class="input-group-addon">.00</div>
                        </div>
                    </div>
                    <button type="submit" class="btn btn-default">${refill}</button>
                </form>
            </div>
        </div>
        <my:user-menu user="${loggedUser}"/>
    </div>
</my:generic-page>

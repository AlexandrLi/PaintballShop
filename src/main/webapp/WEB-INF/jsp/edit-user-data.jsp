<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="my" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<fmt:bundle basename="i18n">
    <fmt:message key="userprofile.pagetitle" var="pagetitle"/>
    <fmt:message key="common.personaldata.title" var="personaldata_title"/>
    <fmt:message key="common.password" var="password"/>
    <fmt:message key="common.firstname" var="firstname"/>
    <fmt:message key="common.lastname" var="lastname"/>
    <fmt:message key="common.phonenumber" var="phonenumber"/>
    <fmt:message key="common.gender.title" var="gender_title"/>
    <fmt:message key="common.button.save" var="b_save"/>
    <fmt:message key="common.button.cancel" var="b_cancel"/>
</fmt:bundle>

<my:generic-page pagetitle="${pagetitle}">
    <div class="row row-offcanvas row-offcanvas-right" style="width: 1200px; margin: auto;">
        <div class="col-lg-10" align="center">
            <div>
                <div class="h3">${personaldata_title}</div>
            </div>
            <form style="width:200px" role="form" action="<c:url value="/do/edit/data"/>" method="post">
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
                <button value="submit" class="btn btn-default">${b_save}</button>
                <a href="<c:url value="/do/user/profile"></c:url>" class="btn btn-default">${b_cancel}</a>
            </form>
        </div>
        <my:user-menu user="${loggedUser}"/>
    </div>
</my:generic-page>

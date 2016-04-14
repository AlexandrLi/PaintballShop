<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="my" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<fmt:setLocale value="${locale}"/>
<fmt:bundle basename="i18n">
    <fmt:message key="userprofile.pagetitle" var="pagetitle"/>
    <fmt:message key="userprofile.form.title" var="form_title"/>
    <fmt:message key="register.password" var="password"/>
    <fmt:message key="register.firstname" var="firstname"/>
    <fmt:message key="register.lastname" var="lastname"/>
    <fmt:message key="register.phonenumber" var="phonenumber"/>
    <fmt:message key="register.gender.title" var="gender_title"/>
    <fmt:message key="userprofile.button.update" var="b_update"/>
    <fmt:message key="userprofile.button.cancel" var="b_cancel"/>
</fmt:bundle>

<my:genericpage pagetitle="${pagetitle}">
    <div class="row row-offcanvas row-offcanvas-right" style="width: 960px; margin: auto;">
        <div class="col-lg-9" align="center">
            <div class="col-lg-6">
                <div>
                    <div class="h3">${form_title}</div>
                </div>
                <form role="form" action="<c:url value="/do/editprofile"/>" method="post">
                    <div class="form-group input-group">
                        <input type="password" class="form-control" placeholder="${password}" name="password"
                               value="${user.password}">
                        <c:if test="${not empty passwordError}">
                            <p class="text-danger" style="height: 10px;font-size: 12px;">${passwordError}</p>
                        </c:if>
                    </div>
                    <div class="form-group input-group">
                        <input type="text" class="form-control" placeholder="${firstname}" name="firstName"
                               value="${user.firstName}">
                        <c:if test="${not empty firstNameError}">
                            <p class=" text-danger" style="height: 10px;font-size: 12px;">${firstNameError}</p>
                        </c:if>
                    </div>
                    <div class="form-group input-group">
                        <input type="text" class="form-control" placeholder="${lastname}" name="lastName"
                               value="${user.lastName}">
                        <c:if test="${not empty lastNameError}">
                            <p class=" text-danger" style="height: 10px;font-size: 12px;">${lastNameError}</p>
                        </c:if>
                    </div>
                    <div class="form-group input-group">
                        <input type="text" class="form-control" placeholder="${phonenumber}" name="phoneNumber"
                               value="${user.phoneNumber}">
                        <c:if test="${not empty phoneNumberError}">
                            <p class=" text-danger" style="height: 10px;font-size: 12px;">${phoneNumberError}</p>
                        </c:if>
                    </div>
                    <div class="form-group input-group">
                        <label for="gender">${gender_title}</label>
                        <select class="form-control" id="gender" name="gender">
                                <%--@elvariable id="gender" type="com.epam.alexandrli.paintballshop.entity.Gender"--%>
                                <%--@elvariable id="user" type="com.epam.alexandrli.paintballshop.entity.User"--%>
                            <c:forEach items="${genders}" var="gender">
                                <c:if test="${user.gender.equals(gender)}">
                                    <option value="${gender.id}" selected>${gender.getName(locale)}</option>
                                </c:if>
                                <option value="${gender.id}">${gender.getName(locale)}</option>
                            </c:forEach>
                        </select>
                    </div>
                    <button value="submit" class="btn btn-default">${b_update}</button>
                    <a href="<c:url value="/do/userprofile"></c:url>" class="btn btn-default">${b_cancel}</a>
                </form>
            </div>
            <div class="col-lg-6" style="margin-top: 120px">
                <form class="form-horizontal" style="width: 200px" action="<c:url value="/do/transfercash"></c:url>"
                      method="post">
                    <h4>Transfer cash:</h4>
                    <div class="form-group">
                        <div class="input-group">
                            <div class="input-group-addon">KZT</div>
                            <input type="number" class="form-control" id="exampleInputAmount" placeholder="Amount"
                                   name="cash" value="0" min="0">
                            <div class="input-group-addon">.00</div>
                        </div>
                    </div>
                    <button type="submit" class="btn btn-default">Apply</button>
                </form>
            </div>
        </div>
        <my:usermenu user="${user}"/>
    </div>
</my:genericpage>

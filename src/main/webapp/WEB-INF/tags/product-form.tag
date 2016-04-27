<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@attribute name="action" required="true" type="java.lang.String" %>
<%@attribute name="product" required="true" type="com.epam.alexandrli.paintballshop.entity.Product" %>
<%@attribute name="types" required="true" type="java.util.List" %>

<fmt:bundle basename="i18n">
    <fmt:message key="common.product.name" var="name_label"/>
    <fmt:message key="common.product.type" var="type_label"/>
    <fmt:message key="common.price" var="price_label"/>
    <fmt:message key="common.description.ru" var="description_ru"/>
    <fmt:message key="common.description.en" var="description_en"/>
    <fmt:message key="common.image" var="image_label"/>
    <fmt:message key="common.button.save" var="b_save"/>
    <fmt:message key="common.button.cancel" var="b_cancel"/>
    <fmt:message key="error.money" var="money_error_message"/>
    <fmt:message key="error.image" var="image_error_message"/>
</fmt:bundle>

<%--@elvariable id="product" type="com.epam.alexandrli.paintballshop.entity.Product"--%>
<%--@elvariable id="types" type="java.util.List"--%>
<%--@elvariable id="type" type="com.epam.alexandrli.paintballshop.entity.ProductType"--%>
<form role="form" action="<c:url value="${action}"/>" method="post"
      enctype="multipart/form-data">
    <input hidden name="id" value="${product.id}">
    <div class="col-lg-6" style="width: 300px">
        <div class="form-group input-group">
            <label for="name">${name_label}</label>
            <input type="text" class="form-control" id="name" name="name"
                   value="${product.name}">
        </div>
        <div class="form-group input-group">
            <label for="image">${image_label}</label>
            <input type="file" class="form-control" id="image" name="image">
            <c:if test="${imageError.equals('true')}">
                <p class="text-danger"
                   style="height: 10px;font-size: 12px;">${image_error_message}</p>
            </c:if>
        </div>
        <div class="form-group input-group">
            <label for="type">${type_label}:</label>
            <select class="form-control" id="type" name="typeId">
                <c:forEach items="${types}" var="type">
                    <option value="${type.id}"<c:if
                            test="${product.type.equals(type)}"> selected </c:if>>${type.getName(locale)}</option>
                </c:forEach>
            </select>
        </div>
        <div class="form-group input-group" style="width:140px;">
            <label for="price">${price_label}:</label>
            <fmt:formatNumber var="formattedPrice" type="number" groupingUsed="false"
                              maxFractionDigits="0"
                              value="${product.price.amount}"/>
            <input type="text" class="form-control" id="price"
                   name="price" value="${formattedPrice}" min="0">
            <c:if test="${moneyError.equals('true')}">
                <p class="text-danger"
                   style="height: 10px;font-size: 12px;">${money_error_message}</p>
            </c:if>
        </div>
    </div>
    <div class="col-lg-6" style="width: 400px">
        <div class="form-group input-group">
            <label for="descriptionRu">${description_ru}</label>
                    <textarea style="width: 400px;height:100px;" class="form-control" id="descriptionRu"
                              name="descriptionRu">${product.descriptionRu}</textarea>
        </div>
        <div class="form-group input-group">
            <label for="descriptionEn">${description_en}</label>
                        <textarea style="width: 400px;height:100px;" class="form-control" id="descriptionEn"
                                  name="descriptionEn">${product.descriptionEn}</textarea>
        </div>
    </div>
    <div class="col-lg-12">
        <button value="submit" class="btn btn-default" style="width: 120px">${b_save}</button>
        <a href="<c:url value="/do/manage/products"/>" class="btn btn-default">${b_cancel}</a>
    </div>
</form>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="my" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<fmt:bundle basename="i18n">
    <fmt:message key="common.product.name" var="name_label"/>
    <fmt:message key="common.product.type" var="type_label"/>
    <fmt:message key="common.price" var="price_label"/>
    <fmt:message key="common.description.ru" var="description_ru"/>
    <fmt:message key="common.description.en" var="description_en"/>
    <fmt:message key="common.image" var="image_label"/>
    <fmt:message key="common.button.save" var="b_save"/>
    <fmt:message key="common.button.cancel" var="b_cancel"/>
</fmt:bundle>
<%--@elvariable id="types" type="java.util.List"--%>
<%--@elvariable id="type" type="com.epam.alexandrli.paintballshop.entity.ProductType"--%>
<my:generic-page pagetitle="${pagetitle}">
    <div class="row row-offcanvas row-offcanvas-right" style="width: 1200px; margin: auto;">
        <div class="col-lg-10" align="center" style="padding-left: 100px">
            <div class="h3" style="margin-bottom: 20px">${form_title}</div>
            <form role="form" action="<c:url value="/do/add/product"></c:url>" method="post"
                  enctype="multipart/form-data">
                <input hidden name="id" value="${product.id}">
                <div class="col-lg-6" style="width: 300px">
                    <div class="form-group input-group">
                        <label for="name">${name_label}</label>
                        <input type="text" class="form-control" id="name" name="name">
                    </div>
                    <div class="form-group input-group">
                        <label for="image">${image_label}</label>
                        <input type="file" class="form-control" id="image" name="image">
                    </div>
                    <div class="form-group input-group">
                        <label for="type">${type_label}:</label>
                        <select class="form-control" id="type" name="type">
                            <c:forEach items="${types}" var="type">
                                <option value="${type.id}">${type.getName(locale)}</option>
                            </c:forEach>
                        </select>
                    </div>
                    <div class="form-group input-group" style="width:140px;">
                        <label for="price">${price_label}:</label>
                        <input type="text" class="form-control" id="price" placeholder="Amount"
                               name="price" min="0">
                    </div>
                </div>
                <div class="col-lg-6" style="width: 400px">
                    <div class="form-group input-group">
                        <label for="descriptionRu">${description_ru}</label>
                    <textarea style="width: 400px;height:100px;" class="form-control" id="descriptionRu"
                              name="descriptionRu"></textarea>
                    </div>
                    <div class="form-group input-group">
                        <label for="descriptionEn">${description_en}</label>
                        <textarea style="width: 400px;height:100px;" class="form-control" id="descriptionEn"
                                  name="descriptionEn"></textarea>
                    </div>
                </div>
                <div class="col-lg-12">
                    <button value="submit" class="btn btn-default" style="width: 120px">${b_save}</button>
                    <a href="<c:url value="/do/manage/products"></c:url>" class="btn btn-default">${b_cancel}</a>
                </div>
            </form>
        </div>
        <my:user-menu user="${loggedUser}"/>
    </div>
</my:generic-page>

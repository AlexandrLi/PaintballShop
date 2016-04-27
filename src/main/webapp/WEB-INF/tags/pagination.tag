<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@attribute name="url" required="true" %>
<%@attribute name="pagesCount" required="true" %>
<%@attribute name="attributeName" required="false" %>
<%@attribute name="attributeValue" required="false" %>

<fmt:bundle basename="i18n">
    <fmt:message key="common.page.number" var="number"/>
    <fmt:message key="common.page.size" var="size"/>
</fmt:bundle>

<%--@elvariable id="page" type="java.lang.Integer"--%>
<%--@elvariable id="pageSize" type="java.lang.Integer"--%>
<nav>
    <ul class="pagination">
        <c:if test="${pagesCount>1}">
            <c:forEach begin="1" end="${pagesCount}" varStatus="loop">
                <li <c:if test="${page==loop.count}">class="active" </c:if>>
                    <a href="<c:url value="${url}">
                <c:if test="${not empty attributeName && not empty attributeValue}">
                    <c:param name="${attributeName}" value="${attributeValue}"/>
                    </c:if>
                    <c:param name="page" value="${loop.count}"/>
                    <c:param name="pageSize" value="${pageSize}"/>
                    </c:url>" title="${number}">${loop.count}</a>
                </li>
            </c:forEach>
        </c:if>
        <form name="form" method="get">
            <c:if test="${not empty attributeName && not empty attributeValue}">
                <input hidden name="${attributeName}" value="${attributeValue}">
            </c:if>
            <select style="margin-top: 10px" name="pageSize" onchange="this.form.submit()" title="${size}">
                <option value="3" <c:if test="${pageSize==3}"> selected </c:if>>3</option>
                <option value="6" <c:if test="${pageSize==6}"> selected </c:if>>6</option>
                <option value="12" <c:if test="${pageSize==12}"> selected </c:if>>12</option>
                <option value="15" <c:if test="${pageSize==15}"> selected </c:if>>15</option>
            </select>
        </form>
    </ul>
</nav>
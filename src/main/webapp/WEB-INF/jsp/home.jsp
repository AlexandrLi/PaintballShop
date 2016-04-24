<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="my" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%--<fmt:setLocale value="${locale}"/>--%>
<fmt:bundle basename="i18n">
    <fmt:message key="home.pagetitle" var="pagetitle"/>
    <fmt:message key="home.aboutcompanytitle" var="titleabout"/>
    <fmt:message key="home.aboutcompany" var="about"/>

</fmt:bundle>

<my:generic-page pagetitle="${pagetitle}">
    <div class="row row-offcanvas row-offcanvas-right" style="width: 1200px; margin: auto;">
        <div class="col-lg-10">
            <div>
                <p align="center" style="font-size: 18px"><b>${titleabout}</b></p>
                <p align="justify">${about}</p>
                <hr>
                <nav>
                    <ul class="pagination">
                        <c:forEach begin="1" end="${pagesCount}" varStatus="loop">
                            <li <c:if test="${page==loop.count}">class="active" </c:if>><a
                                    href="<c:url value="/do/home?page=${loop.count}"></c:url>">${loop.count}</a>
                            </li>
                        </c:forEach>
                    </ul>
                </nav>
            </div>
            <div class="row">
                <c:forEach items="${products}" var="product">
                    <div class="col-lg-4">
                        <my:product product="${product}"/>
                    </div>
                </c:forEach>
            </div>
        </div>
        <my:user-menu user="${loggedUser}"/>
    </div>
</my:generic-page>

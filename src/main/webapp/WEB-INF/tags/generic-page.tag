<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@tag description="Overall Page template" pageEncoding="UTF-8" %>
<%@attribute name="header" fragment="true" %>
<%@attribute name="footer" fragment="true" %>
<%@attribute name="pagetitle" required="true" %>

<%--<fmt:setLocale value="${locale}"/>--%>
<fmt:bundle basename="i18n">
    <fmt:message key="genericpage.navbar.products" var="products"/>
    <fmt:message key="genericpage.navbar.register" var="register"/>
    <fmt:message key="genericpage.navbar.locale" var="lang"/>
    <fmt:message key="genericpage.navbar.login" var="login"/>
    <fmt:message key="genericpage.navbar.logout" var="logout"/>
    <fmt:message key="genericpage.footer.madeby" var="madeby"/>
    <fmt:message key="genericpage.footer.specialfor" var="specialfor"/>
    <fmt:message key="genericpage.footer.date" var="date"/>
</fmt:bundle>

<html>
<head>
    <title>${pagetitle}</title>
    <link rel="stylesheet" href="<c:url value="/css/bootstrap.min.css"/>">
    <script src="<c:url value="/js/jquery-1.12.2.min.js"/>"></script>
    <script src="<c:url value="/js/jquery-2.2.2.min.js"/>"></script>
    <script src="<c:url value="/js/bootstrap.min.js"/>"></script>
</head>
<body>
<div id="pageheader">
    <div class="container" align="center">
        <img style="width: 1200px;margin-bottom: 20px" src="<c:url value="/images/logo.jpg"/>" class="img-responsive"
             alt="logo">
    </div>
    <div align="center" style="width: 1200px;margin: auto">
        <nav class="navbar navbar-inverse">
            <div class="container-fluid">
                <div class="navbar-header">
                    <a class="navbar-brand" href="<c:url value="/do/home"/>"><span
                            class="glyphicon glyphicon-home"></span></a>
                </div>
                <ul class="nav navbar-nav">
                    <li class="dropdown">
                        <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true"
                           aria-expanded="false">${products}<span class="caret"></span></a>
                        <ul class="dropdown-menu">
                            <%--@elvariable id="productTypes" type="java.util.List"--%>
                            <%--@elvariable id="type" type="com.epam.alexandrli.paintballshop.entity.ProductType"--%>
                            <c:forEach items="${productTypes}" var="type">
                                <li>
                                    <a href="<c:url value="/do/catalog?type=${type.id}"></c:url>">${type.getName(locale)}</a>
                                </li>
                            </c:forEach>
                        </ul>
                    </li>
                    <li class="dropdown">
                        <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true"
                           aria-expanded="false">${lang}<span class="caret"></span></a>
                        <ul class="dropdown-menu" style="min-width: 60px">
                            <li><a href="<c:url value="/do/locale?locale=ru"></c:url>" class="language"><img
                                    style="height: 18px"
                                    src="<c:url value="/images/ru.png"/>" alt="Ru"/> Ru</a></li>
                            <li><a href="<c:url value="/do/locale?locale=en"></c:url>" class="language"><img
                                    style="height: 18px"
                                    src="<c:url value="/images/us.png"/>" alt="En"/> En</a></li>
                        </ul>
                    </li>
                </ul>
                <ul class="nav navbar-nav navbar-right">
                    <c:choose>
                        <c:when test="${not empty loggedUser}">
                            <li><a href="<c:url value="/do/logout"/>"><span
                                    class="glyphicon glyphicon-log-out"></span>
                                    ${logout}</a>
                            </li>
                        </c:when>
                        <c:otherwise>
                            <li><a href="<c:url value="/do/register"/>"><span
                                    class="glyphicon glyphicon-user"></span> ${register}</a>
                            </li>
                            <li><a href="<c:url value="/do/login"/>"><span
                                    class="glyphicon glyphicon-log-in"></span>
                                    ${login}</a>
                            </li>
                        </c:otherwise>
                    </c:choose>
                </ul>
            </div>
        </nav>
    </div>
    <jsp:invoke fragment="header"/>
</div>
<div id="body">
    <jsp:doBody/>
</div>
<hr style="margin-top: 50px">
<div id="pagefooter">
    <footer class="footer" style="position: relative;width: 100%; height: 100px">
        <div align="center" class="container">
            <p class="text-muted">${madeby}</p>
            <p class="text-muted">${specialfor}</p>
            <p class="text-muted">${date}</p>
        </div>
    </footer>
    <jsp:invoke fragment="footer"/>
</div>
</body>
</html>
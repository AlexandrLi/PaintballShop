<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@tag description="Overall Page template" pageEncoding="UTF-8" %>
<%@attribute name="header" fragment="true" %>
<%@attribute name="footer" fragment="true" %>
<%@attribute name="pagetitle" required="true" %>
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
        <img style="width: 960px;margin-bottom: 20px" src="<c:url value="/image/logo.jpg"/>" class="img-responsive" alt="logo">
    </div>
    <div align="center" style="width: 960px;margin: auto">
        <nav class="navbar navbar-inverse">
            <div class="container-fluid">
                <div class="navbar-header">
                    <a class="navbar-brand" href="<c:url value="/do/home"/>">Pbshop</a>
                </div>
                <ul class="nav navbar-nav">
                    <li class="dropdown">
                        <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true"
                           aria-expanded="false">Products<span class="caret"></span></a>
                        <ul class="dropdown-menu">
                            <li><a href="#">Markers</a></li>
                            <li><a href="#">Armor</a></li>
                            <li role="separator" class="divider"></li>
                            <li class="dropdown-header">Accessories</li>
                            <li><a href="#">Parts</a></li>
                            <li><a href="#">Batteries</a></li>
                        </ul>
                    </li>
                    <li><a href="#contact">Contacts</a></li>
                    <li><a href="#about">About</a></li>
                </ul>
                <ul class="nav navbar-nav navbar-right">
                    <c:choose>
                        <c:when test="${not empty user}">
                            <li><a href="<c:url value="/do/logout"/>"><span class="glyphicon glyphicon-log-out"></span>
                                Logout</a>
                            </li>
                        </c:when>
                        <c:otherwise>
                            <li><a href="<c:url value="/do/register"/>"><span
                                    class="glyphicon glyphicon-user"></span> Register</a>
                            </li>
                            <li><a href="<c:url value="/do/login"/>"><span class="glyphicon glyphicon-log-in"></span>
                                Login</a>
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
            <p class="text-muted">Project made by Alexandr Li</p>
            <p class="text-muted">Special for EPAM laboratory</p>
            <p class="text-muted">January-April 2016</p>
        </div>
    </footer>
    <jsp:invoke fragment="footer"/>
</div>
</body>
</html>
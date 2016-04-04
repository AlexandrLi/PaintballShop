<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Login</title>
    <link rel="stylesheet" href="<c:url value="/css/bootstrap.min.css"/>">
    <script src="<c:url value="/js/bootstrap.min.js"/>"></script>
</head>
<body>

<c:url var="login_url" value="/do/login"/>
<c:url var="register_url" value="/do/register"/>
<div align="center">
    <img src="<c:url value="/image/logo.jpg"/>" class="img-responsive" alt="logo">
    <div class="h3">Welcome, to Paintball Shop</div>
    <form role="form" action="${login_url}" method="post">
        <div class="form-group input-group" style="width: 300px">
            <span class="input-group-addon" id="basic-addon1">@</span>
            <input type="text" class="form-control" aria-describedby="basic-addon1" placeholder="Email" name="email">
        </div>
        <div class="form-group input-group" style="width: 300px">
            <span class="input-group-addon"><i class="glyphicon glyphicon-lock"></i></span>
            <input type="password" class="form-control" placeholder="Password" name="password">
        </div>
        <div class="form-group">
            <button type="submit" class="btn btn-primary btn-primary-spacing" style="margin-right: 50px;width: 100px">
                Login
            </button>
            <a href="<c:url value="/do/register"/>" class="btn btn-primary btn-primary-spacing" style="width: 100px">Register</a>
        </div>
    </form>
    <p style="color: #c7254e;">${loginError}</p>
</div>
</body>
</html>
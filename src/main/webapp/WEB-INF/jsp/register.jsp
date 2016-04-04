<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Register</title>
    <link rel="stylesheet" href="<c:url value="/css/bootstrap.min.css"/>">
    <script src="<c:url value="/js/bootstrap.min.js"/>"></script>
</head>
<body>
<c:url var="register_url" value="/do/register"/>
<div align="center">
    <div>
        <div class="h3">Please, fill registration form</div>
    </div>
    <form role="form" action="${register_url}" method="post">
        <div class="form-group input-group">
            <input type="text" class="form-control" placeholder="Email" name="email">
        </div>
        <div class="form-group input-group">
            <input type="password" class="form-control" placeholder="Password" name="password">
        </div>
        <div class="form-group input-group">
            <input type="text" class="form-control" placeholder="First name" name="firstName">
        </div>
        <div class="form-group input-group">
            <input type="text" class="form-control" placeholder="Last name" name="lastName">
        </div>
        <div class="form-group input-group">
            <input type="text" class="form-control" placeholder="Phone number" name="phoneNumber">
        </div>
        <div class="form-group input-group">
            <label for="gender">Gender</label>
            <select class="form-control" id="gender" name="gender">
                <option selected>None</option>
                <option>Male</option>
                <option>Female</option>
            </select>
        </div>
        <button value="submit" class="btn btn-default">Register</button>
        <a href="<c:url value="/do/login"/>" class="btn btn-default">Cancel</a>
    </form>
</div>
</body>
</html>

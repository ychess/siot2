<%-- 
    Document   : login
    Created on : 2016-4-27, 11:01:20
    Author     : L.X <gugia@qq.com>
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="shiro" uri="http://shiro.apache.org/tags"%>
<!DOCTYPE html>
<html lang="zh-CN">
    <head>
        <meta charset="utf-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <title>XKT IoT platform</title>
        <spring:url value="/resources/img/favicon.ico" var="ico" />
        <spring:url value="/resources/css/base.css" var="baseCss" />
        <spring:url value="/resources/css/login.css" var="loginCss" />
        <spring:url value="/resources/css/bootstrap.css" var="bootstrapCss" />
        <link rel="icon" href="${ico}">
        <link href="${baseCss}" rel="stylesheet" />
        <link href="${loginCss}" rel="stylesheet" />
        <link href="${bootstrapCss}" rel="stylesheet" />
    </head>
    <body>
        <nav class="navbar navbar-inverse navbar-fixed-top">
            <div class="container">
                <div class="navbar-header">
                    <a class="navbar-brand" href="#">IoT platform</a>
                </div>
            </div>
        </nav>
        <form class="form-signin" action="" method="post">
            <h2 class="form-signin-heading">登录</h2>
            <label for="username" class="sr-only">用户名</label>
            <input type="text" name="username" class="form-control" placeholder="手机号码/邮箱/用户名" <c:if test="${not empty error}">value="${posted_username}"</c:if> required <c:if test="${empty error}">autofocus</c:if> />
                <label for="password" class="sr-only">密码</label>
                <input type="password" name="password" class="form-control" placeholder="请输入密码" required <c:if test="${not empty error}">autofocus</c:if> />
                <div class="checkbox">
                    <label>
                        <input type="checkbox" name="rememberMe" value="true" /> 记住我
                    </label>
                </div>
            <c:if test="${not empty error}"><div class="alert alert-danger">${error}</div></c:if>
            <button class="btn btn-lg btn-primary btn-block" type="submit">登录</button>
        </form>
        <spring:url value="/resources/js/jquery-1.11.3.min.js" var="jqueryJs" />
        <spring:url value="/resources/js/bootstrap.min.js" var="bootstrapJs" />
        <script src="${jqueryJs}" type="text/javascript"></script>
        <script src="${bootstrapJs}" type="text/javascript"></script>
    </body>
</html>

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
        <spring:url value="/resources/css" var="css" />
        <link rel="icon" href="${ico}">
        <link href="${css}/normalize.css" rel="stylesheet" />
        <link href="${css}/login.css" rel="stylesheet" />
    </head>
    <body>
        <div class="login">
            <h1>Login</h1>
            <form method="post">
                <input type="text" name="username" placeholder="手机号码/邮箱/用户名" required="required" <c:if test="${not empty error}">value="${posted_username}"</c:if> />
                <input type="password" name="password" placeholder="请输入密码" required="required" />
                <div class="checkbox">
                    <label>
                        <input type="checkbox" name="rememberMe" value="true" /> 记住我
                    </label>
                </div>
                <c:if test="${not empty error}"><div class="alert alert-danger">${error}</div></c:if>
                <button type="submit" class="btn btn-primary btn-block btn-large">登录</button>
            </form>
        </div>
    </body>
</html>

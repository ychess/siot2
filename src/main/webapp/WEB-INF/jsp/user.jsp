<%-- 
    Document   : user
    Created on : 2016-4-27, 10:40:00
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
        <%@include file="../jspf/navbar.jspf"%>
        <div class="container">
            <button class="btn btn-default" type="submit" onclick="startCs()">启动主节点长连接服务器</button>
            <button class="btn btn-default" type="submit" onclick="stopCs()">停止主节点长连接服务器</button>
        </div>
        <spring:url value="/resources/js/jquery-1.11.3.min.js" var="jqueryJs" />
        <spring:url value="/resources/js/bootstrap.min.js" var="bootstrapJs" />
        <spring:url value="/resources/js/console.js" var="consoleJs" />
        <script src="${jqueryJs}" type="text/javascript"></script>
        <script src="${bootstrapJs}" type="text/javascript"></script>
        <script src="${consoleJs}" type="text/javascript"></script>
    </body>
</html>

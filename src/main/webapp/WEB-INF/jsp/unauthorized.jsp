<%-- 
    Document   : unauthorized
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
    </head>
    <body>
        <h2>没有访问该地址的权限</h2>
    </body>
</html>

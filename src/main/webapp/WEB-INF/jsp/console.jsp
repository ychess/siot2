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
        <title>IoT test platform</title>
        <spring:url value="/resources/img/favicon.ico" var="ico" />
        <spring:url value="/resources/css" var="css" />
        <link rel="icon" href="${ico}">
        <link href="${css}/base.css" rel="stylesheet" />
        <link href="${css}/font-awesome.min.css" rel="stylesheet" />
        <link href="${css}/bootstrap.css" rel="stylesheet" />
    </head>
    <body>
        <div class="container">
            <!-- Static navbar -->
            <nav class="navbar navbar-default">
                <div class="container-fluid">
                    <div class="navbar-header">
                        <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#navbar" aria-expanded="false" aria-controls="navbar">
                            <span class="sr-only">Toggle navigation</span>
                            <span class="icon-bar"></span>
                            <span class="icon-bar"></span>
                            <span class="icon-bar"></span>
                        </button>
                        <a class="navbar-brand" href="#">IoT test platform</a>
                    </div>
                    <div id="navbar" class="navbar-collapse collapse">
                        <ul class="nav navbar-nav">
                            <li class="active"><a href="#">Home</a></li>
                        </ul>
                    </div><!--/.nav-collapse -->
                </div><!--/.container-fluid -->
            </nav>
            <div class="container">
                <button class="btn btn-default" type="submit" onclick="startSS()">启动测试服务器</button>
                <button class="btn btn-default" type="submit" onclick="stopAll()">停止所有服务器</button>
            </div>
        </div> <!-- /container -->
        <spring:url value="/resources/js" var="js" />
        <script src="${js}/jquery-1.11.3.min.js" type="text/javascript"></script>
        <script src="${js}/bootstrap.min.js" type="text/javascript"></script>
        <script src="${js}/console.js" type="text/javascript"></script>
    </body>
</html>

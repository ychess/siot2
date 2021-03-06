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
            <div class="container-fluid">
                <div class="page-header">
                    <h2>简单文本服务器(测试服务器)日志
                        <span class="control-btn">
                            <button class="btn btn-success" type="submit" onclick="ssexcute('start')">启动测试服务器</button>
                            <button class="btn btn-danger" type="submit" onclick="ssexcute('stop')">停止测试服务器</button>
                            <button class="btn btn-default" type="submit" onclick="runclient()">运行测试客户端</button>
                        </span>
                    </h2>
                </div>
                <div class="jumbotron" id="console_output">
                    
                </div>
                <div class="page-header">
                    <h2>SockJs控制
                        <span class="control-btn">
                            <button class="btn btn-default" type="submit" onclick="closeSockJs()">SockJs OFF</button>
                            <button class="btn btn-default" type="submit" onclick="sendSockJs()">Send a message</button>
                        </span>
                    </h2>
                </div>
                <div class="form-group">
                    <label for="text_sockjs">SockJs Message</label>
                    <input type="text" class="form-control" id="text_sockjs" placeholder="Type something…">
                </div>
            </div>
        </div> <!-- /container -->
        <spring:url value="/resources/js" var="js" />
        <script src="${js}/jquery-1.11.3.min.js" type="text/javascript"></script>
        <script src="${js}/bootstrap.min.js" type="text/javascript"></script>
        <script src="${js}/sockjs-1.1.1.js" type="text/javascript"></script>
        <script src="${js}/console.js" type="text/javascript"></script>
    </body>
</html>

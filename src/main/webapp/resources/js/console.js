/* 
 * Copyright 2016 XKT Ltd., Co.
 * Licensed under the Apache License 2.0.
 */
/* 获取格式为"yyyy-MM-dd HH:MM:SS"的当前时间 */
function getNowFormatDate() {
    var date = new Date();
    var seperator1 = "-";
    var seperator2 = ":";
    var month = date.getMonth() + 1;
    var strDate = date.getDate();
    if (month >= 1 && month <= 9) {
        month = "0" + month;
    }
    if (strDate >= 0 && strDate <= 9) {
        strDate = "0" + strDate;
    }
    var currentdate = date.getFullYear() + seperator1 + month + seperator1 + strDate
            + " " + date.getHours() + seperator2 + date.getMinutes()
            + seperator2 + date.getSeconds();
    return currentdate;
}

function getRootPath() {
    var curWwwPath = window.document.location.href;
    var pathName = window.document.location.pathname;
    var pos = curWwwPath.indexOf(pathName);
    var localhostPath = curWwwPath.substring(0, pos);
    var projectName = pathName.substring(0, pathName.substr(1).indexOf('/') + 1);
    return(localhostPath + projectName);
}

function ssexcute(command) {
    var url = getRootPath() + "/console/ss";
    $.ajax({
        type: 'POST',
        url: url,
        data: {cmd: command},
        dataType: 'text',
        success: function (result) {
            if (result !== 'success') {
                printLog(result);
            }
        }
    });
}

function runclient() {
    var url = getRootPath() + "/console/client";
    $.ajax({
        type: 'POST',
        url: url,
        data: null,
        dataType: 'text',
        success: function (result) {
            if (result !== 'success') {
                printLog(result);
            }
        }
    });
}

function stopAll() {
    var url = getRootPath() + "/console/all/stop";
    $.ajax({
        type: 'POST',
        url: url,
        data: null,
        dataType: 'text',
        success: function (result) {
            printLog(result);
        }
    });
}

function printLog(msg) {
    var html = '<p>' + getNowFormatDate() + ' - ' + msg + '</p>';
    $("#console_output").append(html);
    if ($("#console_output").children().size() > 21) {
        $("#console_output").children().eq(0).remove();
    }
}

/* WebSocket */
var sock = new SockJS(getRootPath() + '/sockjs/webSocketServer');
sock.onopen = function () {
    console.log('SockJS 连接已建立');
    printLog('SockJS 连接已建立');
};
sock.onmessage = function (e) {
    console.log('SockJS 收到消息：', e.data);
    var msg = 'SockJS 收到消息：' + e.data;
    printLog(msg);
};
sock.onclose = function () {
    console.log('SockJS 连接已关闭');
    printLog('SockJS 连接已关闭');
};
function sendSockJs() {
    var text = $("#text_sockjs").val();
    if (text === "") {
        alert("发送内容不能为空");
        $("#text_sockjs").focus();
    } else {
        sock.send(text);
    }
}
function closeSockJs() {
    sock.close();
}
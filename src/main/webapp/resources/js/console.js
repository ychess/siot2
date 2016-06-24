/* 
 * Copyright 2016 XKT Ltd., Co.
 * Licensed under the Apache License 2.0.
 */

function getRootPath() {
    var curWwwPath = window.document.location.href;
    var pathName = window.document.location.pathname;
    var pos = curWwwPath.indexOf(pathName);
    var localhostPath = curWwwPath.substring(0, pos);
    var projectName = pathName.substring(0, pathName.substr(1).indexOf('/') + 1);
    return(localhostPath + projectName);
}

function startSS() {
    var url = getRootPath() + "/console/ss/start";
    $.ajax({
        type: 'POST',
        url: url,
        data: null,
        dataType: 'text',
        success: function (result) {
            alert(result);
//            location.reload();
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
            alert(result);
//            location.reload();
        }
    });
}

/* WebSocket */
var sock = new SockJS('http://localhost:8080/siot/sockjs/webSocketServer');
sock.onopen = function () {
    console.log('SockJS 连接已建立');
};
sock.onmessage = function (e) {
    console.log('SockJS 收到消息：', e.data);
    var html = '<p>' + e.data + '</p>';
    $("#console_output").append(html);
    if ($("#console_output").children().size() > 21) {
        $("#console_output").children().eq(0).remove();
    }
};
sock.onclose = function () {
    console.log('SockJS 连接已关闭');
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
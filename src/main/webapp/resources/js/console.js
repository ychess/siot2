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

function startCs() {
    var url = getRootPath() + "/console/cs/start";
    $.ajax({
        type: 'POST',
        url: url,
        data: null,
        dataType: 'text',
        success: function (result) {
            alert(result);
            location.reload();
        }
    });
}

function stopCs() {
    var url = getRootPath() + "/console/cs/stop";
    $.ajax({
        type: 'POST',
        url: url,
        data: null,
        dataType: 'text',
        success: function (result) {
            alert(result);
            location.reload();
        }
    });
}


/*
* 获取ajax处理对象
 * @returns {xmlhttp}
 */
function getXHR(){
    var xmlhttp;
    if (window.XMLHttpRequest) {
        xmlhttp = new XMLHttpRequest();
    } else {
        xmlhttp = new ActiveXObject("Microsoft.XMLHTTP");
    }
    return xmlhttp;
}

/*
 *删除评论
 */
function deletecm(component,comm_id){
    var container = component.parentNode.parentNode;
    var url = "/CMDeleServlet?id="+comm_id;
    // 获取ajax
    var xmlhttp = getXHR();
    xmlhttp.onreadystatechange = function() {
        if (xmlhttp.readyState === 4 && xmlhttp.status === 200) {
            // 处理服务器收到的请求响应
            var res = xmlhttp.responseText;
            // 解析json对象
            res = eval('(' + res + ')');
            //alert( res.msg );
            if(res.msg === "success"){
                //删除评论的视图
                var p = container.parentNode;
                p.removeChild(container);
            }
            else if (res.msg === "illegal") {
                alert("删除失败，请先登录");
            }
            else {
                alert("删除失败");
            }
        }
    };
    xmlhttp.open("POST", url, true);
    xmlhttp.send();
}
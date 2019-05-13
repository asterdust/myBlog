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
 *发送给服务器
 */
function sendURL(url){
    // 获取ajax
    var xmlhttp = getXHR();
    xmlhttp.onreadystatechange = function() {
//		if (xmlhttp.readyState == 4 && xmlhttp.status == 200) {
//			//这里可以写 服务器返回解结果后的处理
//		}
    };
    xmlhttp.open("POST", url, true);
    xmlhttp.send();
}
/**
 * 在某个分类中找到指定的一个input
 * @param class_name
 * @param name
 * @returns
 */
function findInputInClass(class_name , name){
    //获取class_name类的 input 所有输入框数组
    var inputs = document.getElementsByClassName(class_name);
    var input;
    //找到值等于 name 的输入框
    for(var i=0 ;i<inputs.length;i++){
        if(inputs[i].value === name){
            input = inputs[i];
            break;
        }
    }
    return input;
}

/**
 * 删除文章
 * @param hod
 * @param article_id
 */
function delete_article(hod , article_id){
    //remove 视图
    var recorder = hod.parentNode.parentNode;
    var recorder_parent = recorder.parentNode;
    recorder_parent.removeChild(recorder);
    //send
    var url = "/data?op=delete_article"+"&&article_id="+article_id;
    sendURL(url);
}

/**
 * 删除sort
 * @param hod
 * @param sort
 */
function delet_sort(hod,sort){

    var input = findInputInClass("sort",sort);

    //remove 视图
    var recorder = input.parentNode.parentNode.parentNode;
    var recorder_parent = recorder.parentNode;
    recorder_parent.removeChild(recorder);
    //后台删除
    var url = "/data?op=sort_delete"+"&&sort="+sort;
    sendURL(url);
}

/**
 * hod 点击的span参数
 * sort 分类名字
 * @param hod
 * @param sort
 */

var input; //保存input
var temp;//保存input的上一个值
function edit_sort(hod,sort){

    if(hod.innerHTML === "编辑"){

        input = findInputInClass("sort",sort);
        //保存input的原始值
        temp = input.value;
        //允许input可以进行编辑
        input.removeAttribute("disabled");

        //实现input的光标定位
        input.value="";
        input.focus();
        input.value=temp;
        //修改hod内容为保存
        hod.innerHTML="保存";
    }else{
        //点击了保存
        hod.innerHTML="编辑";
        input.setAttribute("disabled","disabled");

        //提交修改请求
        var url = "/data?op=sort_update"+"&&old_sort="+temp+"&&new_sort="+input.value ;
        sendURL(url);
    }
}


/**
 * 删除tag
 * @param hod
 * @param tag
 */
function delet_tag(hod,tag){

    var input = findInputInClass("tags",tag);
    //remove 视图
    var recorder = input.parentNode.parentNode.parentNode;
    var recorder_parent = recorder.parentNode;
    recorder_parent.removeChild(recorder);

    //后台删除
    var url = "/data?op=tag_delete"+"&&tag="+tag;
    sendURL(url)
}


/**
 * hod 点击的span参数
 * tag  标签名字
 * @param hod
 * @param sort
 */

var input_t; //保存input
var temp_t; //保存input的上一个值
function edit_tag(hod,tag){

    if(hod.innerHTML === "编辑"){

        input_t = findInputInClass("tags",tag);
        //保存input的原始值
        temp_t = input_t.value;
        //允许input可以进行编辑
        input_t.removeAttribute("disabled");

        //实现input的光标定位
        input_t.value="";
        input_t.focus();
        input_t.value=temp_t;
        //修改hod内容为保存
        hod.innerHTML="保存";
    }else {
        //点击了保存
        hod.innerHTML = "编辑";
        input_t.setAttribute("disabled", "disabled");

        //提交修改请求
        var url = "/data?op=tag_update" + "&&old_tag=" + temp_t + "&&new_tag=" + input_t.value;
        sendURL(url);
    }
}

function update_password(){
    var password = $('#password').value;
    var repassword = $('#repassword').value;
    if (repassword !== password) return;
    var old_password = $('#old_password').value;
    password = sha256(password);
    old_password = sha256(old_password);
    var xmlhttp = getXHR();
    xmlhttp.onreadystatechange = function() {
		if (xmlhttp.readyState === 4 && xmlhttp.status === 200) {
            var res = xmlhttp.responseText;
            if(res.msg.search("success")){
                showNotifFai("修改成功");
            }
            else {
                showNotifFai("修改失败");
            }
		}
    };
    xmlhttp.open("POST", "/data?op=update_password&password=" + password + "&old_password=" + old_password, true);
    xmlhttp.send();
}

function showNotifFai (msg) {
    $.notify({
            icon: 'la la-bell',
            title: '提醒',
            message: msg
        },
        {
            type: 'failure',
            placement: {
                from: "bottom",
                align: "right"
            },
            time: 1000
        });
}

function showNotifSuc (msg) {
    $.notify({
            icon: 'la la-bell',
            title: '提醒',
            message: msg
        },
        {
            type: 'success',
            placement: {
                from: "bottom",
                align: "right"
            },
            time: 1000
        });
}

function showNotifFai () {
    $.notify({
            icon: 'la la-bell',
            title: '提醒',
            message: '文章提交失败'
        },
        {
            type: 'failure',
            placement: {
                from: "bottom",
                align: "right"
            },
            time: 1000
        });
}

function showNotifSuc () {
    $.notify({
            icon: 'la la-bell',
            title: '提醒',
            message: '文章提交成功'
        },
        {
            type: 'success',
            placement: {
                from: "bottom",
                align: "right"
            },
            time: 1000
        });
}
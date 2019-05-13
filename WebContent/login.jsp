<%@ page contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core"  prefix="c" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="zh_CN">
<head>
    <title>登录 - ${title}</title>
    <jsp:include page="part/front.jsp"/>
    <link href="/static/css/login.css" rel="stylesheet">
</head>

<body>
<div class="container" id="background">
    <img class="active" src="/random_images" />
    <img src=""/>
</div>

<div class="container" id="midground">
    <jsp:include page="/part/footer.jsp"/>
</div>

<div class="container" id="frontground">
    <div class="container">
    <div class="row filed" >
        <div class="col-sm-12 col-md-12">
            <h3 class="form-title">${title}</h3>
            <h6 class="form-title">${subtitle}</h6>
            <form  action="/login" method="post" onsubmit="return checkInput()">
                <div class="form-group">
                    <label for="username">用户名</label>
                    <input class="form-control" required type="text" id="username" name="username" maxlength="32"/>
                </div>
                <div class="form-group" >
                    <label for="password">密  码</label>
                    <input class="form-control" required type="password" id="password" name="password" maxlength="64"/>
                </div>
                <div class="form-group">
                    <button type="submit" class="btn" name="submit">登录</button>
                </div>
            </form>
            <div style="display: inline;">
                <img id="play" onclick="swap()" src="/static/img/pause.png" width="25px"/><a onclick="refresh()">下一张图片:<span id="timer">10</span>s</a>
            </div>
        </div>
    </div>
    </div>
</div>
<jsp:include page="part/tail.jsp"/>
<script type="text/javascript" src="/static/js/sha256.min.js"></script>
<script type="text/javascript" src="/static/js/login.js"></script>
</body>
</html>

<%@ page contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core"  prefix="c" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="zh_CN">
<head>
</head>
<body>
<div class="visible-lg visible-md col-md-3">
    <div class="panel panel-default">
        <div class="panel-body">
            <form role="search" action="/search">
                <div class="form-group" style="margin-bottom:0;">
                    <input type="text" name="s" class="form-control" placeholder="使用正则搜索...">
                </div>
            </form>
        </div>
    </div>
    <div class="panel panel-default">
        <div class="panel-body">
            <ul class="list-unstyled">
                <li class="text-center">
                    <img src="${head_img}" height="100" width="100"
                         alt="丢失了我的头像?" class="img-circle"></li>
                <li class="text-center">
                    <h5>${owner_name}</h5>
                </li>
                <li class="text-center">
                    <h6>${description}</h6>
                </li>
            </ul>

        </div>
    </div>
    <div class="panel panel-default">
        <h4 class="vc-widget-title">阅读排行</h4>
        <div class="panel-body">
            <ul class="list-unstyled">

                <c:forEach var="a"  items="${visit_rank}">
                    <li><a href="/article?id=${a.id}">${a.title}</a>
                    </li>
                </c:forEach>
            </ul>
        </div>
    </div>

    <div class="panel panel-default">
        <h4 class="vc-widget-title">标签</h4>
        <div class="panel-body">
            <ul class="list-unstyled">
                <c:forEach var="t"  varStatus="status" items="${tag_list}" >
                    <li>
                            <span class="vc-tags"><a href="/tags?get=${t.tag}">${t.tag}</a>
                            </span>
                    </li>
                </c:forEach>
                </li>
            </ul>
        </div>
    </div>

    <div class="panel panel-default">
        <h4 class="vc-widget-title">友情链接</h4>
        <div class="panel-body">
            <div class="row text-left">
                <a href="https://blog.imiku.moe/" class="col-md-6 vc-a">iMIKU.moe</a>
            </div>
            <div class="row text-left">
                <a href="https://github.com/imvicchen/vTheme" class="col-md-6 vc-a">vTheme</a>
            </div>
        </div>
    </div>
</div>
</body>
</html>
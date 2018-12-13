<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core"  prefix="c" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>管理 - MyBlog</title>

    <!-- Bootstrap core CSS -->
    <link href="https://cdn.bootcss.com/bootstrap/3.3.7/css/bootstrap.min.css" rel="stylesheet">
    <!-- Custom styles for this template -->
    <link type="text/css" rel="stylesheet" href="/static/css/public.css" />
    <link type="text/css" rel="stylesheet" href="/static/css/admin.css" />

    <script charset="utf-8" type="text/javascript" src="/static/js/admin.js"></script>

</head>
<body>
<div class="container">
    <div id="header">
        <div>
            <h2><a href="/index.jsp">MyBlog</a> | <span style="font-size: small;">管理</span></h2>
        </div>
    </div>
</div>

<div class="container">
    <h3 id="t1">管理日志</h3>

    <a style="margin-top: 10px; margin-bottom: 10px;" href="/admin/add.jsp"><span class="glyphicon glyphicon-pencil"></span>新的文章</a>
    <table style="width: 100%;">
        <c:forEach var="a" items="${articles}">
            <tr class="list-group-item row clearfix">
                <td class="col-md-8 column">${a.title}</td>
                <td class="col-md-2 column">${a.time}</td>
                <td class="col-md-1 column">
                    <a href="${pageContext.request.contextPath}/AdminDataServlet?op=edit_article&&article_id=${a.id}">
                        <button class="btn btn-default" style="color:#5bc0de"><span class="glyphicon glyphicon-pencil"></span>编辑</button>
                    </a>
                </td>
                <td class="col-md-1 column">
                    <button class="btn btn-default" style="color:#d9534f" onclick="delete_article(this,'${a.id}')"><span class="glyphicon glyphicon-trash"></span>删除</button>
                </td>
            </tr>
        </c:forEach>
    </table>
</div>

<div class="container">
    <h3 id="t2">管理分类</h3>
    <table style="width: 100%;">
        <c:forEach var="s" items="${sort}">
            <tr class="list-group-item row clearfix">
                <td class="col-md-10 sort column"><input class="tags" disabled value="${s}"></td>
                <td class="col-md-1 column">
                    <button class="btn btn-default" style="color:#5bc0de;" onclick="edit_sort(this,'${s}')"><span class="glyphicon glyphicon-pencil"></span>编辑</button>
                </td>
                <td class="col-md-1 column">
                    <button class="btn btn-default" style="color:#d9534f;" onclick="delet_sort(this,'${s}')"><span class="glyphicon glyphicon-trash"></span>删除</button>
                </td>
            </tr>
        </c:forEach>
    </table>
</div>

<div class="container">
    <h3 id="t3">管理标签</h3>
    <table style="width: 100%;">
        <c:forEach var="t" items="${tags}">
            <tr class="list-group-item row clearfix">
                <td class="col-md-10 sort column"><input class="tags" disabled value="${t.tag}"></td>
                <td class="col-md-1 column">
                    <button class="btn btn-default" style="color:#5bc0de;" onclick="edit_tag(this,'${t.tag}')"><span class="glyphicon glyphicon-pencil"></span>编辑</button>
                </td>
                <td class="col-md-1 column">
                    <button class="btn btn-default" style="color:#d9534f;" onclick="delet_tag(this,'${t.tag}')"><span class="glyphicon glyphicon-trash"></span>删除</button></button>
                </td>
            </tr>
        </c:forEach>
    </table>
</div>

<div class="container">
    <div class="foot_line"></div>
    <div id="footer">
        <a href="/index.jsp">MyBlog</a>
    </div>
</div>

</body>
</html>
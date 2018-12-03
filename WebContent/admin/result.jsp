<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core"  prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>结果 | MyBlog</title>
    <!-- Bootstrap core CSS -->
    <link
            href="https://cdn.bootcss.com/bootstrap/3.3.7/css/bootstrap.min.css"
            rel="stylesheet">

    <!-- 引入页面公共样式 -->
    <link type="text/css" rel="stylesheet" href="/static/css/public.css" />

</head>
<body>
<div class="container">
    <div id="header">
        <div>
            <h2><a href="/index.jsp">MyBlog</a> | <span style="font-size: small;"><a href="/AdminServlet">管理</a> > 结果</span></h2>
        </div>
    </div>
</div>
<div class="container" id="main">
    <div class="row c_center" style="margin:0, auto;">
        <c:choose>
            <c:when test="${article==null}">
                <h2>
                    创建或更新文章失败
                </h2>
                <a href="/AdminServlet">返回主管理页面</a>
            </c:when>
            <c:otherwise>
                <h2>新文章已提交成功</h2>
                <a href="/ArticleServlet?id=${article.id}">马上查看 ${article.title}</a>
                <a href="/AdminServlet">返回主管理页面</a>
            </c:otherwise>
        </c:choose>
    </div>
</div>

<div class="container">
    <div class="foot_line"></div>
    <div id="footer">
        <a href="/index.jsp">MyBlog</a>
    </div>
</div>
</body>
</html>
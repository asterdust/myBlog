<%@ page contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core"  prefix="c" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="zh_CN">
<head>
</head>
<body>
<header class="vc-header">
    <nav class="navbar navbar-default" data-spy="affix" data-offset-top="80">
        <div class="container">
            <div class="navbar-header">
                <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#vmenu">
                    <span class="sr-only">Toggle navigation</span>
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                </button>
                <a class="navbar-brand" href="/">
                    <h1 class="vc-logo" style="background: url(${logo}) no-repeat;">${title}</h1>
                </a>
            </div>

            <div class="collapse navbar-collapse" id="vmenu">
                <ul class="nav navbar-nav navbar-right">
                    <li class="<%if(request.getParameter("current_index").equals("0")) out.print("active");%>">
                        <a href="/">首页</a>
                    </li>
                    <li class="<%if(request.getParameter("current_index").equals("1")) out.print("active");%>">
                        <a href="/SortServlet?get=all">分类</a>
                    </li>
                    <li class="<%if(request.getParameter("current_index").equals("2")) out.print("active");%>">
                        <a href="/AxisServlet">时间轴</a>
                    </li>
                    <li class="<%if(request.getParameter("current_index").equals("3")) out.print("active");%>">
                        <a href="/AboutServlet">关于</a>
                    </li>
                    <li class="dropdown">
                        <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-expanded="false">更多 <span class="caret"></span></a>
                        <ul class="dropdown-menu" role="menu">
                            <c:if test="${sessionScope.user!=null}">
                                <li><a href="/AdminServlet">后台</a>
                                </li>
                                <li class="divider"></li>
                                <li><a href="/LogoutServlet">退出</a>
                                </li>
                            </c:if>
                            <c:if test="${sessionScope.user==null}">
                                <li><a href="/Login">登录</a>
                                </li>
                            </c:if>
                        </ul>
                    </li>
                </ul>
            </div>
        </div>
    </nav>
</header>
</body>
</html>
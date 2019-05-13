<%@ page contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core"  prefix="c" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="zh_CN">
<head>
</head>
<body>
<div class="sidebar">
    <div class="scroll-wrapper scrollbar-inner sidebar-wrapper" style="position: relative;">
        <div class="scrollbar-inner sidebar-wrapper scroll-content" style="height: 750.4px; margin-bottom: 0px; margin-right: 0px; max-height: none;">
            <div class="user">
                <div class="photo">
                    <img src="https://www.gravatar.com/avatar/${user.email_hash}?s=50&amp;r=${rating}">
                </div>
                <div class="info">
                    <a class="" data-toggle="collapse" aria-expanded="true">
                    <span>
                        ${user.user_name}
                            <span class="user-level">Administrator</span>
                    </span>
                    </a>
                </div>
            </div>
            <ul class="nav">
                <li class="nav-item <%if(request.getParameter("current_index").equals("0")) out.print("active");%>">
                    <a href="/admin">
                        <i class="la la-dashboard"></i>
                        <p>Dashboard</p>
                    </a>
                </li>
                <li class="nav-item <%if(request.getParameter("current_index").equals("1")) out.print("active");%>">
                    <a href="/manage?t=article">
                        <i class="la la-newspaper-o"></i>
                        <p>文章</p>
                    </a>
                </li>
                <li class="nav-item <%if(request.getParameter("current_index").equals("2")) out.print("active");%>">
                    <a href="/manage?t=sort">
                        <i class="la la-bar-chart"></i>
                        <p>分类</p>
                    </a>
                </li>
                <li class="nav-item <%if(request.getParameter("current_index").equals("3")) out.print("active");%>">
                    <a href="/manage?t=tag">
                        <i class="la la-th"></i>
                        <p>标签</p>
                    </a>
                </li>
                <%--<li class="nav-item <%if(request.getParameter("current_index").equals("4")) out.print("active");%>">--%>
                <%--<a href="/account">--%>
                <%--<i class="la la-th"></i>--%>
                <%--<p>账号</p>--%>
                <%--</a>--%>
                <%--</li>--%>
            </ul>
        </div>
    </div>
</div>
</body>
</html>
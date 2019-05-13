<%@ page contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core"  prefix="c" %>
<html class="nav_open">
<head>
    <jsp:include page="../part/admin_front.jsp"/>
    <script src="/static/js/admin.js" type="text/javascript"></script>
</head>
<body>

<div class="wrapper">
    <jsp:include page="../part/admin_header.jsp"/>
    <jsp:include page="../part/admin_side.jsp">
        <jsp:param value="1" name="current_index"/>
    </jsp:include>
    <div class="main-panel">
        <div class="content">
            <div class="container-fluid">
                <h4 class="page-title">Dashboard</h4>
                <div class="row">
                    <div class="col-md-12">
                        <div class="card">
                            <div class="card-header">
                                <h4 class="card-title">管理文章</h4>
                            </div>
                            <div class="card-action">
                                <a href="/add" class="btn btn-success">新文章</a>
                            </div>
                            <div class="card-body">
                                <table class="table mt-3">
                                    <thead>
                                    <tr>
                                        <th scope="col">标题</th>
                                        <th scope="col">时间</th>
                                        <th scope="col">操作</th>
                                    </tr>
                                    </thead>
                                    <tbody>
                                    <c:forEach var="a" items="${articles}">
                                        <tr>
                                            <td>${a.title}</td>
                                            <td>${a.time}</td>
                                            <td>
                                                <a class="btn btn-primary" href="/data?op=edit_article&&article_id=${a.id}">编辑</a>
                                                <button class="btn btn-danger" onclick="delete_article(this,'${a.id}')">删除</button>
                                            </td>
                                        </tr>
                                    </c:forEach>
                                    </tbody>
                                </table>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <jsp:include page="../part/admin_footer.jsp"/>
    </div>
</div>
<jsp:include page="../part/admin_tail.jsp"/>

</div>

<c:if test='${state.equals("1")}'>
    <script>showNotifSuc();</script>
</c:if>
<c:if test='${!state.equals("2")}'>
    <script>showNotifFai();</script>
</c:if>
</body>
</html>
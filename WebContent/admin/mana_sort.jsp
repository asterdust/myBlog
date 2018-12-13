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
        <jsp:param value="2" name="current_index"/>
    </jsp:include>
    <div class="main-panel">
        <div class="content">
            <div class="container-fluid">
                <h4 class="page-title">Dashboard</h4>
                <div class="row">
                    <div class="col-md-12">
                        <div class="card">
                            <div class="card-header">
                                <h4 class="card-title">分类</h4>
                                <p class="card-category">编辑博客文章的分类</p>
                            </div>
                            <div class="card-body">
                                <table class="table mt-3">
                                    <thead>
                                    <tr>
                                        <th scope="col">分类名称</th>
                                        <th scope="col"></th>
                                    </tr>
                                    </thead>
                                    <tbody>
                                    <c:forEach var="s" items="${sort}">
                                        <tr>
                                            <td>
                                                <div class="form-group">
                                                    <input type="text" class="sort form-control" disabled value="${s}">
                                                </div>
                                            </td>
                                            <td>
                                                <button class="btn btn-success" onclick="edit_sort(this,'${s}')">编辑</button>
                                                <button class="btn btn-default" onclick="delet_sort(this,'${s}')">删除</button>
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

</body>
</html>
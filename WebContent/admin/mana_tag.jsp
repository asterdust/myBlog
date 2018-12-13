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
        <jsp:param value="3" name="current_index"/>
    </jsp:include>
    <div class="main-panel">
        <div class="content">
            <div class="container-fluid">
                <h4 class="page-title">Dashboard</h4>
                <div class="row">
                    <div class="col-md-12">
                        <div class="card">
                            <div class="card-header">
                                <h4 class="card-title">文章</h4>
                                <p class="card-category">管理文章</p>
                            </div>
                            <div class="card-body">

                                <table class="table mt-3">
                                    <thead>
                                    <tr>
                                        <th scope="col">标签名</th>
                                        <th scope="col"></th>
                                    </tr>
                                    </thead>
                                    <tbody>
                                    <c:forEach var="t" items="${tags}">
                                        <tr>
                                            <td>
                                                <div class="form-group">
                                                    <input type="text" class="tags form-control" disabled value="${t.tag}">
                                                </div>
                                            </td>
                                            <td>
                                                <button class="btn btn-success" onclick="edit_tag(this,'${t.tag}')">编辑</button>
                                                <button class="btn btn-default" onclick="delet_tag(this,'${t.tag}')">删除</button>
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
</body>
</html>
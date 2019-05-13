<%@ page contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core"  prefix="c" %>
<html class="nav_open">
<head>
    <jsp:include page="../part/admin_front.jsp"/>
</head>
<body>

<div class="wrapper">
    <jsp:include page="../part/admin_header.jsp"/>
    <jsp:include page="../part/admin_side.jsp">
        <jsp:param value="4" name="current_index"/>
    </jsp:include>
    <div class="main-panel">
        <div class="content">
            <div class="container-fluid">
                <h4 class="page-title">Dashboard</h4>
                <div class="row">
                    <div class="col-md-12">
                        <div class="card">
                            <div class="card-header">
                                <h4 class="card-title">用户信息</h4>
                            </div>
                            <form action="/data" method="post">
                                <div class="card-body">
                                    <input hidden="hidden" name="op" value="update_user"/>
                                    <div class="form-group">
                                        <label for="email">邮箱</label>
                                        <input type="text" class="form-control" id="email" name="email" value="${sessionScope.user.email}">
                                    </div>
                                </div>
                                <div class="card-action">
                                    <button class="btn btn-success" type="submit">提交</button>
                                </div>
                            </form>
                        </div>
                    </div>
                </div>
                <div class="row">
                    <div class="col-md-12">
                        <div class="card">
                            <div class="card-header">
                                <h4 class="card-title">修改密码</h4>
                            </div>
                            <form>
                                <div class="card-body"><div class="form-group">
                                    <label for="old_password">当前密码</label>
                                    <input type="password" class="form-control" id="old_password" name="old_password" value="">
                                </div>
                                    <div class="form-group">
                                        <label for="password">密码</label>
                                        <input type="password" class="form-control" id="password" name="password" value="">
                                    </div>
                                    <div class="form-group">
                                        <label for="repassword">重复新密码</label>
                                        <input type="password" class="form-control" id="repassword" name="repassword" value="">
                                    </div>
                                </div>
                                <div class="card-action">
                                    <button class="btn btn-success" type="button" onclick="update_password();">提交</button>
                                </div>
                            </form>
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

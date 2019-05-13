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
        <jsp:param value="0" name="current_index"/>
    </jsp:include>
    <div class="main-panel">
        <div class="content">
            <div class="container-fluid">
                <h4 class="page-title">Dashboard</h4>
                <div class="row">
                    <div class="col-md-3">
                        <div class="card card-stats card-warning">
                            <div class="card-body ">
                                <div class="row">
                                    <div class="col-5">
                                        <div class="icon-big text-center">
                                            <i class="la la-users"></i>
                                        </div>
                                    </div>
                                    <div class="col-7 d-flex align-items-center">
                                        <div class="numbers">
                                            <p class="card-category">访问</p>
                                            <h4 class="card-title">${visited}/${member}</h4>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="col-md-3">
                        <div class="card card-stats card-success">
                            <div class="card-body ">
                                <div class="row">
                                    <div class="col-5">
                                        <div class="icon-big text-center">
                                            <i class="la la-newspaper-o"></i>
                                        </div>
                                    </div>
                                    <div class="col-7 d-flex align-items-center">
                                        <div class="numbers">
                                            <a class="card-category" href="/manage?t=article">文章</a>
                                            <h4 class="card-title">${article_count}</h4>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="col-md-3">
                        <div class="card card-stats card-danger">
                            <div class="card-body">
                                <div class="row">
                                    <div class="col-5">
                                        <div class="icon-big text-center">
                                            <i class="la la-bar-chart"></i>
                                        </div>
                                    </div>
                                    <div class="col-7 d-flex align-items-center">
                                        <div class="numbers">
                                            <a class="card-category" href="/manage?t=sort">分类</a>
                                            <h4 class="card-title">${sort_count}</h4>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="col-md-3">
                        <div class="card card-stats card-primary">
                            <div class="card-body ">
                                <div class="row">
                                    <div class="col-5">
                                        <div class="icon-big text-center">
                                            <i class="la la-th"></i>
                                        </div>
                                    </div>
                                    <div class="col-7 d-flex align-items-center">
                                        <div class="numbers">
                                            <a class="card-category" href="/manage?t=tag">标签</a>
                                            <h4 class="card-title">${tag_count}</h4>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
                <div class="row">
                    <div class="col-md-12">
                        <div class="card">
                            <div class="card-header">
                                <h4 class="card-title">设置</h4>
                                <p class="card-category">修改博客设置</p>
                            </div>
                            <form action="/data" method="post">
                                <div class="card-body">
                                    <input hidden="hidden" name="op" value="update_config"/>
                                    <c:forEach items="${des_map}" var="entry">
                                        <div class="form-group">
                                            <label for="${entry.key}">${entry.value[0]}</label>
                                            <input type="text" class="form-control" id="${entry.key}" name="${entry.key}" value="${entry.value[2]}">
                                            <c:if test='${!entry.value[1].equals("")}'>
                                                <small id="emailHelp" class="form-text text-muted">${entry.value[1]}</small>
                                            </c:if>
                                        </div>
                                    </c:forEach>
                                </div>
                                <div class="card-action">
                                    <button class="btn btn-success" type="submit">提交</button>
                                    <button class="btn btn-danger" type="reset">取消</button>
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

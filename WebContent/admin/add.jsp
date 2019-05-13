<%@ page contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core"  prefix="c" %>
<html class="nav_open">
<head>
    <jsp:include page="../part/admin_front.jsp"/>

    <link rel="stylesheet" href="/editormd/css/editormd.css" />

    <script src="/editormd/js/zepto.min.js"></script>
    <script src="/editormd/js/editormd.js"></script>
    <script src="/static/js/add.js"></script>
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
                        <form action="/new_article" method="post">
                            <div class="card">
                                <div class="card-header">
                                    <h4 class="card-title">修改文章</h4>
                                </div>
                                <div class="card-body">
                                    <div class="form-group">
                                        <label for="title">标题</label>
                                        <input id="title" type="text" class="form-control" name="title" value="">
                                    </div>
                                    <div class="form-group">
                                        <label for="time">时间</label>
                                        <input id="time" type="text" class="form-control" name="time" value="">
                                    </div>
                                    <div class="form-group">
                                        <label for="author">作者</label>
                                        <input id="author" type="text" class="form-control" name="author" value="">
                                    </div>
                                    <div class="form-group">
                                        <label>分类</label>
                                        <c:forEach var="s"  items="${sort_count}">
                                            <span class="badge badge-info" value="${s.key}" onclick="sort_click(this)"></span>
                                        </c:forEach>
                                        <input id="sort" type="text" class="form-control" name="sort" value="">
                                    </div>
                                    <div class="form-group">
                                        <label>标签</label>
                                        <c:forEach var="tag" items="${all_tag}">
                                            <span class="badge badge-default" value="${tag.tag}" onclick="tags_click(this)"></span>
                                        </c:forEach>
                                        <input type="text" class="form-control" id="tags" name="tags" >
                                    </div>
                                </div>
                            </div>

                            <div class="card">
                                <div class="card-header">
                                    <h4 class="card-title">修改文章</h4>
                                </div>
                                <div class="card-body">
                                    <div class="editormd" id="mdView">
                                        <textarea name="content"></textarea>
                                    </div>
                                </div>
                                <div class="card-action">
                                    <button class="btn btn-success" type="submit">提交</button>
                                    <a class="btn btn-danger" href="/ManageServlet?t=article">取消</a>
                                </div>
                            </div>
                        </form>
                    </div>
                </div>
            </div>
        </div>
        <jsp:include page="../part/admin_footer.jsp"/>
    </div>
</div>
<jsp:include page="../part/admin_tail.jsp"/>
<script type="text/javascript">
    var testEditor;
    var jQuery = Zepto;
    $(function() {
        testEditor = editormd("mdView", {
            width  : "90%",
            height : 720,
            path   : '/editormd/lib/',
            codeFold : true,
            searchReplace : true,
            saveHTMLToTextarea : true,    // 保存 HTML 到 Textarea
            htmlDecode : "style,script,iframe|on*", // 开启 HTML 标签解析，为了安全性，默认不开启
            emoji : true,
            taskList : true,
            tocm: true,
            tex : true,
            flowChart : true,
            sequenceDiagram : true,
            imageUpload : true,
            imageFormats : ["jpg", "jpeg", "gif", "png", "bmp", "webp"],
            imageUploadURL : "/Blog/UploadPic",
            //后台只需要返回一个 JSON 数据
            onload : function() {
                //console.log("onload =>", this, this.id, this.settings);
            }
        });
        editor.setToolbarAutoFixed(false);//工具栏自动固定定位的开启与禁用
    });
    var myDate = new Date();
    $('#time').val(myDate.toLocaleString('chinese', { hour12: false }));
</script>
</body>
</html>

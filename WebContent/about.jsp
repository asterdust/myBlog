<%@ page contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core"  prefix="c" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="zh_CN">
<head>
    <title>关于 - ${title}</title>
    <jsp:include page="part/front.jsp"/>
    <style>
       .panel > .container {
            margin-top: 50px;
            margin-bottom: 50px;
        }
    </style>
</head>

<body>
<jsp:include page="part/header.jsp">
    <jsp:param value="3" name="current_index"/>
</jsp:include>

<div class="container vc-container">
    <div class="row">
        <div class="col-sm-12 col-md-9">
            <div class="panel panel-default">
                <div class="container">
                    <div class="row clearfix">
                        <div class="col-md-8 column text-center">
                            <dl>
                                <dt>
                                    <h5>感谢以下开源项目或工具的支持</h5>
                                </dt>
                                <dd>
                                    Gravatar: 全球公认的头像 - <a href="http://cn.gravatar.com/site/implement/">官网</a>
                                </dd>
                                <dd>
                                    editor.md: markdown 编辑与显示工具 - <a href="https://github.com/pandao/editor.md">github</a>
                                </dd>
                                <dd>
                                    菜鸟可视化编辑器: 布局构成 - <a href="http://www.runoob.com/try/bootstrap/layoutit/">官网</a>
                                </dd>
                                <dd>
                                    let's encrypt: SSL证书 - <a href="https://letsencrypt.org/">官网</a>
                                </dd>
                                <dd>
                                    sha256.js: sha256加密 - <a href="https://github.com/emn178/js-sha256">github</a>
                                </dd>
                            </dl>
                        </div>
                    </div>
                </div>

                <div class="container">
                    <div class="row clearfix">
                        <div class="col-md-8 column text-center">
                            <h5>
                                MLQS team & iMIKU.moe 版权所有
                            </h5>
                            <p>基于 MIT 协议开源于</p>
                            <p>
                                Github: <a href="https://github.com/asterdust/myBlog">https://github.com/asterdust/myBlog</a>
                            </p>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <jsp:include page="part/sideinfo.jsp"/>
    </div>
</div>
<jsp:include page="part/footer.jsp"/>
<jsp:include page="part/tail.jsp"/>
</body>

</html>
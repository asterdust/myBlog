<%@ page contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core"  prefix="c" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="zh_CN">
<head>
    <title>关于 - ${title}</title>
    <jsp:include page="part/front.jsp"/>
</head>

<body>
<jsp:include page="part/header.jsp"/>

<div class="container vc-container">
    <div class="row">
        <div class="col-sm-12 col-md-9">
            <div class="panel panel-default">
                <div class="container">
                    <div class="row c_center">
                        <div class="col-md-12 column">
                            <h5>
                                关于开发者们
                            </h5>
                        </div>
                    </div>
                </div>
                <div class="container">
                    <div class="row clearfix">
                        <div class="col-md-3 column"></div>
                        <div class="col-md-2 column">
                            <dl>
                                <dt>
                                    姓名
                                </dt>
                                <dd>
                                    XXX
                                </dd>
                                <dt>
                                    职位
                                </dt>
                                <dd>
                                    XXX
                                </dd>
                            </dl>
                        </div>
                        <div class="col-md-2 column">
                            <dl>
                                <dt>
                                    姓名
                                </dt>
                                <dd>
                                    XXX
                                </dd>
                                <dt>
                                    职位
                                </dt>
                                <dd>
                                    XXX
                                </dd>
                            </dl>
                        </div>
                        <div class="col-md-2 column">
                            <dl>
                                <dt>
                                    姓名
                                </dt>
                                <dd>
                                    XXX
                                </dd>
                                <dt>
                                    职位
                                </dt>
                                <dd>
                                    XXX
                                </dd>
                            </dl>
                        </div>
                    </div>
                </div>
                <div class="container">
                    <div class="row clearfix">
                        <div class="col-md-4 column">
                            <dl>
                                <dt>
                                    感谢以下开源项目或工具的支持
                                </dt>
                                <dd>
                                    javaee-blog: <a href="https://github.com/Lemonreds/javaee-blog">github</a>
                                </dd>
                                <dd>
                                    Gravatar: <a href="http://cn.gravatar.com/site/implement/">官网</a>
                                </dd>
                                <dd>
                                    editor.md: <a href="https://github.com/pandao/editor.md">github</a>
                                </dd>
                                <dd>
                                    菜鸟可视化编辑器: <a href="http://www.runoob.com/try/bootstrap/layoutit/">官网</a>
                                </dd>
                            </dl>
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
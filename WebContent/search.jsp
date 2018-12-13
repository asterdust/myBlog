<%@ page contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core"  prefix="c" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="zh_CN">
<head>
    <title>搜索 - ${title}</title>
    <jsp:include page="part/front.jsp"/>
</head>

<body>
<jsp:include page="part/header.jsp">
    <jsp:param value="-1" name="current_index"/>
</jsp:include>

<div class="container vc-container">
    <div class="row">
        <div class="col-sm-12 col-md-9">
            <div class="well well-sm text-center">
                搜索关键字：胡同
            </div>
            <div class="thumbnail">
                <div class="caption">
                    <h1 class="vc-page-title"><a href="">胡同里的小太子</a></h1>
                    <p class="vc-author-info">
                        <time>2015-04-28</time> &bull; <span>Vic Chen</span> &bull; <span><a href="">27条评论</a></span>
                    </p>
                    <p class="hidden-xs">它是胡同里的常客，偶尔静静的在那里等待主人，偶尔惊扰周末睡懒觉的邻居，其实都没什么大不了，大家已经习...</p>
                    <p class="clearfix">
                        <a class="hidden-xs pull-right vc-more-link" href="" role="button">继续阅读 &raquo;</a>
                        <span class="vc-tags">
                                <a href="">我是标签</a>
                                <a href="">我是标签2</a>
                                <a href="">我是标签3</a>
                            </span>
                    </p>
                </div>
            </div>

            <div class="thumbnail">
                <div class="caption">
                    <h1 class="vc-page-title"><a href="">重游潘家园，会老友</a></h1>
                    <p class="vc-author-info">
                        <time>2015-04-28</time> &bull; <span>Vic Chen</span> &bull; <span><a href="">27条评论</a></span>
                    </p>
                    <p class="hidden-xs">潘家园旧货市场位于北京东三环南路潘家园桥西南，占地4.85万平方米。市场分为地摊区、古建房区、古典家具区...</p>
                    <p class="clearfix">
                        <a class="hidden-xs pull-right vc-more-link" href="" role="button">继续阅读 &raquo;</a>
                        <span class="vc-tags">
                                <a href="">我是标签</a>
                                <a href="">我是标签2</a>
                                <a href="">我是标签3</a>
                            </span>
                    </p>
                </div>
            </div>
            <nav>
                <ul class="pager">
                    <li class="previous"><a href="#"><i class="fa fa-angle-left"></i> Older</a>
                    </li>
                    <li class="next"><a href="#">Newer <i class="fa fa-angle-right"></i></a>
                    </li>
                </ul>
            </nav>
        </div>
        <jsp:include page="part/sideinfo.jsp"/>
    </div>
</div>
<jsp:include page="part/footer.jsp"/>
<jsp:include page="part/tail.jsp"/>
</body>

</html>
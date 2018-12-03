<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>分类 | MyBlog</title>
    <link href="https://cdn.bootcss.com/bootstrap/3.3.7/css/bootstrap.min.css" rel="stylesheet">
    <link type="text/css" rel="stylesheet" href="/static/css/public.css" />
    <link type="text/css" rel="stylesheet" href="/static/css/main.css" />
    <link type="text/css" rel="stylesheet" href="/static/css/sort.css" />

    <script type="text/javascript" src="/static/js/jquery-1.11.1.min.js"></script>
    <script type="text/javascript" src="/static/js/toggle.js"></script>

</head>

<body>
<div class="head_line"></div>

<div class="container" id="main">

    <div id="header"></div>

    <div class="row c_center">
        <div class="col-md-3" id="left_content">

            <div id="title">
                <h2><a href="/login.html">MyBlog</a></h2>
                <h5 class="text-muted">${subtitle}</h5>
            </div>

            <div class="c_center" id="person_info">
                <img src="${head_img}" height="130" width="130"
                     alt="丢失了我的头像?" class="img-circle">
                <h4 class="text-muted">${name}</h4>
                <h5 class="text-muted">${sign}</h5>
            </div>

            <div id="list">
                <table class="table table-hover c_center">
                    <tr>
                        <td><a href="/index.jsp"><span class="glyphicon glyphicon-home"></span>
                            首页</a></td>
                    </tr>
                    <tr class="active">
                        <td><a href="/SortServlet?get=all"><span class="glyphicon glyphicon-list"></span>
                            分类</a></td>
                    </tr>
                    <tr>
                        <td><a href="/TagsServlet?get=all"><span class="glyphicon glyphicon-tags"></span>
                            标签</a></td>
                    </tr>
                    <tr>
                        <td><a href="/AxisServlet"><span class="glyphicon glyphicon-book"></span>
                            时间轴</a></td>
                    </tr>
                    <tr>
                        <td><a href="/about.html"><span class="glyphicon glyphicon-user"></span>
                            关于</a></td>
                    </tr>


                    <!-- admin here -->
                    <c:if test="${sessionScope.user!=null}">

                        <tr>
                            <td><a href="/AdminServlet"><span class="glyphicon glyphicon-user"></span>
                                管理</a></td>
                        </tr>
                    </c:if>
                    <!--  -->
                </table>
            </div>
        </div>
        <div class="col-md-2" id="center_content">
        </div>

        <div  class="col-md-7 " id="right_Content">
            <br />
            <br />
            <div class="list-group">
                <a href="#" class="list-group-item active">分类</a>
                <!-- 这里初始化列表 -->
                <c:forEach var="map" items="${sort_article_map}" varStatus="i">
                    <div class="sort_name">
                        <!-- 分类名字 -->
                        <span id="title-${i.index}" class="toggle-button glyphicon glyphicon-triangle-bottom"></span>
                        &nbsp;&nbsp;${map.key}
                    </div>

                    <div>
                        <!-- 分类信息 -->
                        <ul class="list-group" id="list-${i.index}">

                            <c:forEach var="list" items="${map.value}">
                                <li class="list-group-item">
                                    <div>
                                        <div>
                                            <a href="/ArticleServlet?id=${list.id}">${list.title}</a>
                                        </div>
                                        <div class="c_right">
                                            <img class="glyphicon" src="/static/img/time.png">
                                                ${list.time}
                                            &nbsp;
                                            <span class="visit"><img class="glyphicon" src="/static/img/visit.png">
									${list.visit}
                                            </span>
                                        </div>
                                    </div>
                                </li>
                            </c:forEach>
                        </ul>
                    </div>

                </c:forEach>
            </div>
        </div>
    </div>
</div>

<div class="container">
    <div class="foot_line"></div>
    <div id="footer">
        <a href="/index.jsp">MyBlog</a>
    </div>
</div>
</body>
</html>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>时间轴 | MyBlog</title>

    <link href="https://cdn.bootcss.com/bootstrap/3.3.7/css/bootstrap.min.css" rel="stylesheet">

    <link type="text/css" rel="stylesheet" href="/static/css/public.css" />
    <link type="text/css" rel="stylesheet" href="/static/css/main.css" />
    <link type="text/css" rel="stylesheet" href="/static/css/axis.css" />

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
                    <tr>
                        <td><a href="/SortServlet?get=all"><span class="glyphicon glyphicon-list"></span>
                            分类</a></td>
                    </tr>
                    <tr>
                        <td><a href="/TagsServlet?get=all"><span class="glyphicon glyphicon-tags"></span>
                            标签</a></td>
                    </tr>
                    <tr class="active">
                        <td><a href="/AxisServlet"><span class="glyphicon glyphicon-book"></span>
                            时间轴</a></td>
                    </tr>
                    <tr>
                        <td><a href="/about.html"><span class="glyphicon glyphicon-user"></span>
                            关于</a></td>
                    </tr>
                    <c:if test="${sessionScope.user!=null}">

                        <tr>
                            <td><a href="/AdminServlet"><span class="glyphicon glyphicon-user"></span>
                                管理</a></td>
                        </tr>
                    </c:if>
                </table>
            </div>
        </div>
        <div class="col-md-2" id="center_content">
        </div>

        <div  class="col-md-7 " id="right_Content axis_div">
            <c:forEach var="axis" varStatus="status" items="${axis_list}">
                <c:choose>
                    <c:when test="${axis.id==0}">
                        <div class="longline_div">
                            <div>${axis.year}</div>
                        </div>
                    </c:when>
                    <c:otherwise>
                        <div class="line_div">
                            <div>
                                <span class="time-tag">${axis.month}-${axis.day}</span>
                                <a href="/ArticleServlet?id=${axis.id}">${axis.title}
                                </a>
                            </div>
                        </div>
                    </c:otherwise>
                </c:choose>
            </c:forEach>
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
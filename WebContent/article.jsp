<%@ page contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core"  prefix="c" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>${article.title} | MyBlog</title>
    <!-- Bootstrap core CSS -->
    <link href="https://cdn.bootcss.com/bootstrap/3.3.7/css/bootstrap.min.css" rel="stylesheet">

    <link type="text/css" rel="stylesheet" href="/static/css/public.css" />
    <link type="text/css" rel="stylesheet" href="/static/css/article.css?v=1" />

    <script type="text/javascript" charset="UTF-8" src="/static/js/article.js"></script>

</head>
<body>

<div class="container">
    <div id="header">
        <div>
            <h2><a href="/index.jsp">MyBlog</a> | <span style="font-size: small;">文章</span></h2>
        </div>
    </div>
</div>

<div class="container">
    <div class="row">
        <div class="col-md-12 column">
            <h3>
                ${article.title}
            </h3>
            <div class="line"></div>
            <div class="row">
                <div class="col-md-4 column">
                </div>
                <div class="col-md-4 column">
                    <span>${article.time}</span>
                    &nbsp;
                    <a href="/SortServlet?get=${article.sort}">${article.sort}</a>
                    &nbsp;
                    ${article.author}
                </div>
                <div class="col-md-4 column">
                    <span class="glyphicon glyphicon-eye-open"></span>
                    <span>${article.visit}</span>
                    <span class="glyphicon glyphicon-heart" id="love"></span>
                    <span>${article.star}</span>
                    <span class="glyphicon glyphicon-comment"></span>
                    <span>${article.comment}</span>
                </div>
            </div>

            <div class="row">
                <div class="col-md-12 column">
                    <c:forEach var="t" items="${article_tags}">
                        <a href="/TagsServlet?get=${t.tag}">${t.tag}&nbsp;</a>
                    </c:forEach>
                </div>
            </div>
            <%--<iframe src="/show.jsp" frameborder="0"></iframe>--%>
        </div>

        <div class="container">
            <div id="aaaaaaaaa">

                <%--<iframe id="a_content" src="/show.jsp" frameborder="0"></iframe>--%>
                <%--<script>--%>
                <%--var _iframe = document.getElementById("a_content");--%>
                <%--if (_iframe.attachEvent) {--%>
                <%--_iframe.attachEvent("onload", function() {--%>
                <%--_iframe.contentDocument.documentElement.getElementsByTagName("textarea").item(0).setAttribute("value", "${article.content_safe}");--%>
                <%--_iframe.contentDocument.documentElement.getElementsByTagName("button").item(0).click();--%>
                <%--});--%>
                <%--} else {--%>
                <%--_iframe.onload = function() {--%>
                <%--_iframe.contentDocument.documentElement.getElementsByTagName("textarea").item(0).setAttribute("value", "${article.content_safe}");--%>
                <%--_iframe.contentDocument.documentElement.getElementsByTagName("button").item(0).click();--%>
                <%--};--%>
                <%--}--%>
                <%--</script>--%>
                <jsp:include page="/show.jsp"/>
            </div>
        </div>

        <div class="container">
            <div class="row">
                <div class="col-md-4 column">
                    <span class="glyphicon glyphicon-chevron-left"></span>
                    <c:choose>
                        <c:when test="${article_pre!=null}">
                            <a href="/ArticleServlet?id=${article_pre.id}">&nbsp;上一篇:${article_pre.title}</a>
                        </c:when>
                        <c:otherwise>
                            &nbsp;没有更早的文章了
                        </c:otherwise>
                    </c:choose>
                </div>
                <div class="col-md-4 column">
                    <div>
                        <span class="btn btn-default" style="color:#d9534f;"  onclick="love_article(${article.id})" >点赞</span>
                    </div>
                </div>
                <div class="col-md-4 column">
                    <c:choose>
                        <c:when test="${article_next!=null}">
                            <a href="/ArticleServlet?id=${article_next.id}">下一篇:&nbsp;${article_next.title}</a>
                        </c:when>
                        <c:otherwise>
                            &nbsp;没有更多的文章了
                        </c:otherwise>
                    </c:choose>
                    <span class="glyphicon glyphicon-chevron-right"></span>
                </div>
            </div>
        </div>
        <div class="container">
            <div class="line"></div>
            <c:if test="${comment != null}">
                <c:forEach var="comm" varStatus="status" items="${comment}">
                    <div class="row">
                        <div class="col-md-1 column">
                            <img class="img-circle" src="https://www.gravatar.com/avatar/${comm.email_hash}?s=50&r=pg" />
                        </div>

                        <div class="col-md-2 column" style="text-align: left">
                            <span style="color: #428bca">${comm.nickname}</span>
                            <br>
                            <small>${comm.email}<br>${comm.time}</small>
                        </div>
                    </div>
                    <div class="row">
                        <div class="col-md-1 column"></div>
                        <div class="col-md-8 column">
                            <div>
                                <pre class="comment">${comm.content}</pre>
                            </div>
                        </div>
                        <div class="col-md-2 column">
                            <a><span class="glyphicon glyphicon-thumbs-up"  onclick="star(this,${comm.id})">&nbsp;${comm.star}</span></a>
                            <a><span class="glyphicon glyphicon-thumbs-down" onclick="diss(this,${comm.id})">&nbsp;${comm.diss}</span></a>
                        </div>
                        <div class="col-md-1 column">
                            <c:if test="${sessionScope.user!=null}">
                                <span class="btn btn-default" style="color:red;" onclick="deletecm(this,${comm.id})">删除</span>
                                &nbsp;
                            </c:if>
                        </div>
                    </div>
                    <div class="line"></div>
                </c:forEach>
            </c:if>
        </div>
    </div>


    <!-- 写评论 -->

    <div class="container">

        <form action="/NewCommentServlet?id=${article.id}" method="post">
            <div class="form-group">
                <label for="w_nickname" class>昵称：</label>
                <input  style="width:30%" required class="form-control" type="text" id="w_nickname" name="w_nickname" value="网络喷子">
            </div>
            <div class="form-group">
                <label for="w_email" class>邮箱：</label>
                <input  style="width:30%" required class="form-control" type="text" id="w_email" name="w_email" placeholder="example@example.com"  >
            </div>
            <div class="form-group">
                <textarea style="resize:none; width:100%; height:180px;" required name="w_content"></textarea>
            </div>
            <div class="form-group" style="text-align: center">
                <input class="btn btn-default" type="submit" value="评论" onclick="onclick"/>
            </div>
        </form>
    </div>
</div>

<div class="container">
    <div id="footer">
        <div class="foot_line"></div>
        <a href="/index.jsp">MyBlog</a>
    </div>
</div>
</body>
</html>
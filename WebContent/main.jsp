<%@ page contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core"  prefix="c" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="zh_CN">
<head>
    <title>${title}</title>
    <jsp:include page="part/front.jsp"/>
</head>

<body>
<jsp:include page="part/header.jsp">
    <jsp:param value="0" name="current_index"/>
</jsp:include>

<div class="container vc-container">
    <div class="row">
        <div class="col-sm-12 col-md-9">
            <c:forEach var="article" items="${article_list}" varStatus="i">
                <div class="thumbnail">
                    <div class="vc-pagelist-img">
                        <a href="/ArticleServlet?id=${article.id}"><img src="${header_img_list.get(i.index)}" alt="demo image">
                        </a>
                    </div>
                    <div class="caption">
                        <h1 class="vc-page-title"><a href="/ArticleServlet?id=${article.id}">${article.title}</a></h1>
                        <p class="vc-author-info">
                            <time>${article.time}</time> &bull; <span>${article.sort}</span> &bull; <span>${article.visit}次浏览</span>
                        </p>
                        <p class="hidden-xs">${article.content.replaceAll("< ?img.*>", "")}</p>
                        <p class="clearfix">
                            <a class="hidden-xs pull-right vc-more-link" href="/ArticleServlet?id=${article.id}" role="button">继续阅读 &raquo;</a>
                            <a href="">${article.sort}</a>
                        </p>
                    </div>
                </div>
            </c:forEach>
        </div>
        <jsp:include page="part/sideinfo.jsp"/>
    </div>
</div>
<jsp:include page="part/footer.jsp"/>
<jsp:include page="part/tail.jsp"/>
</body>

</html>
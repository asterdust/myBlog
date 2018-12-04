<%@ page contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core"  prefix="c" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="zh_CN">
<head>
    <title>${article.title} - ${title}</title>
    <jsp:include page="part/front.jsp"/>
</head>

<body>
<jsp:include page="part/header.jsp"/>

<div class="container vc-container">
    <div class="row">
        <div class="col-sm-12 col-md-9">

            <c:forEach var="map" items="${id_tag_map}" varStatus="i">
                <div class="tags_name">
                    <!-- 标签名字 -->
                    <span id="title-${i.index}" class="toggle-button glyphicon glyphicon-triangle-bottom"></span>${map.key}
                </div>
                <div>
                    <!-- 标签信息 -->
                    <ul class="list-group" id="list-${i.index}">
                        <c:forEach var="list" items="${map.value}">
                            <li class="list-group-item">
                                <div>
                                    <div>
                                        <a href="./ArticleServlet?id=${list.id}">${list.title}</a>
                                    </div>
                                    <div class="clearfix pull-right">
                                        <time>${list.time}</time> &bull; <span>${list.visit}次浏览</span>
                                    </div>
                                </div>
                            </li>
                        </c:forEach>
                    </ul>
                </div>
            </c:forEach>

        </div>
        <jsp:include page="part/sideinfo.jsp"/>
    </div>
</div>
<jsp:include page="part/footer.jsp"/>
<jsp:include page="part/tail.jsp"/>
<script type="text/javascript" src="./static/js/toggle.js"></script>
</body>

</html>
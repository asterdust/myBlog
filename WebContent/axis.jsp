<%@ page contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core"  prefix="c" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="zh_CN">
<head>
    <title>时间轴 - ${title}</title>
    <jsp:include page="part/front.jsp"/>
    <link href="/static/css/axis.css" rel="stylesheet" type="text/css" />
</head>

<body>
<jsp:include page="part/header.jsp">
    <jsp:param value="2" name="current_index"/>
</jsp:include>

<div class="container vc-container">
    <div class="row">
        <div class="col-sm-12 col-md-9">
            <c:forEach var="axis" varStatus="status" items="${axis_list}">
                <c:choose>
                    <c:when test="${axis.id==0}">
                        <div class="longline_div">
                            <h4>${axis.year}</h4>
                        </div>
                    </c:when>
                    <c:otherwise>
                        <div class="line_div">
                            <div>
                                <span class="time-tag">${axis.month}-${axis.day}</span>
                                <a href="/article?id=${axis.id}">${axis.title}
                                </a>
                            </div>
                        </div>
                    </c:otherwise>
                </c:choose>
            </c:forEach>
        </div>
        <jsp:include page="part/sideinfo.jsp"/>
    </div>
</div>
<jsp:include page="part/footer.jsp"/>
<jsp:include page="part/tail.jsp"/>
</body>

</html>
<%@ page contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core"  prefix="c" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="zh_CN">
<head>
    <title>提示 - ${title}</title>
    <jsp:include page="part/front.jsp"/>
</head>

<body>
<jsp:include page="part/header.jsp">
    <jsp:param value="-1" name="current_index"/>
</jsp:include>

<div class="container vc-container">
    <div class="row">
        <div class="col-sm-12 col-md-9">
            <div class="well well-sm text-center" style="background: ${back_color}">${message}</div>
        </div>
        <jsp:include page="part/sideinfo.jsp"/>
    </div>
</div>
<jsp:include page="part/footer.jsp"/>
<jsp:include page="part/tail.jsp"/>
</body>

</html>
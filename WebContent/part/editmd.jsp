<%@ page language="java" contentType="text/html; charset=UTF-8"   pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Md2Html</title>
    <!-- 这个文件用来转换 Markdown 到Html 显示 -->
</head>
<!-- preview的css -->
<%--<link rel="stylesheet" href="/editormd/css/style.css" />--%>
<%--<link rel="stylesheet" href="/editormd/css/editormd.preview.css" />--%>

<!-- 引入editormd相关 -->
<script type="text/javascript" charset="UTF-8" src="/editormd/js/zepto.min.js"></script>
<script type="text/javascript" charset="UTF-8" src="/editormd/js/editormd.js"></script>
<script type="text/javascript" charset="UTF-8" src="/editormd/js/jquery.min.js"></script>
<script type="text/javascript" charset="UTF-8" src="/editormd/lib/marked.min.js"></script>
<script type="text/javascript" charset="UTF-8" src="/editormd/lib/prettify.min.js"></script>
<script type="text/javascript" charset="UTF-8" src="/editormd/lib/raphael.min.js"></script>
<script type="text/javascript" charset="UTF-8" src="/editormd/lib/underscore.min.js"></script>
<script type="text/javascript" charset="UTF-8" src="/editormd/lib/sequence-diagram.min.js"></script>
<script type="text/javascript" charset="UTF-8" src="/editormd/lib/flowchart.min.js"></script>
<script type="text/javascript" charset="UTF-8" src="/editormd/lib/jquery.flowchart.min.js"></script>
<script type="text/javascript" charset="UTF-8" src="/editormd/editormd.js"></script>

<body>
<div id="mdView">
    <textarea id="article_content">${article.content}</textarea>
</div>
<br/>
<script type="text/javascript">
    function mdToHtml() {
        editormd.markdownToHTML("mdView", {
            htmlDecode : "style,script,iframe",
            emoji : true,
            taskList : true,
            tex : true,
            flowChart : true,
            sequenceDiagram : true
        });
    }
    mdToHtml();
</script>

</body>
</html>
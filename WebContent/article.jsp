<%@ page contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core"  prefix="c" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="zh_CN">
<head>
    <title>${article.title} - ${title}</title>
    <jsp:include page="part/front.jsp"/>
</head>

<body>
<jsp:include page="part/header.jsp">
    <jsp:param value="-1" name="current_index"/>
</jsp:include>

<div class="container vc-container">
    <div class="row">
        <div class="col-sm-12 col-md-9">
            <!-- CODE START -->
            <div class="panel panel-default">
                <article class="vc-blog">
                    <h1 class="vc-blog-title">${article.title}</h1>
                    <p class="vc-author-info">
                        <time>${article.time}</time> &bull; <span>${article.author}</span> &bull; <span>${article.visit}次浏览</span>
                    </p>
                    <div class="vc-blog-content">
                        <img src="${header_img}" alt="">
                        <jsp:include page="/part/editmd.jsp"/>
                    </div>
                    <span class="vc-tags">
                        <c:forEach var="t" items="${article_tags}">
                            <a href="/TagsServlet?get=${t.tag}">${t.tag}</a>
                        </c:forEach>
                    </span>
                    <%--<div class="vc-copyright">--%>
                    <%--本站文章除注明转载外，均为原创文章。转载请注明：文章转载自：VICCHEN.ME（<a href="http://vicchen.me/">http://vicchen.me/</a>）--%>
                    <%--</div>--%>
                </article>
            </div>
            <nav>
                <ul class="pager">
                    <li class="previous">
                        <c:choose>
                            <c:when test="${article_pre!=null}">
                                <a href="/ArticleServlet?id=${article_pre.id}"><i class="fa fa-angle-left"></i> 上一篇:${article_pre.title}</a>
                            </c:when>
                            <c:otherwise>
                                <span style="cursor: no-drop"><i class="fa fa-angle-left"></i> 没有了</span>
                            </c:otherwise>
                        </c:choose>
                    </li>
                    <li class="next">
                        <c:choose>
                            <c:when test="${article_next!=null}">
                                <a href="/ArticleServlet?id=${article_next.id}">下一篇:${article_next.title} <i class="fa fa-angle-right"></i></a>
                            </c:when>
                            <c:otherwise>
                                <span style="cursor: no-drop">没有了 <i class="fa fa-angle-right"></i></span>
                            </c:otherwise>
                        </c:choose>
                    </li>
                </ul>
            </nav>
            <section id="comments">
                <h3 class="vc-comments-title">${article.title} - 有${article.comment}条评论</h3>
                <ol class="commentlist">
                    <c:if test="${comment != null}">
                        <c:forEach var="comm" varStatus="status" items="${comment}">
                            <li class="comment even thread-even depth-1 parent" id="comment-1">
                                <div id="div-comment-1" class="comment-body">
                                    <div class="comment-author vcard">
                                        <img src="https://www.gravatar.com/avatar/${comm.email_hash}?s=50&r=${rating}"
                                             class="avatar avatar-50 photo avatar-default"
                                             height="50"
                                             width="50">
                                        <cite class="fn">
                                            <c:choose>
                                                <c:when test="${comm.website!=null}">
                                                    <a href="${comm.website}" rel="external nofollow" class="url">${comm.nickname}</a>
                                                </c:when>
                                                <c:otherwise>
                                                    <span class="url">${comm.nickname}</span>
                                                </c:otherwise>
                                            </c:choose>

                                            <c:if test="${comm.admin}">
                                                <span class="vc-admin">管理员</span>
                                            </c:if>
                                        </cite>
                                        <time>${comm.time}</time>
                                    </div>

                                    <div>
                                        <pre>${comm.content}</pre>
                                    </div>

                                    <footer class="comment-footer">
                                        <c:if test="${sessionScope.user!=null}">
                                            <button class="comment-reply-link" style="color: red" onclick="deletecm(this,${comm.id})" aria-label="删除">删除</button>
                                        </c:if>
                                    </footer>
                                </div>
                            </li>
                        </c:forEach>
                    </c:if>
                </ol>
                <div id="respond" class="comment-respond">
                    <h3 id="reply-title" class="comment-reply-title">发表评论</h3>
                    <form action="/NewCommentServlet?id=${article.id}" method="post" id="commentform" class="comment-form">
                        <c:choose>
                            <c:when test="${sessionScope.user==null}">
                                <p class="comment-notes"><span id="email-notes">电子邮件地址不会被公开。</span>必填项已用<span class="required">*</span>标注</p>
                                <p class="comment-form-author">
                                    <label for="nickname">姓名 <span class="required">*</span>
                                    </label>
                                    <input id="nickname" name="nickname" type="text" value="" size="30" aria-required="true" required="required">
                                </p>
                                <p class="comment-form-email">
                                    <label for="email">电子邮件 <span class="required">*</span>
                                    </label>
                                    <input id="email" name="email" type="text" size="30" aria-describedby="email-notes" aria-required="true" required="required">
                                </p>
                                <p class="comment-form-url">
                                    <label for="website">站点</label>
                                    <input id="website" name="website" type="text" value="" size="30">
                                </p>
                            </c:when>
                            <c:otherwise>
                                <input hidden name="nickname" value="${sessionScope.user.user_name}">
                                <input hidden name="email" value="${sessionScope.user.email}">
                                <input hidden name="website" value="">
                                <input hidden name="admin_check" value="1">
                                <img src="https://www.gravatar.com/avatar/${sessionScope.user.email_hash}?s=50&r=${rating}"
                                     class="avatar avatar-50 photo avatar-default"
                                     height="50"
                                     width="50">
                                <span>用户 ${sessionScope.user.user_name} 已登录</span>
                            </c:otherwise>
                        </c:choose>
                        <p class="comment-form-comment">
                            <label for="content">评论</label>
                            <textarea id="content" name="content" rows="5" aria-describedby="form-allowed-tags" aria-required="true" required="required"></textarea>
                        </p>
                        <p class="form-submit">
                            <input name="submit" type="submit" id="submit" class="submit" value="发表评论">
                        </p>
                    </form>
                </div>
            </section>
            <!-- CODE END -->
        </div>
        <jsp:include page="part/sideinfo.jsp"/>
    </div>
</div>

<jsp:include page="part/footer.jsp"/>
<jsp:include page="part/tail.jsp"/>
<script type="text/javascript" src="./static/js/article.js"></script>
</body>

</html>
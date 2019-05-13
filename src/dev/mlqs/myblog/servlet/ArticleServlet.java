package dev.mlqs.myblog.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dev.mlqs.myblog.model.Article;
import dev.mlqs.myblog.service.ArticleService;
import dev.mlqs.myblog.service.CommentService;
import dev.mlqs.myblog.service.TagService;
import dev.mlqs.myblog.utils.ImageUtils;
import dev.mlqs.myblog.utils.SideInfoUtils;

@WebServlet("/article")
public class ArticleServlet extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        String id = request.getParameter("id");
        ArticleService as = ArticleService.getInstance();
        
        Article a = as.getArticle("id", id).get(0);
        request.setAttribute("article", a);

        
        TagService ts = TagService.getInstance();
        request.setAttribute("article_tags", ts.getTagById(id));
        
        request.setAttribute("article_pre", as.getPreviousArticle(a.getTime()));
        
        request.setAttribute("article_next", as.getNextArticle(a.getTime()));
        
        CommentService cs = CommentService.getInstance();
        List list = cs.loadComment(a.getId());
        request.setAttribute("comment", list);

        request.setAttribute("header_img", ImageUtils.getImg(id));

        SideInfoUtils.setUp(request, this);

        request.getRequestDispatcher("/article.jsp").forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        doGet(request, response);
    }

}

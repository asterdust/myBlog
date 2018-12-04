package dev.mlqs.myblog.servlet;

import dev.mlqs.myblog.model.Article;
import dev.mlqs.myblog.service.ArticleService;
import dev.mlqs.myblog.utils.ImageUtils;
import dev.mlqs.myblog.utils.SideInfoUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/IndexServlet")
public class IndexServlet extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        ArticleService as = ArticleService.getInstance();
        request.setAttribute("sort_count_map", as.getSortAndCount());
        request.setAttribute("article_list", as.getArticle());
        List<String> img_list = new ArrayList<>();
        for (Article article : as.getArticle()) {
            img_list.add(ImageUtils.getImg(article.getId()));
        }
        request.setAttribute("header_img_list", img_list);

        SideInfoUtils.setUp(request, this);
        request.getRequestDispatcher("/main.jsp").forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);
    }
}

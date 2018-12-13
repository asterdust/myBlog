package dev.mlqs.myblog.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dev.mlqs.myblog.service.ArticleService;
import dev.mlqs.myblog.service.TagService;
import dev.mlqs.myblog.utils.SideInfoUtils;

@WebServlet("/ManageServlet")
public class ManageServlet extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        if (request.getSession().getAttribute("user") == null) {
            response.sendError(403);
            return;
        }
        SideInfoUtils.setUp(request, this);

        ArticleService as;
        switch(request.getParameter("t")) {
            case "article":
                as = ArticleService.getInstance();
                request.setAttribute("articles", as.getArticle());
                request.getRequestDispatcher("/admin/mana_article.jsp").forward(request, response);
                break;
            case "sort":
                as = ArticleService.getInstance();
                request.setAttribute("sort", as.getAllSort());
                request.getRequestDispatcher("/admin/mana_sort.jsp").forward(request, response);
                break;
            case "tag":
                TagService ts = TagService.getInstance();
                request.setAttribute("tags", ts.getAllTag());
                request.getRequestDispatcher("/admin/mana_tag.jsp").forward(request, response);
                break;
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // TODO Auto-generated method stub
        doGet(request, response);
    }

}

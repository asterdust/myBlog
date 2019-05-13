package dev.mlqs.myblog.servlet;

import dev.mlqs.myblog.db.VisitorDB;
import dev.mlqs.myblog.service.ArticleService;
import dev.mlqs.myblog.service.TagService;
import dev.mlqs.myblog.utils.ConfigUtils;
import dev.mlqs.myblog.utils.SideInfoUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/admin")
public class AdminServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        if (request.getSession().getAttribute("user") == null) {
            response.sendError(403);
            return;
        }

        request.setAttribute("visited", VisitorDB.totalVisit());
        request.setAttribute("member", VisitorDB.totalMember());

        ArticleService as = ArticleService.getInstance();
        request.setAttribute("article_count", as.getArticle().size());
        request.setAttribute("sort_count", as.getAllSort().size());
        TagService ts = TagService.getInstance();
        request.setAttribute("tag_count", ts.getAllTag().size());

        request.setAttribute("des_map", ConfigUtils.getConfigUtils(this).des_map);
        SideInfoUtils.setUp(request, this);
        
        request.getRequestDispatcher("/admin/admin.jsp").forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        doGet(request, response);
    }
}

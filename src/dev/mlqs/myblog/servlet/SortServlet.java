package dev.mlqs.myblog.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dev.mlqs.myblog.service.ArticleService;
import dev.mlqs.myblog.utils.SideInfoUtils;
import dev.mlqs.myblog.utils.StringUtils;

@WebServlet("/sort")
public class SortServlet extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String get = StringUtils.pareCode(request.getParameter("get"));
        ArticleService as = ArticleService.getInstance();

        request.setAttribute("sort_article_map", as.getSortAndAirticle(get));

        SideInfoUtils.setUp(request, this);

        request.getRequestDispatcher("/sort.jsp").forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);
    }

}

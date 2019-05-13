package dev.mlqs.myblog.servlet;

import dev.mlqs.myblog.service.ArticleService;
import dev.mlqs.myblog.utils.SideInfoUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/search")
public class SearchServlet extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        SideInfoUtils.setUp(request, this);

        String s = request.getParameter("s");
        if (s == null || s.equals("")) return;

        request.setAttribute("s", s);
        ArticleService as = ArticleService.getInstance();
        request.setAttribute("article_list", as.search(s));

        request.getRequestDispatcher("/search.jsp").forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);
    }
}

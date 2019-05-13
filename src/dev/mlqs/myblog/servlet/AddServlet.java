package dev.mlqs.myblog.servlet;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dev.mlqs.myblog.service.ArticleService;
import dev.mlqs.myblog.service.TagService;
import dev.mlqs.myblog.utils.SideInfoUtils;


@WebServlet("/add")
public class AddServlet extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        if (request.getSession().getAttribute("user") == null) {
            response.sendError(403);
            return;
        }

        SideInfoUtils.setUp(request, this);
        
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = new Date();
        request.setAttribute("time", df.format(date));
        
        ArticleService as = ArticleService.getInstance();
        Map sort_count = as.getSortAndCount();
        request.setAttribute("sort_count", sort_count);
        
        TagService tg = TagService.getInstance();
        List all_tag = tg.getAllTag();
        request.setAttribute("all_tag", all_tag);

        request.getRequestDispatcher("/admin/add.jsp").forward(request, response);

    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);
    }
}

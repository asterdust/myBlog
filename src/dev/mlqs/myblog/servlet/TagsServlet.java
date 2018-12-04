package dev.mlqs.myblog.servlet;

import java.io.IOException;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dev.mlqs.myblog.service.TagService;
import dev.mlqs.myblog.utils.SideInfoUtils;
import dev.mlqs.myblog.utils.StringUtils;
import dev.mlqs.myblog.utils.XMLUtils;

@WebServlet("/TagsServlet")
public class TagsServlet extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String get = StringUtils.pareCode(request.getParameter("get"));
        TagService ts = TagService.getInstance();
        request.setAttribute("id_tag_map", ts.getTagAndArticle(get));

        SideInfoUtils.setUp(request, this);

        request.getRequestDispatcher("/tags.jsp").forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);
    }

}
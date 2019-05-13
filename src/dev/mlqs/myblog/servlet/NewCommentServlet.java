package dev.mlqs.myblog.servlet;

import java.io.IOException;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dev.mlqs.myblog.model.Comment;
import dev.mlqs.myblog.service.CommentService;
import dev.mlqs.myblog.utils.DateUtils;
import dev.mlqs.myblog.utils.FailException;
import dev.mlqs.myblog.utils.Form2Bean;


@WebServlet("/new_comment")
public class NewCommentServlet extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response) {

        String cookie_name = "comment_cookie" + request.getParameter("id");
        boolean isRpeat = false;

        Cookie[] cookies = request.getCookies();
        if (cookies != null) {
            for (Cookie c : cookies) {
                if (c.getName().equals(cookie_name)) {
                    isRpeat = true;
                    break;
                }
            }
        }

        if (!isRpeat) {
            Comment cm;
            try {
                cm = Form2Bean.commentForm2Bean(request);
                if (request.getParameter("admin_check") != null
                        && request.getParameter("admin_check").equals("1")
                        && request.getSession().getAttribute("user") != null) {
                    cm.setAdmin(true);
                }
                CommentService cs = CommentService.getInstance();
                boolean result = cs.addComment(cm);
            } catch (FailException e) {
                e.printStackTrace();
            }
        }

        response.setStatus(response.SC_MOVED_TEMPORARILY);
        response.setHeader("Location", "/article?id=" + request.getParameter("id"));

    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) {
        doGet(request, response);
    }

}

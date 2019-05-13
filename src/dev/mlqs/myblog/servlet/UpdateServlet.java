package dev.mlqs.myblog.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dev.mlqs.myblog.model.Article;
import dev.mlqs.myblog.service.AdminService;


@WebServlet("/update")
public class UpdateServlet extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        if (request.getSession().getAttribute("user") == null) {
            response.sendError(403);
            return;
        }
        AdminService as = AdminService.getInstance();
        Article result = as.updateArticle(request);

        response.setStatus(response.SC_MOVED_TEMPORARILY);
        if (result != null)
            response.setHeader("Location", "/manage?t=article&t=suc");
        else
            response.setHeader("Location", "/manage?t=article&t=fai");
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws IOException {

        doGet(request, response);
    }

}

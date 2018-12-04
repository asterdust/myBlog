package dev.mlqs.myblog.servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dev.mlqs.myblog.dao.ArticleDao;
import dev.mlqs.myblog.db.VisitorDB;
import dev.mlqs.myblog.model.Article;
import dev.mlqs.myblog.service.ArticleService;
import dev.mlqs.myblog.service.TagService;
import dev.mlqs.myblog.utils.ImageUtils;
import dev.mlqs.myblog.utils.LoginUtils;
import dev.mlqs.myblog.utils.SideInfoUtils;
import dev.mlqs.myblog.utils.XMLUtils;

/**
 * Login->index.jsp->init data
 */
@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        if (LoginUtils.login(request)) {
            request.getRequestDispatcher("/IndexServlet").forward(request, response);
        }
        else {
            request.getRequestDispatcher("/NotifServlet?ec=1").forward(request, response);
        }


    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);
    }

}
